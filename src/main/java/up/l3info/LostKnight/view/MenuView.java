package up.l3info.LostKnight.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import up.l3info.LostKnight.mvc.View;

public class MenuView extends HBox implements View {

    private final Button graphique;
    private final Button textuelle;
    private final Button levelEditorCreating;
    private final Button levelEditorLoading;
    private final Button quitter;
    private ConfigSection createSection;
    private ConfigSection loadSection;
    private VBox main;

    private MenuView(int sizeX, int sizeY) {
    	super(40);
        main = new VBox(20);
        
        setStyle("-fx-background-color:" + DEFAULT_BACKGROUND_COLOR + ";");
        super.setAlignment(Pos.CENTER);
        super.setPadding(new Insets(50, 70, 50, 70));
        setMinHeight(400);

        Label title = new Label("Lost Knight");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");

        this.graphique = new Button("Mode Graphique");
        this.graphique.setPrefWidth(200);

        this.textuelle = new Button("Mode Textuel");
        this.textuelle.setPrefWidth(200);
        
        levelEditorCreating = new Button("Créer un level");
        levelEditorCreating.setPrefWidth(200);
        
        levelEditorLoading = new Button("Charger un level");
        levelEditorLoading.setPrefWidth(200);
        
        createSection = ConfigSection.create(sizeX, sizeY, false);
        createSection.setMaxWidth(200);
        createSection.confirmButton().setPrefWidth(200);
        
        loadSection = ConfigSection.create(sizeX, sizeY, true);
        loadSection.setMaxWidth(200);
        loadSection.confirmButton().setPrefWidth(200);
        
        this.quitter = new Button("Quitter");
        this.quitter.setPrefWidth(200);

        main.getChildren().addAll(title, this.graphique, this.textuelle, levelEditorCreating, levelEditorLoading, this.quitter);
        getChildren().addAll(new Spring(), main, new Spring());
    }

    public static MenuView create(int sizeX, int sizeY) {
        return new MenuView(sizeX, sizeY);
    }

    @Override
    public void hide(){super.setVisible(false); }

    @Override
    public void show() {super.setVisible(true); }

    public Button getGraphique(){
        return this.graphique;
    }
    public Button getTextuelle(){
        return this.textuelle;
    }
    public Button getLevelEditorCreating() {
    	return levelEditorCreating;
    }
    public Button getLevelEditorLoading() {
    	return levelEditorLoading;
    }
    public Button getQuitter(){
        return this.quitter;
    }
    
    public void showLoadSection() {
    	main.getChildren().add(main.getChildren().size() - 1, loadSection);
    }
    
    public void showCreateSection() {
    	main.getChildren().add(4, createSection);
    }
    
    public Button getConfirmCreateButton() {
    	return createSection.confirmButton();
    }
    
    public Button getConfirmLoadButton() {
    	return loadSection.confirmButton();
    }
    
    public Integer xBlocks() {
		return createSection.xBlocks(); 
	}
	
	public Integer yBlocks() {
		return createSection.yBlocks();
	}
	
	public String createdName() {
		return createSection.getLocationName();
	}
	
	public String loadedName() {
		return loadSection.getLocationName();
	}
}