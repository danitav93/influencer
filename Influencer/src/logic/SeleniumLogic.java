package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JDialog;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import Utility.ConfigFileNotFoundException;
import Utility.LoginFaildException;
import Utility.MyDriver;
import Utility.MyProgressBar;
import Utility.NessunaPaginaTrovataException;
import Utility.PropertiesNotFoundException;
import Utility.PropertiesService;
import views.ListOfPaginaJFrame;
import views.PaginaModel;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class SeleniumLogic {


	private String queryString;

	long startTime = System.currentTimeMillis();


	private  Semaphore semPagine= new Semaphore(1);
	private  Semaphore semUrls= new Semaphore(1);
	private  Semaphore semCounter= new Semaphore(1);
	private Semaphore semProgBar= new Semaphore(1);
	private Semaphore semDriver= new Semaphore(1);
	
	private MyProgressBar myProgressBar;

	private SeleniumLogic seleniumLogic=this;


	private  List<PaginaModel> pagine= new ArrayList<>();

	private  Queue<String> urls= new LinkedList<>();


	private  List<MyDriver> drivers= new ArrayList<>();

	private  AtomicInteger numberOfPagesToProcessCounter=new AtomicInteger(0);

	private  boolean isProcessingPageStarted=false;

	private  JDialog dlg;
	
	private JButton btnEseguiRicerca;

	public SeleniumLogic(String query, JDialog dlg,JButton btnEseguiRicerca) {
		this.queryString=query;
		this.dlg=dlg;
		this.btnEseguiRicerca=btnEseguiRicerca;
	}

	public void startSeleniumLogic() throws PropertiesNotFoundException,ConfigFileNotFoundException, NessunaPaginaTrovataException, LoginFaildException {

		System.setProperty("webdriver.chrome.driver", PropertiesService.getStringProperty("driverPath"));
		
		//inizializzo il primo driver così vado veloce
		initFirstDriver();

		//initialize mydrivers, they have to stay legged into the profile
		for (int i=1;i<=PropertiesService.getIntProperty("numberOfDrivers")-1; i++) {

			final int  j=i;
			Thread thread = new Thread(){
				public void run() {
					try  {
						
						
						
						MyDriver driver= new MyDriver(pagine,semPagine,semUrls,urls,semCounter,numberOfPagesToProcessCounter,seleniumLogic,j);

						semDriver.acquire();
						if (drivers==null) {
							semDriver.release();
							driver.getDriver().close();
							this.destroy();//lo so che è deprecato ma sono sicuro di aver rilasciato tutte le risorse
						}
						drivers.add(driver);
						semDriver.release();
						
						// launch Fire fox and direct it to the Base URL
						driver.getDriver().get(Constants.FACEBOOK_BASE_URL);
						driver.getDriver().findElement(By.xpath(".//*[@data-testid='royal_email']")).sendKeys(PropertiesService.getStringProperty("facebookEmail"));
						driver.getDriver().findElement(By.xpath(".//*[@data-testid='royal_pass']")).sendKeys(PropertiesService.getStringProperty("facebookPassword"));
						driver.getDriver().findElement(By.xpath(".//*[@data-testid='royal_login_button']")).click();//login
						try {
							driver.getDriver().findElement(By.className("_3ixn")).click();//remove black shadowed window
						} catch (Exception e) {

						}
						driver.setReady(true);
						if (isProcessingPageStarted && !driver.isHasBeenAdvisedToStart() ) {
							driver.fetchUrlFromGlobalList();
							driver.setHasBeenAdvisedToStart(true);
						}
						

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			thread.start();

		}

		//wait to load page
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//use driver0 to fetch pages urls
		try {
		drivers.get(0).getDriver().findElement(By.xpath(".//*[@data-testid='search_input']")).sendKeys(queryString); //insert search
		} catch (NoSuchElementException e) {
			closeDrivers();
			dlg.setVisible(false);
			throw new LoginFaildException();
		}
		drivers.get(0).getDriver().findElement(By.xpath(".//*[@data-testid='facebar_search_button']")).click();

		boolean arrivedToPages=false;

		while (!arrivedToPages) {
			//go to the "page" window
			try {
				List<WebElement> elements=drivers.get(0).getDriver().findElements(By.tagName("li"));

				for (WebElement element: elements) {
					if (element.getAttribute("data-edge")!=null && element.getAttribute("data-edge").equals("keywords_pages")) {
						element.click();
						arrivedToPages=true;
						break;
					}
				}
				if (!arrivedToPages) {
				dlg.setVisible(false);
				closeDrivers();
				throw new NessunaPaginaTrovataException();
				}
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex) { //repeat the action in case of error

			} 
		}

		//scroll down the page to obtain more pages
		try {
			for (int i=0;i<=PropertiesService.getIntProperty("numberOfPagesScroll");i++) {
				Thread.sleep(1000);
				JavascriptExecutor js = (JavascriptExecutor) drivers.get(0).getDriver();
				js.executeScript("window.scrollTo(0,10000);");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//find pages elements
		List<WebElement> listaPagine= drivers.get(0).getDriver().findElements(By.cssSelector("span[class='_5bl2']"));

		for (WebElement elem: listaPagine) {
			String url= elem.findElement(By.tagName("a")).getAttribute("href");
			urls.add(url);
		}

		numberOfPagesToProcessCounter.set(urls.size());

		//set the progress bar
		//Where member variables are declared:
		//Where the GUI is constructed:
		
		myProgressBar = new MyProgressBar(numberOfPagesToProcessCounter.get());
		
		myProgressBar.update(numberOfPagesToProcessCounter.get()+" pagine da processare.",null);
		
		myProgressBar.setVisible(true);
		
		dlg.setVisible(false);
		
		
		startDriverProcessing();


	}





	public void closeDrivers() {
		try {
			semDriver.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (MyDriver driver: drivers) {
			if (driver!=null) {
				driver.getDriver().close();
			}
		}
		drivers=null;
		semDriver.release();

	}

	private  void initFirstDriver() throws PropertiesNotFoundException, ConfigFileNotFoundException {

		MyDriver driver= new MyDriver(pagine,semPagine,semUrls,urls,semCounter,numberOfPagesToProcessCounter,seleniumLogic,0);

		drivers.add(driver);

		// launch Fire fox and direct it to the Base URL
		driver.getDriver().get(Constants.FACEBOOK_BASE_URL);
		driver.getDriver().findElement(By.xpath(".//*[@data-testid='royal_email']")).sendKeys(PropertiesService.getStringProperty("facebookEmail")); 
		driver.getDriver().findElement(By.xpath(".//*[@data-testid='royal_pass']")).sendKeys(PropertiesService.getStringProperty("facebookPassword"));
		driver.getDriver().findElement(By.xpath(".//*[@data-testid='royal_login_button']")).click();//login
		try {
			driver.getDriver().findElement(By.className("_3ixn")).click();//remove black shadowed window
		} catch (Exception e) {

		}

		driver.setReady(true);

	}


	private  void startDriverProcessing() {
		for (MyDriver driver: drivers) {
			if (driver.isReady()) {
				driver.fetchUrlFromGlobalList();
				driver.setHasBeenAdvisedToStart(true);
			}
		}
		isProcessingPageStarted=true;
	}

	public  void endDriverProcessing() {

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		

		myProgressBar.update("Processing delle pagine completato in "+elapsedTime/1000+"s.",null);
		Collections.sort(pagine,Collections.reverseOrder());
		for (PaginaModel pagina: pagine) {
			System.out.println(pagina.getNomePagina()+"   Score: "+pagina.getFinalScore() );
		}
		System.out.println(elapsedTime/1000+"s");
		ListOfPaginaJFrame dialog = new ListOfPaginaJFrame(pagine,myProgressBar.getTempoDiEsecuzione()); 
		dialog.setVisible(true);
		btnEseguiRicerca.setEnabled(true);
		myProgressBar.setVisible(false);


	}

	public MyProgressBar getProgressBar() {
		return myProgressBar;
	}

	

	public Semaphore getSemProgBar() {
		return semProgBar;
	}

	




}
