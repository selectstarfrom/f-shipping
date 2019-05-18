package networks.menu;

import java.util.Scanner;

import networks.NetworkApp;
import networks.executors.DirectoryExecutor;
import networks.executors.IFileExecutor;
import networks.executors.SingleFileExecutor;
import networks.services.DataReaderService;
import networks.services.NetworkPathResolverService;
import networks.services.PricingService;
import networks.services.UserService;
import networks.utils.Constants;

/**
 * Application Menu
 *
 * @author Syed Firoze K
 *
 */
public class ApplicationMenu {

	/**
	 * Shows the Application Menu Options to the user.<br>
	 * User has to select an {@link MenuOption} from the available list of
	 * options.<br>
	 * If the user selects any other options that is not available in the provided
	 * list, Application will be exited.
	 * 
	 * @return {@link MenuOption}
	 */
	public static MenuOption show() {

		Scanner lScanner = new Scanner(System.in);

		System.out.println("Options:");

		MenuOption.getDisplayOptions().forEach(p -> {
			System.out.println(String.format(Constants.MENUT_OPTION_TEMPLATE, p.code(), p.label()));
		});

		System.out.print("Please enter your choice:");

		try {
			int choice = lScanner.nextInt();
			System.out.print("\n\n");
			return MenuOption.get(choice);
		} catch (Exception lE) {
			System.out.print("\n\n");
			return MenuOption.INVALID;
		}
	}

	/**
	 * Based on the user selection, corresponding actions will be executed.<br>
	 * 
	 * @param pUserChoice Menu option selected by the User.
	 * @param pApp        Instance of NetworkApp
	 * @see {@link MenuOption}
	 * @throws Exception
	 */
	public static void processUserChoice(MenuOption pUserChoice, NetworkApp pApp) throws Exception {

		switch (pUserChoice) {
		case SINGLE_FILE:
			String lFilename = promptForFileOrDirectoryDetails(pUserChoice);
			getExecurtorInstance(SingleFileExecutor.class).execute(lFilename);
			System.exit(0);
			break;
		case DIRECTORY:
			String lDirectory = promptForFileOrDirectoryDetails(pUserChoice);
			getExecurtorInstance(DirectoryExecutor.class).execute(lDirectory);
			break;
		case EXIT:
			ApplicationMenu.printSelection(pUserChoice);
			System.exit(0);
			break;

		default:
			ApplicationMenu.printSelection(pUserChoice);
			System.exit(0);
		}
	}

	/**
	 * Prompt user for entering the Directory Location or Filename.
	 * 
	 * @param pUserChoice
	 * @return
	 */
	private static String promptForFileOrDirectoryDetails(MenuOption pUserChoice) {
		ApplicationMenu.printSelection(pUserChoice);
		Scanner lScanner = new Scanner(System.in);
		String lFilename = lScanner.nextLine();
		System.out.print("\n\n");
		return lFilename;
	}

	/**
	 * Prints the selected option to the console. Also, any hints associated with
	 * selected Menu Option will be printed.
	 * 
	 * @param pMenuOption
	 */
	static void printSelection(MenuOption pMenuOption) {
		System.out.println(String.format("You have selected the '%s' option.", pMenuOption.label().toUpperCase()));
		System.out.print(pMenuOption.hint());
	}

	/**
	 * Creates an Instance of IFileExecutor.
	 * 
	 * @param pType
	 * @return
	 */
	public static IFileExecutor getExecurtorInstance(Class pType) {

		DataReaderService lDataReaderService = new DataReaderService();
		NetworkPathResolverService lNetworkPathResolverService = new NetworkPathResolverService();
		UserService lUserService = new UserService();
		PricingService lPricingService = new PricingService();

		if (pType.equals(SingleFileExecutor.class)) {
			return new SingleFileExecutor(lDataReaderService, lNetworkPathResolverService, lUserService,
					lPricingService);
		} else {
			return new DirectoryExecutor(lDataReaderService, lNetworkPathResolverService, lUserService,
					lPricingService);
		}

	}

}
