package up.l3info.LostKnight.model.core.items;

import up.l3info.LostKnight.model.core.character.AttackableCharacter;

public class MagicWeapon extends Weapon{

    private Potion potion;

    /**
     * This constructor creates a magic weapon with a name, its damages amount, a custom description, its magic power, posX and posY
     *
     * @param name The name of the weapon
     * @param damage The damages the weapon inflicts
     * @param desc The custom description of the weapon
     * @param posX X position
     * @param posY Y position
     */
    public MagicWeapon(String name, int damage, String desc , Potion p,int posX, int posY){
        super(name,damage ,desc,posX, posY);
        this.potion = p;
    }

    public void use(AttackableCharacter attacker, AttackableCharacter target) {
		attacker.attack(target, this);
        potion.use(attacker, attacker);
	}


}
