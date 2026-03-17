package up.l3info.LostKnight.model.core.map;

import up.l3info.LostKnight.model.core.miscellaneous.*;
import up.l3info.LostKnight.mvc.Model;

/**
 * This class represents an exit in the game. It Leads to a location
 * 
 * @author Noé
 */
public class Exit extends GameObject implements LookableObject, Model {

	private Location location;
	private String name;
	private final String description;
	private static final String DEFAULT_DESC = "This %s leads to %s";

	/**
	 * Returns the the location this exit leads to
	 * 
	 * @return The location this exit leads to
	 */
	public Location takeExit() {
		return this.location;
	}

	/**
	 * This constructor creates an Exit with its name, its location it leads to and a custom description
	 * 
	 * @param exitName The exit name
	 * @param location The location it leads to
	 * @param desc the The description of the Exit
	 */
	public Exit(String exitName, Location location, String desc, int posX, int posY) {
		setPosX(posX);
		setPosY(posY);
		this.name = exitName;
		this.location = location;
		if(desc == null) {			
			this.description = String.format(DEFAULT_DESC, exitName, location.getName());
		}else {
			this.description = desc;
		}
	}
	
	/**
	 * This constructor creates an Exit with its name, its location it leads to and a default description
	 * 
	 * @param exitName The exit name
	 * @param location The location it leads to
	 */
	public Exit(String exitName, Location location, int posX, int posY) {
		this(exitName, location, null, posX, posY);
	}

	/**
	 * Returns the name of the exit
	 * 
	 * @return the name of the exit
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Implementation of the LookableObject interface
	 */
	public void look() {
		System.out.println(description);
	}
	
	public String toString() {
		return "\033[93;1m" + getName() + "\033[0;2m";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public Location getLocation(){return this.location;}

}