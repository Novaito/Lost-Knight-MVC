package up.l3info.LostKnight.model;

import up.l3info.LostKnight.mvc.Model;

public class ItemModel implements Model {

	@Override
	public void run() {}

	
	public static ItemModel create() {
		ItemModel itemModel = new ItemModel();
		return itemModel;
	}
	
	public void use(HeroModel hero) {
		
	}
}
