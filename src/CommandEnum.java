
public enum CommandEnum {
	NULL("null"),
	QUIT("quit"),
	HELP("help"),
	GO("go"),
	STATUS("status"),
	BACK("back"),
	LOOK("look"), 
	EXAMINE("examine");

	/* text stores the string that would be entered by the person playing the game. */
	private final String text;
	
	private CommandEnum(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
}
