
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
	EXAMINE("examine");

	/** text stores the string that would be entered by the person playing the game. */
	private final String text;
	
	private CommandEnum(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	} 
	
}
