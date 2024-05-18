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

		if (command.isUnknown()) {
			Writer.println("That's not a valid command. Try again.");
		}
		
		else {
			
			switch (command.getCommandWord()) {
			
			case QUIT:
				wantToQuit = quit(command);
				break;
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
				back();
				break;
			case EXAMINE:
				examine(command);
				break;
			case TAKE:
				take(command);
				break;
			case DROP:
				drop(command);
				break;
			case INVENTORY:
				displayInventory();
				break;
				
				
			default:
				Writer.println("Sorry, this command is not implemented yet.");
				
			}
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
				score += newRoom.getPoints(); 	//This is where the score is updated.
				
				int newHealth = ryderFalcone.getHealth() + newRoom.getHealthChange();  //The health changes for wrongRooms will be negative so should use plus.
				ryderFalcone.setHealth(newHealth);
				
				printLocationInformation();
			}
		}
	}

	
	/**
	 * Prints out the locations description.
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
	
	private void back() {
		Room prev = ryderFalcone.getPreviousRoom();
		if (prev != null) {
			ryderFalcone.setCurrentRoom(prev);
			printLocationInformation();
		}
		else {
			Writer.println("You can't go back!");
		}
	}
	
	
	/** 
	 * 
	 * @param command is the full command that the player typed in. First word should be the drop commandEnum. 
	 * */
	private void drop(Command command) {
	    if (!command.hasSecondWord()) {
	        // If there is no second word, we don't know what to drop so we display the message.
	        Writer.println("What do you want to drop?");
	    } else {
		    String itemName = command.getRestOfLine(); //The rest of the line in the command should be the item name.
	        Item item = ryderFalcone.getItem(itemName);  // Retrieve the item from the player's inventory

	        if (item == null) {
	            Writer.println("You don't have " + itemName + " in your inventory.");
	        } else {
	            ryderFalcone.removeItem(itemName);   // Remove the item from inventory. removeItem handles weight.
	            ryderFalcone.getCurrentRoom().addItem(itemName, item);  // Add the item to the current room
	            Writer.println("You have successfully dropped the " + itemName + " from your inventory. The item is now in the room.");
	        }
	    }
	}
	
	
	/** 
	 * Note: The cases from the GWT that deal with weight capacity are handled by the addItemToInventory method.
	 * @param command is the full command that the player typed in. First word should be the take commandEnum. 
	 * */
	private void take(Command command) {	    
		if (!command.hasSecondWord()) {
			// If there is no second word, we don't know what to drop so we display the message.
			Writer.println("What do you want to take?");
	    } else {
			String itemName = command.getRestOfLine(); //The rest of the line in the command should be the item name.
	        Item item = ryderFalcone.getCurrentRoom().getItem(itemName);  // Retrieve the item from the room.

	        if (item == null) {
	            Writer.println(itemName + " isn't in this room.");
	        } else {
	        	//This method handles the case where the item makes it so weight limit is exceeded.
	        	//It displays a message for when weight is exceeded. A different message if the item was successfully added. Message displaying done by addItemToInventory
	        	//It also updates the curent weight of the inventory (carryWeight)
	            if (ryderFalcone.addItemToInventory(item)) { //still calls the function but also checks if true 
	            	ryderFalcone.getCurrentRoom().removeItem(itemName);	//removes the item from the room.
	            }
	        }
	        
	    }
	}

	
	private void examine(Command command) {    
		if (!command.hasSecondWord()) {
			// If there is no second word, we don't know what to drop so we display the message.
			Writer.println("What do you want to examine?");
		} else {
			String itemName = command.getRestOfLine(); //The rest of the line in the command should be the item name.
			// If the item we want to examine is in the room, retrieve the item from the room.
			Item roomItem = ryderFalcone.getCurrentRoom().getItem(itemName);  
			// If the item we want to examine is in the playe's inventory, retrieve the item from the inventory.
			Item inventoryItem = ryderFalcone.getItem(itemName);  
			
			if ((roomItem == null) && (inventoryItem == null)) {
	            Writer.println(itemName + " isn't in this room or in you inventory.");
			} 
			else if (roomItem != null) { 		//The item is in the room
				Writer.println(roomItem.toString()); 
			}
			else {								//The item is in the inventory
				Writer.println(inventoryItem.toString());
				Writer.println("By the way, this item is in your inventory.");
			}
		}
	}
	
	/** Displays the list of items in the player's inventory by calling the getItemsInInventoryString method from Player class. */
	private void displayInventory() {
		Writer.println(ryderFalcone.getItemsInInventoryString());
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
		Writer.println("I see you're confused. This is normal for a detective. Maybe this will help: ");
		Writer.println("Here's a list of all available commands: \r\n");
		
		String message = "";
		CommandEnum[] list = CommandEnum.values();
		for (CommandEnum command : list) {
			message += command.getText() + "  ";
		}
		Writer.println(message);
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
