package networks.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import networks.entities.RowData;
import networks.services.DataReaderService;

/**
 * @author Syed Firoze K
 *
 */
public class TestDataReaderService {

	private static final String CSV_1 = "01.csv";
	private static final String CSV_2 = "02.csv";

	@Test
	public void testReadCsv1() {
		DataReaderService lDataReader = new DataReaderService();

		ClassLoader classLoader = getClass().getClassLoader();
		String lPath = classLoader.getResource(CSV_1).getPath();
		try {
			List<RowData> lReadCsv = lDataReader.readCsv(lPath);
			int lRecordsCount = lReadCsv.size();
			assertEquals("No. of lines read from csv file", 7, lRecordsCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadCsv2() {
		DataReaderService lDataReader = new DataReaderService();
		
		ClassLoader classLoader = getClass().getClassLoader();
		String lPath = classLoader.getResource(CSV_2).getPath();
		try {
			List<RowData> lReadCsv = lDataReader.readCsv(lPath);
			int lRecordsCount = lReadCsv.size();
			assertEquals("No. of lines read from csv file", 14, lRecordsCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
