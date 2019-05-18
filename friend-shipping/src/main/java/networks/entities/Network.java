package networks.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Represent the whole network of Users.
 * 
 * @author Syed Firoze K
 *
 */
public class Network {

	private List<AssertData> assertData = new ArrayList<>();
	private Set<User> users = new HashSet<>();
	/**
	 * The list holding all Users who are not relaxed.
	 */
	private List<User> nonRelaxedUsers = new ArrayList<>();

	/**
	 * Initialize {@link Network#nonRelaxedUsers} with list of all Users in the
	 * Network.
	 * 
	 */
	public void initializeNonRelaxedUsersList() {
		nonRelaxedUsers = new ArrayList<>(users);
	}

	/**
	 * Removes the given User from the Non-Relaxed user list.
	 * 
	 * @param pSource
	 */
	public void removeFromNoRelaxedUsers(User pSource) {
		nonRelaxedUsers.remove(pSource);
	}

	/**
	 * Adds a User to the Network.<br>
	 * No user with same <b>id</b> will be allowed.
	 * 
	 * @param pUser
	 * @return
	 */
	public boolean addUser(User pUser) {
		if (users.stream().anyMatch(p -> p.getId().equals(pUser.getId()))) {
			return false;
		}
		this.users.add(pUser);
		return true;
	}

	public void printNetwork() {
		System.out.println("Network:");
		this.users.forEach(p -> {
			System.out.println("\t" + p.toString());
		});
	}

	public Set<User> getUsers() {
		return users;
	}

	public List<AssertData> getAssertData() {
		return assertData;
	}

	/**
	 * Returns the User matching the given Id.
	 * 
	 * @param pId
	 * @return
	 */
	public Optional<User> getUserById(String pId) {
		return users.stream().filter(p -> p.getId().equals(pId)).findFirst();
	}

	/**
	 * Returns the size of the Network, which is the number of users in the Network.
	 * 
	 * @return
	 */
	public int size() {
		return users.size();
	}

	/**
	 * Adds the User Input Scenario which needs be tested against the provided
	 * Network of Users.
	 * 
	 * @param pParseAssertData
	 */
	public void addAssertData(AssertData pParseAssertData) {
		this.assertData.add(pParseAssertData);

	}

	public List<User> getNonRelaxedUsers() {
		return nonRelaxedUsers;
	}

	/**
	 * Returns true if all users are relaxed.<br>
	 * 
	 * @return
	 */
	public boolean isAllRelaxed() {
		return nonRelaxedUsers.isEmpty();
	}

	public ShortestRoute getShortestRoute(String pSourceId, String pDestincationId) {
		ShortestRoute lShortestRoute = getUserById(pSourceId).get().getRoutes().stream()
				.filter(p -> p.getDestination().getId().equals(pDestincationId)).findFirst().get();
		return lShortestRoute;
	}

}
