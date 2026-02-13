package fr.l3info.lostknight.items;

import fr.l3info.lostknight.character.*;

/**
 * This class represents the Food in the game. It extends Consumnable.
 * 
 * @author Noé
 */
public class Food extends Consumnable {

	private int FOOD_POINTS;
	private static final String DEFAULT_DESC = "You see a \u001B[1m%s\u001B[0m. It looks like something you could eat.";
	
	/**
	 * This constructor creates a food item with its name, its foodPoints amount and a custom description.
	 * 
	 * @param name The name of the food
	 * @param foodPoints the amount of hunger this food regenerates
	 * @param desc The custom description of this food
	 */
	public Food(String name, int foodPoints, String desc) {
		super(name, desc);
		this.FOOD_POINTS = foodPoints;
	}
	
	/**
	 * This constructor creates a food item with its name, its foodPoints amount and a default description.
	 * 
	 * @param name The name of the food
	 * @param foodPoints the amount of hunger this food regenerates
	 */
	public Food(String name, int foodPoints) {
		this(name, foodPoints, String.format(DEFAULT_DESC, name));
	}
	
	/**
	 * Returns the food points of the food
	 * 
	 * @return the food points the food regenerates
	 */
	public int getFoodPoints() {
		return FOOD_POINTS;
	}

	/**
	 * Uses the food. Removes it from the character inventory and add the amount of food of the food item to the hunger
	 * 
	 * @param character The character that uses the food
	 * @param target The character to use the food on
	 */
	public void use(AttackableCharacter character, AttackableCharacter target) {
		if(character instanceof Hero && target instanceof Hero) {
			((Hero) target).feed(FOOD_POINTS);
			((Hero) target).showHunger();
			((Hero)character).getInventory().removeItem(this);
		} else {
			System.out.println("Warning: you can only use food on yourself");
		}
	}

	/**
	 * Implementation of the food interface
	 */
	@Override
	public void look() {
		// TODO Auto-generated method stub
		System.out.println(getDescription());
		
	}
	
	/**
	 * Give the name of the food and its food points
	 */
	@Override
	public String toString() {
		return "\033[93;1m" + this.getName() + "\033[0;0m : " + getFoodPoints() + " food points";
	}


}