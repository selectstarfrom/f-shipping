package networks;

import networks.exceptions.InvalidAssertDataException;
import networks.exceptions.InvalidDataException;
import networks.executors.DirectoryExecutor;
import networks.executors.SingleFileExecutor;
import networks.menu.ApplicationMenu;
import networks.menu.MenuOption;
import networks.utils.ApplicationProperties;
import networks.utils.BannerUtil;
import networks.utils.Constants;

/**
 * NetworkApp: The entrypoint of the Application.
 * 
 * @author Syed Firoze K
 *
 */
public class NetworkApp {

	public static void main(String[] pArgs) {
		try {
			if (Constants.PROG_ARGS_COUNT == pArgs.length) {
				String lOption = pArgs[0];
				String lPath = pArgs[1];
				if (Constants.PROG_ARG_FILE.equalsIgnoreCase(lOption)) {
					ApplicationMenu.getExecurtorInstance(SingleFileExecutor.class).execute(lPath);
				} else if (Constants.PROG_ARG_DIRECTORY.equalsIgnoreCase(lOption)) {
					ApplicationMenu.getExecurtorInstance(DirectoryExecutor.class).execute(lPath);
				}
			} else if (0 == pArgs.length) {
				NetworkApp lApp = new NetworkApp();
				BannerUtil.printBanner();

				MenuOption lUserChoice = ApplicationMenu.show();

				ApplicationMenu.processUserChoice(lUserChoice, lApp);
			}
		} catch (InvalidAssertDataException | InvalidDataException pException) {
			exit(pException);
		} catch (Exception lE) {
			exit(lE);
		}

	}

	private static void exit(Exception pException) {
		System.err.println("\n\t\t" + pException.getMessage());
		System.err.println(ApplicationProperties.getExitMessage());
		System.exit(0);
	}

}
