package up.l3info.LostKnight.controller.gui;

import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.CharactersView;

public class HeroController extends Controller<Hero, CharactersView>{

	public HeroController(Hero p_model, CharactersView p_view) {
		super(p_model, p_view);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static HeroController create(Hero hero) {
		HeroController characterController = new HeroController(hero, CharactersView.create(null, hero.getPosX(), hero.getPosY()));//TODO les textures
		
		return characterController;
	}

}
