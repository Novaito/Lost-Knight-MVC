package up.l3info.LostKnight.model;

import up.l3info.LostKnight.model.core.character.GameCharacter;
import up.l3info.LostKnight.mvc.Model;

public class CharacterModel implements Model{
	
	private GameCharacter gameCharacter;
	
	public CharacterModel(GameCharacter gc) {
		this.gameCharacter = gc;
	}
	
	public GameCharacter getGameCharacter() {
		return this.gameCharacter;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
