import java.util.HashMap;

/**
 * This class represents the entire world that makes up the "Campus of Kings"
 * application. "Campus of Kings" is a very simple, text based adventure game.
 * Users can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 *
 * This world class creates the world where the game takes place.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class World {
	/** The rooms in the world. */
	private HashMap<String, Room> rooms;
 
	/**
	 * Constructor for the world.
	 */
	public World() {
		rooms = new HashMap<String, Room>();
		createRooms();
	}

	/**
	 * This method takes care of creating all of the aspects of the world for
	 * the "Campus of Kings" application.
	 *
	 * @param name
	 *            The provided name of the room.
	 * @return The room associated with the provided name
	 */
	public Room getRoom(String name) {
		return rooms.get(name.toLowerCase());
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// Start of private helper methods

	/**
	 * Helper method for recreating a Room. Ensure that the room is created and
	 * installed in to the collection of Rooms
	 *
	 * @param theRoom
	 *            The room to add to the world.
	 */
	private void addRoom(Room theRoom) {
		rooms.put(theRoom.getName().toLowerCase(), theRoom);
	}


	/**
	* Helper method for creating doors between rooms.
	*
	* @param from The room where the door originates.
	* @param direction The direction of the door in the from room.
	* @param to The room where the door goes.
	*/
	private void createDoor(Room from, String direction, Room to) {
		Door door = new Door(to);
		from.setExit(direction, door);
	}
	
	
	/**
	 * This method creates all of the individual places in this world and all
	 * the doors connecting them.
	 */
	private void createRooms() {
		// Creating all the rooms. 
		//The constructor is made so that the first string is the room title and the second string is the initial description.
		Room room1 = new Room("Ryder Falcone’s Mansion’s - Living Room & Kitchen", "Falcone is making a protein shake "
				+ "in his kitchen and he notices the news on the TV. He walks to the living room and sees that the news "
				+ "is reporting a weirdly shaped body tied to the city’s large clock tower. He immediately gets a call from "
				+ "the chief of police to come to the police station as they have more info. \r\n"
				+ "\r\n"
				+ "Go to basement to get weapons and items\r\n"
				+ "", 0);
		
		Room room2 = new Room("Ryder Falcone’s Mansion’s – Weapon Armory in Basement", "We must prepare for what is to come, "
				+ "make your choice for what weapon or weapons you want to carry. You may be tempted to grab it all, but beware, we can’t "
				+ "carry more than 50 lbs so make sure to leave enough room to carry other things. Also, it’s a disadvantage to carry more than 15 lbs "
				+ "during battle. Anyway, here are your options:\r\n"
				+ "-	Falcone’s Daily – Silenced 9mm Pistol – 3 lbs.  *recommended*\r\n"
				+ "-	Escrima Sticks (has electricity) – 7 lbs    *recommended*\r\n"
				+ "-	RPG – 35 lbs\r\n"
				+ "-	Flash Grenade – 2 lbs    *recommended*\r\n"
				+ "-	CR-56 Amax – 25 lbs \r\n"
				+ "", 0);
		
		Room room3 = new Room("Mansion Helipad", "Falcone walks toward the helicopter as wind blows in his face and enters the Helicopter to head to police station.", 0);
		
		Room room4 = new Room("Police Station Helipad", "Officer Bob Marshall walks toward you calmly as wind from the chopper blows in his face. "
				+ "As you both walk inside, he says: 'Looks like someone is looking to challenge you. We don’t know who, but the lunatic is obsessed "
				+ "with riddles and hidden clues. I just wish the clues were written on paper rather than hidden in bodies. Who knows how many people "
				+ "he’s killed, but he sent us a teasing message revealing that there’s 8 bodies that he’s used as just clues. The whole country knows "
				+ "that where the first clue is, it’s so obviously hung on the clock tower. We need you to investigate that body and use the clues to find "
				+ "all bodies. Be careful when examining the body, you wouldn’t want to get contaminated. Eventually the clues will lead you right to him, "
				+ "just beware since he’ll have home field advantage'.\r\n"
				+ "\r\n"
				+ "I must go check out the body. Go to your Audi R8\r\n"
				+ "", 0);
		
		Room room5 = new Room("Audi R8", "“Oh sweet! My assistant prepped the Audi beforehand. There is protective gear placed on the backseat.”"
				+ "", 0);
		
		CorrectRoom room6Body1 = new CorrectRoom("Clock Tower", "in Dr Sliva's office.", 100);
		
		WrongRoom room7Wrong1a = new WrongRoom("Deli", "“How ya doin! What can I get ya?” – Antonio\r\n"
				+ "“A dead body?” – Falcone\r\n"
				+ "“Man get the hell outta here!” – Antonio\r\n"
				+ "", -10, 0);
		
		WrongRoom room8Wrong1b = new WrongRoom("Aaron's Tattoo Shop", "“What you wanna get big guy?” – Aaron\r\n"
				+ "“Do you happen to notice any suspicious activity recently?” – Falcone\r\n"
				+ "“Nah” – Aaron\r\n"
				+ "“I’m gonna investigate the shop sir” – Falcone\r\n"
				+ "“You’re all good, nothing here” - Falcone\r\n"
				+ ".", -10, 0);
		
		CorrectRoom room9Body2 = new CorrectRoom("Mulberry Apartment Rooftop", "In the slums lies a 20 floor, damaged apartment complex "
				+ "with a large LED sign that says Mulberry. \r\n"
				+ "“Based on the clue, I assume the body is on the roof, so I’ll begin examining from top down.”\r\n"
				+ "", 100);

				
		WrongRoom room10Wrong2a = new WrongRoom("Night Club", "“This is the busiest I’ve ever seen this place. "
				+ "I highly doubt that none of these people noticed anything in such a small area."
				+ " Let’s not waste any time.” – Falcone", -10, 0);
		
		WrongRoom room11Wrong2b = new WrongRoom("Delta Phi Frat House", "“Suhhh duuude?” – Brad\r\n"
				+ "“I need to investigate the house for a dead body” – Falcone\r\n"
				+ "“You bring any drinks? Where your girls at?” – Brad\r\n"
				+ "“no” – Falcone\r\n"
				+ "“Get lost crodie” - Brad\r\n"
				+ "", -10, 0);
		
		CorrectRoom room12Body3 = new CorrectRoom("Casino", "You must do a search of the casino but must first inform the owner that he’s required by "
				+ "the police department to clear the building for this search. After going to the officer guarding the door and informing "
				+ "him about the search, he calls the owner of the casino and approves the search. Everyone has fled the building and it’s "
				+ "time to search.  \r\n"
				+ "\r\n"
				+ "After a thorough search, Falcone was able to find the body on the ceiling of the janitor’s office. The body is horizontally"
				+ " hanging from wall to wall with her feet glued to one wall and her arms to the opposing wall. There’s a toy car on the woman’s stomach."
				+ " As usual, the body is morphing and has green bubbles dripping green liquid. This made a puddle under the woman.\r\n"
				+ "", 100);
		
		WrongRoom room13Wrong3a = new WrongRoom("Sewer Ab3432", "After looking up the city’s sewer map, you went to this location and entered the sewer. "
				+ "It is a long 1-mile tunnel of sewage. \r\n"
				+ "“This place definitely matches the vibe of the last two bodies. Guess I better get searching”\r\n"
				+ "After searching through the whole tunnel… you found… nothing\r\n"
				+ "\r\n"
				+ "You lost 10 HP due to being in the stench for too long.\r\n"
				+ "", -10, -10);
		
		WrongRoom room14Wrong3b = new WrongRoom("Norriton’s Zoo", "“I don’t have time to search the entire zoo. I must look at the drone footage they have” – Falcone\r\n"
				+ "“I need to check the camera footage to see if I can find a green dead body ” – Falcone\r\n"
				+ "“Sure thing – Zoo Camera Officer\r\n"
				+ "\r\n"
				+ "The body isn’t here\r\n"
				+ "As you are walking out to leave the zoo, a Gorilla throws his poo at you (-10 HP)\r\n"
				+ "", -10, 0);
		
		CorrectRoom room15Body4 = new CorrectRoom("Nightfall Bridge", "Falcone hopped in the R8 and drove over the Nightfall Bridge. "
				+ "You weren’t able to find anything suspicious on the top of the bridge. You decide to search under the "
				+ "bridge before leaving. “Good thing I checked down here…Never mind… this is disgusting! The body is hanging "
				+ "from a hook connected to the bottom of the bridge. Let me lower this thing to take a closer look.”", 100);
		
		WrongRoom room16Wrong4a = new WrongRoom("Fieri’s Grocery Store", "“You have 10 minutes to get the ingredients for a tuna casserole "
				+ "with chocolate syrup. 3, 2, 1 Go!” – Guy Fieri\r\n"
				+ "“What…. I need to…” – Falcone\r\n"
				+ "“Go!” – Brad\r\n"
				+ "You got drawn into the challenge and wasted your time. However, you were slightly on track."
				+ " While rushing around the track you looked for suspicious signs but couldn’t find anything. \r\n"
				+ "“Sorry Guy but I got lives to save. I didn’t notice any suspicious activity but I’ll have a backup "
				+ "team check for confirmation. Goodbye” - Falcone\r\n"
				+ "", -10, 0);
		
		WrongRoom room17Wrong4b = new WrongRoom("Bobby Flay’s Filet Place", "“Hey good friend. Anything I can do to help you, "
				+ "I noticed you’re busy on this new case with the green morphing dead bodies” – Bobby Flay\r\n"
				+ "“I just need to examine your place for any suspicious activity. I think the clue on the last "
				+ "body led me here” – Falcone\r\n"
				+ "“Go ahead, but first, let’s see if you can beat bobby flay in a protein shake making competition” – Bobby\r\n"
				+ "“Let me cook you real quick” – Falcone\r\n"
				+ "Having made a protein shake right before starting this whole journey, you beat bobby flay! He gifted "
				+ "you a exquisite steak. (+10 HP)\r\n"
				+ "", -10, 10);
		
		CorrectRoom room18Body5 = new CorrectRoom("Ramsay’s Restaurant", "“Gordon’s my buddy. I hope nothing happened to him.” Pick up your "
				+ "phone and call him to empty the restaurant for a search. ", 100);
		
		WrongRoom room19Wrong5a = new WrongRoom("City Hall", "The body is not here. Go back.", -20, 0);
		
		WrongRoom room20Wrong5b = new WrongRoom("Public Square", "“I’d be really disappointed if the body were here. "
				+ "This is literally hiding in plain sight as the police station is right next to the public square."
				+ " But I’m the detective for a reason so let’s investigate.” \r\n"
				+ "\r\n"
				+ "As expected, nothing to be seen. This was embarrassing clue analysis. This place is full of the homeless. \r\n"
				+ "", -20, 0);
		
		CorrectRoom room21Body6 = new CorrectRoom("Jazz Club", "“We didn’t have to travel far for this one as it’s in this same rich part of the city.”\r\n"
				+ " The Jazz Club is packed with men in suits and women in dresses. Everyone seems to be having a sophisticated conversation "
				+ "while drinking coffee as they wait for the Louis Armstrong performance in 15 minutes. You look around for hints of green "
				+ "blood as you walk through the club. You notice a small circular burn mark on the drum set on stage. You look closer and "
				+ "see a splash of green by the drum. \r\n"
				+ "“I should check out the instrument storage room. There it is. This victim has been stabbed in the chest repeatedly and, "
				+ "clearly, also contaminated with whatever is causing these transformations.”\r\n"
				+ "", 100);
		
		WrongRoom room22Wrong6a = new WrongRoom("K’s Coffee Shop", "The body is not here.", -20, 0);
		
		WrongRoom room23Wrong6b = new WrongRoom("No’s Coffee Shop", "The body is not here.", -20, 0);
		
		WrongRoom room24Wrong6c = new WrongRoom("Ho’s Coffee Shop", "iThe body is not here.", -20, 0);
		
		CorrectRoom room25Body7 = new CorrectRoom("Po’s Coffee Shop", "The body is clearly visible through the tinted windows. "
				+ "However, the coffee shop is closed due to the death of the owner – Highest. "
				+ "You need to break into the shop. Maybe try shooting the lock. ", 100);
		
		WrongRoom room26Wrong7a = new WrongRoom("Planet Fitness", "“Man, idk what the hell you’re doin’ here! I’ve never seen "
				+ "no one as fit in my life!” – CaseOh\r\n"
				+ "“Have you seen suspicious activity anywhere” – Falcone\r\n"
				+ "“Nothin’ other than the massive dookie I left on that toilet. "
				+ "Them steakhouse burritos from Taco Bell were fire, had 20 of the thangs!” - CaseOh\r\n"
				+ "\r\n"
				+ "Case caused an earthquake as he walked away from you (-10 HP)\r\n"
				+ "", -10, -10);
		
		WrongRoom room27Wrong7b = new WrongRoom("Yoga Studio", "“Am I stupid. There’s nothing here but an open "
				+ "hall full of old ladies following this yoga routine to get their cardio in.” – Falcone\r\n"
				+ "\r\n"
				+ "You slapped yourself in the face too hard (-10HP)\r\n"
				+ "", -10, -10);
		
		CorrectRoom room28Body8 = new CorrectRoom("Ancient Library", "You get a call from your assistant: “Mr. Falcone, I noticed "
				+ "a delayed output from the scanner. Just out of curiosity, sir, I did some digging on the final clue."
				+ " I’m sure by now you’ve already figured out the location of the last body, but this information may help. "
				+ "There’s this very ancient book called “Odd One Out” written by an old mathematician about his theory of prime "
				+ "numbers. The book is located in the library’s 21st floor. Hopefully this book helps with whatever this lunatic has "
				+ "planned next” – Assistant\r\n"
				+ "\r\n"
				+ "“That’s very helpful Alfred, I’ll head to the 21st floor now” – Falcone\r\n"
				+ "\r\n"
				+ "After searching the 21st floor for 15 minutes, you found the book. After pulling out the book, the entire shelf flipped "
				+ "around revealing the body. The body is disgustingly stoned to the wall and next to it are five unit vector component "
				+ "notations. These are likely addresses to the next step. The Audi can convert this cryptic notation into an address and "
				+ "take you there, you just have to pick the correct address.\r\n"
				+ "", 100);
		
		Room room29 = new Room("Abandoned Building", "in Dr Sliva's office.", 0);
	
		
		// Adding all the rooms to the world.
		this.addRoom(room1);
		this.addRoom(room2);
		this.addRoom(room3);
		this.addRoom(room4);
		this.addRoom(room5);
		this.addRoom(room6Body1);
		this.addRoom(room7Wrong1a);
		this.addRoom(room8Wrong1b);
		this.addRoom(room9Body2);
		this.addRoom(room10Wrong2a);
		this.addRoom(room11Wrong2b);
		this.addRoom(room12Body3);
		this.addRoom(room13Wrong3a);
		this.addRoom(room14Wrong3b);
		this.addRoom(room15Body4);
		this.addRoom(room16Wrong4a);
		this.addRoom(room17Wrong4b);
		this.addRoom(room18Body5);
		this.addRoom(room19Wrong5a);
		this.addRoom(room20Wrong5b);
		this.addRoom(room21Body6);
		this.addRoom(room22Wrong6a);
		this.addRoom(room23Wrong6b);
		this.addRoom(room24Wrong6c);
		this.addRoom(room25Body7);
		this.addRoom(room26Wrong7a);
		this.addRoom(room27Wrong7b);
		this.addRoom(room28Body8);
		this.addRoom(room29);
		
		// Creating all the doors between the rooms.
		//Rooms that are just part of the story before bodies begin.
		this.createDoor(room1, "basement", room2);
		this.createDoor(room2, "elevator", room3);
		this.createDoor(room3, "helicopter", room4);
		this.createDoor(room4, "audi", room5);
		this.createDoor(room5, "clock", room6Body1);
		
		//Doors for Body 1
		this.createDoor(room6Body1, "A", room7Wrong1a);
		this.createDoor(room6Body1, "B", room8Wrong1b);
		this.createDoor(room6Body1, "C", room9Body2);
		//Doors for Body 2
		this.createDoor(room9Body2, "A", room12Body3);
		this.createDoor(room9Body2, "B", room10Wrong2a);
		this.createDoor(room9Body2, "C", room11Wrong2b);
		//Doors for Body 3
		this.createDoor(room12Body3, "A", room15Body4);
		this.createDoor(room12Body3, "B", room13Wrong3a);
		this.createDoor(room12Body3, "C", room14Wrong3b);
		//Doors for Body 4
		this.createDoor(room15Body4, "A", room16Wrong4a);
		this.createDoor(room15Body4, "B", room18Body5);
		this.createDoor(room15Body4, "C", room17Wrong4b);
		//Doors for Body 5
		this.createDoor(room18Body5, "A", room19Wrong5a);
		this.createDoor(room18Body5, "B", room20Wrong5b);
		this.createDoor(room18Body5, "C", room21Body6);
		//Doors for Body 6. This one has 4 choices
		this.createDoor(room21Body6, "A", room22Wrong6a);
		this.createDoor(room21Body6, "B", room25Body7);
		this.createDoor(room21Body6, "C", room23Wrong6b);
		this.createDoor(room21Body6, "D", room24Wrong6c);
		//Doors for Body 7
		this.createDoor(room25Body7, "A", room28Body8);
		this.createDoor(room25Body7, "B", room26Wrong7a);
		this.createDoor(room25Body7, "C", room27Wrong7b);
		//Doors for Body 8. This one has 5 choices. It's the final room with a body
		this.createDoor(room28Body8, "A", need to design room);
		this.createDoor(room28Body8, "B", );
		this.createDoor(room28Body8, "C", );
		this.createDoor(room28Body8, "D", room29);
		this.createDoor(room28Body8, "E", );
		
		//Doors for all wrong rooms.
		
		
		
	}
}
