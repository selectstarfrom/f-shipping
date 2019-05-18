package networks.executors;

import java.util.List;

import networks.services.DataReaderService;
import networks.services.NetworkPathResolverService;
import networks.services.PricingService;
import networks.services.UserService;
import networks.utils.FileUtil;

/**
 * NetworkApp: The entrypoint of the Application.
 * 
 * @author Syed Firoze K
 *
 */
public class DirectoryExecutor implements IFileExecutor {

	private DataReaderService dataReaderService;
	private NetworkPathResolverService networkPathResolverService;
	private UserService userService;
	private PricingService pricingService;

	private SingleFileExecutor fileExecutor;

	public DirectoryExecutor(DataReaderService pDataReaderService,
			NetworkPathResolverService pNetworkPathResolverService, UserService pUserService,
			PricingService pPricingService) {
		super();
		dataReaderService = pDataReaderService;
		networkPathResolverService = pNetworkPathResolverService;
		userService = pUserService;
		pricingService = pPricingService;
		fileExecutor = new SingleFileExecutor(dataReaderService, networkPathResolverService, userService,
				pricingService);
	}

	/**
	 * Calling this method will initiate the processing of Single Input CSV file.
	 * 
	 * @param pFileName
	 *            Full qualified filename of the input csv.
	 * @return
	 * @throws Exception
	 */
	public boolean execute(String pDirectoryPath) throws Exception {

		System.out.println(String.format("\n\n\tLocation Entered:%s", pDirectoryPath));
		List<String> lCSVFileNames = FileUtil.getCSVFilesInDirectory(pDirectoryPath);
		System.out.println(String.format("\tFound %s CSV files.", lCSVFileNames.size()));
		lCSVFileNames.forEach(p -> {
			System.out.println(String.format("\t\t File: %s", p));
		});
		int lFileNo = 0;
		for (String lFilename : lCSVFileNames) {
			lFileNo++;
			fileExecutor.setCurrentIndex(lFileNo);
			fileExecutor.execute(lFilename);
		}
		return true;

	}

}
