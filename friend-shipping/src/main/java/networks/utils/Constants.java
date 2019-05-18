package networks.utils;

/**
 * Application specific Constants.
 * 
 * @author Syed Firoze K
 *
 */
public interface Constants {

	/**
	 * Extension of input csv filename.
	 */
	static final String INPUT_FILE_EXTENSION = ".csv";
	/**
	 * Delimiter used in a CellData, to separate User Id and Hardness
	 */
	static final String NAME_HARDNESS_DELIMITTER = ":";
	/**
	 * Character used to mark the CSV row represent an Assertion data.
	 */
	static final String ASSERT_ROW_INDICATOR = "@";
	/**
	 * Used for parsing dimension(length, width, height and weight) from Packet
	 * description.
	 */
	static final String DIMENSION_DELIMIITER = "x";
	/**
	 * Delimiter used in the CSV file to separate each cell.
	 */
	static final String INPUT_FILE_DELIMITER = ",";

	/**
	 * Column number of the Destination User Id in the assertion row.
	 */
	static final int COL_DESTINATION_ID = 1;
	/**
	 * Column number of the Packet Description the assertion row.
	 */
	static final int COL_PACK_DESC = 2;
	/**
	 * Column number of the Expected Output in the assertion row.
	 */
	static final int COL_EXPECTED_COST = 3;
	static final int PROG_ARGS_COUNT = 2;
	static final String PROG_ARG_FILE = "F";
	static final String PROG_ARG_DIRECTORY = "D";
	static final String MENUT_OPTION_TEMPLATE = "\t%s: %s";
}
