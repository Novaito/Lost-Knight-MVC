package up.l3info.LostKnight.model.core.items;

import up.l3info.LostKnight.model.core.character.AttackableCharacter;

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
