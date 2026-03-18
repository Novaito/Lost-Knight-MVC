package up.l3info.LostKnight.model;

import up.l3info.LostKnight.model.core.map.Location;
import up.l3info.LostKnight.mvc.Model;

public class LocationModel implements Model{
	
	private Location location;
	
	public LocationModel(Location loc) {
		this.location = loc;
	}
	
	public Location getLocation() {
		return location;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
