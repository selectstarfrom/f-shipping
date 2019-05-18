package networks.entities;

/**
 * To Represent each value parsed from the CSV file.
 * @author Syed Firoze K
 *
 */
public class CellData {

	private int rowNo;
	private int colNo;
	private String value;

	public CellData(int pRowNo, int pColNo, String pValue) {
		super();
		rowNo = pRowNo;
		colNo = pColNo;
		value = pValue;
	}

	public String getValue() {
		return value;
	}

	public int getRowNo() {
		return rowNo;
	}

	public int getColNo() {
		return colNo;
	}

	@Override
	public String toString() {
		return "CellData [colNo=" + colNo + ", value=" + value + "]";
	}

}
