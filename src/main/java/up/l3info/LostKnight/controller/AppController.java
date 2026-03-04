package up.l3info.LostKnight.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;
import up.l3info.LostKnight.model.AppModel;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.mvc.Model;
import up.l3info.LostKnight.mvc.View;
import up.l3info.LostKnight.view.AppTextualView;

public abstract class AppController extends Controller<Model, View>{
	
	private final Stage mainStage;

	private AppController(Stage primaryStage, View p_view) {
		super(new AppModel(), p_view);
		mainStage = primaryStage;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public static AppController create(Stage primaryStage, Boolean displayGUI) {
		List<Controller<Model, View>> subControllers;
		if (displayGUI) {
			subControllers = AppController.createGUISubControllers();
		} else {
			subControllers = AppController.createTextualSubControllers();
		}
		
		List<View> subViews = AppController.extractViewFromControllers(subControllers);
		
		AppTextualView appView = AppTextualView.create(subViews);
		
		AppController appController = new AppController(primaryStage, appView);
		return appController;
	}
	
	private static List<View> extractViewFromControllers(List<Controller<Model, View>> subControllers) {
		List<View> views = new ArrayList<View>();
		for (Controller<Model, View> c: subControllers) {
			views.add(c.getView());
		}
		return views;
	}
	
	private static List<Controller<Model, View>> createGUISubControllers() {
		List<Controller<Model, View>> subControllers = new ArrayList<>();
		// ---> Ajout des enfants
		return subControllers;
	}
	
	/**
	 * Instancie les sous-controllers de la version texte
	 * @return Liste de sous-controllers
	 */
	private static List<Controller<Model, View>> createTextualSubControllers() {
		List<Controller<Model, View>> subControllers = new ArrayList<>();
		// ----> Ajout des enfants
		return subControllers;
	}
	
	public abstract void executeGoCommand();
	
	public abstract void executeHelpCommand();
	
	public abstract void executeUseCommand();
	
	public abstract void executeTakeCommand();
	
	public abstract void executeAttackCommand();
	
	public abstract void executeMoveCommand();
	
	
}
