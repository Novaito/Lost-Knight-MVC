package up.l3info.LostKnight.controller.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import up.l3info.LostKnight.model.GameModel;
import up.l3info.LostKnight.model.HeroModel;
import up.l3info.LostKnight.model.LocationModel;
import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.mvc.Model;
import up.l3info.LostKnight.mvc.View;
import up.l3info.LostKnight.view.CharactersView;
import up.l3info.LostKnight.view.GameView;
import up.l3info.LostKnight.view.ItemView;
import up.l3info.LostKnight.view.LocationView;

public class GameController extends Controller<GameModel, GameView>{

	private final SimpleBooleanProperty heroLifeChanged;
	private final SimpleBooleanProperty heroPosChanged;
	
	public GameController(GameModel p_model, GameView p_view, SimpleBooleanProperty heroLifeChanged, SimpleBooleanProperty heroPosChanged) {
		super(p_model, p_view);
		
		this.heroLifeChanged = heroLifeChanged;
		this.heroPosChanged = heroPosChanged;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		for (Controller controller : subControllers) {
			if (controller instanceof ItemController) {
				((ItemController) controller).getView().setOnMouseClicked(event -> {
					useItem(((ItemController) controller).getModel().getName());
				});
			}
		}
		
		
	}
	
	public static GameController create(GameModel game) {
		SimpleBooleanProperty heroLifeChanged = new SimpleBooleanProperty(false);
		SimpleBooleanProperty heroPosChanged = new SimpleBooleanProperty(false);
		
		List<Controller<? extends Model, ? extends View>> subControllers = 
				GameController.createSubcontrollers(game, heroLifeChanged, heroPosChanged);
		
		List<View> subViews = extractSubViews(subControllers);
		
		GameController gameController = 
				new GameController(game, GameView.create(null , (LocationView)subViews.get(1), (CharactersView)subViews.get(0)), heroLifeChanged, heroPosChanged);
		
		
		return gameController;
	}
	
	public static List<Controller<?extends Model, ? extends View>> createSubcontrollers(
			GameModel gameModel,
			SimpleBooleanProperty heroLifeChanged,
			SimpleBooleanProperty heroPosChanged){
		List<Controller<? extends Model, ? extends View>> subControllers = new ArrayList<Controller<? extends Model,? extends View>>();
		subControllers.add(HeroController.create(new HeroModel(gameModel.getHero()), heroLifeChanged)); //TODO il devrait y avoir deux property, life et pos
		subControllers.add(LocationController.create(new LocationModel(gameModel.getCurrentLocation())));
		return subControllers;
	}
	
	
	
	
	private HeroController getHeroController() {
		return (HeroController) subControllers.get(0);
	}
	
	private LocationController getLocationController() {
		return (LocationController) subControllers.get(1);
	}
	
	private void heroMove(int posX, int posY) {
		
	}
	
	private void useItem(String itemName) {
		if(this.model.canUseItem(itemName)) {
			this.model.useItem(itemName);
		}
	}

	

}
