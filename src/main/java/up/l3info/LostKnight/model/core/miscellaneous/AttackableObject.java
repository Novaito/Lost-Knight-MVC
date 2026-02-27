package up.l3info.LostKnight.model.core.miscellaneous;

import up.l3info.LostKnight.model.core.character.AttackableCharacter;
import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.model.core.items.Item;
import up.l3info.LostKnight.model.core.items.Weapon;
import up.l3info.LostKnight.model.core.spells.Spell;

public interface AttackableObject {

    public void attack(AttackableCharacter character, Item item);

    /**
     * The character receive the amount of damage and loses HP.
     * @param damageReceived The amount of damage to get
     */
    public void getAttacked(int damageReceived);

    /**
     * Returns if the character is alive or not.
     * @return If he's dead.
     */
    public boolean isDead();

    /**
     * Permits to cast spell on character.
     * @param target The character targeted
     * @param spell The spell to cast
     */
    public void useSpell(AttackableCharacter target, Spell spell);

    /**
     * Returns the current level of the character's hp.
     * @return The level of hp
     */
    public int getHp();

    /**
     * Set the new current level of hp of the character.
     * @param hp The current level of hp he has
     */
    public void setHp(int hp);

    /**
     * Add an amount of hp to the character.
     * @param amount The amount of hp received (negative or positive)
     */
    public void regenHP(int amount);

    /**
     * Returns the current level of weakness of the Hero.
     * @return Level of weakness
     */
    public int getWeakness();


    /**
     * Add an amount of weakness to the character
     * @param weakness_amount The amount of weakness the character receive (negative or positive)
     */
    public void addWeakness(int weakness_amount);
    /**
     * Prints the character's weakness.
     */
    public void showWeakness();
    /**
     * Prints the character's hp.
     */
    public void showHP();
}
