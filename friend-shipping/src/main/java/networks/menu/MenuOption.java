package networks.menu;

import java.util.Arrays;
import java.util.List;

/**
 * All Available Menu Options
 * 
 * @author Syed Firoze K
 *
 */
public enum MenuOption {

//	@formatter:off
	/**
	 * Option to process a single CSV input file.<br>
	 * Once this option is selected, user will be promoted for fully qualified filename(Absolute Path).
	 */
	SINGLE_FILE(1, "Solve single input csv file in directory", "Please enter the input csv file path:"),
	/**
	 * Option to process all CSV input file within a directory.<br>
	 * Once this option is selected, user will be promoted for fully qualified directory(Absolute Path).
	 */
	DIRECTORY(2,"Solve all input csv file in directory", "Please enter the full path to the directory of csv files:"),
	/**
	 * Application will Exit
	 */
	EXIT(3, "Exit", "You will be exited from the Application now.\nThank you for using the applicaiton...."),
	/**
	 * Any other options will be mapped to this Enum and Application will be exited.
	 */
	INVALID(4, "Invalid", "You will be exited from the Application now.\nThank you for using the applicaiton....");
//	@formatter:on

	/**
	 * Code
	 */
	private int code;
	/**
	 * Display value of the MenuOption.
	 */
	private String label;
	/**
	 * Any hints or info associated with the Option.
	 */
	private String hint;

	private MenuOption(int pCode, String pLabel, String pHint) {
		code = pCode;
		label = pLabel;
		hint = pHint;
	}

	/**
	 * @return code
	 */
	int code() {
		return code;
	}

	/**
	 * @return Display value of the MenuOption.
	 */
	String label() {
		return label;
	}

	/**
	 * @return Display any hints associated with the MenuOption.
	 */
	String hint() {
		return hint;
	}

	/**
	 * @param pCode
	 * @return MenuOption instance corresponding to provided code.
	 */
	public static MenuOption get(int pCode) {
		switch (pCode) {
		case 1:
			return MenuOption.SINGLE_FILE;
		case 2:
			return MenuOption.DIRECTORY;
		case 3:
			return MenuOption.EXIT;
		default:
			return INVALID;
		}
	}

	static List<MenuOption> getDisplayOptions() {
		return Arrays.asList(MenuOption.SINGLE_FILE, MenuOption.DIRECTORY, MenuOption.EXIT);
	}

}