package up.l3info.LostKnight.model.command;

import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.mvc.Model;

public class GoCommand implements Model{
	
	
	private Game game;
	private String exitName;
	
	public GoCommand(Game game) {
		this.game = game;
	}
	
	public void setExitName(String exitName) {
		this.exitName = exitName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
