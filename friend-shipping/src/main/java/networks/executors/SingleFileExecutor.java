package networks.executors;

import java.util.List;

import networks.entities.AssertData;
import networks.entities.Network;
import networks.entities.RowData;
import networks.entities.ShortestRoute;
import networks.services.DataReaderService;
import networks.services.NetworkPathResolverService;
import networks.services.PricingService;
import networks.services.UserService;
import networks.utils.ApplicationProperties;
import networks.utils.LogUtil;

/**
 * NetworkApp: The entrypoint of the Application.
 * 
 * @author Syed Firoze K
 *
 */
public class SingleFileExecutor implements IFileExecutor {

	private DataReaderService dataReaderService;
	private NetworkPathResolverService networkPathResolverService;
	private UserService userService;
	private PricingService pricingService;
	private int index = 1;

	public SingleFileExecutor(DataReaderService pDataReaderService,
			NetworkPathResolverService pNetworkPathResolverService, UserService pUserService,
			PricingService pPricingService) {
		super();
		dataReaderService = pDataReaderService;
		networkPathResolverService = pNetworkPathResolverService;
		userService = pUserService;
		pricingService = pPricingService;
	}

	/**
	 * Calling this method will initiate the processing of Single Input CSV file.
	 * 
	 * @param pFileName Full qualified filename of the input csv.
	 * @return
	 * @throws Exception
	 */
	public boolean execute(String pFileName) throws Exception {

		LogUtil.logProcessStartInfo(pFileName, index);

		List<RowData> lCsvDate = dataReaderService.readCsv(pFileName);

		Network lNetwork = userService.createNetwork(lCsvDate);

		List<AssertData> lAssertData = lNetwork.getAssertData();

		int lIndex = 0;

		for (AssertData lItem : lAssertData) {

			lIndex++;

			Network lSolvedNetwork = networkPathResolverService.resolveShortestRouteToAllUsers(lNetwork);

			String lDestination = lItem.getDestination();

			ShortestRoute lShortestRoute = lSolvedNetwork.getShortestRoute(ApplicationProperties.getShippingSource(),
					lDestination);

			String lCost = "~";
			if (lShortestRoute.isDeilverable()) {
				Double lHardness = lShortestRoute.getHardness();
				Double lShippingCost = pricingService.getShippingCost(lItem, lHardness);
				lCost = String.format("%.2f", lShippingCost);
			}

			LogUtil.logAssertOutput(lIndex, lItem, lShortestRoute, lCost);

		}

		return true;

	}

	public void setCurrentIndex(int psetCurrentIndex) {
		index = psetCurrentIndex;
	}

}
