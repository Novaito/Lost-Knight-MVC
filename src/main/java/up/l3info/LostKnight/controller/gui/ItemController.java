package up.l3info.LostKnight.controller.gui;

import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.ItemView;

public class ItemController extends Controller<Item, ItemView>{

	public ItemController(Item p_model, ItemView p_view) {
		super(p_model, p_view);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static ItemController create(Item item) {
		
		ItemController itemController = new ItemController(item, ItemView.create(null, item.getPosX(), item.getPosY()));
		
		return itemController;
		
	}

}
