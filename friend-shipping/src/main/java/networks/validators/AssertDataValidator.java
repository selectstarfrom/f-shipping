package networks.validators;

import networks.entities.CellData;
import networks.entities.RowData;
import networks.exceptions.InvalidAssertDataException;
import networks.utils.Constants;

/**
 * To validate the packet description data in the input CSV file.
 * 
 * @author Syed Firoze K
 *
 */
public class AssertDataValidator {

	private static final String PACKET_FORMAT_EXCEPTION_FORMAT = "Invalid packet desc at Row:%s, Col:%s";

	public boolean validate(RowData pRowData) throws InvalidAssertDataException {

		CellData lCellData = null;

		lCellData = pRowData.values().get(Constants.COL_DESTINATION_ID);

		String lFormat = String.format(PACKET_FORMAT_EXCEPTION_FORMAT, lCellData.getRowNo(), lCellData.getColNo());

		String lDestination = lCellData.getValue();
		if (lDestination.isEmpty()) {
			throw new InvalidAssertDataException(lFormat);
		}

		lCellData = pRowData.values().get(Constants.COL_PACK_DESC);
		String lPackDesc = lCellData.getValue();
		if (lPackDesc.isEmpty()) {
			throw new InvalidAssertDataException(lFormat);
		}
		String[] lSplit = lPackDesc.toLowerCase().split(Constants.DIMENSION_DELIMIITER);

		if (4 == lSplit.length) {
			try {
				Double.parseDouble(lSplit[0].trim());
				Double.parseDouble(lSplit[1].trim());
				Double.parseDouble(lSplit[2].trim());
				Double.parseDouble(lSplit[3].trim());
			} catch (Exception lE) {
				throw new InvalidAssertDataException(lFormat);
			}

		} else {
			throw new InvalidAssertDataException(lFormat);
		}

		lCellData = pRowData.values().get(Constants.COL_EXPECTED_COST);
		String lExpectedCost = lCellData.getValue();
		if (lExpectedCost.isEmpty()) {
			throw new InvalidAssertDataException(lFormat);
		}

		return true;
	}
}
