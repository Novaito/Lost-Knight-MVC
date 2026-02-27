package up.l3info.LostKnight.model.core.character;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import up.l3info.LostKnight.model.core.miscellaneous.*;


/**
 * This abstract class gather all characters that you can interact with.
 * 
 * @author Thomas & Noé
 * 
 */


public abstract class GameCharacter extends GameObject implements LookableObject {

	private final String NAME;
	private final String DIALOG;

	/**
	 * 
	 * Permit to a character to discuss with an other.
	 * @param character
	 */
	public abstract void discuss(GameCharacter character);

	/**
	 * This constructor creates a character hand-made with a name and a dialog precise.
	 * @param name The character's name
	 * @param dialog The character's dialog
	 */
	public GameCharacter(String name, String dialog, int posX , int posY) {
		this.NAME = name;
		this.DIALOG = dialog;
		this.setPosX(posX);
		this.setPosY(posY);
	}

	/**
	 * This constructor instantiate a character with a name and dialog. To get the dialog you have to give
	 * the path to read .txt file.
	 * 
	 * @param name The character's name. 
	 * @param fn The file path
	 * @param fromFile true or false : No matter, it's in order to differentiate with the other constructor.
	 */
	public GameCharacter(String name, String fn , boolean fromFile, int posX, int posY){

		this.NAME = name; 
		String dial = "";
		this.setPosX(posX);
		this.setPosY(posY);

		try{
			
			InputStream is = getClass().getResourceAsStream(fn);
			InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                dial += line;
            }

		}
		catch(IOException e){
			e.printStackTrace();
		}
		this.DIALOG = dial;
		System.out.println(DIALOG);
	}

	/**
	 * Returns the character's name.
	 * @return The name
	 */
	public String getName() {
		return NAME;
	}
	
	/**
	 * Returns the character's dialog.
	 * @return The dialog
	 */
	public String getDialog() {
		return DIALOG;
	}

	/**
	 * Returns formatted name to print
	 * @return Formatted name
	 */
	public String toString() {
		return "  ║ \033[93;1m" + getName() + "\033[0;2m";
	}



}

