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
	
	/**
	 * Instantiate correctly the levelEditor
	 * @param lvlEditModel
	 * @param backToMenu
	 * @param isLoading false/true
	 * @return
	 */
	public static LevelEditorController create(LevelEditorModel lvlEditModel, SimpleBooleanProperty backToMenu, boolean isLoading) {
		SimpleStringProperty holdingTexture = new SimpleStringProperty("");
		SimpleIntegerProperty tileIndexUpdate = new SimpleIntegerProperty();
		
		//List<Controller<? extends Model, ? extends View>> subCont = createSubControllers(lvlEditModel);
		
		// Extract datas
		Map<String, List<String>> assetsMap = lvlEditModel.extractAssets("/img/");
		
		LevelEditorController lvlEditController;
		
		if (isLoading) {
			//System.out.println("Controller\n" + +lvlEditModel.getFloorTiles().length);
			//Stream.of(lvlEditModel.getFloorTiles()).forEach(e -> System.out.println(e));
			lvlEditController = new LevelEditorController(lvlEditModel, LevelEditorView.load(assetsMap, lvlEditModel.getFloorTiles(), lvlEditModel.getOthersTiles(), lvlEditModel.getSizeX(), lvlEditModel.getSizeY(), holdingTexture, tileIndexUpdate), holdingTexture, tileIndexUpdate, backToMenu);
		} else {
			lvlEditController = new LevelEditorController(lvlEditModel, LevelEditorView.create(assetsMap, lvlEditModel.getSizeX(), lvlEditModel.getSizeY(), holdingTexture, tileIndexUpdate), holdingTexture, tileIndexUpdate, backToMenu);
		}
		
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
		
		getView().getSave().setOnAction(e -> {
			getModel().save();
		});
	}
}
