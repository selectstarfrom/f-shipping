package networks.exceptions;

/**
 * Exception class to handle the Packet Description violates the expected
 * Format.
 * 
 * @author Syed Firoze K
 *
 */
public class InvalidAssertDataException extends Exception {

	public InvalidAssertDataException(String pMessage) {
		super(pMessage);
	}

	private static final long serialVersionUID = 6914793387436519174L;

}
