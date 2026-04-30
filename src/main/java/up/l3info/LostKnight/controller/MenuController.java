package up.l3info.LostKnight.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;
import up.l3info.LostKnight.controller.gui.GameController;
import up.l3info.LostKnight.controller.gui.LevelEditorController;
import up.l3info.LostKnight.model.GameModel;
import up.l3info.LostKnight.model.LevelEditorModel;
import up.l3info.LostKnight.model.MenuModel;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.GameView;
import up.l3info.LostKnight.view.LevelEditorView;
import up.l3info.LostKnight.view.MenuView;

import up.l3info.LostKnight.model.core.levelEditor.GameLoader;
import up.l3info.LostKnight.model.core.map.Location;
import up.l3info.LostKnight.model.core.character.Hero;
import up.l3info.LostKnight.model.core.game.Game;
import up.l3info.LostKnight.model.core.items.Weapon;
import java.io.IOException;
import java.io.File;

public class MenuController extends Controller<MenuModel, MenuView> {

	private static final int DEFAULT_SIZE_EDITOR = 5;

    private final Stage stage;
    private final GameModel gameModel;
    private final SimpleBooleanProperty backToMenu;
    private Scene menuScene;

    private MenuController(Stage p_stage, GameModel p_gameModel) {
        super(new MenuModel(), MenuView.create(DEFAULT_SIZE_EDITOR, DEFAULT_SIZE_EDITOR));
        this.stage     = p_stage;
        this.gameModel = p_gameModel;
        this.backToMenu = new SimpleBooleanProperty(false);
    }

    public static MenuController create(Stage p_stage, GameModel p_gameModel) {
        MenuController ctrl = new MenuController(p_stage, p_gameModel);
        ctrl.init();
        ctrl.initLaunch();
        return ctrl;
    }

    //GROS DOUTE, POSSIBLEMENT LE METTRE DANS MODEL
    @Override
    public void init() {
    	getView().getGraphique().setOnAction(e -> launchGraphique());
        getView().getTextuelle().setOnAction(e -> launchTextuelle());
        getView().getLevelEditorCreating().setOnAction(e -> launchEditorCreating());
        getView().getLevelEditorLoading().setOnAction(e -> launchEditorLoading());
        getView().getQuitter().setOnAction(e -> Platform.exit());
        getView().getPlayLevel().setOnAction(e -> launchGameFromLevel());
        
        backToMenu.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Platform.runLater(() -> {
                    initLaunch();
                    backToMenu.set(false);
                });
            }
        });
    }
    
    private void initLaunch() {
    	if (menuScene == null) {
    		menuScene = new Scene(getView());
    	}
    	stage.setScene(menuScene);
    	stage.setTitle("Home Menu");
    }

    private void launchGraphique() {
        Controller<GameModel, GameView> gameController = GameController.create(this.gameModel);
        this.stage.setScene(new Scene(gameController.getView()));
        this.stage.setTitle("Game");
    }
    
    private void launchEditorCreating() {
    	getView().showCreateSection();
    	stage.minWidthProperty().bind(getView().widthProperty());
    	stage.minHeightProperty().bind(getView().heightProperty());
    	
    	getView().getConfirmCreateButton().setOnAction(e -> {
    		int sizeX = (int)getView().xBlocks();
    		int sizeY = (int)getView().yBlocks();
    		Controller<LevelEditorModel, LevelEditorView> lvlController = LevelEditorController.create(new LevelEditorModel(gameModel.getGame(), getView().createdName(), sizeX, sizeY, false), backToMenu, false);
    		stage.setScene(new Scene(lvlController.getView()));
    		stage.setTitle("Level Editor");
    	});
    	
    }
    
    private void launchEditorLoading() {
    	getView().showLoadSection();
    	stage.minWidthProperty().bind(getView().widthProperty());
    	stage.minHeightProperty().bind(getView().heightProperty());
    	
    	getView().getConfirmLoadButton().setOnAction(e -> {
    		LevelEditorModel lvlEditModel = new LevelEditorModel(gameModel.getGame(), getView().loadedName(), 5, 5, true);
    		Controller<LevelEditorModel, LevelEditorView> lvlController = LevelEditorController.create(lvlEditModel, backToMenu, true);
    		stage.setScene(new Scene(lvlController.getView()));
    		stage.setTitle("Level Editor");
    	});
    }

    //pas encore fait
    private void launchTextuelle() {
    }

    private void launchGameFromLevel() {
        getView().showLoadSection();
        stage.minWidthProperty().bind(getView().widthProperty());
        stage.minHeightProperty().bind(getView().heightProperty());

        getView().getConfirmLoadButton().setOnAction(e -> {
            try {
                String levelName = getView().loadedName();
                GameLoader loader = new GameLoader(new File("./save/" + levelName + ".json"));
                Location spawn = loader.load(levelName);

                Hero hero = new Hero("Hero", 100, "/img/profileHero.png", "hello im hero", 1, 1);
                hero.setWeapon(new Weapon("sword", 20, 0, 0));
                hero.setHp(100);

                Game game = new Game(hero, spawn);
                GameModel freshModel = new GameModel(game);

                Controller<GameModel, GameView> gameController = GameController.create(freshModel);
                stage.setScene(new Scene(gameController.getView()));
                stage.setTitle("Game");
            } catch (IOException ex) {
                System.out.println(">>> Erreur chargement level : " + ex.getMessage());
            }
        });
    }

}