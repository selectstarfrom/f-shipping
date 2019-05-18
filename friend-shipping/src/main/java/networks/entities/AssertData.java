package networks.entities;

import networks.services.NetworkPathResolverService;

/**
 * AssertData for performing trials on the {@link NetworkPathResolverService}
 * This info describe a input scenario, which has to be tested and verified. An
 * An Input scenario is described by <b>Destination</b>, <b>Packet Description</b> and <b>Expected Minimum Cost</b>.<br>
 * 
 * @author Syed Firoze K
 *
 */
public class AssertData {

	/**
	 * Destination, (User Id) to which the Packet has to delivered.
	 */
	private String destination;
	/**
	 * ExpectedCost, The minimum cost expected for delivering the Packet to the
	 * destincation.
	 */
	private String expectedCost;
	/**
	 * PacketDesc, Description of the packet size and weight.
	 */
	private String packetDesc;

	private double height;
	private double width;
	private double length;
	private double weight;

	public AssertData(String pDestination, String pPacketDesc, String pExpectedCost, double pHeight, double pWidth,
			double pLength, double pWeight) {
		super();
		destination = pDestination;
		expectedCost = pExpectedCost;
		height = pHeight;
		width = pWidth;
		length = pLength;
		weight = pWeight;
		packetDesc = pPacketDesc;
	}

	public String getDestination() {
		return destination;
	}

	public String getExpectedCost() {
		return expectedCost;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getLength() {
		return length;
	}

	public double getWeight() {
		return weight;
	}

	public String getPacketDesc() {
		return packetDesc;
	}

	@Override
	public String toString() {
		return "AssertData [destination=" + destination + ", expectedCost=" + expectedCost + ", height=" + height
				+ ", width=" + width + ", length=" + length + ", weight=" + weight + "]";
	}

}
