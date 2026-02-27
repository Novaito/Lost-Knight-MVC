package up.l3info.LostKnight.model.command;

import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.mvc.Model;

public class TakeCommand implements Model{

	
	private Game game;
	private String itemName;
	
	public TakeCommand(Game game) {
		this.game = game;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
