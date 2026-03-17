package up.l3info.LostKnight.model;

import up.l3info.LostKnight.model.core.items.Food;
import up.l3info.LostKnight.mvc.Model;

public class FoodModel implements Model {

	private Food foodItem;
	
	@Override
	public void run() {}

	private FoodModel(Food food) {
		foodItem = food;
	}
	
	public static FoodModel create(Food food) {
		FoodModel itemModel = new FoodModel(food);
		return itemModel;
	}
	
	public String foodName() {
		return foodItem.getName();
	}
	
	public int foodPoints() {
		return foodItem.getFoodPoints();
	}
	
	public int posXValue() {
		return foodItem.getPosX();
	}
	
	public int posYValue() {
		return foodItem.getPosY();
	}
}
