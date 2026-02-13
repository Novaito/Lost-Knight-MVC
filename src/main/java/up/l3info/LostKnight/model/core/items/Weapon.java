package up.l3info.LostKnight.model.core.items;

import up.l3info.LostKnight.model.core.character.AttackableCharacter;

/**
 * This class represents a Weapon in the game
 * 
 * @author Noé
 */
public class Weapon extends Item {

	private int damage;
	private static final String DEFAULT_DESC = "This is a %s. It does %d damages per attack";

	/**
	 * This constructor creates a weapon with a name, its damages amount, and a custom description
	 * 
	 * @param name The name of the weapon
	 * @param damage The damages the weapon inflicts
	 * @param desc The custom description of the weapon
	 */
	public Weapon(String name, int damage, String desc) {
		super(name, desc);
		this.damage = damage;
	}
	
	/**
	 * This constructor creates a weapon with a name, its damages amount and a default description 
	 * 
	 * @param name The name of the weapon
	 * @param damage The damages the weapon inflicts
	 */
	public Weapon(String name, int damage) {
		this(name, damage, String.format(DEFAULT_DESC, name, damage));
		this.damage = damage;
	}

	/**
	 * Implementation of the LookableItem interface
	 */
	@Override
	public void look() {
		System.out.println(getDescription());
		
	}
	
	/**
	 * Returns the damages that inflict this weapon
	 * @return The damages that inflict this weapon
	 */
	public int getDamages() {
		return damage;
	}
	
	/**
	 * The attacker attacks the target with the weapon. Calls the attack function of the attacker.
	 * 
	 * @param attacker The character that attacks
	 * @param target The character that gets attacked
	 */
	public void use(AttackableCharacter attacker, AttackableCharacter target) {
		attacker.attack(target, this);
	}
	
	/**
	 * Gives the name and the damages of the weapon
	 */
	@Override 
	public String toString() {
		return "\033[93;1m" + this.getName() + "\033[0;0m : " + getDamages() + " damage points";
	}
}