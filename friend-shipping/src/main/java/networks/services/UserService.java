package networks.services;

import java.util.ArrayList;
import java.util.List;

import networks.entities.AssertData;
import networks.entities.CellData;
import networks.entities.Friend;
import networks.entities.Network;
import networks.entities.RowData;
import networks.entities.User;
import networks.exceptions.InvalidCellDataException;
import networks.exceptions.InvalidDataException;
import networks.exceptions.InvalidAssertDataException;
import networks.utils.Constants;
import networks.validators.AssertDataValidator;

/**
 * To create a {@link Network} instance that contains all available {@link User}
 * in the given list of {@link RowData}.<br>
 * Includes creation of a list of {@link User} with its direct friends
 * (<i>immediate child</i>) and hardness to its each friends.<br>
 * Also, includes creation of a list of {@link AssertData} which is used to test
 * and verify the actual output against expected output.
 * 
 * @author Syed Firoze K
 *
 */
public class UserService {

	private AssertDataValidator packetDescriptionValidator;

	public UserService() {
		super();
		packetDescriptionValidator = new AssertDataValidator();
	}

	/**
	 * Creates a {@link Network} instance from the given list of
	 * {@link RowData}.<br>
	 * 
	 * @param pRowData list of all data formed from input CSV file.
	 * @return
	 * @throws InvalidDataException
	 */
	public Network createNetwork(List<RowData> pRowData) throws InvalidDataException {
		Network lNetwork = new Network();

		List<String> lMessages = new ArrayList<>();

		pRowData.stream().forEach(p -> {
			List<CellData> lValues = p.values();
			if (!lValues.isEmpty()) {
				CellData lFirstCellData = lValues.get(0);
				User lFirstUser = null;
				try {
					lFirstUser = parseFirstUser(lFirstCellData, lNetwork);
					if (lFirstUser != null) {
						lNetwork.addUser(lFirstUser);
						for (int lI = 1; lI < lValues.size(); lI++) {
							CellData lFriendCellData = lValues.get(lI);
							try {
								Friend lFriend = parseFriends(lFriendCellData, lNetwork);
								if (lFriend != null) {
									lFirstUser.addFriends(lFriend);
									lNetwork.addUser(lFriend.getUser());
								} else {
									break;
								}
							} catch (InvalidCellDataException e) {
								lMessages.add(e.getMessage());
							}
						}
					} else {
						try {
							AssertData lParseAssertData = parseAssertData(p, lNetwork);
							lNetwork.addAssertData(lParseAssertData);
						} catch (InvalidCellDataException e) {
							lMessages.add(e.getMessage());
						}
					}
				} catch (InvalidCellDataException | InvalidAssertDataException e) {
					lMessages.add(e.getMessage());
				}

			}
		});
		if (!lMessages.isEmpty()) {
			throw new InvalidDataException(lMessages);
		}
		return lNetwork;

	}

	/**
	 * Create a {@link Friend} instance from the given CellData.<br>
	 * If the pCellData equals to {@link Constants#ASSERT_ROW_INDICATOR}, it will
	 * return null so that calling method can try creating Assert data from the same
	 * RowData.
	 * 
	 * @param pCellData
	 * @param pNetwork
	 * @return
	 * @throws InvalidCellDataException
	 */
	private Friend parseFriends(CellData pCellData, Network pNetwork) throws InvalidCellDataException {

		String lValue = pCellData.getValue();
		if (Constants.ASSERT_ROW_INDICATOR.equals(lValue.trim())) {
			return null;
		}
		String[] lSplit = lValue.split(Constants.NAME_HARDNESS_DELIMITTER);
		if (lSplit.length != 2) {
			throw new InvalidCellDataException(pCellData.getRowNo(), pCellData.getColNo());
		}

		String lName = lSplit[0];
		if (lName.isEmpty()) {
			throw new InvalidCellDataException(pCellData.getRowNo(), pCellData.getColNo());
		}

		String lHardnessStringVal = lSplit[1];
		double lParseDoubleValue = Double.MAX_VALUE;
		try {
			lParseDoubleValue = Double.parseDouble(lHardnessStringVal);
		} catch (NumberFormatException | NullPointerException nfe) {
			throw new InvalidCellDataException(pCellData.getRowNo(), pCellData.getColNo());
		}

		final double lHardness = lParseDoubleValue;

		Friend lFriend = pNetwork.getUserById(lValue).map(pVal -> {
			Friend lNewFriend = new Friend(pVal, lHardness);
			return lNewFriend;
		}).orElseGet(() -> {
			Friend lNewFriend = new Friend(new User(lName), lHardness);
			return lNewFriend;
		});

		return lFriend;

	}

	/**
	 * Create a {@link User} instance from the given CellData.<br>
	 * If the pCellData equals to {@link Constants#ASSERT_ROW_INDICATOR}, it will
	 * return null, so that calling method can try creating Assert data from the
	 * same RowData.
	 * 
	 * @param pCellData
	 * @param pNetwork
	 * @return
	 * @throws InvalidCellDataException
	 */
	private User parseFirstUser(CellData pCellData, Network pNetwork) throws InvalidCellDataException {

		String lValue = pCellData.getValue();
		if (Constants.ASSERT_ROW_INDICATOR.equals(lValue.trim())) {
			return null;
		}

		if (lValue.isEmpty()) {
			throw new InvalidCellDataException(pCellData.getRowNo(), pCellData.getColNo());
		}

		User lUser = pNetwork.getUserById(lValue).map(pVal -> {
			return pVal;
		}).orElseGet(() -> {
			return new User(lValue);
		});

		return lUser;
	}

	/**
	 * Create a {@link AssertData} instance from the given CellData.<br>
	 * 
	 * @param pRowData
	 * @param pNetwork
	 * @return
	 * @throws InvalidCellDataException
	 * @throws InvalidAssertDataException If Any error occurred while parsing assert
	 *                                    data row like the Packet Description Cell
	 *                                    is not in the expected Format, a
	 *                                    {@link InvalidAssertDataException} will be
	 *                                    thrown.
	 */
	private AssertData parseAssertData(RowData pRowData, Network pNetwork) throws InvalidAssertDataException {

		packetDescriptionValidator.validate(pRowData);

		CellData lCellData = null;

		lCellData = pRowData.values().get(Constants.COL_DESTINATION_ID);

		String lDestination = lCellData.getValue();

		lCellData = pRowData.values().get(Constants.COL_PACK_DESC);
		String lPackDesc = lCellData.getValue();

		String[] lSplit = lPackDesc.toLowerCase().split(Constants.DIMENSION_DELIMIITER);

		double lHeight = 0.0;
		double lLength = 0.0;
		double lWidth = 0.0;
		double lWeight = 0.0;

		lHeight = Double.parseDouble(lSplit[0].trim());
		lLength = Double.parseDouble(lSplit[1].trim());
		lWidth = Double.parseDouble(lSplit[2].trim());
		lWeight = Double.parseDouble(lSplit[3].trim());

		lCellData = pRowData.values().get(Constants.COL_EXPECTED_COST);
		String lExpectedCost = lCellData.getValue();

		return new AssertData(lDestination, lPackDesc, lExpectedCost, lHeight, lWidth, lLength, lWeight);
	}

}
