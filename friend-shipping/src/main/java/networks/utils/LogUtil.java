package networks.utils;

import networks.entities.AssertData;
import networks.entities.ShortestRoute;

public class LogUtil {

	public static void logProcessStartInfo(String pFileName, int pFileNo) {
		System.out.println(String.format("\n\n\tProcessing File %s: %s", pFileNo, pFileName));
		System.out.println("\t==============================================================================");
	}

	public static void logAssertOutput(int pIndex, AssertData pAssertData, ShortestRoute pShortestRoute, String pCost) {
		System.out.println("\t\tAssert " + pIndex + ":");
		System.out.println("\t\t\t Destincation: " + pShortestRoute.getDestination());
		System.out.println("\t\t\t Packet Desc: " + pAssertData.getPacketDesc());
		System.out.println("\t\t\t Expected Cost: " + pAssertData.getExpectedCost());
		System.out.println("\t\t\t Actual: " + pCost);
	}
}
