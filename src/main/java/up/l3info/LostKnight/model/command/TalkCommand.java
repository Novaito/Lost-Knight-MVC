package up.l3info.LostKnight.model.command;

import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.mvc.Model;

public class TalkCommand implements Model{
	
	private String talkTarget;
	private Game game;
	
	public TalkCommand(Game game) {		
		this.game = game;
	}
	
	public void setTalkTarget(String s) {
		talkTarget = s;
	}

	@Override
	public void run() {
		//TODO
	}

}
