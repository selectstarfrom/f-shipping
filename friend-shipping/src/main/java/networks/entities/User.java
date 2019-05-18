package networks.entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Represent each User in the Network.
 * @author Syed Firoze K
 *
 */
public class User implements Comparable<User> {

	/**
	 * An identifier to uniquely identify user in the Network. 
	 */
	private String id;
	/**
	 * Shortest Routes to each User in the Network.
	 */
	private List<ShortestRoute> shortestRoutes = new LinkedList<>();
	/**
	 * Direct friends of the user.
	 */
	private Set<Friend> friends = new HashSet<>();

	public User(String pId) {
		this.id = pId;
	}

	public String getId() {
		return id;
	}

	public List<ShortestRoute> getRoutes() {
		return shortestRoutes;
	}

	public Set<Friend> getFriends() {
		return friends;
	}

	/**
	 * Adds a user to friend's list.
	 * @param pFriend
	 */
	public void addFriends(Friend pFriend) {
		this.friends.add(pFriend);
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}

	@Override
	public int compareTo(User pUser) {
		return this.id.compareTo(pUser.getId());
	}

	/**
	 * Adds Shortest Route.
	 * @param pShortestRoute
	 */
	public void addShortestRoute(ShortestRoute pShortestRoute) {
		shortestRoutes.add(pShortestRoute);
	}

}
