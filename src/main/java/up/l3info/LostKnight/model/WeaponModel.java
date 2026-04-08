package up.l3info.LostKnight.model;

import up.l3info.LostKnight.model.core.items.Weapon;
import up.l3info.LostKnight.mvc.Model;

public class WeaponModel implements Model {

	private Weapon weaponItem;
	
	@Override
	public void run() {}
	
	public WeaponModel(Weapon weapon) {
		weaponItem = weapon;
	}
	
	/*public static WeaponModel create(Weapon weapon) {
		WeaponModel weaponModel = new WeaponModel(weapon);
		return weaponModel;
	}*/
	
	public String nameWeapon() {
		return weaponItem.getName();
	}
	
	public int damagesValue() {
		return weaponItem.getDamages();
	}
	
	public Weapon getWeapon() {
		return weaponItem;
	}
	

}
