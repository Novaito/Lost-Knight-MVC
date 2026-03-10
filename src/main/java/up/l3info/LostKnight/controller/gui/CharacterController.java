package up.l3info.LostKnight.controller.gui;

import up.l3info.LostKnight.model.core.character.GameCharacter;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.CharactersView;

public class CharacterController extends Controller<GameCharacter, CharactersView> {

	public CharacterController(GameCharacter p_model, CharactersView p_view) {
		super(p_model, p_view);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static CharacterController create(GameCharacter character) {
		CharacterController characterController = new CharacterController(character, CharactersView.create(null, character.getPosX(), character.getPosY()));//TODO les textures
		
		return characterController;
	}
	
	
	
	

}
