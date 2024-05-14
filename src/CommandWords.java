import java.util.HashMap;
/**
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognize commands as they are typed in.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */

public class CommandWords {
	/** A hashmap that holds all valid command words. keys are names of commands (type String), values are commands from the command enum */
	private static HashMap<String, CommandEnum> validCommands = new HashMap<String, CommandEnum>();

	/**
	 * Static block to put commandEnums and their names into the valid commands hashmap
	 */
	static {
		validCommands.put(CommandEnum.QUIT.getText(), CommandEnum.QUIT);
		validCommands.put(CommandEnum.HELP.getText(), CommandEnum.HELP);
		validCommands.put(CommandEnum.GO.getText(), CommandEnum.GO);
		validCommands.put(CommandEnum.STATUS.getText(), CommandEnum.STATUS);
		validCommands.put(CommandEnum.BACK.getText(), CommandEnum.BACK);
		validCommands.put(CommandEnum.LOOK.getText(), CommandEnum.LOOK);
	}

	/** 
	 * Check whether a given String is a valid command word.
	 *
	 * @param aString The string to determine whether it is a valid command.
	 * @return true if a given string is a valid command, false if it isn't.
	 */
	public static boolean isCommand(String aString) {
		return validCommands.containsKey(aString);
	}
	
	/**
	* Converts a String into a CommandEnum object.
	*
	* @param theString The String containing the command word.
	* @return The CommandEnum object representing the command, or null if the command does
	,→ not exist.
	*/
	public static CommandEnum getCommand(String theString) {
			return validCommands.get(theString);
	}
		
	}

