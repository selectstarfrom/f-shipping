package networks.services;

import networks.entities.AssertData;

/**
 * To calculate the Cost involved in delivering the packet from one User to
 * another.
 * 
 * @author Syed Firoze K
 *
 */
public class PricingService {

	/**
	 * A normalized package weight is the grater value of an actual weight or a
	 * volumetric weight.
	 */
	private double normalized;

	public PricingService() {
		super();

	}

	/**
	 * A normalized weight will be calculated from the given packet description
	 * {@link AssertData#getPacketDesc()}.<br>
	 * 
	 * @param pAssertData
	 * @return
	 */
	private double normalizeWeight(AssertData pAssertData) {

		double lHeight = pAssertData.getHeight();
		double lLength = pAssertData.getLength();
		double lWidth = pAssertData.getWidth();
		double lWeight = pAssertData.getWeight();

		double lWeightInKg = lWeight / 1000;
		double lVolume = lHeight * lLength * lWidth;
		double lNormalizedWeight = (lVolume / 5000);

		double lMax = (lNormalizedWeight > lWeightInKg) ? lNormalizedWeight : lWeightInKg;

		normalized = roundNormalWeightToHalfKg(lMax);
		return normalized;

	}

	private double roundNormalWeightToHalfKg(double pDoubleInputValue) {

		String lStringVal = pDoubleInputValue + "";
		String[] lSplit = lStringVal.split("\\.");
		String lIntegerPart = lSplit[0];
		String lDecimalPart = lSplit[1];

		Double lIntegerPartDouble = Double.parseDouble(lIntegerPart);
		Double lDecimalPartDouble = Double.parseDouble("." + lDecimalPart);

		Double lNewDecimal = 0.0;
		Double lDivByHalf = lDecimalPartDouble / 0.5;
		if (lDivByHalf > 1) {
			lNewDecimal = lIntegerPartDouble + 1;
		} else {
			lNewDecimal = lIntegerPartDouble + .5;
		}

		return lNewDecimal;
	}

	private double getNormalized() {
		return normalized;
	}

	/**
	 * Calculates the cost of delivering packet for the given Hardness(aka,
	 * distance).<br>
	 * Formulae applied is <b><i> shipping cost(EUR) = sqrt(sum(HARD)) *
	 * normalized-weight(kg)<i></b>.<br>
	 * 
	 * @param pAssertData
	 * @param pHardness   @return.
	 */
	public double getShippingCost(AssertData pAssertData, Double pHardness) {
		normalizeWeight(pAssertData);
		double lCost = Math.sqrt(pHardness) * getNormalized();
		lCost = Math.round(lCost * 100.0) / 100.0;
		return lCost;
	}

}
