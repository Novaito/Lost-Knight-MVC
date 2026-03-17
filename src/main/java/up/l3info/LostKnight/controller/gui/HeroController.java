package up.l3info.LostKnight.controller.gui;

import javafx.beans.property.SimpleBooleanProperty;
import up.l3info.LostKnight.model.HeroModel;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.CharactersView;

public class HeroController extends Controller<HeroModel, CharactersView>{
	
	private final SimpleBooleanProperty ONCHANGE;

	private HeroController(HeroModel p_model, CharactersView p_view, SimpleBooleanProperty onChange) {
		super(p_model, p_view);
		ONCHANGE = onChange;
	}

	@Override
	public void init() {
		ONCHANGE.addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				getView().setX(getModel().posXValue());
				getView().setY(getModel().posYValue());
				getView().setHp(getModel().hpPercentage());
			}
		});
	}
	
	public static HeroController create(HeroModel hero, SimpleBooleanProperty onChange) {
		HeroController characterController = new HeroController(
											hero, 
											CharactersView.create("/img/shKnightStand.png", hero.posXValue(), hero.posYValue(), hero.hpPercentage()),
											onChange);
		characterController.init();
		return characterController;
	}

}
