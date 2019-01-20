package Utility;

public class ConfigFileNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConfigFileNotFoundException() {
		super("Errore con il file di configurazione. Controlla la presenza del file config.properties");
	}

}
