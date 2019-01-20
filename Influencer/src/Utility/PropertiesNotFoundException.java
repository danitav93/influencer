package Utility;

public class PropertiesNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PropertiesNotFoundException() {
		super("Errore con il file di configurazione: property assente");
		
	}

}
