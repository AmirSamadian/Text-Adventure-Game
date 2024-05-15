
public class Item {
	
	/** stores the name of an item. */
	private String name;
	
	/** stores the description of an item. */
	private String description;
	
	/** stores the point value of an item. */
	private int pointValue;
	
	/** stores the weight of an item. */
	private float weight;

	/**
	 * @param name
	 * @param description
	 * @param pointValue
	 * @param weight
	 */
	public Item(String name, String description, int pointValue, float weight) {
		this.name = name;
		this.description = description;
		this.pointValue = pointValue;
		this.weight = weight;
	}
	
	
	/**
	* Returns a string description including all the details of an Item.
	* For example,
	* 
	* @return A string representing all the details of a Room.
	*/
	public String toString() {
		
		String message = "Information about this item: \r\n" + name + ": " + "\r\n"
				+ "Item description: " + description + "\r\n" + "Weight: " + weight + "lbs";
		
		return message;
	}
}
