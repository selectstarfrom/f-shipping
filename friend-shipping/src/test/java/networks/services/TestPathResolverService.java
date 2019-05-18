package networks.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.junit.Test;

import networks.entities.AssertData;
import networks.entities.Network;
import networks.entities.RowData;
import networks.entities.ShortestRoute;
import networks.entities.User;
import networks.utils.ApplicationProperties;

/**
 * @author Syed Firoze K
 *
 */
public class TestPathResolverService {

	private static final String CSV_1 = "01.csv";
	private static final String CSV_2 = "02.csv";

	@Test
	public void testResolveForCSV1() {
		try {
			DataReaderService lDataReader = new DataReaderService();

			ClassLoader classLoader = getClass().getClassLoader();
			List<RowData> lRowData = null;

			String lPath = classLoader.getResource(CSV_1).getPath();
			lRowData = lDataReader.readCsv(lPath);

			UserService lUserService = new UserService();

			PricingService lPricingService = new PricingService();
			Network lNetwork = lUserService.createNetwork(lRowData);

			NetworkPathResolverService networkPathResolverService = new NetworkPathResolverService();

			List<AssertData> lAssertData = lNetwork.getAssertData();

			String lShippingSource = ApplicationProperties.getShippingSource();

			for (AssertData lItem : lAssertData) {
				Network lSolvedNetwork = networkPathResolverService.resolveShortestRouteToAllUsers(lNetwork);

				String lDestination = lItem.getDestination();
				String lExpectedCost = lItem.getExpectedCost();

				ShortestRoute lShortestRoute = lSolvedNetwork.getShortestRoute(lShippingSource, lDestination);

				String lCost = "~";
				if (lShortestRoute.isDeilverable()) {
					Double lHardness = lShortestRoute.getHardness();
					Double lShippingCost = lPricingService.getShippingCost(lItem, lHardness);
					lCost = String.format("%.2f", lShippingCost);
				}

				assertEquals(String.format("Minimum Cost for delivering from  %s to %s", lShippingSource, lDestination),
						lExpectedCost, lCost);

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testResolveForCSV2() {
		try {
			DataReaderService lDataReader = new DataReaderService();

			ClassLoader classLoader = getClass().getClassLoader();
			List<RowData> lRowData = null;

			String lPath = classLoader.getResource(CSV_2).getPath();
			lRowData = lDataReader.readCsv(lPath);

			UserService lUserService = new UserService();

			PricingService lPricingService = new PricingService();
			Network lNetwork = lUserService.createNetwork(lRowData);

			NetworkPathResolverService networkPathResolverService = new NetworkPathResolverService();

			List<AssertData> lAssertData = lNetwork.getAssertData();

			String lShippingSource = ApplicationProperties.getShippingSource();

			for (AssertData lItem : lAssertData) {
				Network lSolvedNetwork = networkPathResolverService.resolveShortestRouteToAllUsers(lNetwork);

				String lDestination = lItem.getDestination();
				String lExpectedCost = lItem.getExpectedCost();

				ShortestRoute lShortestRoute = lSolvedNetwork.getShortestRoute(lShippingSource, lDestination);

				String lCost = "~";
				if (lShortestRoute.isDeilverable()) {
					Double lHardness = lShortestRoute.getHardness();
					Double lShippingCost = lPricingService.getShippingCost(lItem, lHardness);
					lCost = String.format("%.2f", lShippingCost);
				}

				assertEquals(String.format("Minimum Cost for delivering from  %s to %s", lShippingSource, lDestination),
						lExpectedCost, lCost);

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
