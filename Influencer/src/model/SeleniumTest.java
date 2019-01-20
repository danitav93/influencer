package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import views.PaginaModel;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class SeleniumTest {


	public static void main(String[] args) {
		// declaration and instantiation of objects/variables
		String exePath = "C:\\Users\\User\\Downloads\\drivers\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		//comment the above 2 lines and uncomment below 2 lines to use Chrome
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();

		String baseUrl = "https://www.facebook.com/";


		// launch Fire fox and direct it to the Base URL
		driver.get(baseUrl);

		driver.findElement(By.xpath(".//*[@data-testid='royal_email']")).sendKeys("");
		driver.findElement(By.xpath(".//*[@data-testid='royal_pass']")).sendKeys("");

		driver.findElement(By.xpath(".//*[@data-testid='royal_login_button']")).click();

		driver.findElement(By.className("_3ixn")).click();


		driver.findElement(By.xpath(".//*[@data-testid='search_input']")).sendKeys("Guidonia");

		driver.findElement(By.xpath(".//*[@data-testid='facebar_search_button']")).click();


		try {
			List<WebElement> elements=driver.findElements(By.tagName("li"));

			for (WebElement element: elements) {
				if (element.getAttribute("data-edge")!=null && element.getAttribute("data-edge").equals("keywords_pages")) {
					element.click();
					break;
				}
			}
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			List<WebElement> elements=driver.findElements(By.tagName("li"));

			for (WebElement element: elements) {
				if (element.getAttribute("data-edge")!=null && element.getAttribute("data-edge").equals("keywords_pages")) {
					element.click();
					break;
				}
			}
		}

		try {
			for (int i=0;i<=15;i++) {
				Thread.sleep(1000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0,10000);");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		List<WebElement> listaPagine= driver.findElements(By.tagName("span"));
		List<String> listaPagineUrls= new ArrayList<>();

		for (WebElement elem: listaPagine) {
		
			
			
			if (elem.getAttribute("class").equals("_5bl2")) {
				String url= elem.findElement(By.tagName("a")).getAttribute("href");
			
				listaPagineUrls.add(url);
			}
		}
		
		for (String url: listaPagineUrls) {
			
			driver.get(url);
			
			try {
				for (int i=0;i<=6;i++) {
					Thread.sleep(1000);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0,10000);");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PaginaModel pagina=new PaginaModel();
			
			String miPiaceText=driver.findElement(By.cssSelector("div[class='_4-u2 _6590 _3xaf _4-u8']")).findElements(By.cssSelector("div[class='_4bl9']")).get(0).findElement(By.tagName("div")).getText();
			
			int miPiace=Integer.parseInt(miPiaceText.substring(8, 8+miPiaceText.substring(8).indexOf(" ")));
			
			pagina.setMiPiacePagina(miPiace);

			String followersText=driver.findElement(By.cssSelector("div[class='_4-u2 _6590 _3xaf _4-u8']")).findElements(By.cssSelector("div[class='_4bl9']")).get(1).findElement(By.tagName("div")).getText();
			
			int followers=Integer.parseInt(followersText.substring(10));
			
			pagina.setMiPiacePagina(followers);
			
			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			
			Date date = calendar.getTime();
			
			int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
			
			List<WebElement> postsElement= driver.findElements(By.cssSelector("div[class='_1dwg _1w_m _q7o']"));
			
			for (WebElement element: postsElement) {
				String data= element.findElement(By.cssSelector("span[class='timestampContent']")).getText();
				
			}
			
		
		
		}

		
		
		
		
		//close Fire fox
		//driver.close();

	}

}
