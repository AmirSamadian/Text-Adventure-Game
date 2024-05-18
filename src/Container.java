import java.util.HashMap;

/** Items that contain other other items in them are containers. */
public class Container extends Item {
	
	/** HashMap storing all the items that are in this container. */
	private HashMap<String, Item> containerItems;

	/** Constructor for the container class. 
	 * @param name
	 * @param description
	 * @param pointValue
	 * @param weight
	 * 
	 * */
	public Container(String name, String description, int pointValue, double weight) {
		super(name, description, pointValue, weight);
		this.containerItems = new HashMap<>();
		// TODO Auto-generated constructor stub
	}
	
	/** 
	 * adds an item to the container and updates the container weight.
	 * @param item
	 * */
	public void addItem(Item item) {
		 containerItems.put(item.getName(), item);	//adds item to the hashmap
		 this.weight += item.getWeight();	//updates the weight of the container to include the weight of all items in it.
	}
	
	/**
	* removes the key, value pair associated with the item whose name is name. removes from containerItems.
	* @param name The name of the item we want to remove.
	* @return The item that we are removing. Null if no mapping to that key.
	*/
	public Item removeItem(String name) {
		double itemWeight = containerItems.get(name).getWeight(); 	//gets the item's weight from it's name.
		this.weight -= itemWeight; 	//updates the container's weight by subtracting the items weight from it.
		return containerItems.remove(name); 		//removes the item from the containerItems hashmap and returns the removed item. Null if no mapping
	}
	
	
	/**
	* Returns a string description of the list of items in the container.
	* For example,
	* These are the items in this chest:
	* key1
	* key2
	* key3
	*
	* @return A string showing the list of items in the container.
	*/
	public String getItemsInContainerString() {
		String itemsMessage = "These are the items in this " + name + ": \r\n";
		if (containerItems.isEmpty()) {
			itemsMessage = "There's nothing in this thing.";
		} 
		else {
			for (String item : containerItems.keySet()) {
				itemsMessage += item + " \r\n";
			}
		}
		return itemsMessage;
	}
	
	/**
	* Returns a string description including all the details of a container item.
	* 
	* @return A string representing all the details of a container.
	*/
	public String toString() {
		String message = name + ": " + "\r\n" 
				+ "Item description: " + description + "\r\n" + "Weight: " + weight + "lbs \r\n" 
				+ getItemsInContainerString();
		
		return message;
	}

}
