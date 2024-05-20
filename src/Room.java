import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via doors. The doors are labeled north, east, south, west.
 * For each direction, the room stores a reference to an instance of door.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class Room {
	/** Counter for the total number of rooms created in the world. */
	private static int counter;
	/** The name of this room.  Room names should be unique. */
	private String name;
	/** The description of this room. */
	private String description;
	/** This stores the number of points someone gets for entering this room. */
	private int points;
	/** healthChange contains the amount of health that a Room will add or subtract from the player's health. */
	protected int healthChange;
	
	
	/** This HashMap maps a string to a door that will be an exit of a room. */
	protected HashMap<String,Door> exits;
	/** This HashMap maps a string to an Item. Collection of items stored in this room. */
	protected HashMap<String,Item> items;

	/**
	 * Static initializer.
	 */
	static {
		counter = 0;
	}
	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 *
	 * @param name  The room's name.
	 * @param description
	 *            The room's description.
	 *            
	 * @param points : The amount of points a player gets for entering each room is set by the constructor
	 */
	public Room(String name, String description, int points) {
		this.name = name;
		this.description = description;
		this.points = points;
		this.exits = new HashMap<String, Door>();
		this.items = new HashMap<String, Item>();
		counter++;
	}

	/**
	 * Returns the name of this room.
	 *
	 * @return The name of this room.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of this room.
	 *
	 * @return The description of this room.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the number of rooms that have been created in the world.
	 * @return The number of rooms that have been created in the world.
	 */
	public static int getCounter() {
		return counter;
		
	}
	

	/**
	 * getter for the points variable. Also makes sure that you only get points the first time you enter a room. If second time, gives 0.
	 * 
	 * @return the points you get for entering a room. Usually points gives 0.
	 * @return temp variable which returns number of points you get for entering a room. Usually a nonzero value.
	 */
	public int getPoints() {
		if (points > 0) {
			int temp = points;
			points = 0;
			return temp;
		}
		return points;
	}

	/**
	 * Setter for points variable. 
	 * 
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * returns the amount of health change that this room causes for a player.
	 * @return the healthChange
	 */
	public int getHealthChange() {
		return healthChange;
	}

	/**
	 * sets the health change for a room.
	 * @param healthChange the healthChange to set
	 */
	public void setHealthChange(int healthChange) {
		this.healthChange = healthChange;
	}
	
	/**
	* adds an item to the items hashmap.
	*
	* @param itemName The name of the item.
	* @param item The item that has that name that we're putting in the hashmap.
	*/
	public void addItem(String itemName, Item item) {
		items.put(itemName, item);
	}
	
	/**
	* Gets an item from the room with the name that was passed to the method.
	* @param itemName The name of the item.
	* @return The item with that name.
	*/
	public Item getItem(String itemName) {
		return items.get(itemName);
	}
	
	/**
	* removes the key, value pair associated with the item whose name is itemName.
	* @param itemName The name of the item we want to remove.
	* @return The item that we are removing. Null if no mapping to that key.
	*/
	public Item removeItem(String itemName) {
		return items.remove(itemName);
			
	}
	
	
	/**
	* Defines an exit from this room.
	*
	* @param direction The direction of the exit.
	* @param door The door in the given direction.
	*/
	public void setExit(String direction, Door door) {
		exits.put(direction, door);
	}
	
	/**
	* Gets a door in a specified direction if it exists.
	*
	* @param direction the direction a door is located.
	* @return The door in the specified direction or null if it does not exist.
	*/
	public Door getExit(String direction) {
		return exits.get(direction);
	}
	
	
	/**
	* Returns a string description of a Room's exits.
	* For example,
	* Where to go?
	* Choice A: Zoo
	* Choice B: Sewer 
	* Choice C: Casino
	*
	* @return A string showing the possible exits of a room.
	*/
	protected String getExitString() {
		String exitMessage = "Where to go?  \r\n";
		for (String currentDirection : exits.keySet()) {
			exitMessage += currentDirection + " \r\n";
		}
		
		return exitMessage;
	}
	
	/**
	* Returns a string description of the list  of items in a room.
	* For example,
	* This room contains the following:
	* couch
	* body
	* necklace
	*
	* @return A string showing the list of items in the room.
	*/
	protected String getItemsInRoomString() {
		String itemsMessage = "This room contains the following: \r\n";
		if (items.isEmpty()) {
			itemsMessage = "There are no items in this room. \r\n \r\n";
		} 
		else {
			for (String item : items.keySet()) {
				itemsMessage += item + " \r\n";
			}
		}	
		return itemsMessage;
	}
	
	/**
	* Returns a string description including all the details of a Room.
	* For example,
	* Outside:
	* You are outside in the center of the King's College campus.
	* Exits: north east south west
	*
	* @return A string representing all the details of a Room.
	*/
	public String toString() {
		
		String message = name + ": " + "\r\n"
				+ description + "\r\n" + getItemsInRoomString() + "\r\n" + getExitString();
		
		return message;
		
	}
	
}
