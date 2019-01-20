package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JDialog;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import Utility.ConfigFileNotFoundException;
import Utility.MyDriver;
import Utility.PropertiesNotFoundException;
import Utility.PropertiesService;
import views.ListOfPaginaJDialog;
import views.PaginaModel;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class SeleniumLogic {


	private String queryString;

	long startTime = System.currentTimeMillis();


	private  Semaphore semPagine= new Semaphore(1);
	private  Semaphore semUrls= new Semaphore(1);
	private  Semaphore semCounter= new Semaphore(1);

	private SeleniumLogic seleniumLogic=this;


	private  List<PaginaModel> pagine= new ArrayList<>();

	private  Queue<String> urls= new LinkedList<>();


	private  List<MyDriver> drivers= new ArrayList<>();

	private  AtomicInteger numberOfPagesToProcessCounter=new AtomicInteger(0);

	private  boolean isProcessingPageStarted=false;
	
	private  JDialog dlg;

	public SeleniumLogic(String query, JDialog dlg) {
		this.queryString=query;
		this.dlg=dlg;
	}

	public void startSeleniumLogic() throws PropertiesNotFoundException,ConfigFileNotFoundException {


		//used by Seleniuk
		System.setProperty("webdriver.chrome.driver", PropertiesService.getStringProperty("driverPath"));

		//inizializzo il primo driver cos� vado veloce
		initFirstDriver();

		//initialize mydrivers, they have to stay legged into the profile
		for (int i=1;i<=PropertiesService.getIntProperty("numberOfDrivers")-1; i++) {

			Thread thread = new Thread(){
				public void run() {
					try  {
						MyDriver driver= new MyDriver(pagine,semPagine,semUrls,urls,semCounter,numberOfPagesToProcessCounter,seleniumLogic);

						drivers.add(driver);

						driver.getDriver().manage().window().maximize();

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
		drivers.get(0).getDriver().findElement(By.xpath(".//*[@data-testid='search_input']")).sendKeys(queryString); //insert search
		drivers.get(0).getDriver().findElement(By.xpath(".//*[@data-testid='facebar_search_button']")).click();

		//go to the "page" window
		try {
			List<WebElement> elements=drivers.get(0).getDriver().findElements(By.tagName("li"));
			for (WebElement element: elements) {
				if (element.getAttribute("data-edge")!=null && element.getAttribute("data-edge").equals("keywords_pages")) {
					element.click();
					break;
				}
			}
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex) { //repeat the action in case of error
			List<WebElement> elements=drivers.get(0).getDriver().findElements(By.tagName("li"));

			for (WebElement element: elements) {
				if (element.getAttribute("data-edge")!=null && element.getAttribute("data-edge").equals("keywords_pages")) {
					element.click();
					break;
				}
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


		startDriverProcessing();


	}





	private  void initFirstDriver() throws PropertiesNotFoundException, ConfigFileNotFoundException {

		MyDriver driver= new MyDriver(pagine,semPagine,semUrls,urls,semCounter,numberOfPagesToProcessCounter,seleniumLogic);

		drivers.add(driver);

		driver.getDriver().manage().window().maximize();

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
		
		Collections.sort(pagine,Collections.reverseOrder());
		for (PaginaModel pagina: pagine) {
			System.out.println(pagina.getNomePagina()+"   Score: "+pagina.getFinalScore() );
		}
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime/1000+"s");
		ListOfPaginaJDialog dialog = new ListOfPaginaJDialog(pagine); 
		dialog.setVisible(true);
		dlg.setVisible(false);
		
	}




}
