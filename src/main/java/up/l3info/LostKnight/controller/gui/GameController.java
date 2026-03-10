package up.l3info.LostKnight.controller.gui;

import java.util.ArrayList;
import java.util.List;

import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.mvc.Model;
import up.l3info.LostKnight.mvc.View;
import up.l3info.LostKnight.view.GameView;

public class GameController extends Controller<Game, GameView>{

	public GameController(Game p_model, GameView p_view) {
		super(p_model, p_view);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static GameController create(Game game) {
		List<Controller<? extends Model, ? extends View>> subControllers = GameController.createSubcontrollers();
		List<View> subViews = extractSubViews(subControllers);
		
		GameController gameController = new GameController(game, GameView.create(subViews));
		
		
		return gameController;
	}
	
	public static List<Controller<?extends Model, ? extends View>> createSubcontrollers(){
		List<Controller<? extends Model, ? extends View>> subControllers = new ArrayList<Controller<? extends Model,? extends View>>();
		subControllers.add(HeroController.create(((HeroController) subControllers.get(0)).getModel()));
		return subControllers;
	}
	
	
	
	private HeroController getHeroController() {
		return (HeroController) subControllers.get(0);
	}

	

}
