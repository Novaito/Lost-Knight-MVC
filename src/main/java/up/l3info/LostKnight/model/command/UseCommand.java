package up.l3info.LostKnight.model.command;

import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.mvc.Model;

public class UseCommand implements Model{
	
	private Game game;
	private String target;
	private String itemName;
	
	public UseCommand(Game game) {
		this.game = game;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Override
	public void run() {
		// TODO
		
	}

}
