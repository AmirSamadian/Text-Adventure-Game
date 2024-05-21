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
		boolean isDead = false;
		while (!wantToQuit && !isDead) {
			Command command = Reader.getCommand();
			wantToQuit = processCommand(command);
			if (ryderFalcone.getHealth() <= 0) {		//checks if ryderFalcone is dead. If so, isDead is changed to true.
				isDead = true;
				Writer.println();
				Writer.println("YOU DIED	\r\n \r\n");
			}
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
			case LOCK:
				lock(command);
				break;
			case UNLOCK:
				unlock(command);
				break;
			case PACK:
				pack(command);
				break;
			case UNPACK:
				unpack(command);
				break;
			case CWEIGHT:
				containerWeight(command);
				break;
			case SCAN:
				scan();
				break;
			case ESCAPE:
				escape();
				break;
			case SHOOT:
				shoot(command);
				break;
			case FLASH:
				flash();
				break;
			case ATTACK:
				attack();
				break;
			case DEACTIVATE:
				wantToQuit = deactivate();
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
			} 
			else if (doorway.isLocked() == true) {
				Writer.println("The door to that location is locked. Get the appropriate key to unlock it");
			} else {
				Room newRoom = doorway.getDestination();
				ryderFalcone.setCurrentRoom(newRoom);
				score += newRoom.getPoints(); 	//This is where the score is updated for points that come from rooms.
				
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
		Writer.println("  - Current Health: " + ryderFalcone.getHealth());
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
	 * removes an item from player inventory and puts it into the room.
	 * @param command is the full command that the player typed in. First word should be the drop commandEnum. 
	 */
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
		Writer.println("you are carrying " + ryderFalcone.getCarryWeight() + "lbs");
	}
	
	/** 
	 * All cases covered in GWT file. Main purpose is to set an unlocked door to locked when appropriate key in player inventory.
	 * @param command is the command that was entered that included command word lock.
	 * */
	private void lock(Command command) {
		if (!command.hasSecondWord()) {
			// If there is no second word, we don't know what to unlock so we display the message.
			Writer.println("What do you want to unlock?");
		} else {
			String direction = command.getRestOfLine();	//string after command word lock
			// Try to leave current.
			Door doorway = null;
			doorway = ryderFalcone.getCurrentRoom().getExit(direction);	//get the door that leads to next room
			
			if (doorway == null) {
				Writer.println("There is no door!");
			} 
			else if (doorway.isLocked() == true) {
				Writer.println("That door is already locked.");
			}
			else if (doorway.getKeyName() == null) {
				Writer.println("You can't unlock this door/path. There's no key for it.");
			}
			else {					//There is a door, it's unlocked, and a key exists to lock it.
				Writer.println("Enter the key name");		//re-prompt user to give us key name.
				String response = Reader.getResponse();
				if (!response.equals(doorway.getKeyName())) {
					Writer.println("That's not the correct key's name");
				} else {
					if (ryderFalcone.getItem(doorway.getKeyName()) == null) {	//checks if player has key in their inventory
						Writer.println("You don't have the key.");
					} else {
						doorway.setLocked(true);
						Writer.println("The door has been locked.");
					}	
				}
			}
		}
	}
	
	/** 
	 * All cases covered in GWT file. Main purpose is to set a locked door to unlocked when appropriate key is in player inventory.
	 * @param command is the command that was entered that included command word unlock.
	 * */
	private void unlock(Command command) {
		if (!command.hasSecondWord()) {
			// If there is no second word, we don't know what to unlock so we display the message.
			Writer.println("What do you want to unlock?");
		} else {
			String direction = command.getRestOfLine();	//string after command word lock
			// Try to leave current.
			Door doorway = null;
			doorway = ryderFalcone.getCurrentRoom().getExit(direction);	//get the door that leads to next room
			
			if (doorway == null) {
				Writer.println("There is no door!");
			} 
			else if (doorway.isLocked() == false) {
				Writer.println("That door is already unlocked.");
			}
			else {					//There is a door and it's locked.
				Writer.println("Enter the key name");		//re-prompt user to give us key name.
				String response = Reader.getResponse();
				if (!response.equals(doorway.getKeyName())) {
					Writer.println("That's not the correct key's name. Try again, check for typos.");
				} else {
					if (ryderFalcone.getItem(doorway.getKeyName()) == null) {	//checks if player has key in their inventory
						Writer.println("You don't have the key.");
					} else {
						doorway.setLocked(false);
						Writer.println("The door has been unlocked. You may now enter.");
					}	
				}
			}
		}	
	}
	
	
	/** 
	 * All cases in the GWT txt file. Supposed to remove an item from room or inventory and add it to a specified container.
	 * @param command is the command that was entered containing the command word pack. May have more words
	 * 
	*/
	private void pack(Command command) {
		// Check if the command has a second word
	    if (!command.hasSecondWord()) {
	        Writer.println("What do you want to pack?");
	        return; // End function if no second word
	    }

	    String itemName = command.getRestOfLine();
	    Item item = ryderFalcone.getItem(itemName);
	    boolean itemInRoom = false;		//this boolean is used to more conveniently check if the item is in the room or inv.

	    // Try to find the item in the current room if it's not in the inventory
	    if (item == null) {
	        item = ryderFalcone.getCurrentRoom().getItem(itemName);
	        itemInRoom = true;
	    }

	    // If the item is not found in both inventory and room
	    if (item == null) {
	        Writer.println("You don't have it. It's not in your inventory or at your current location.");
	        return; // End function if item not found
	    }

	    // Check if the item is too heavy to lift
	    if (item.getWeight() > ryderFalcone.getMaxLiftWeight()) {
	        Writer.println("You can't lift that lil bro. Too heavy.");
	        return; // End function if item is too heavy
	    }

	    Writer.println("What container do you want to pack " + itemName + " into.");
	    String containerName = Reader.getResponse();
	    Item container = ryderFalcone.getItem(containerName);
	    boolean containerInRoom = false;		//this boolean is used to more conveniently check if the container is in the room or inv.

	    // Try to find the container in the current room if it's not in the inventory
	    if (container == null) {
	        container = ryderFalcone.getCurrentRoom().getItem(containerName);
	        containerInRoom = true;
	    }

	    // If the container is not found in both inventory and room
	    if (container == null) {
	        Writer.println("You can't see that container. It's not in the room or in your inventory.");
	        return; // End function if container not found
	    }

	    // Check if the item is a valid container
	    if (!container.isContainer()) {
	        Writer.println("That's not a container.");
	        return; // End function if the item is not a container
	    }
	    
	    double initialWeight = ryderFalcone.getCarryWeight();
	    
	    if (itemInRoom && !containerInRoom) {
	    	double newWeight = initialWeight + item.getWeight();
	    	if (newWeight > ryderFalcone.getMaxCarryWeight()) {
	    		 Writer.println("This will exceed your carrying capacity of 50 lbs.");
	    		 return;
	    	} else {
	    		((Container) container).addItem(item);
	    		ryderFalcone.getCurrentRoom().removeItem(itemName);
	    		ryderFalcone.setCarryWeight(newWeight);
	    		Writer.println(itemName + " was successfully packed into " + container.getName());
	    	}
	    } else if (!itemInRoom && !containerInRoom) {	//since the item and container both start out in inventory, there shouldn't be a case were exceeds carry weight.
	    	((Container) container).addItem(item);
	    	ryderFalcone.removeItem2(itemName);		//doesn't change inventory weight but removes from hashmap.
	    	//ryderFalcone.setCarryWeight(initialWeight); redundant
	    	 Writer.println(itemName + " was successfully packed into " + container.getName());
	    } else if (!itemInRoom && containerInRoom) {		//taking an item out of inv so should never exceed weight. But do have to subtract item weight.
	    	((Container) container).addItem(item);
	    	ryderFalcone.removeItem(itemName);	//this handles inventory weight.
	    	 Writer.println(itemName + " was successfully packed into " + container.getName());
	    } else if (itemInRoom && containerInRoom) {
	    	((Container) container).addItem(item);
    		ryderFalcone.getCurrentRoom().removeItem(itemName);
    		Writer.println(itemName + " was successfully packed into " + container.getName());
	    }
	}
	
	
	
	
	
	/** 
	 * All cases in the GWT txt file. Supposed to remove an item from a specified container and add it to the player's invenrtory.
	 * @param command is the command that was entered containing the command word unpack. May have more words
	 * */
	private void unpack(Command command) {
		if (!command.hasSecondWord()) {
			// If there is no second word, we don't know what to unpack so we display the message.
			Writer.println("What do you want to unpack?");
			return;
		} 
		
		String containerName = command.getRestOfLine();	//string after command word unpack
		Item container = ryderFalcone.getItem(containerName);	//if the container is not in inventory, container will be null.
		
		if (container == null) {
			container = ryderFalcone.getCurrentRoom().getItem(containerName);
		}
		if (container == null) {	//if this is null that means the container wasn't in the room either.
			 Writer.println("You can't see that container. It's not in the room or in your inventory.");
		     return; // End function if container not found
		}
		if (!container.isContainer()) {
			Writer.println("That's not a container.");
		    return; // End function if container not found
		}
		
		Writer.println("What item do you want to unpack from " + container.getName());
		String itemName = Reader.getResponse();
		//We already verified that container var is a Container so we can cast it so it can use getItem.
		Item item = ((Container) container).getItem(itemName); 
		
		if (item == null) {		
			Writer.println(itemName + " is not in the container.");
			return;
		}
		
		double currentWeight = ryderFalcone.getCarryWeight();
		
		//whenever an item is removed from a container and the container is in the inventory, want to remove the item's weight from carry weight.
		if (ryderFalcone.getItem(containerName) != null) {
			ryderFalcone.setCarryWeight(currentWeight - item.getWeight());
		}
		
		if (ryderFalcone.addItemToInventory(item)) { 		//adds item's weight to carry weight
			((Container) container).removeItem(itemName);		//removes item from container and subtracts item weight from container weight
		}
	}
	
	/** 
	 * This goes with the command word cweight. Displays a message revealing weight of container. It's helpful for testing code but also for playing the game so I keep it in. Doesn't create an easy cheat.
	 * @param command  
	 * */
	private void containerWeight(Command command) {
		if (!command.hasSecondWord()) {
			// If there is no second word, we don't know what to unpack so we display the message.
			Writer.println("no second word entered. What container do you want to check thee weight for?");
			return;
		} 
		String containerName = command.getRestOfLine();	//string after command word unpack
		Item container = ryderFalcone.getItem(containerName);
		if (container == null) {
			container = ryderFalcone.getCurrentRoom().getItem(containerName);
		}
		if (container == null) {	//if this is null that means the container wasn't in the room either.
			 Writer.println("You can't see that container. It's not in the room or in your inventory.");
		     return; // End function if container not found	
		}	
		Writer.println("the total weight of this container is " + container.getWeight());
	}
 
	
	
	/** 
	 * Function for the command scan. Follows scenarios outlined in GWT's.
	 * */
	private void scan() {
		if (ryderFalcone.getItem("scanner") == null) {
			Writer.println("The scanner is not in your inventory. It might be in a container, if so, unpack it.");
		} else if (!ryderFalcone.getCurrentRoom().isCorrectRoom()) {	//not a correct room so no dead body to scan
			Writer.println("There's no dead body in this room for you to scan. You are in the wrong place to scan, detective.");
		} else {
			String hint = ((CorrectRoom) ryderFalcone.getCurrentRoom()).getScannerHint();
			Writer.println(hint);
		}
	}
	
	
	/** 
	 * Function for the command escape. Follows scenarios outlined in GWT's. A special feature.
	 * */
	private void escape() {
		Room room29 = world.getRoom("- 843.91 i-hat, - 665.74 j-hat");
		if (ryderFalcone.getCurrentRoom() != room29) { //if not in room29
			Writer.println("You are free, there's nothing to escape out of.");
			return;
		} 
		Writer.println("What do you think would be helpful to escape this electric cage? Enter the name of an item in your inventory. "		//re-prompt user
				+ "Make sure you first unpack any items in containers if you think they are helpful.");
		String response = Reader.getResponse();
		
		if (!response.equals("escrima sticks")) {
			Writer.println ("That won't help. Try another item. Remember the cage is electric so something electric would be helpful.");
			return;
		} 
		if (ryderFalcone.getItem("escrima sticks") == null) {
			Writer.println("It's not in your inventory. Sorry but you're gonna be stuck here forever and die.");
			ryderFalcone.setHealth(0);
			return;
		}
		Writer.println("You slammed the escrima sticks into the ground which created an electrical blast that entered the power box of "
				+ "the cage and disabled the electricity allowing you to escape the cage. Look for the key to this abandoned building to go to the mall." );
		
		Item key = new Item("abandoned key", "Since you escaped the cage, you now can take this key which lets you go to the mall outside", 6, 0.01);
		room29.addItem("abandoned key", key);
	}
	
	
	/** 
	 * Function for the command shoot. Follows scenarios outlined in GWT's. Part of guard battling special feature.
	 * @param command the command entered by the user that's first word was shoot
	 * */
	private void shoot(Command command) {
		Room room34 = world.getRoom("mall parking");
		boolean areDead = false;
		if (ryderFalcone.getCurrentRoom() != room34) { //if not in room34
			Writer.println("The shoot command isn't appropriate for this room.");
			return;
		} 
		if (!command.hasSecondWord()) {
			// If there is no second word, we don't know what to shoot so we display the message.
			Writer.println("Who do you need to shoot?");
			return;
		}
		String target = command.getRestOfLine();
		if ((!target.equals("guards")) && (!target.equals("goons"))) {
			Writer.println("We need to shoot the guards idiot. Enter shoot guards or shoot goons");
			Writer.println("*" + target + "*");
			return;
		}
		Writer.println("Whith what weapon?");
		String response = Reader.getResponse();
		
		if (ryderFalcone.getItem(response) == null) {		//item that they entered isn't in their inventory
			Writer.println("You don't have that weapon in your inventory. Maybe you have it packed?");
			return;
		}
		if (response.equals("cr-56 amax")) {
			Writer.println("You were able to kill guard 1 but gave your position away. Guard 2 ran away and called for backup. "
					+ "They sniped you before you could escape.");
			ryderFalcone.setHealth(0);  		//kill Falcone
			return;
		}
		
		if (response.equals("rpg")) {
			Writer.println("You blew those guards up! They are both dead. Go get the key to the mall from their clothes and enter the mall.");
			areDead = true;
		} 
		else if (response.equals("falcones daily")) {
			Writer.println("You killed guard 1 and guard 2 was confused as to where he got shot from since falcones daily is a silenced pistol."
					+ "By the time he could react, you killed him too. Go get the key to the mall from their clothes and enter the mall.");
			areDead = true;
		}
		else {
			Writer.println("That's not a long-ranged weapon?");
			areDead = false;
		}
		if (areDead) {
			Item key = new Item("mall key", "This is an access card that opens up the mall. It’s in the goon’s jacket’s pocket", 0, 0.01);
			room34.addItem("mall key", key);
		}
	}
	
	/** 
	 * Function for the command flash. Follows scenarios outlined in GWT's. Part of guard battling special feature.
	 * */
	private void flash() {
		Room room35 = world.getRoom("mall");
		Item grenade = ryderFalcone.getItem("flash grenade");
		if (ryderFalcone.getCurrentRoom() != room35) { //if not in room35
			Writer.println("You shouldn't use a flash grenade here.");
			return;
		} 
		if (grenade == null) { 	//flash grenade not in inventory
			Writer.println("You don't have a flash grenade in your inventory");
			return;
		}
	
		Writer.println("You have successfully blinded the group of goons. Take advantage of this and follow up with an attack using another weapon.");
		ryderFalcone.setHasFlashed(true);
		ryderFalcone.removeItem(grenade.getName()); //get rid of grenade bc it has been used.
	}
	
	
	/** 
	 * Function for the command attack. Follows scenarios outlined in GWT's. Part of guard battling special feature.
	 * */
	private void attack() {
		Room room35 = world.getRoom("mall");
		boolean areDead = false;									//if goons are dead, it turns to true
		if (ryderFalcone.getCurrentRoom() != room35) { 	  //if not in room35
			Writer.println("Attack isn't an appropriate command for this room");
			return;
		} 
		Writer.println("with what?");
		String response = Reader.getResponse();
		if (ryderFalcone.getItem(response) == null) {
			Writer.println("You don't have that weapon in your inventory. Maybe you have it packed?");
			return;
		}
		
		if (response.equals("escrima sticks")) {
			if (ryderFalcone.getHasFlashed() == false) {
				Writer.println("Don't bring a melee weapon to a gunfight unless you have some advantage, detective. "
						+ "I thought you were a better fighter than this.");
				ryderFalcone.setHealth(0);
			} else {
				if (ryderFalcone.getCarryWeight() > 15) {
					Writer.println("You can't move as fast as you normally do with all this junk in your inventory. You weren't battle ready!");
					ryderFalcone.setHealth(0);
				} else {
					Writer.println("While the goons were struggling to see, you acrobatically jumped towards them. "
							+ "You used 10 different martial arts techniques to disarm them while smacking the hell "
							+ "out of them with the escrima sticks. Grab the key from them and unlock the door.");
					areDead = true;
				}
			}
		}
		else if (response.equals("falcones daily")) {
			if (ryderFalcone.getHasFlashed() == false) {
				Writer.println("You let your ego get the best of you, detective. 10 assault rifles always beat a pistol,"
						+ "unless you have some advantage. I thought you were a better fighter than this.");
				ryderFalcone.setHealth(0);
			} else {
				if (ryderFalcone.getCarryWeight() > 15) {
					Writer.println("You can't move as fast as you normally do with all this junk in your inventory. You weren't battle ready!");
					ryderFalcone.setHealth(0);
				} else {
					Writer.println("You were able to kill all of them with precision aim as they were busy trying to see. Grab the key from them and unlock the door.");
					areDead = true;
				}
			}
		}
		
		else if (response.equals("rpg")) {
			Writer.println("BOOOM! Down go the goons. Grab the key from them and unlock the door.");
			areDead = true;
		}
		else if (response.equals("cr-56 amax")) {
			Writer.println("Unlucky! your gun jammed and the goons saw you.");
			ryderFalcone.setHealth(0);
		}
		else { 		//This is when the response is not an item that is a weapon
			Writer.println("You ran at the guards like an idiot holding a " + response + " and they killed you.");
			ryderFalcone.setHealth(0);
		}
		
		if (areDead) {
			Item key = new Item("secret key", "This is an access card that opens up the passcode room. It’s in the goon’s jacket’s pocket", 0, 0.01);
			room35.addItem("secret key", key);
		}
		
	}
	
	
	
	/** 
	 * Function for the command deactivate. Follows scenarios outlined in GWT's. 
	 * @return false is bomb wasn't deactivated. True if bomb was deactivated.
	 * 
	 * */
	private boolean deactivate() {
		boolean wentThroughLoop = false;
		Room room37 = world.getRoom("Radioactive Warehouse in Mall");
		if (ryderFalcone.getCurrentRoom() != room37) { //if not in room37
			Writer.println("There is nothing to deactivate in this room");
			return false;
		} 
		for (int num = 0; num < 3; num++) {
			wentThroughLoop = true;
			Writer.println("Enter the passcode: ");
			String response = Reader.getResponse();
			if (response.equals("24080369717")) {
				Writer.println("You win! Congratulations detective. This becomes case #10,001 comleted. You have saved the city from this lunatic!");
				return true;
			} 
			if (num != 2) {
				Writer.println("INCORRECT. Try Again.  Enter the passcode: ");
			}
		}
		if (wentThroughLoop) {
			Writer.println("INCORRECT. 3, 2, 1, BEEP BEEP BEEP BOOOM!! The world is doomed.");
			ryderFalcone.setHealth(0);
			return false;
		}
		return false;	//default is to return false
	}
	
	
	/**
	 * Print out the closing message for the player.
	 */
	private void printGoodbye() {
		//The points obtained from items gets added to score. The player was keeping track of this. points from rooms have already been added to score by goRoom().
		score += ryderFalcone.getPointsFromItemsTally();
		Writer.println("Thank you for playing! I hope you enjoyed the game.");
		Writer.println("You have earned " + score + " points in " + turn + " turns");
		Writer.println("The max amount of points you could've gotten was 1200 points.");

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
		Writer.println("Welcome to Detective Ryder Falcone: Riddler’s Transformation. This is a mystery "
				+ "text adventure game where you must find and investigate dead bodies based on clues. "
				+ "Find out where the bodies lead you and try to save the city from the Riddler. \n"
				+ "Use the help command to get the list of all commands in the game.\n"
				+ "");
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
