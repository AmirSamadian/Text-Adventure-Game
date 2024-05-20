
public enum CommandEnum { 
	/** Instance of type CommandEnum representing the quit command. */
	QUIT("quit"),
	/** Instance of type CommandEnum representing the help command. */
	HELP("help"),
	/** Instance of type CommandEnum representing the go command. */
	GO("go"),
	/** Instance of type CommandEnum representing the status command. */
	STATUS("status"),
	/** Instance of type CommandEnum representing the back command. */
	BACK("back"),
	/** Instance of type CommandEnum representing the look command. */
	LOOK("look"), 
	/** Instance of type CommandEnum representing the examine command. */
	EXAMINE("examine"),
	/** Instance of type CommandEnum representing the take command. */
	TAKE("take"),
	/** Instance of type CommandEnum representing the drop command. */
	DROP("drop"),
	/** Instance of type CommandEnum representing the inventory command. */
	INVENTORY("inventory"),
	/** Instance of type CommandEnum representing the unlock command. */
	UNLOCK("unlock"),
	/** Instance of type CommandEnum representing the lock command. */
	LOCK("lock"),
	/** Instance of type CommandEnum representing the pack command. */
	PACK("pack"),
	/** Instance of type CommandEnum representing the unpack command. */
	UNPACK("unpack"),
	/** Instance of type CommandEnum representing the scan command. */
	SCAN("scan"),
	/** Instance of type CommandEnum representing the cweight command. */
	CWEIGHT("cweight");

	/** text stores the string that would be entered by the person playing the game. */
	private final String text;
	
	private CommandEnum(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	} 
	
}
