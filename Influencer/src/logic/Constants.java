package logic;


public class Constants {
	
	
	//urls
	public static final String FACEBOOK_BASE_URL="https://www.facebook.com/";
	
	
	//application
	public static final int MAX_DRIVER_PROCESSING_PAGE_NUMBER = 10;
	public static final String PROPERTIES_FILE_PATH = System.getProperty("user.home")+"/FindYourInfluencer/config.properties";
	public static final String JASPER_PAGES_FILE_PATH = System.getProperty("user.home")+"/FindYourInfluencer/Leaf_Red_Tabulare.jasper";
	public static final String JRXML_PAGES_FILE_PATH = System.getProperty("user.home")+"/FindYourInfluencer/Leaf_Red_Tabulare.jrxml";

	//default parameters
	public static final String driverPath=System.getProperty("user.home")+ "\\Desktop"+"\\chromedriver.exe";
	public static final int	numberOfDrivers=3;
	public static final String	facebookEmail="";
	public static final	String	facebookPassword="Guit.1993";
	public static final	int numberOfPagesScroll=3;
	public static final	int numberOfPostScroll=3;
	public static final	int daysCheck=10;
	public static final	int miPiacePaginaScore=1;
	public static final	int followersPaginaScore=1;
	public static final	int mediaCommentiScore=1;
	public static final	int mediaCondivisioniScore=1;
	public static final	int mediaLikeScore=1;
	public static final	int mediaPostGiornalieriScore=1;
	public static final	int mediaVisualizzazioniScore=1;
	public static final	int ordineFacebookScore=1;
	
	//versione
	public static final int versione=2;


}
