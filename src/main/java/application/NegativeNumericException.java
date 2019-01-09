package application;

public class NegativeNumericException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeNumericException() {
		System.out.println("INPUT CANNOT BE NEGATIVE");
	}
}
