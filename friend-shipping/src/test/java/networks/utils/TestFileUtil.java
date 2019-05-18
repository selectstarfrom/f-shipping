package networks.utils;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import networks.exceptions.FriendShippingException;
import networks.exceptions.InvalidDataException;

/**
 * @author Syed Firoze K
 *
 */
public class TestFileUtil {

	private static final String CSV_1 = "01.csv";

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testGetCSVFilesInDirectory1() throws InvalidDataException, FriendShippingException {

		ClassLoader classLoader = getClass().getClassLoader();
		String lPath = classLoader.getResource(CSV_1).getPath();
		String lParent = new File(lPath).getParent();

		List<String> lCsvFilesInDirectory = FileUtil.getCSVFilesInDirectory(lParent);
		assertEquals("CSV files found in directory", 3, lCsvFilesInDirectory.size());
	}

	@Test
	public void testGetCSVFilesInDirectory2() throws InvalidDataException, FriendShippingException {

		expectedEx.expect(FriendShippingException.class);
		ClassLoader classLoader = getClass().getClassLoader();
		String lPath = classLoader.getResource(CSV_1).getPath();
		String lParent = new File(lPath).getParent() + "X";
		;

		List<String> lCsvFilesInDirectory = FileUtil.getCSVFilesInDirectory(lParent);
		assertEquals("CSV files found in directory", 3, lCsvFilesInDirectory.size());
	}

}
