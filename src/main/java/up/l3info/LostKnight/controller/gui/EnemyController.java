package up.l3info.LostKnight.controller.gui;

import up.l3info.LostKnight.model.EnemyModel;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.CharactersView;

public class EnemyController extends Controller<EnemyModel, CharactersView> {

	public EnemyController(EnemyModel p_model, CharactersView p_view) {
		super(p_model, p_view);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static EnemyController create(EnemyModel enemy) {
		EnemyController characterController = 
				new EnemyController(enemy, CharactersView.create("/img/mechant_placeholder.png", enemy.getGameCharacter().getPosX(), enemy.getGameCharacter().getPosY(), enemy.hpPercentage()));//TODO les textures
		
		return characterController;
	}
	
	
	
	

}
