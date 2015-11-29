package database.extension;

public class DatabaseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseException(){
		super("Something went wrong with the Database");
	}
}
