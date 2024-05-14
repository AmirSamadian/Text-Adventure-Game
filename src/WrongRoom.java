
public class WrongRoom extends Room{
	
	/** damage contains the amount of health that a WrongRoom will subtract from the player's health. */
	private int healthChange;

	
	/*
	 * Constructor for the WrongRoom
	 */
	public WrongRoom(String name, String description, int points, int healthChange) {
		super(name, description, points);
		this.healthChange = healthChange;
	}


	/**
	 * getter for the healthChange field.
	 * @return the healthChange
	 */
	public int getHealthChange() {
		return healthChange;
	}


	/**
	 * @param healthChange the healthChange to set
	 */
	public void setHealthChange(int healthChange) {
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
