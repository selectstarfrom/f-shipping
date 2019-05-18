package networks.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import networks.entities.CellData;
import networks.entities.RowData;
import networks.exceptions.FriendShippingException;
import networks.utils.Constants;

/**
 * Service to Read, Parse and Generate a representation of CSV input data in
 * terms of Row Data {@link RowData} & Column Data {@link CellData}.
 * 
 * @author Syed Firoze K
 *
 */
public class DataReaderService {

	public DataReaderService() {

	}

	/**
	 * Entrypoint to the service.<br>
	 * Calling this method will invoke process of reading, parsing and generating RowData and CellData representation of CSV data.<br>
	 * @see {@link RowData}, {@link CellData}
	 * @param pFilePath
	 * @return
	 * @throws Exception
	 */
	public List<RowData> readCsv(String pFilePath) throws Exception {

		File lFile = new File(pFilePath);

		List<RowData> records = new ArrayList<>();

		try (Scanner lScanner = new Scanner(lFile);) {
			int lRowNo = 0;
			while (lScanner.hasNextLine()) {
				lRowNo++;
				String lNextLine = lScanner.nextLine();
				RowData lRowData = new RowData(lRowNo);
				getRecordFromLine(lNextLine, lRowData);
				records.add(lRowData);
			}
		} catch (FileNotFoundException e) {
			throw new FriendShippingException("Cannot find the requested file.");
		}

		return records;
	}

	/**
	 * Reads given line of CSV file, parse each column, creates Column Data and adds to the Given instance on RowData.
	 * @param pLine
	 * @param pRowData
	 */
	private void getRecordFromLine(String pLine, RowData pRowData) {

		try (Scanner lRowScanner = new Scanner(pLine)) {
			lRowScanner.useDelimiter(Constants.INPUT_FILE_DELIMITER);
			int lColNo = 0;
			while (lRowScanner.hasNext()) {
				lColNo++;
				String lNext = lRowScanner.next();
				CellData lCellData = new CellData(pRowData.getRowNo(), lColNo, lNext);
				pRowData.addValue(lCellData);
			}
		}
	}

}