/**
 * Class Door - a door or portal between two Rooms in an adventure game.
 *
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * A "Door" represents a door or portal between two locations of the game.
 * It stores a reference to the neighboring room and whether that door
 * or portal is locked.  Doors are not locked by default.
 *
 * @author Maria Jump
 * @author Amir Samadian
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University.
 */
public class Door {  

	/** The room that this door leads to. */
	private Room destination;
	/** Whether this door is locked. */
	private boolean locked;
	/** The name of the key item that unlocks this door. */
	private String keyName;
	

	/**
	 * Constructor for the Door class.
	 * @param destination The room this door leads to
	 */
	public Door(Room destination) {
		this.destination = destination;
		this.locked = false;
	}
 
	/**
	 * A getter for the room this door leads to.
	 * @return The room this door leads to
	 */
	public Room getDestination() {
		return destination;
	}

	/**
	 * A getter for whether this door is locked.
	 * @return Whether this door is locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * A setter for whether this door is locked.
	 * @param locked Whether this door is locked.
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * getter for key.
	 * @return the key
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * setter for key.
	 * @param keyName the name of the key to set.
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	
}
