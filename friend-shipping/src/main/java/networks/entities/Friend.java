package networks.entities;

/**
 * To represent the immediate neighbour of a User.
 * @author Syed Firoze K
 *
 */
public class Friend {

	/**
	 * A User's friend.
	 */
	private User user;
	/**
	 * A synonym for denoting how far the friend is located (<i>Proximity of a user to his  or her friend).
	 */
	private double hardness;

	public Friend(User pUser, double pHardness) {
		super();
		user = pUser;
		hardness = pHardness;
	}

	public User getUser() {
		return user;
	}

	public double getHardness() {
		return hardness;
	}

	@Override
	public String toString() {
		return "Friend [user=" + user.getId() + ", hardness=" + hardness + "]";
	}
}
