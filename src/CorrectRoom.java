
public class CorrectRoom extends Room {

	public CorrectRoom(String name, String description, int points) {
		super(name, description, points);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Contains the text that will be displayed when the examine command is entered.
	 */
	private String examineDescription;
	/** Contains the text that will be displayed when the scanner item is used. */
	private String scannerHint;

	/*	*//** Contains one of the texts that will be displayed for the exits */
	/*
	 * private String choiceA;
	 *//** Contains one of the texts that will be displayed for the exits */
	/*
	 * private String choiceB;
	 *//** Contains one of the texts that will be displayed for the exits *//*
																			 * private String choiceC;
																			 */

	/**
	 * @return the examineDescription
	 */
	public String getExamineDescription() {
		return examineDescription;
	}

	/**
	 * @param examineDescription the examineDescription to set Setter for
	 *                           examineDescription
	 */
	public void setExamineDescription(String examineDescription) {
		this.examineDescription = examineDescription;
	}

	/**
	 * @return the scannerHint
	 */
	public String getScannerHint() {
		return scannerHint;
	}

	/**
	 * @param scannerHint the scannerHint to set
	 */
	public void setScannerHint(String scannerHint) {
		this.scannerHint = scannerHint;
	}

	/*	*//**
			 * @return the choiceA
			 */
	/*
	 * public String getChoiceA() { return choiceA; }
	 * 
	 *//**
		 * @param choiceA the choiceA to set
		 */
	/*
	 * public void setChoiceA(String choiceA) { this.choiceA = "Choice A: " +
	 * choiceA; }
	 * 
	 *//**
		 * @return the choiceB
		 */
	/*
	 * public String getChoiceB() { return choiceB; }
	 * 
	 *//**
		 * @param choiceB the choiceB to set
		 */
	/*
	 * public void setChoiceB(String choiceB) { this.choiceB = "Choice B: " +
	 * choiceB; }
	 * 
	 *//**
		 * @return the choiceC
		 */
	/*
	 * public String getChoiceC() { return choiceC; }
	 * 
	 *//**
		 * @param choiceC the choiceC to set
		 */
	/*
	 * public void setChoiceC(String choiceC) { this.choiceC = "Choice C: " +
	 * choiceC; }
	 * 
	 * 
	 * 
	 *//**
		 * Defines an exit from this room.
		 *
		 * @param direction The direction of the exit.
		 * @param neighbor  The door in the given direction.
		 *//*
			 * public void setExit(String direction, Door neighbor) { exit.put(choiceA, );
			 * exit.put(choiceB, ); exit.put(choiceC, ); }
			 */

}
