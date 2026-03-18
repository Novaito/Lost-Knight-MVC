package up.l3info.LostKnight.controller.gui;

import up.l3info.LostKnight.model.WeaponModel;
import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.ItemView;

public class WeaponController extends Controller<WeaponModel, ItemView>{

	public WeaponController(WeaponModel p_model, ItemView p_view) {
		super(p_model, p_view);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static WeaponController create(WeaponModel weaponModel) {
		
		WeaponController itemController = new WeaponController(weaponModel, ItemView.create(null, weaponModel.getWeapon().getPosX(), weaponModel.getWeapon().getPosY()));
		
		return itemController;
		
	}

}
