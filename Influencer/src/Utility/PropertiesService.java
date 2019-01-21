package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
	
	
}
