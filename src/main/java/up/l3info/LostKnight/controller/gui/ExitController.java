package up.l3info.LostKnight.controller.gui;

import up.l3info.LostKnight.model.core.map.Exit;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.ExitView;

public class ExitController extends Controller<Exit, ExitView>{

	public ExitController(Exit p_model, ExitView p_view) {
		super(p_model, p_view);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static ExitController create(Exit exit) {
		
		ExitController exitController = new ExitController(exit, ExitView.create(null, exit.getPosX(), exit.getPosY()));//TODO image
		return exitController;
		
	}

}
