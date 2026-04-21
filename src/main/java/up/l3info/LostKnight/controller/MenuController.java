package up.l3info.LostKnight.controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import up.l3info.LostKnight.controller.gui.GameController;
import up.l3info.LostKnight.model.GameModel;
import up.l3info.LostKnight.model.MenuModel;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.GameView;
import up.l3info.LostKnight.view.MenuView;

public class MenuController extends Controller<MenuModel, MenuView> {

    private final Stage stage;
    private final GameModel gameModel;

    private MenuController(Stage p_stage, GameModel p_gameModel) {
        super(new MenuModel(), MenuView.create());
        this.stage     = p_stage;
        this.gameModel = p_gameModel;
    }

    public static MenuController create(Stage p_stage, GameModel p_gameModel) {
        MenuController ctrl = new MenuController(p_stage, p_gameModel);
        ctrl.init();
        return ctrl;
    }

    //GROS DOUTE, POSSIBLEMENT LE METTRE DANS MODEL
    @Override
    public void init() {
        super.view.getGraphique().setOnAction(e -> launchGraphique());
        super.view.getTextuelle().setOnAction(e -> launchTextuelle());
        super.view.getQuitter().setOnAction(e -> Platform.exit());
    }

    private void launchGraphique() {
        Controller<GameModel, GameView> gameController = GameController.create(this.gameModel);
        this.stage.setScene(new Scene(gameController.getView()));
        this.stage.setTitle("Lost Knight");
    }

    //pas encore fait
    private void launchTextuelle() {
    }
}