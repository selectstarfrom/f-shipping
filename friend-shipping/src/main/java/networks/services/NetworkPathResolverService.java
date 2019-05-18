package networks.services;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import networks.entities.Friend;
import networks.entities.Network;
import networks.entities.ShortestRoute;
import networks.entities.User;
import networks.exceptions.FriendShippingException;
import networks.utils.ApplicationProperties;

/**
 * Service to find the shortest route from a given User to another.<br>
 * The algorithm used is <b>Dijkstra's shortest path algorithm<b>. <br>
 * 
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Dijkstra's
 *      Algorithm</a>.
 * @author Syed Firoze K
 *
 */
public class NetworkPathResolverService {

	/**
	 * Resolves the shortest route from the <b>Source User</b> to another in the
	 * network.<br>
	 * <b>Source User</b> is the User with id <b>ME</b>,
	 * {@link ApplicationProperties#getShippingSource}.<br>
	 * This User id can be defined in the <b>application properties file<b>. <br>
	 * 
	 * @param pNetwork
	 * @return
	 * @throws FriendShippingException
	 */
	public Network resolveShortestRouteToAllUsers(Network pNetwork) throws FriendShippingException {
		Optional<User> lUserById = pNetwork.getUserById(ApplicationProperties.getShippingSource());
		if (lUserById.isPresent()) {
			return resolveShortestRouteToAllUsers(pNetwork, lUserById.get());
		} else {
			throw new FriendShippingException("No User with id" + ApplicationProperties.getShippingSource() + " found");
		}

	}

	/**
	 * Resolves the shortest route from the given Source User to another in the
	 * network.<br>
	 * 
	 * @param pNetwork
	 * @param pSource
	 * @return
	 * @throws FriendShippingException
	 */
	public Network resolveShortestRouteToAllUsers(Network pNetwork, User pSource) throws FriendShippingException {

		pNetwork.initializeNonRelaxedUsersList();

		initializeAllShortestRoutes(pNetwork, pSource);

		initializeShortestRoutesToFriends(pSource);

		pNetwork.removeFromNoRelaxedUsers(pSource);

		User lCurrentUser = pSource;
		while (!pNetwork.isAllRelaxed()) {
			List<User> lNonRelaxedUsers = pNetwork.getNonRelaxedUsers();
			ShortestRoute lShortestRouteToClosestUser = getClosestNonRelaxedUser(pSource, lNonRelaxedUsers);

			lCurrentUser = lShortestRouteToClosestUser.getDestination();
			applyRelaxation(lCurrentUser, pSource);

			pNetwork.removeFromNoRelaxedUsers(lCurrentUser);

		}

		return pNetwork;
	}

	/**
	 * Applies relaxation process on the given User.
	 * 
	 * @param pCurrentUser
	 * @param pSource
	 */
	private void applyRelaxation(User pCurrentUser, User pSource) {

		ShortestRoute lShortestRouteToCurrentUser = getShortestRoute(pSource, pCurrentUser);

		Set<Friend> lFriends = pCurrentUser.getFriends();

		lFriends.forEach(p -> {
			User lUser = p.getUser();
			Double lHardnessToFriend = p.getHardness();
			ShortestRoute lShortestRoute = pSource.getRoutes().stream()
					.filter(q -> q.getDestination().getId().equals(lUser.getId())).findFirst().get();

			// START OF:RELAXTION
			Double lNewHardness = lShortestRouteToCurrentUser.getHardness() + lHardnessToFriend;

			if (lNewHardness < lShortestRoute.getHardness()) {
				lShortestRoute.updateHardness(lNewHardness);
				updateShortestRoutes(pSource, lShortestRoute);
			}
			// END OF:RELAXTION
		});
	}

	private void updateShortestRoutes(User pSource, ShortestRoute pShortestRoute) {
		pSource.getRoutes().stream()
				.filter(p -> p.getDestination().getId().equals(pShortestRoute.getDestination().getId())).findFirst()
				.get().updateHardness(pShortestRoute.getHardness());
	}

	private ShortestRoute getShortestRoute(User pSource, User pCurrentUser) {
		return pSource.getRoutes().stream().filter(p -> p.getDestination().getId().equals(pCurrentUser.getId()))
				.findFirst().get();
	}

	/**
	 * Returns the next closest Friend (immediate child-user) which is not yet
	 * <b>Relaxed</b>.<br>
	 * 
	 * By closest, it means the User to which is minimum hardness.
	 * 
	 * @param pSource
	 * @param pNonRelaxedUsers
	 * @return
	 */
	private ShortestRoute getClosestNonRelaxedUser(User pSource, List<User> pNonRelaxedUsers) {
		List<ShortestRoute> lShortestRoutes = pSource.getRoutes();
		ShortestRoute lClosest = lShortestRoutes.stream().filter(p -> {
			User lDestination = p.getDestination();
			String lId = lDestination.getId();
			boolean lEquals = lId.equals(pSource.getId());
			boolean lNotRelaxed = pNonRelaxedUsers.contains(lDestination);
			return !lEquals && lNotRelaxed;
		}).min(Comparator.comparing(ShortestRoute::getHardness)).orElseThrow(NoSuchElementException::new);

		return lClosest;
	}

	/**
	 * Updates the shortest-route from given Source User to its friends.<br>
	 * As the Friends of given Source User has a always a direct route, the shortest
	 * route is updated with value which is directly given in the input data.<br>
	 * As for all other Users, this hardness value may or may not be again updated
	 * gradually, passing through several iterations of <b>Relaxation</b>.<br>
	 * <br>
	 * 
	 * @see <a href=
	 *      "https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Dijkstra's
	 *      Algorithm</a>.
	 * @param pSource
	 */
	private void initializeShortestRoutesToFriends(User pSource) {
		pSource.getFriends().forEach(p -> {
			double lHardness = p.getHardness();
			updateShortestRoutes(pSource, new ShortestRoute(p.getUser(), lHardness));
		});
	}

	/**
	 * Initialize a shortest-route from given Source User to all other User with
	 * hardness as {@link Double#MAX_VALUE}.<br>
	 * This initial hardness value represents an infinite value.<br>
	 * <br>
	 * 
	 * Also, as the shortest route from a User to itself is always ZERO, the
	 * shortest route hardness from given Source User to itself is updated with
	 * hardness as ZERO.<br>
	 * 
	 * @param pNetwork
	 * @param pSource
	 */
	private void initializeAllShortestRoutes(Network pNetwork, User pSource) {
		pNetwork.getUsers().forEach(p -> {
			pSource.addShortestRoute(new ShortestRoute(p, Double.MAX_VALUE));
		});
		updateShortestRoutes(pSource, new ShortestRoute(pSource, 0.0));
	}

}
