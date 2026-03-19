package up.l3info.LostKnight.controller.gui;

import java.util.ArrayList;
import java.util.List;

import up.l3info.LostKnight.model.CharacterModel;
import up.l3info.LostKnight.model.FoodModel;
import up.l3info.LostKnight.model.LocationModel;
import up.l3info.LostKnight.model.WeaponModel;
import up.l3info.LostKnight.model.core.character.GameCharacter;
import up.l3info.LostKnight.model.core.items.Food;
import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.items.Weapon;
import up.l3info.LostKnight.model.core.map.Exit;
import up.l3info.LostKnight.model.core.map.Location;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.mvc.Model;
import up.l3info.LostKnight.mvc.View;
import up.l3info.LostKnight.view.LocationView;

public class LocationController extends Controller<LocationModel, LocationView>{
	

	public LocationController(LocationModel loc, LocationView locView, List<Controller<? extends Model, ? extends View>> subControllers) {
		super(loc, locView);
		this.subControllers = subControllers;
	}
	
	public static LocationController create(LocationModel loc) {
		
		List<Controller<? extends Model, ? extends View>> subCont = createSubControllers(loc);
		
		LocationController locationController = new LocationController(loc, LocationView.create("/img/locationTest.png", extractSubViews(subCont)), subCont);
		
		return locationController;
	}
	
	private static List<Controller<? extends Model, ? extends View>> createSubControllers(LocationModel locationModel){
		
		Location loc = locationModel.getLocation();
		
		List<Controller<? extends Model, ? extends View>> subControllers = new ArrayList<>();
		for (Item item : loc.getItems().values()) {
			if (item instanceof Food) {				
				subControllers.add(FoodController.create(new FoodModel((Food)item)));
			}else if(item instanceof Weapon) {
				subControllers.add(WeaponController.create(new WeaponModel((Weapon)item)));
			}
		}
		
		for (Exit exit : loc.getExits().values()) {
			subControllers.add(ExitController.create(exit));
		}
		
		for (GameCharacter character : loc.getCharacters().values()) {
			subControllers.add(CharacterController.create(new CharacterModel(character)));
		}
		
		return subControllers;
		
	}
	
	public void updateSubControllers() {
		subControllers = createSubControllers(getModel());
		//TODO update les subviews de locationView

	}
	
	public List<Controller<? extends Model, ? extends View>> getSubControllers() {
		return subControllers;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
