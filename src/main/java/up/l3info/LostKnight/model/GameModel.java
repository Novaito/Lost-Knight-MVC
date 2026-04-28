package up.l3info.LostKnight.model;

import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.model.core.map.Location;
import up.l3info.LostKnight.mvc.Model;

public class GameModel implements Model{
	
	private Game game;
	
	public GameModel(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean canUseItem(String itemName) {
		return game.isItemReachable(itemName);
	}
	
	public void useItem(String itemName) {
		game.use(itemName, "hero");
	}
	
	public Hero getHero() {
		return game.getHero();
	}
	
	public Location getCurrentLocation() {
		return game.getCurrentLocation();
	}
	

	public boolean canHeroMove(int posX, int posY) {
		return game.canHeroMove(posX, posY);
	}

	public void heroMove(int posX, int posY) {
		this.game.heroMove(posX, posY);
		
	}
	
	public boolean characterExists(String characterName) {
		return game.characterExists(characterName);
	}
	
	public boolean isCharacterReachable(String characterName) {
		return game.isCharacterReachable(characterName);
	}
	
	public void heroAttack(String characterName) {
		game.attack("hero", characterName);
	}
	
	/**
	 * For LevelEditor usage only /!\/!\
	 * @return the current game core
	 */
	public Game getGame() {
		return game;
	}
	
	

}
