package networks.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * To Represent each row parsed from the CSV file.
 * @author Syed Firoze K
 *
 */
public class RowData {

	private int rowNo;

	/**
	 * Holds values in each column of the row.
	 */
	private List<CellData> values;

	public RowData(int pRowNo) {
		super();
		this.rowNo = pRowNo;
		values = new ArrayList<>();
	}

	public void addValue(CellData pData) {
		this.values.add(pData);
	}

	public List<CellData> values() {
		return this.values;
	}

	public int getRowNo() {
		return rowNo;
	}

	@Override
	public String toString() {
		return "RowData [rowNo=" + rowNo + ", values=" + values + "]";
	}
}
