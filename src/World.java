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
	* Helper method for creating doors between rooms. This is called in createRooms
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
	* overloaded version of the constructor for making locked doors. I made this to avoid having to enter redundant arguments for rooms that aree already unlocked.
	* 
	* Helper method for creating doors between rooms. This is called in createRooms
	*
	* @param from The room where the door originates.
	* @param direction The direction of the door in the from room.
	* @param to The room where the door goes.
	* @param locked is true if the room is locked, false if its unlocked
	* @param keyName is the name of the key item that unlocks this door. 
	* 
	*/
	private void createDoor(Room from, String direction, Room to, boolean locked, String keyName) {
		Door door = new Door(to);
		from.setExit(direction, door);
		door.setLocked(locked);
		door.setKeyName(keyName);
	}
	
	/**
	* Helper method for creating and adding items into a room. This is called in createRooms.
	*
	* @param room The room that we want to put an item into.
	* @param itemName The name of the item that we want to add.
	* @param description The description of the item we're adding.
	* @param pointValue The number of points the item has.
	* @param weight The weight of the item.
	*/
	private void createItems(Room room, String itemName, String description, int pointValue, double weight) {
		Item item = new Item(itemName, description, pointValue, weight);
		room.addItem(itemName, item);
	}
	
	/**
	* Helper method for creating and adding containers into a room. This is called in createRooms.
	*
	* @param room The room that we want to put an item into.
	* @param itemName The name of the container item that we want to add.
	* @param description The description of the item we're adding.
	* @param pointValue The number of points the item has.
	* @param weight The weight of the item.
	*/
	private void createContainer(Room room, String itemName, String description, int pointValue, double weight) {
		Container container = new Container(itemName, description, pointValue, weight);
		room.addItem(itemName, container);
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
				+ "Go to basement to get weapons and necessary items\r\n"
				+ "", 0);
		
		Room room2 = new Room("Ryder Falcone’s Mansion’s – Weapon Armory in Basement", "You must prepare for what is to come, "
				+ "make your choice for what weapon or weapons you want to carry. You may be tempted to grab it all, but beware, we can’t "
				+ "carry more than 50 lbs so make sure to leave enough room to carry other things. Also, it’s a disadvantage to carry more than 15 lbs "
				+ "during battle. Anyway, here are your options:\r\n"
				+ "-	Scanner - 2 lbs		**required** \r\n "
				+ "-	Falcones Daily – Silenced 9mm Pistol – 3 lbs.  *recommended*\r\n"
				+ "-	Escrima Sticks (has electricity) – 7 lbs    *recommended*\r\n"
				+ "-	RPG – 35 lbs\r\n"
				+ "-	Flash Grenade – 2 lbs    *recommended*\r\n"
				+ "-	CR-56 Amax – 25 lbs \r\n"
				+ "-	Weapon Case - 10 lbs 		*recommended* \r\n", 0);
		
		Room room3 = new Room("Mansion Helipad", "Wind blows in your face as you walk toward the helicopter. Let’s go to the police station to get more information.", 0);
		
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
		
		CorrectRoom room6Body1 = new CorrectRoom("Clock Tower", "The body has already been lowered from the tower by the police. "
				+ "It’s lying on a tarp on the floor.\r\n"
				+ "“This is brutal. The body looks inhumane, it’s unnatural in every way possible. Whoever did this had goals beyond "
				+ "just killing this person. It looks as if it’s transforming into a different being. His skin is turning green and "
				+ "there are cut marks all over him and green bubonic-plague-type bubbles”\r\n"
				+ "", 100);
		
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
				+ "You are at the roof. There are AC units and antennas everywhere. After a few minutes of zigzagging "
				+ "through the AC units and antennas, you notice a green liquid dripping from an AC unit. "
				+ "We need to reveal the body from under the AC unit. “There it is. The body looks just like the last: "
				+ "sick, green, and morphing. It has the same cut marks and bubbles. This seems like a common theme.” \r\n", 100);

				
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
				+ "bridge before leaving. “Good thing I checked down here…Never mind… this is pure gore! The body is hanging "
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
		
		CorrectRoom room18Body5 = new CorrectRoom("Ramsay’s Restaurant", "“Gordon’s my buddy. I hope nothing happened to him. Let me try calling him. Huh… unresponsive. Let me call his assistant.  \r\n"
				+ "“Hey, I need to do a search of the restaurant. Can you clear it for examination?” – Falcone\r\n"
				+ "“Sure thing!” – assistant\r\n"
				+ "You are now in the restaurant. It’s very chic and fancy. The place is clean and smells good. They also have some nice soothing jazz playing in the background.\r\n"
				+ "\r\n"
				+ "“NOOOOO! He got to Ramsay.\r\n"
				+ "", 100);
		
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
				+ "You broke into the shop and can now see the body more clearly.", 100);
		
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
		
		Room room29 = new Room("- 843.91 i-hat, - 665.74 j-hat", "This looks like an abandoned building. It’s nighttime and there are no lights on. "
				+ "You enter the building and see nothing due to the darkness, but before you can move, a giant cage falls from the room and traps you inside. "
				+ "The cage is electrically powered. The electricity allows for a force field that prevents anything from passing through it. \r\n"
				+ "From the darkness, a glowing green object approaches you, as it gets closer, things start to clear up and you see a man in a lab coat holding "
				+ "a syringe gun with glowing, bubbly green fluid in it.\r\n"
				+ "“Falcone…what took you so long? It’s rude to keep your toughest challenge waiting. HAHA. I’m glad that you finally get to put a face to the man "
				+ "who’s been terrorizing your small brain this whole time since the clock tower incident. Now let me introduce myself… I am the Riddler, master of clues. "
				+ "Riddle me this: do you recognize this old man?” - Riddler\r\n"
				+ "The green lights of the warehouse turn on and a group of 10 goons wearing grey jackets bring a body in front of you.\r\n"
				+ "“NOOOOOO! You’re an absolute lunatic! I HATEE YOUUU! Why would you do this? Officer Bob Marshall has done nothing but provide justice and safety for this city!” – Falcone\r\n"
				+ "“HAHAHAAAA this is GREAT! I’ve never seen you get so rowdy and uncontrollable. Don’t worry Falcone, I didn’t have anything against him, he was just in the way. "
				+ "Everyone in this city will be gifted this opportunity. A new life! They’ll transform into a being much more powerful than humans. You see, this radioactive syringe "
				+ "that I hold in my hand has been responsible for all the people you found unconscious. That’s right, they’re not dead. They will get back up ready to enjoy their new body. "
				+ "But of course, a syringe isn’t that effective. How about we go booom! HAHAHAHA” – Riddler\r\n"
				+ "The riddler and his goons leave the room and you are stuck in this cage. \r\n"
				+ "\r\n"
				+ "“While the Riddler was busy laughing, I was able to observe the jackets the goons were wearing. They all had grey jackets with the same logo. The logo said Uptown Mall. This is the only lead I have since they took Bob Marshall’s body with them. First I have to get out of this cage.” – Falcone\r\n"
				+ "*Try the escape command\r\n"
				+ "", 100);
		WrongRoom room30Wrong8a = new WrongRoom("143.21 i-hat, 265.34 j-hat","Certainly not where I have to be, "
				+ "this is just a cornfield with no possible secret entrances.", -10, -10);
		WrongRoom room31Wrong8b = new WrongRoom("21.21 i-hat, - 2121.21 j-hat","Certainly not where I have to be, "
				+ "this is just a children’s playground.", -10, -10);
		WrongRoom room32Wrong8c = new WrongRoom("- 433.94 i-hat, 109.76 j-hat","Certainly not where I have to be, "
				+ "this is just a tennis court.", -10, -10);
		WrongRoom room33Wrong8d = new WrongRoom("what is going on!","Focus on the signs and the scanner hint", 2, 0);
		
		Room room34 = new Room("mall parking", "It’s 4 AM as you’re in your Audi R8 driving toward the mall’s parking. You notice that two jacked goons are guarding the mall’s entrance with machine guns. "
				+ "You didn’t approach sneakily so they noticed your trademark car first and fired some shots at it. Luckily, you turned around quickly and didn’t take any damage.\r\n"
				+ "“Their weapons outpower mine, I must find a sneaky approach to killing them and entering the mall. There’s a hill bordering the parking lot and there are many trees for cover. "
				+ "I should drive to the bottom and walk all the way up” - Falcone\r\n"
				+ "You are now at the top of the hill and the goons have no idea. Shoot them with one of your guns. \r\n"
				+ "", 100);
		
		Room room35 = new Room("mall", "You are now in this empty mall. Based on the fact that goons were guarding this mall, it’s certain that you’re in the right place. "
				+ "Sneaking and searching this ginormous mall was tedious, but eventually, you heard a group of 10 armed goons cracking jokes as they were guarding a room."
				+ " Whatever that room is must be important, but you must kill all goons before you can enter. \r\n"
				+ "“I must be smart with the way I choose to attack. I need to pick a weapon or combination of weapons that allows me to kill them all at once or "
				+ "one-by-one without interference from the others.” – Falcone\r\n"
				+ "Use help command if you need a refresher of all available commands. \r\n"
				+ "", 30);
		
		Room room36 = new Room("Passcode Room","This room is completely empty, except there is a door and a chest.", 55);
		
		Room room37 = new Room("Radioactive Warehouse in Mall", "You have opened the door and see a stairway going down into a warehouse. You hear a maniacal laugh as you walk down the stairs. "
				+ "The riddler is dancing with joy as the countdown on the bomb keeps going down but doesn’t see you. It’s at 59 seconds.\r\n"
				+ "“Riddle me this dumbass” – Falcone\r\n"
				+ "“How did you - ” – Riddler\r\n"
				+ "You knocked out the riddler and killed him with your bare hands. Deactivate the bomb. You have 10 seconds. \r\n"
				+ "", 100);
		
		
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
		this.addRoom(room30Wrong8a);
		this.addRoom(room31Wrong8b);
		this.addRoom(room32Wrong8c);
		this.addRoom(room33Wrong8d);
		this.addRoom(room34);
		this.addRoom(room35);
		this.addRoom(room36);
		this.addRoom(room37);
		
		//Adding all the scanner hints for the correct rooms.
		room6Body1.setScannerHint("Analysis of minuscule code from cut marks reveals the following clue: High up");
		room9Body2.setScannerHint("DNA analysis of the person’s blood reveals 0.15 blood alcohol content.");
		room12Body3.setScannerHint("Analysis of miniscule code from cut marks reveals the following clue: From A to B");
		room15Body4.setScannerHint("The scanner has revealed a UV light marking that says: First is 18th");
		room18Body5.setScannerHint("The scanner analyzed the code marked on Ramsay’s skin via knife scratches. "
				+ "The scanner converted it to the following sentence: “I don’t want to be around the homeless or "
				+ "think about how to solve the issue”");
		room21Body6.setScannerHint("Due to examining so many bodies, the scanner figured out some of the chemical "
				+ "components in the altered, green blood. Abnormal Elements in the blood: 2% Holmium, 7% Uranium, "
				+ "5% Nobelium, 10% Potassium, 42% Polonium");
		room25Body7.setScannerHint("The scanner took longer than usual and initially failed. Falcone, as the creator "
				+ "of the device, was able to debug the issue and fix the scanner. The scan was rerun and revealed "
				+ "the following message from the numbers: “(2k +1) won (not in)”");
		room28Body8.setScannerHint("The scanner just shows: Quadrant 3");
		
		// Creating all the doors between the rooms.
		//Rooms that are just part of the story before bodies begin.
		this.createDoor(room1, "basement", room2, true, "armory key");
		
		this.createDoor(room2, "elevator", room3);
		this.createDoor(room2, "kitchen", room1);
		
		this.createDoor(room3, "helicopter", room4);
		this.createDoor(room3, "basement", room2, false, "armory key");
		
		this.createDoor(room4, "audi", room5);
		
		this.createDoor(room5, "clock", room6Body1);
		this.createDoor(room5, "police", room4);
		
		//Doors for Body 1
		this.createDoor(room6Body1, "a", room7Wrong1a);
		this.createDoor(room6Body1, "b", room8Wrong1b);
		this.createDoor(room6Body1, "c", room9Body2);
		this.createDoor(room6Body1, "audi", room5);
		//Doors for Body 2
		this.createDoor(room9Body2, "a", room12Body3);
		this.createDoor(room9Body2, "b", room10Wrong2a);
		this.createDoor(room9Body2, "c", room11Wrong2b);
		this.createDoor(room9Body2, "prev body", room6Body1);
		//Doors for Body 3
		this.createDoor(room12Body3, "a", room15Body4);
		this.createDoor(room12Body3, "b", room13Wrong3a);
		this.createDoor(room12Body3, "c", room14Wrong3b);
		this.createDoor(room12Body3, "prev body", room9Body2);
		//Doors for Body 4
		this.createDoor(room15Body4, "a", room16Wrong4a);
		this.createDoor(room15Body4, "b", room18Body5);
		this.createDoor(room15Body4, "c", room17Wrong4b);
		this.createDoor(room15Body4, "prev body", room12Body3);
		//Doors for Body 5
		this.createDoor(room18Body5, "a", room19Wrong5a);
		this.createDoor(room18Body5, "b", room20Wrong5b);
		this.createDoor(room18Body5, "c", room21Body6);
		this.createDoor(room18Body5, "prev body", room15Body4);
		//Doors for Body 6. This one has 4 choices
		this.createDoor(room21Body6, "a", room22Wrong6a);
		this.createDoor(room21Body6, "b", room25Body7);
		this.createDoor(room21Body6, "c", room23Wrong6b);
		this.createDoor(room21Body6, "d", room24Wrong6c);
		this.createDoor(room21Body6, "prev body", room18Body5);
		//Doors for Body 7
		this.createDoor(room25Body7, "a", room28Body8);
		this.createDoor(room25Body7, "b", room26Wrong7a); 
		this.createDoor(room25Body7, "c", room27Wrong7b);
		this.createDoor(room25Body7, "prev body", room21Body6);
		//Doors for Body 8. This one has 5 choices. It's the final room with a body
	
		this.createDoor(room28Body8, "a", room30Wrong8a);
		this.createDoor(room28Body8, "b", room31Wrong8b); 
		this.createDoor(room28Body8, "c", room32Wrong8c);
		this.createDoor(room28Body8, "d", room29); 
		this.createDoor(room28Body8, "e", room33Wrong8d);
		this.createDoor(room28Body8, "prev body", room25Body7);
		
		this.createDoor(room29, "mall parking", room34, true, "abandoned key");
		
		this.createDoor(room34, "mall", room35, true, "mall key");
		
		this.createDoor(room35, "secret room", room36, true, "secret key");
		
		this.createDoor(room36, "door", room37);
		
		
		//Wrong rooms don't have any doors other than the one door that got the player to that room. 
		//When a player enters a wrong room, they must use back command to go back.
		
		
		//this.createItems(room5, "cologne", "Wood", 0, 1); //template
		
		//Creating all the items and containers that go into each room.
		this.createItems(room1, "phone", "You can make important calls with this that will give important information", 5, 1);
		this.createItems(room1, "protein shake", "This is just a chocolate-flavored protein shaken", 0, 1);
		this.createItems(room1, "tv", "The news is showing a weirdly shaped green dead body hanging on the clock tower", 0, 60);
		this.createItems(room1, "couch", "Black, leather, L-shaped couch by the fireplace", 0, 400);
		this.createItems(room1, "armory key", "key to unlock armory", 0, 0.01);
		
		this.createItems(room2, "scanner", "Falcone built this device himself. It can provide useful information "
				+ "from the slightest of hints and marks left behind. It can analyze every language on earth in "
				+ "addition to all sorts of code", 1, 2);
		this.createItems(room2, "falcones daily", "Loaded, silenced 9mm Pistol", 0, 3);
		this.createItems(room2, "escrima sticks", "Weapon that is two, short electrically charged bars. Useful for acrobatic battle and wrecking electrical supplies", 0, 6);
		this.createItems(room2, "rpg", "makes things boom", 0, 35);
		this.createItems(room2, "flash grenade", "Blinds enemies. Can blind a whole group at once", 0, 2);
		this.createItems(room2, "cr-56 amax", "Heavy Assault Rifle", 0, 25);
		//This is one of the container items. This one starts out as empty.
		this.createContainer(room2, "weapon case", "This is a fancy, organized case that can store all the weapons you choose. "
				+ "Pack your chosen weapons into the case once you add the case to your inventory. But make sure to unpack the "
				+ "weapons before you use them.", 5, 10);
		
		this.createItems(room5, "protective gear", "This is good precaution for examining the bodies", 2, 4);
		this.createItems(room5, "cologne", "Tom Ford Oud Wood", 0, 0.5);
		
		this.createItems(room6Body1,"body 1" ,"The person is wearing a T-shirt and shorts. \r\n"
				+ " Bubbles are visible. There’s green blood pouring out of the bubbles.\r\n"
				+ "The cut marks contain binary code written in minuscule print. Maybe try using "
				+ "the scanner to decode the marks.\r\n", 0, 145);
		this.createItems(room6Body1, "lion sculptures", "Nothing special here, just decoration", 0, 100);
		this.createItems(room6Body1, "paper guide", "What are you thinking, stop wasting time. It’s just a guide for tourists", 0, 0.5);
		this.createItems(room6Body1, "tarp", "not bad thinking, there may have been a clue here but there’s nothing but green blood on it", 0, 1);
	
		this.createItems(room7Wrong1a, "sandwich", "bacon egg and cheese", 0, 2);
		
		this.createItems(room8Wrong1b, "needle", "Aaron’s tattoo needle", 0, 0.5);

		this.createItems(room9Body2, "body 2", "The person is old, overweight, and is wearing a polo and suit pants\r\n"
				+ "The person’s wallet is empty\r\n", 0, 300);
		this.createItems(room9Body2, "antenna", "These antennas seem normal", 0, 15);
		this.createItems(room9Body2, "pigeon", "You approached the pigeon, and it flew away. How embarrassing.", 0, 0.5);
		
		this.createItems(room10Wrong2a, "tequilla", "You can’t drink on the job. Put it down", 0, 0.1);
		this.createItems(room10Wrong2a, "beer", "You can’t drink on the job. Put it down", 0, 1);
		
		this.createItems(room11Wrong2b, "vodka", "You can’t drink on the job. Put it down", 0, 0.1);
		
		this.createItems(room12Body3, "body 3", "The woman has a necklace with the letter N on it.", 0, 110);
		this.createItems(room12Body3, "bucket", "Janitor’s bucket", 0, 14);
		this.createItems(room12Body3, "mop", "it’s a wet mop", 0, 4);
		
		this.createItems(room13Wrong3a, "poo", "You’re disgusting, why are you touching this", -2, 0.1);
		
		this.createItems(room14Wrong3b, "poop", "Damn it! I got poo all over my face.", 0, 0.1);
		
		this.createItems(room15Body4, "body 4", "After lowering the body from the hook, you notice that the person "
				+ "is a man in an elegant, collared shirt with the buttons undone and is wearing a Hublot Watch. ", 0, 165);
		this.createItems(room15Body4, "rocks", "There’s a variety of rocks on the bed of the bridge", 0, 1);
		this.createItems(room15Body4, "hook", "The hook has no marks on it leading to the villain", 0, 3);
		
		this.createItems(room16Wrong4a, "tuna", "can of tuna", 0, 0.2);
		this.createItems(room16Wrong4a, "syrup", "chocolate syrup", 0, 0.1);
		this.createItems(room16Wrong4a, "shopping cart", "Guy Fieri’s shopping cart", 0, 30);
		
		this.createItems(room17Wrong4b, "blender", "you used this to make the shake", 0, 3);
		this.createItems(room17Wrong4b, "plate", "empty plate for the stake you just ate", 0, 1);
		
		this.createItems(room18Body5, "body 5" ,"Stuck to Gordon’s green, transforming body is an image of a man in an all-black suit "
				+ "and a top hat drinking coffee.", 0, 185);
		this.createItems(room18Body5, "sandwich", "idiot sandwich", 1, 2);
		this.createItems(room18Body5, "spatula", "", 0, 1);
		this.createItems(room18Body5, "stove", "Gordon’s special stove", 0, 100);
		
		this.createItems(room21Body6, "body 6", "This is a coffee shop owner. His name is Highest.", 0, 130);
		this.createItems(room21Body6, "drums", "This is the drum that had the green mark", 0, 80);
		this.createItems(room21Body6, "saxophone", "", 0, 12);
		this.createItems(room21Body6, "trumpet", "", 0, 7);
		
		this.createItems(room25Body7, "body 7", "The person is so far into the transformation that no facial or "
				+ "body features can be a clue. This new being doesn’t even look like an animal. It holds the shape"
				+ " of a human but has features of an unknown creature. The skin is FULLY covered in numbers as if "
				+ "someone wrote an essay with a precision knife. ", 0, 90);
		this.createItems(room25Body7, "coffee machine", "", 0, 20);
		this.createItems(room25Body7, "mugs", "", 0, 12);
		this.createItems(room25Body7, "tv", "It’s just playing some Louis Armstrong music", 0, 50);
		
		this.createItems(room28Body8, "body 8", "On the person’s body it says: For every wrong guess,"
				+ " a building gets blown up, GREEN!", 0, 170);
		
		this.createItems(room36, "card", "passcode = 24080369717", 0, 1);
		
	}
	
}
