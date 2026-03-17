package up.l3info.LostKnight.controller.gui;

import up.l3info.LostKnight.model.HeroModel;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.CharactersView;

public class HeroController extends Controller<HeroModel, CharactersView>{

	public HeroController(HeroModel p_model, CharactersView p_view) {
		super(p_model, p_view);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static HeroController create(HeroModel hero) {
		HeroController characterController = new HeroController(hero, CharactersView.create(null, hero.posXValue(), hero.posYValue()));//TODO les textures
		
		return characterController;
	}

}
