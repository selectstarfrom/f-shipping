package networks.exceptions;

/**
 * @author Syed Firoze K
 *
 */
public class FriendShippingException extends Exception {

	public FriendShippingException(String pMessage) {
		super(String.format("\n\nOops...Something went wrong!!!\n%s\nPlease try again.\nExiting Application...",
				pMessage));
	}

	private static final long serialVersionUID = 6914793387436519174L;

}
