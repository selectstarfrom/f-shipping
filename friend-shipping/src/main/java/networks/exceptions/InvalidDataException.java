package networks.exceptions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exception class to handle the case of any error occurred during parsing data
 * from CSV file.<br>
 * 
 * @author Syed Firoze K
 *
 */
public class InvalidDataException extends Exception {

	public InvalidDataException(List<String> pMessages) {
		super(pMessages.stream().map(p -> p).collect(Collectors.joining("\n")));
	}

	private static final long serialVersionUID = 6914793387436519174L;

}
