package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import logic.Constants;

public class PropertiesService {
	
	private static Properties props;
	private static InputStream is;
	private static File f;
    private static OutputStream out ;

	
	public static void init() throws PropertiesNotFoundException, ConfigFileNotFoundException {
		
		try {
			props= new Properties();
			is = null;
			f = new File(Constants.PROPERTIES_FILE_PATH);
			is = new FileInputStream( f );
			props.load( is );
		} catch (Exception e) {
			throw new ConfigFileNotFoundException();
		}
	}

	public static String getStringProperty(String key) throws PropertiesNotFoundException, ConfigFileNotFoundException {
		try {
		String toReturn=props.getProperty(key);
		
		if (toReturn==null) {
			throw new PropertiesNotFoundException();
		}
		 return toReturn;
		} catch (Exception e) {
			throw new ConfigFileNotFoundException();
		}
	}
	
	public static int getIntProperty(String key) throws PropertiesNotFoundException, ConfigFileNotFoundException {
		try {
			String toReturn=props.getProperty(key);
			
			if (toReturn==null) {
				throw new PropertiesNotFoundException();
			}
			 return Integer.parseInt(toReturn);
			} catch (Exception e) {
				throw new ConfigFileNotFoundException();
			}
	}
	
	public static float getFloatProperty(String key) throws PropertiesNotFoundException, ConfigFileNotFoundException {
		try {
			String toReturn=props.getProperty(key);
			
			if (toReturn==null) {
				throw new PropertiesNotFoundException();
			}
			 return Float.parseFloat(toReturn);
			} catch (Exception e) {
				throw new ConfigFileNotFoundException();
			}
	}
	
	
	public static void saveParamChanges (String key, String value) throws PropertiesNotFoundException, ConfigFileNotFoundException {
		
		try {
			out = new FileOutputStream( f );
	        props.setProperty(key, value);
	        props.store(out,null);
	    }
	    catch (Exception e ) {
	        e.printStackTrace();
	        throw new ConfigFileNotFoundException();
	    }
	}

	public static void saveParamsChanges(String[] keys, String[] values) throws ConfigFileNotFoundException {
		try {
			out = new FileOutputStream( f );
			for (int i=0;i<keys.length;i++) {
				props.setProperty(keys[i], values[i]);
			}
	        
	        props.store(out,null);
	    }
	    catch (Exception e ) {
	        e.printStackTrace();
	        throw new ConfigFileNotFoundException();
	    }
		
	}

	public static void createPropertiesFile(String path) throws IOException {
		
		File file= new File(path);
		
		file.createNewFile();
		
		OutputStream newout = new FileOutputStream( file );
		
		Properties newProps= new Properties();
		
		newProps.setProperty("driverPath", Constants.driverPath);
		newProps.setProperty("facebookPassword", "");
		newProps.setProperty("numberOfDrivers",Integer.toString( Constants.numberOfDrivers));
		newProps.setProperty("mediaLikeScore", Float.toString(Constants.mediaLikeScore));
		newProps.setProperty("mediaCommentiScore", Float.toString(Constants.mediaCommentiScore));
		newProps.setProperty("facebookEmail", "");
		newProps.setProperty("miPiacePaginaScore", Float.toString(Constants.miPiacePaginaScore));
		newProps.setProperty("mediaPostGiornalieraScore", Float.toString(Constants.mediaPostGiornalieriScore));
		newProps.setProperty("numberOfPostScroll", Integer.toString( Constants.numberOfPostScroll));
		newProps.setProperty("followersPaginaScore",Float.toString(Constants.followersPaginaScore));
		newProps.setProperty("daysCheck", Integer.toString( Constants.daysCheck));
		newProps.setProperty("mediaCondivisioniScore", Float.toString(Constants.mediaCondivisioniScore));
		newProps.setProperty("numberOfPagesScroll", Integer.toString( Constants.numberOfPagesScroll));
		newProps.setProperty("mediaVisualizzazioniScore", Float.toString(Constants.mediaCommentiScore));
		newProps.setProperty("ordineFacebookScore", Float.toString(Constants.ordineFacebookScore));
		newProps.store(newout,null);
		
	}
	
	
}
