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
	/** This stores the number of points someone gets for entering this room */
	private int points;
	
	
	/** This room's north exit, null if none exits. */
	public Door northExit;
	/** This room's south exit, null if none exits. */
	public Door southExit;
	/** This room's east exit, null if none exits. */
	public Door eastExit;
	/** This room's west exit, null if none exits. */
	public Door westExit;

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
	 * @return the points you get for entering a room
	 * @return the temp variable which 
	 * Getter for points variable
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
	 * @param points the points to set
	 * Setter for points variable
	 */
	public void setPoints(int points) {
		this.points = points;
	}
}
