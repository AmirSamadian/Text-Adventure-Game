
public class CorrectRoom extends Room {

	/** Contains the text that will be displayed when the scanner item is used. */
	private String scannerHint;
	
	public CorrectRoom(String name, String description, int points) {
		super(name, description, points);
		this.healthChange = 0;
		// TODO Auto-generated constructor stub
	}
	
	
	/** 
	 * boolean method that returns false if the room is not of type correctRoom and true if it is a correctRoom. 
	 * overridden version of isCorrectRoom in the correctRoom subclass to return true.
	 * @return true because this is a correct room.
	 * */
	public boolean isCorrectRoom() {
		return true;
	}
	

	/**
	 * Getter for the hint message that should be displayed when a scanner is used.
	 * @return the scannerHint
	 */
	public String getScannerHint() {
		return scannerHint;
	}

	/**
	 * Setter for the hint message that should be displayed when a scanner is used.
	 * @param scannerHint the scannerHint to set
	 */
	public void setScannerHint(String scannerHint) {
		this.scannerHint = scannerHint;
	}

	
	/* This is an overriden version of getExitStriing from the Room class. 
	 * exit would be a direction string from the HashMap containing the doors. 
	 * For these rooms, exit will always be a letter (A, B, C, D, or E). Most of the time it's 3 options. 
	 * There's only 1 room with 4 and 1 room with 5.
	 * */
	public String getExitString() {
		String exitMessage = "Where to go?  \r\n";
		for (String exit : exits.keySet()) {
			exitMessage += "Choice " + exit + ": " + exits.get(exit).getDestination().getName() + " \r\n";
		}
		
		exitMessage += "Pick: ";
		for (String exit : exits.keySet()) {
			exitMessage += exit + " ";
		}
		
		return exitMessage;
	}
	
	
}
