
/**
 * @author AmirSamadian
 * This is the player character
 */
public class Player {
	/**
	 * This is the player's current room
	 */
	private Room currentRoom;

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
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	

}
//Amir is stupid
