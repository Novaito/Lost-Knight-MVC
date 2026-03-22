package up.l3info.LostKnight.model;

import up.l3info.LostKnight.model.core.character.Enemy;
import up.l3info.LostKnight.model.core.character.GameCharacter;
import up.l3info.LostKnight.mvc.Model;

public class EnemyModel implements Model{
	
	private Enemy enemy;
	
	public EnemyModel(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public GameCharacter getGameCharacter() {
		return this.enemy;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		return enemy.getName();
	}
	
	public double hpPercentage() {
		return (double)enemy.getHp() / enemy.getMaxHp();
	}
	
	public int hpValue() {
		return enemy.getHp();
	}

}
