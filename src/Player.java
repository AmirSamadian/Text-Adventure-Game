import java.util.HashMap;
/**
 * Contains fields and methods associated with a player object.
 * 
 * @author AmirSamadian
 * This is the player character
 */
public class Player {
	/**
	 * This is the player's current room.
	 */
	private Room currentRoom;
	/** This stores the room the player just came from. */
	private Room previousRoom;
	/** health keeps track of the players health and starts at 100. */
	private int health = 100;
	/** constant variable that tells the max amount of weight the player can even lift. */
	private final double maxLiftWeight = 300;
	/** constant variable that tells the max carrying capacity of the player. How much will exceed inventory limit. */
	private final double maxCarryWeight = 50; 
	/** variable that stores the amount of weight the player is carrying. */
	private double carryWeight = 0;
	 
	/** HashMap storing all the items in the player's inventory. */
	private HashMap<String, Item> inventory;
	
	
	/**
	 * This is the constructor of the Player class.
	 * @param currentRoom  
	 * 
	 */
	public Player(Room currentRoom) {
		this.currentRoom = currentRoom;
		this.inventory = new HashMap<String, Item>();
	}
	
	/**
	 * getter for health field.
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * setter for health field.
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
 

	
	/**
	 * gets the player's current room.
	 * @return the currentRoom 
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * sets current room to param and previous room to the previous value of current room.
	 * 
	 * @param currentRoom the currentRoom to set
	 * 
	 */
	public void setCurrentRoom(Room currentRoom) {
		//sets the previous room to the room that the player is currently in
		this.previousRoom = this.currentRoom;
		//sets cuentRoom to the room they're going to (the parameter).
		this.currentRoom = currentRoom;
	}

	
	/**
	 * A getter for the previous room field.
	 * @return the previousRoom (type Room)
	 */
	public Room getPreviousRoom() {
		return previousRoom;
	}

	/**
	 * A setter for the previous room field.
	 * @param previousRoom the previousRoom to set
	 */
	public void setPreviousRoom(Room previousRoom) {
		this.previousRoom = previousRoom;
	}
	
	
	/**
	 * getter for carryWeight.
	 * @return the carryWeight
	 */
	public double getCarryWeight() {
		return carryWeight;
	}
	
	/**
	 * setter for carryWeight.
	 * @param weight : the new carryWeight
	 */
	public void setCarryWeight(double weight) {
		this.carryWeight = weight;
	}
	
	/**
	 * getter for maxLiftWeight.
	 * @return the maxLiftWeight
	 */
	public double getMaxLiftWeight() {
		return maxLiftWeight;
	}

	/**
	 * getter for maxCarryWeight.
	 * @return the maxCarryWeight
	 */
	public double getMaxCarryWeight() {
		return maxCarryWeight;
	}

	/**
	 * Checks if you're able too add an item to the inventory by checking how it would change your carryWeight. 
	 * If new weight is less than or equal to max carry weight, item is added to inventory, carry weight is updated and true is returned.
	 * If exceeds max weight, return false and give a message saying what happened.
	 * @param item is the item we want to add
	 * @retun true if the item meets the weight requirement and was able to be added to the inventory.
	 */
	public boolean addItemToInventory(Item item) {
		double newWeight = carryWeight + item.getWeight();
		if (item.getWeight() > maxLiftWeight) {		//300lbs is the most ryderFalcone can pick up
			Writer.println("You can't lift that lil bro. Too heavy");
			return false;
		}
		else if (newWeight <= maxCarryWeight) {
			inventory.put(item.getName(), item);    //adds the item to the player's inventory
			carryWeight = newWeight; //updates the carryWeight to include the weight of the item just added
			Writer.println(item.getName() + " was successfully added to your inventory!");
			return true;
		}	
		else {
			Writer.println("Can't add " + item.getName() + ". This item will exceed your carrying capacity of 50 lbs.");
			return false;
			
		}
	}

	
	/**
	* Gets an item from the inventory with the name that was passed to the method.
	* @param itemName The name of the item.
	* @return The item with that name. Null if not in the inventory
	*/
	public Item getItem(String itemName) {
		return inventory.get(itemName);
	}
	
	/**
	* removes the key, value pair associated with the item whose name is itemName. removes from inventory.
	* @param itemName The name of the item we want to remove.
	* @return The item that we are removing. Null if no mapping to that key.
	*/
	public Item removeItem(String itemName) {
		double itemWeight = inventory.get(itemName).getWeight(); 	//gets the item's weight from it's name.
		carryWeight = carryWeight - itemWeight; 	//updates carryWeight by subtracting the items weight from it.
		return inventory.remove(itemName); 		//removes the item from the inventory hashmap and returns the removed item. Null if no mapping.
	}
	
	/** 
	 * This is a simple version of removeItem. It just takes the item out of the inventory without handling anything with weight.
	 * Functions that use this method will handle the weight when appropriate.
	 * @param itemName is the name of the item being removed.
	 * @return returns the item removed.
	 * */
	public Item removeItem2(String itemName) {	//simple version. no weight handling
		return inventory.remove(itemName); 		//removes the item from the inventory hashmap and returns the removed item. Null if no mapping.
	}
	
	
	/**
	* Returns a string description of the list of items in the player's inventory.
	* For example,
	* These are the items in your inventory:
	* silenced pistol
	* phone
	* scanner
	*
	* @return A string showing the list of items in the room.
	*/
	public String getItemsInInventoryString() {
		String itemsMessage = "These are the items in your inventory: \r\n";
		if (inventory.isEmpty()) {
			itemsMessage = "There are no items in your inventory";
		} 
		else {
			for (String item : inventory.keySet()) {
				itemsMessage += item + " \r\n";
			}
		}
		return itemsMessage;
	}
	
	
}

