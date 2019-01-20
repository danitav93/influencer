package Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import logic.Constants;
import logic.SeleniumLogic;
import views.PaginaModel;

public class MyDriver {

	private SeleniumLogic seleniumLogic;
	
	private List<PaginaModel> pagine;

	private WebDriver driver=  new ChromeDriver();

	private Queue<String> listaUrlsLocale = new LinkedList<>();

	private boolean booleanIsRunning=false;
	
	private boolean hasBeenAdvisedToStart=false;

	private Semaphore semPagine;

	private  Semaphore semUrls;

	private Queue<String> globalUrlList;

	private Semaphore semCounter;

	private AtomicInteger numberOfPagesToProcessCounter;

	private boolean ready=false;


	public MyDriver(List<PaginaModel> pagine, Semaphore sem,Semaphore semUrls,Queue<String> globalUrlList, Semaphore semCounter, AtomicInteger numberOfPagesToProcessCounter,SeleniumLogic seleniumLogic) {
		this.pagine=pagine;
		this.semPagine=sem;
		this.semUrls=semUrls;
		this.globalUrlList=globalUrlList;
		this.semCounter=semCounter;
		this.numberOfPagesToProcessCounter=numberOfPagesToProcessCounter;
		this.seleniumLogic=seleniumLogic;
	}

	public void fetchUrlFromGlobalList() {
		try {
			semUrls.acquire();
			if (globalUrlList.isEmpty()) {
				semUrls.release();
				driver.close();
				return;
			}
			listaUrlsLocale.add(globalUrlList.remove());
			if (!booleanIsRunning) {
				Thread thread = new Thread(){
					public void run(){
						processPagina();
					}
				};
				thread.start();
			}
			semUrls.release();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}



	private void processPagina() {

		if (listaUrlsLocale.isEmpty()) {
			booleanIsRunning=false;
			return;

		}

		booleanIsRunning=true;

		String urlPagina=listaUrlsLocale.remove();

		driver.get(urlPagina);

		try {
			for (int i=0;i<=Constants.NUMBER_OF_POSTS_SCROLL;i++) {
				try {
					if (driver.findElement(By.className("_3ixn"))!=null) {
						driver.findElement(By.className("_3ixn")).click();
					}
				} catch (Exception e ) {
				}
				Thread.sleep(1000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0,10000);");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		PaginaModel pagina=new PaginaModel();

		try {
			semPagine.acquire();
			pagine.add(pagina);
			semPagine.release();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			return;
		}

		try {

			String miPiaceText=driver.findElement(By.cssSelector("div[class='_4-u2 _6590 _3xaf _4-u8']")).findElements(By.cssSelector("div[class='_4bl9']")).get(0).findElement(By.tagName("div")).getText();

			int miPiace=Method.parseInt(miPiaceText.substring(8, 8+miPiaceText.substring(8).indexOf(" ")));

			pagina.setMiPiacePagina(miPiace);

			String followersText=driver.findElement(By.cssSelector("div[class='_4-u2 _6590 _3xaf _4-u8']")).findElements(By.cssSelector("div[class='_4bl9']")).get(1).findElement(By.tagName("div")).getText();

			int followers=Method.parseInt(followersText.substring(10));

			pagina.setFollowersPagina(followers);

			pagina.setUrl(urlPagina);

			String nomePagina=driver.findElement(By.cssSelector("a[class='_64-f']")).findElement(By.tagName("span")).getText();

			pagina.setNomePagina(nomePagina);

			int numeroPostUltimoPeriodo=0;

			int condivisioniPostUltimoPeriodo=0;

			int numeroLikePostUltimoPeriodo=0;

			int commentiPostUltimoPeriodo=0;

			int visualizzazioniPostUltimoPeriodo=0;

			int numeroPostVisualizzazioniUltimoPeriodo=0;

			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			calendar.add(Calendar.DAY_OF_MONTH, - Constants.DAYS_CHECK);		

			List<WebElement> postsElementUp= driver.findElements(By.cssSelector("div[class='_1dwg _1w_m _q7o']"));
			List<WebElement> postsElementDown= driver.findElements(By.cssSelector("div[class='_57w']"));



			for (int i=0;i<postsElementUp.size();i++) {
				WebElement elementUp=postsElementUp.get(i);
				WebElement elementDown=postsElementDown.get(i);

				String data= elementUp.findElement(By.cssSelector("span[class='timestampContent']")).getText();
				Calendar calendarFound=null;
				if (data.length()<5 && data.contains("h")) {
					calendarFound= Calendar.getInstance(TimeZone.getDefault());
				}  else {
					Date dateDected=null;
					try {
						String[] dataSplitted= data.split(" ");
						int year=0;
						try {
							year=Integer.parseInt(dataSplitted[2]);
						} catch (Exception e) {
							year=Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR);
						}
						String dataJoint=dataSplitted[0]+" "+dataSplitted[1]+" "+year;

						dateDected = new SimpleDateFormat("dd MMMM yyyy").parse(dataJoint);
						calendarFound=Calendar.getInstance();
						calendarFound.setTime(dateDected);
					} catch (ParseException e) {
						calendarFound=Calendar.getInstance(TimeZone.getDefault());
					}
				}
				if (calendarFound.after(calendar)) {

					numeroPostUltimoPeriodo++;


					int likePost= Method.parseInt(((elementDown.findElement(By.cssSelector("span[class='_4arz']")).findElement(By.tagName("span")).getText())));
					numeroLikePostUltimoPeriodo=numeroLikePostUltimoPeriodo+likePost;

					List<WebElement> rankScoresELements= elementDown.findElements(By.cssSelector("div[class='_36_q']"));

					if (rankScoresELements.size()>0) {
						int commentiPost=Method.parseRankPost(rankScoresELements.get(0).findElement(By.tagName("a")).getText());
						commentiPostUltimoPeriodo=commentiPostUltimoPeriodo+commentiPost;
					}
					if (rankScoresELements.size()>1) {
						int condivisioniPost=Method.parseRankPost(rankScoresELements.get(1).findElement(By.tagName("a")).getText());
						condivisioniPostUltimoPeriodo=condivisioniPostUltimoPeriodo+condivisioniPost;
					}
					if (rankScoresELements.size()>2) {
						int visualizzazioniPost=Method.parseRankPost(rankScoresELements.get(2).findElement(By.tagName("span")).getText());
						visualizzazioniPostUltimoPeriodo=visualizzazioniPostUltimoPeriodo+visualizzazioniPost;
						numeroPostVisualizzazioniUltimoPeriodo++;
					}

				}

			}
		

		pagina.setNumeroPostUltimoPeriodo(numeroPostUltimoPeriodo);

		pagina.setNumeroLikePostUltimoPeriodo(numeroLikePostUltimoPeriodo);

		pagina.setCondivisioniPostUltimoPeriodo(condivisioniPostUltimoPeriodo);

		pagina.setVisualizzazioniPostUltimoPeriodo(visualizzazioniPostUltimoPeriodo);

		pagina.setCommentiPostUltimoPeriodo(commentiPostUltimoPeriodo);

		if (Constants.DAYS_CHECK>0) {
			pagina.setMediaPostGiornaliera((float)numeroPostUltimoPeriodo/Constants.DAYS_CHECK);
		} else {
			pagina.setMediaPostGiornaliera((float)numeroPostUltimoPeriodo/1);
		}

		if (numeroPostUltimoPeriodo>0) {
			pagina.setMediaCommenti((float)commentiPostUltimoPeriodo/numeroPostUltimoPeriodo);
		}

		if (numeroPostUltimoPeriodo>0) {
			pagina.setMediaCondivisioni((float)condivisioniPostUltimoPeriodo/numeroPostUltimoPeriodo);
		}

		if  (numeroPostUltimoPeriodo>0) {
			pagina.setMediaLike((float)numeroLikePostUltimoPeriodo/numeroPostUltimoPeriodo);
		}

		if (numeroPostVisualizzazioniUltimoPeriodo>0) {
			pagina.setMediaVisualizzazioni((float)visualizzazioniPostUltimoPeriodo/numeroPostVisualizzazioniUltimoPeriodo);
		}

		pagina.setFinalScore(pagina.getMiPiacePagina()*Constants.miPiacePaginaScore+pagina.getFollowersPagina()*Constants.followersPaginaScore+pagina.getMediaCommenti()*Constants.mediaCommentiScore+pagina.getMediaCondivisioni()*Constants.mediaCondivisioniScore+pagina.getMediaLike()*Constants.mediaLikeScore+pagina.getMediaPostGiornaliera()*Constants.mediaPostGiornalieraScore+pagina.getMediaVisualizzazioni()*Constants.mediaVisualizzazioniScore);

		} catch (Exception e) {
		}
		try {
			semCounter.acquire();
			numberOfPagesToProcessCounter.set(numberOfPagesToProcessCounter.get()-1);
			if (numberOfPagesToProcessCounter.get()==0) {
				seleniumLogic.endDriverProcessing();
			}
			semCounter.release();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			return;
		}

		fetchUrlFromGlobalList();

		processPagina();

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void setReady(boolean b) {
		this.ready=b;
	}

	public boolean isReady() {
		return ready;
	}

	public boolean isHasBeenAdvisedToStart() {
		return hasBeenAdvisedToStart;
	}

	public void setHasBeenAdvisedToStart(boolean hasBeenAdvisedToStart) {
		this.hasBeenAdvisedToStart = hasBeenAdvisedToStart;
	}
	
	

}
