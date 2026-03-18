package up.l3info.LostKnight.controller.gui;

import up.l3info.LostKnight.model.FoodModel;
import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.ItemView;

public class FoodController extends Controller<FoodModel, ItemView>{

	public FoodController(FoodModel p_model, ItemView p_view) {
		super(p_model, p_view);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static FoodController create(FoodModel foodModel) {
		//TODO graphic
		FoodController itemController = new FoodController(foodModel, ItemView.create("/img/porc.png", foodModel.getFood().getPosX(), foodModel.getFood().getPosY()));
		
		return itemController;
		
	}

}
