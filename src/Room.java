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
	
	
	/** This HashMap maps a string to a door that will be an exit of a room. */
	protected HashMap<String,Door> exits;

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
	 * @return the points you get for entering a room
	 * @return the temp variable which 
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
	* Defines an exit from this room.
	*
	* @param direction The direction of the exit.
	* @param neighbor The door in the given direction.
	*/
	public void setExit(String direction, Door neighbor) {
		exits.put(direction, neighbor);
	}
	
	/**
	* Gets a door in a specified direction if it exists.
	*
	* @return The door in the specified direction or null if it does not exist.
	*/
	public Door getExit(String direction) {
		return exits.get(direction);
	}
	
	
	/**
	* Returns a string description of a Room's exits.
	* For example,
	* Exits: 
	* Choice A: Zoo
	* Choice B: Sewer 
	* Choice C: Casino
	*
	* @return A string showing the possible exits of a room.
	*/
	public String getExitString() {
		String exitMessage = "Where to go?  \r\n";
		for (String currentDirection : exits.keySet()) {
			exitMessage += currentDirection + " \r\n";
		}
		
		return exitMessage;
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
				+ description + "\r\n" + getExitString();
		
		return message;
		
	}
	
}
