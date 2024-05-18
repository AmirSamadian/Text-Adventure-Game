
public class WrongRoom extends Room{
	
	/*
	 * Constructor for the WrongRoom
	 */
	public WrongRoom(String name, String description, int points, int healthChange) {
		super(name, description, points);
		this.healthChange = healthChange;
	}
	
	/*
	 * Overriden version of getexitString from Room. 
	 * It tells the player that there are no new exits so they have to go back.
	 */
	public String getExitString() {
		String exitMessage = "Where to go?  \r\n" 
	+ "You're not supposed to be here, Go back";
		
		return exitMessage;
	}
}
