package up.l3info.LostKnight.model.command;

import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.mvc.Model;

public class MoveCommand implements Model{
	
	private Game game;
	private int targetX;
	private int targetY;
	
	public MoveCommand(Game game) {
		this.game = game;
	}
	
	public void setTargetX(int x) {
		this.targetX = x;
	}
	
	public void setTargetY(int y) {
		this.targetY = y;
	}
	
	public void setTarget(int x, int y) {
		setTargetX(x);
		setTargetY(y);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
