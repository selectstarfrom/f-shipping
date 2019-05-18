package networks.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import networks.entities.Network;
import networks.entities.RowData;
import networks.entities.User;
import networks.exceptions.InvalidDataException;

/**
 * @author Syed Firoze K
 *
 */
public class TestUserService {

	private static final String CSV_1 = "01.csv";
	private static final String CSV_2 = "02.csv";
	private static final String PACK_ERROR_CSV = "pack_error.csv";

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testPackError() throws Exception {

		expectedEx.expect(InvalidDataException.class);
		expectedEx.expectMessage("Invalid packet desc at Row:5, Col:2");

		DataReaderService lDataReader = new DataReaderService();

		ClassLoader classLoader = getClass().getClassLoader();
		List<RowData> lRowData = null;

		String lPath = classLoader.getResource(PACK_ERROR_CSV).getPath();

		lRowData = lDataReader.readCsv(lPath);
		int lRecordsCount = lRowData.size();

		assertEquals("No. of lines read from csv file", 7, lRecordsCount);

		UserService lUserService = new UserService();

		lUserService.createNetwork(lRowData);

	}

	@Test
	public void testCreateNetwrokCSV1() throws Exception {
		DataReaderService lDataReader = new DataReaderService();

		ClassLoader classLoader = getClass().getClassLoader();
		List<RowData> lRowData = null;

		String lPath = classLoader.getResource(CSV_1).getPath();
		lRowData = lDataReader.readCsv(lPath);

		UserService lUserService = new UserService();
		Network lNetwork = lUserService.createNetwork(lRowData);

		assertEquals("No. of users in the network", 7, lNetwork.size());

		Optional<User> lUserById;

		lUserById = lNetwork.getUserById("ME");
		assertTrue("Expected 1 user with Id=ME, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for ME", 5, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Stefan");
		assertTrue("Expected 1 user with Id=Stefan, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Stefan", 2, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Adam");
		assertTrue("Expected 1 user with Id=Adam, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Adam", 3, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Diana");
		assertTrue("Expected 1 user with Id=Diana, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Diana", 2, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Philipp");
		assertTrue("Expected 1 user with Id=Philipp, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Philipp", 0, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Martin");
		assertTrue("Expected 1 user with Id=Martin, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Martin", 0, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Amir");
		assertTrue("Expected 1 user with Id=Amir, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Amir", 0, lUserById.get().getFriends().size());

	}

	@Test
	public void testCreateNetwrokCSV2() throws Exception {
		DataReaderService lDataReader = new DataReaderService();

		ClassLoader classLoader = getClass().getClassLoader();
		List<RowData> lRowData = null;

		String lPath = classLoader.getResource(CSV_2).getPath();
		lRowData = lDataReader.readCsv(lPath);

		UserService lUserService = new UserService();
		Network lNetwork = lUserService.createNetwork(lRowData);

		assertEquals("No. of users in the network", 16, lNetwork.size());

		Optional<User> lUserById;

		lUserById = lNetwork.getUserById("ME");
		assertTrue("Expected 1 user with Id=ME, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for ME", 5, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Felipe");
		assertTrue("Expected 0 user with Id=Felipe, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Felipe", 5, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Sarah");
		assertTrue("Expected 0 user with Id=Sarah, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Sarah", 1, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Manuel");
		assertTrue("Expected 0 user with Id=Manuel, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Manuel", 0, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Philipp");
		assertTrue("Expected 1 user with Id=Philipp, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Philipp", 3, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Adam");
		assertTrue("Expected 1 user with Id=Adam, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Adam", 3, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Diana");
		assertTrue("Expected 1 user with Id=Diana, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Diana", 1, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Stefan");
		assertTrue("Expected 1 user with Id=Stefan, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Stefan", 0, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Amir");
		assertTrue("Expected 1 user with Id=Amir, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Amir", 4, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Louise");
		assertTrue("Expected 1 user with Id=Louise, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Louise", 0, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Steven");
		assertTrue("Expected 1 user with Id=Steven, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Steven", 1, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Robson");
		assertTrue("Expected 1 user with Id=Robson, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Robson", 3, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Martin");
		assertTrue("Expected 1 user with Id=Martin, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Martin", 0, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Luciana");
		assertTrue("Expected 1 user with Id=Luciana, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Luciana", 0, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Manuela");
		assertTrue("Expected 1 user with Id=Manuela, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Manuela", 0, lUserById.get().getFriends().size());

		lUserById = lNetwork.getUserById("Julie");
		assertTrue("Expected 1 user with Id=Julie, but 0 user was found", lUserById.isPresent());
		assertEquals("No. of friends for Julie", 2, lUserById.get().getFriends().size());

	}
}
