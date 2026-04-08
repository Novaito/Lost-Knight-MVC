package up.l3info.LostKnight.controller.gui;

import javafx.beans.property.SimpleBooleanProperty;
import up.l3info.LostKnight.model.HeroModel;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.CharactersView;

public class HeroController extends Controller<HeroModel, CharactersView>{
	
	private final SimpleBooleanProperty ON_LIFE_CHANGE;
	private final SimpleBooleanProperty ON_POS_CHANGE;

	private HeroController(HeroModel p_model, CharactersView p_view, SimpleBooleanProperty onLifeChange, SimpleBooleanProperty onPosChange) {
		super(p_model, p_view);
		ON_LIFE_CHANGE = onLifeChange;
		ON_POS_CHANGE = onPosChange;
	}

	@Override
	public void init() {
		ON_LIFE_CHANGE.addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				getView().setHp(getModel().hpPercentage());
				ON_LIFE_CHANGE.set(false);
			}
		});
		
		ON_POS_CHANGE.addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				getView().setX(getModel().posXValue());
				getView().setY(getModel().posYValue());
				ON_POS_CHANGE.set(false);
			}
		});
	}
	
	public static HeroController create(HeroModel hero, SimpleBooleanProperty onLifeChange, SimpleBooleanProperty onPosChange) {
		HeroController characterController = new HeroController(
											hero, 
											CharactersView.create("/img/shKnightStand.png", hero.posXValue(), hero.posYValue(), hero.hpPercentage()),
											onLifeChange,
											onPosChange);
		characterController.init();
		return characterController;
	}

}
