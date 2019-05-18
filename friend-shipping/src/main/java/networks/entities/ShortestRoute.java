package networks.entities;

import java.util.LinkedList;

/**
 * Represent shortest path to the destination from a particular User.
 * 
 * @author Syed Firoze K
 *
 */
public class ShortestRoute {

	/**
	 * Destination to which shortest route is defined.
	 */
	private User destination;
	/**
	 * Path from User(Source) to the Destination(User)
	 */
	private LinkedList<User> path;
	/**
	 * Proximity or Cost, a term for representing how far or complex to reach
	 * destination.
	 */
	private Double hardness = Double.MAX_VALUE;

	public ShortestRoute(User pDestination, Double pHardness) {
		destination = pDestination;
		hardness = pHardness;
	}

	/**
	 * @See {@link ShortestRoute#destination}
	 * @return
	 */
	public User getDestination() {
		return destination;
	}

	/**
	 * @See {@link ShortestRoute#path}
	 * @return
	 */
	public LinkedList<User> getPath() {
		return path;
	}

	/**
	 * @See {@link ShortestRoute#hardness}
	 * @return
	 */
	public Double getHardness() {
		return hardness;
	}

	/**
	 * Updates the hardness.
	 * 
	 * @param pHardness
	 * @return
	 */
	public Double updateHardness(Double pHardness) {
		return hardness = pHardness;
	}

	@Override
	public String toString() {
		return "ShortestRoute [destination=" + destination.getId() + ", hardness=" + hardness + "]";
	}

	public boolean isDeilverable() {
		return Double.MAX_VALUE > this.hardness;
	}

}
