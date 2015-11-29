package classmapper;

public class MapUnmatchedException extends RuntimeException{

	/**
	 * Exception raised when keys and values length do on fit
	 */
	private static final long serialVersionUID = 4915l;
	
	public MapUnmatchedException(){
		super("The Arrays length is not equal");
	}
}
