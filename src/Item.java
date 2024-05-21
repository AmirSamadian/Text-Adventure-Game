/** 
 * It contains all information and methods related to items. superclass of container.
 * @author Amir Samadian
 * */
public class Item {
	
	/** stores the name of an item. */
	protected final String name;
	
	/** stores the description of an item. */
	protected String description;
	
	/** stores the point value of an item. */
	protected int pointValue;
	
	/** stores the weight of an item. */
	protected double weight;

	/**
	 * constructor for the Item class.
	 * 
	 * @param name
	 * @param description
	 * @param pointValue
	 * @param weight
	 */
	public Item(String name, String description, int pointValue, double weight) {
		this.name = name;
		this.description = description;
		this.pointValue = pointValue;
		this.weight = weight;
	}
	
	
	/**
	 * getter for an item's name.
	 * @return the item name
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for an item's description. 
	 * @return the item description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * setter for the description field.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * getter for an item's point value. 
	 * Makes sure that you only get the points for an item the first time you grab it. Once points is used, it gets reset to 0, so you can't rack up points.
	 * @return the pointValue
	 */
	public int getPointValue() {
		if (pointValue > 0) {
			int temp = pointValue;
			pointValue = 0;
			return temp;
		}
		return pointValue;
	}


	/**
	 * getter for an item's weight.
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/** 
	 * If an item is just an item, it will return false. This is overriden in the container subclass to return true if the item is a container. 
	 * @return false
	 * */
	public boolean isContainer() {
		return false;
	}
	
	/**
	* Returns a string description including all the details of an Item.
	* For example,
	* 
	* @return A string representing all the details of an Item.
	*/
	public String toString() {
		
		String message = name + ": " + "\r\n"
				+ "Item description: " + description + "\r\n" + "Weight: " + weight + "lbs";
		
		return message;
	}
}
