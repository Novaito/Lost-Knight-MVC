package fr.l3info.lostknight.character;

import fr.l3info.lostknight.items.*;
import fr.l3info.lostknight.spells.*;


/**
 * The abstract class of an attackable character. It gather the Hero and the Enemies.
 * @author Thomas
 */

public abstract class AttackableCharacter extends GameCharacter {

	private int MAX_HP;
	private int hp;
	private int weakness;

	/**
	 * This constructor creates an attackable character with his heal points.
	 * @see GameCharacter
	 * @param name Character's name
	 * @param hp Max heal points that can have the character
	 * @param dialog Character's dialog
	 */
	public AttackableCharacter(String name, int hp, String dialog) {
		super(name, dialog);
		this.hp = hp;
		MAX_HP = hp;
	}

	/**
	 * Permit to a character to attack on other character. The targeted character received or not the damages of the items.
	 * @param character The character targeted
	 * @param item The item used to attack him
	 */
	public void attack(AttackableCharacter character, Item item) {
		if (character != null) {
			if (item instanceof Weapon) {
				character.getAttacked(((Weapon)item).getDamages());
				if (character instanceof Hero) {
					System.out.println("\nOuch this one hurt !");
				} else {
					System.out.println("\nHehe nice one ! He would not last long next time. ");
				}					
				character.showHP();
			} else {
				System.out.println("\n" + item + " doesn't do anything but...    ok.");
			}
		} else {
			System.out.println("Nobody to attack.");
		}
	}

	/**
	 * The character receive the amount of damage and loses HP. 
	 * @param damageReceived The amount of damage to get
	 */
	public void getAttacked(int damageReceived) {
		if (isDead()) {
			System.out.println("Calm down, he's dead mate.");
		} else {
			if (-damageReceived - getWeakness() <= 0) {
				setHp(getHp() - damageReceived - getWeakness());				
			}
		}
	}

	/**
	 * Returns if the character is alive or not.
	 * @return If he's dead.
	 */
	public boolean isDead() {
		return getHp() == 0;
	}

	/**
	 * Permits to cast spell on character.
	 * @param target The character targeted
	 * @param spell The spell to cast
	 */
	public void useSpell(AttackableCharacter target, Spell spell) {
		if (target != null) {
			spell.castSpell(target);
		} else {
			System.out.println("Nobody to cast spell on.");
		}
	}

	/**
	 * Returns the current level of the character's hp.
	 * @return The level of hp
	 */
	public int getHp() {
		return this.hp;
	}

	/**
	 * Set the new current level of hp of the character.
	 * @param hp The current level of hp he has
	 */
	public void setHp(int hp) {
		this.hp = hp;
		if (this.hp > MAX_HP) {
			this.hp = MAX_HP;
		}
		if (this.hp < 0) {
			this.hp = 0;
		}
	}

	/**
	 * Add an amount of hp to the character.
	 * @param amount The amount of hp received (negative or positive)
	 */
	public void regenHP(int amount) {
		if (!isDead()) {
			setHp(hp + amount);;
			
		} else {
			System.out.println(" It's too late mate, you will not bring him back...");
		}
	}
	
	/**
	 * Returns the current level of weakness of the Hero.
	 * @return Level of weakness
	 */
	public int getWeakness() {
		return weakness;
	}
	
	/**
	 * Add an amount of weakness to the character
	 * @param weakness_amount The amount of weakness the character receive (negative or positive)
	 */
	public void addWeakness(int weakness_amount) {
		weakness += weakness_amount;
	}
	
	/**
	 * Prints the character's weakness.
	 */
	public void showWeakness() {
		if (weakness == 0) {
			System.out.println("No particular weakness.");
		} else {
			if (weakness > 0) {
				System.out.println(getName() + " receive \033[91;1m" + weakness + "\033[0;2m more damage points.");
			} else {
				System.out.println(getName() + " receive \033[92;1m" + -weakness + "\033[0;2m less damage points.");
			}
		}
	}
	
	/**
	 * Prints the character's hp.
	 */
	public void showHP() {
		if (isDead()) {
			System.out.println("\033[0;1mDead.\033[0;2m");
		} else {
			System.out.println(getName() + " has \033[0;1m" + getHp() + "\033[0;2m HP.");
		}
	}
}