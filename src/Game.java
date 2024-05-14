/**
 * This class is the main class of the "Campus of Kings" application.
 * "Campus of Kings" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * This game class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Maria Jump
 * @author Amir Samadian
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */

//test

public class Game {
	/** The world where the game takes place. */
	private World world;
	/** ryderFalcone is the name of the Game's player. */
	private Player ryderFalcone;
	/** Variable turn keeps track of the number of turns made in the game. */
	private int turn = 0;
	/** Variable score keeps track of the total scare. Tally of all the points gained throughout the game */
	private int score = 0;
	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		world = new World();
		// set the starting room
		ryderFalcone = new Player(world.getRoom("Ryder Falcone’s Mansion’s - Living Room & Kitchen"));
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main game loop. Here we repeatedly read commands and
		// execute them until the game is over.
		boolean wantToQuit = false;
		while (!wantToQuit) {
			Command command = Reader.getCommand();
			wantToQuit = processCommand(command);
			// other stuff that needs to happen every turn can be added here.
			
			turn++;
		}
		printGoodbye();
	}
	
	/**
	 * Prints out the current location's full description and exits.
	 */
	private void printLocationInformation() {
		
		Writer.println(ryderFalcone.getCurrentRoom().toString());
		Writer.println();
		
	}
	///////////////////////////////////////////////////////////////////////////
	// Helper methods for processing the commands

	/**
	 * Given a command, process (that is: execute) the command.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		
		switch (command.getCommandWord()) {
		
			case QUIT:
				wantToQuit = quit(command);
			case HELP:
				printHelp();
				break;
			case GO:
				goRoom(command);
				break;
			case LOOK:
				look();
				break;
			case STATUS:
				status();
				break;
			case BACK:
				Writer.println(CommandEnum.BACK.getText() + " is not implemented yet!");
				printHelp();
				break;
		
			default:
				Writer.println("That's not a valid command. Try again.");
				
		}
		
		return wantToQuit;
	}

	///////////////////////////////////////////////////////////////////////////
	// Helper methods for implementing all of the commands.
	// It helps if you organize these in alphabetical order.

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			Writer.println("Go where?");
		} else {
			String direction = command.getRestOfLine();

			// Try to leave current.
			Door doorway = null;
			doorway = ryderFalcone.getCurrentRoom().getExit(direction);
			
			if (doorway == null) {
				Writer.println("There is no door!");
			} else {
				Room newRoom = doorway.getDestination();
				ryderFalcone.setCurrentRoom(newRoom);
				score += newRoom.getPoints();
				printLocationInformation();
			}
		}
	}

	
	/**
	 * Prints out the location information.
	 */
	private void look() {
		printLocationInformation();
	}
	
	private void status() {
		Writer.println("Here is your current status: ");
		Writer.println("  - Current Score: " + score);
		Writer.println("  - Turns used so far: " + turn);
		Writer.println("  - Description of your current location: " + ryderFalcone.getCurrentRoom().getDescription());
		
	}
	
	
	/**
	 * Print out the closing message for the player.
	 */
	private void printGoodbye() {
		Writer.println("I hope you weren't too bored here on the Campus of Kings!");
		Writer.println("Thank you for playing.  Good bye.");
		Writer.println("You have earned " + score + " points in " + turn + " turns");
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		Writer.println("You are lost. You are alone. You wander");
		Writer.println("around at the university.");
		Writer.println();
		Writer.println("Your command words are:");
		Writer.println("   go quit help");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		Writer.println();
		Writer.println("Welcome to the Campus of Kings!");
		Writer.println("Campus of Kings is a new, incredibly boring adventure game.");
		Writer.println("Type 'help' if you need help.");
		Writer.println();
		printLocationInformation();
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		boolean wantToQuit = true;
		if (command.hasSecondWord()) {
			Writer.println("Quit what?");
			wantToQuit = false;
		}
		return wantToQuit;
	}
}
