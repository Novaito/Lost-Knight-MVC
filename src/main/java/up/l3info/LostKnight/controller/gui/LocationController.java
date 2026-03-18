package up.l3info.LostKnight.controller.gui;

import java.util.ArrayList;
import java.util.List;

import up.l3info.LostKnight.model.LocationModel;
import up.l3info.LostKnight.model.core.character.GameCharacter;
import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.map.Exit;
import up.l3info.LostKnight.model.core.map.Location;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.mvc.Model;
import up.l3info.LostKnight.mvc.View;
import up.l3info.LostKnight.view.LocationView;

public class LocationController extends Controller<LocationModel, LocationView>{
	
	private static List<Controller<? extends Model, ? extends View>> subControllers;

	public LocationController(LocationModel loc, LocationView locView) {
		super(loc, locView);
	}
	
	public static LocationController create(LocationModel loc) {
		
		List<Controller<? extends Model, ? extends View>> subCont = createSubControllers(loc);
		
		LocationController locationController = new LocationController(loc, LocationView.create(null, extractSubViews(subCont)));
		
		return locationController;
	}
	
	private static List<Controller<? extends Model, ? extends View>> createSubControllers(LocationModel locationModel){
		
		Location loc = locationModel.getLocation();
		
		subControllers = new ArrayList<>();
		for (Item item : loc.getItems().values()) {
			subControllers.add(ItemController.create(item));
		}
		
		for (Exit exit : loc.getExits().values()) {
			subControllers.add(ExitController.create(exit));
		}
		
		for (GameCharacter character : loc.getCharacters().values()) {
			subControllers.add(CharacterController.create(character));
		}
		
		return subControllers;
		
	}
	
	public List<Controller<? extends Model, ? extends View>> getSubControllers() {
		return subControllers;
	}
	
	public void setNewSubControllers(List<Controller<? extends Model, ? extends View>> newSubControllers) {
		subControllers = newSubControllers;
	}
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
