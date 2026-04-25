package up.l3info.LostKnight.controller.gui;

import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import up.l3info.LostKnight.model.LevelEditorModel;
import up.l3info.LostKnight.mvc.Controller;
import up.l3info.LostKnight.view.LevelEditorView;

public class LevelEditorController extends Controller<LevelEditorModel, LevelEditorView> {

	private final SimpleStringProperty holdingTexture;
	private final SimpleIntegerProperty tileIndexUpdate;
	private final SimpleBooleanProperty backToMenu;
	
	@Override
	public void init() {}
	
	private LevelEditorController(LevelEditorModel lvlEditModel, 
									LevelEditorView lvlEditView,
									SimpleStringProperty holdingTexture,
									SimpleIntegerProperty tileIndexUpdate,
									SimpleBooleanProperty backToMenu) {
		
		super(lvlEditModel, lvlEditView);
		this.holdingTexture = holdingTexture;
		this.tileIndexUpdate = tileIndexUpdate;
		this.backToMenu = backToMenu;
		
		buildListener();
	}
	
	// TODO : SimpleBooleanProperty backMainMenu param
	public static LevelEditorController create(LevelEditorModel lvlEditModel, SimpleBooleanProperty backToMenu) {
		SimpleStringProperty holdingTexture = new SimpleStringProperty("");
		SimpleIntegerProperty tileIndexUpdate = new SimpleIntegerProperty();
		
		//List<Controller<? extends Model, ? extends View>> subCont = createSubControllers(lvlEditModel);
		
		// Extract datas
		Map<String, List<String>> assetsMap = lvlEditModel.extractAssets("/img/");
		
		LevelEditorController lvlEditController = new LevelEditorController(lvlEditModel, LevelEditorView.create(assetsMap, 15, 15, holdingTexture, tileIndexUpdate), holdingTexture, tileIndexUpdate, backToMenu);
		
		return lvlEditController;
	}
	
	private void buildListener() {
		tileIndexUpdate.addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				((LevelEditorModel) getModel()).updateTileSrc(holdingTexture.getValue(), (int)newValue);
			}
			newValue = -1;
		});
		
		getView().getCancel().setOnAction(e -> {
			backToMenu.set(true);
		});
	}
}
