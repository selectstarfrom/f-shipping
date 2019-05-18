package networks.exceptions;

/**
 * Exception class to handle the case of any unexpected data found in the Input
 * CSV file.<br>
 * Gives the Row N.o and Column No. at which the error occurred.
 * 
 * @author Syed Firoze K
 *
 */
public class InvalidCellDataException extends RuntimeException {

	private static final long serialVersionUID = 6914793387436519174L;

	private final static String MSG_TEMPLATE = "Invalid value at row-%s, col-%s";

	public InvalidCellDataException(int pRowNo, int pColNo) {
		super(String.format(MSG_TEMPLATE, pRowNo, pColNo));
	}

}
