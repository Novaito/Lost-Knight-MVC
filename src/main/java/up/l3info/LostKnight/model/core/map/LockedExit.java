package up.l3info.LostKnight.model.core.map;

/**
 * This class is an Exit but is initially locked
 * 
 * @author Noé
 */
public class LockedExit extends Exit {

	private boolean locked;
	
	private static final String DEFAULT_DESC = "This %s seems locked";

	/**
	 * This constructor creates a locked door with a name, the location it leads to and a description
	 * 
	 * @param name The name of the location
	 * @param location The location it leads to
	 * @param desc The description of the exit
	 */
	public LockedExit(String name, Location location, String desc) {
		super(name, location, desc);
		locked = true;
	}
	
	/**
	 * This constructor creates a locked door with a name, the location it leads to and a default description
	 * 
	 * @param name The name of the door
	 * @param location The location it leads to
	 */
	public LockedExit(String name, Location location) {
		this(name, location, String.format(DEFAULT_DESC, name));
	}
	
	/**
	 * Returns the Location the exit leads to
	 * If the exit is locked, then returns null
	 * 
	 * @return the location the exit leads to or null if locked
	 */
	@Override
	public Location takeExit() {
		if(!isLocked()) {			
			return super.takeExit();
		}else {
			return null;
		}
	}
	
	/**
	 * Returns true if the exit is locked
	 * @return true if the exit is locked
	 */
	public boolean isLocked() {
		return this.locked;
	}
	
	/**
	 * Unlock the exit
	 */
	public void unlock() {
		locked = !locked;
	}

}