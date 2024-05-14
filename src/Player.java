
/**
 * @author AmirSamadian
 * This is the player character
 */
public class Player {
	/**
	 * This is the player's current room
	 */
	private Room currentRoom;
	/** This stores the room the player just came from. */
	private Room previousRoom;
	/** health keeps track of the players health and starts at 100. */
	private int health = 100;

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @param currentRoom
	 * This is the constructor of the Player class
	 */
	public Player(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	/**
	 * @return the currentRoom 
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * @param currentRoom the currentRoom to set
	 * 
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.previousRoom = this.currentRoom;
		this.currentRoom = currentRoom;
	}

	
	/**
	 * @return the previousRoom
	 */
	public Room getPreviousRoom() {
		return previousRoom;
	}

	/**
	 * @param previousRoom the previousRoom to set
	 */
	public void setPreviousRoom(Room previousRoom) {
		this.previousRoom = previousRoom;
	}
	

}
//Amir is stupid
