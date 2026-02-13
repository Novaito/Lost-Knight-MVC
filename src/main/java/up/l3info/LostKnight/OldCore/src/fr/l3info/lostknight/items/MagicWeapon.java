package fr.l3info.lostknight.items;

import fr.l3info.lostknight.character.AttackableCharacter;

//mouais
public class MagicWeapon extends Weapon{

    private Potion potion;

    public MagicWeapon(String name, int damage, String desc , Potion p){
        super(name,damage ,desc);
        this.potion = p;
    }

    public void use(AttackableCharacter attacker, AttackableCharacter target) {
		attacker.attack(target, this);
        potion.use(attacker, attacker);
	}


}
