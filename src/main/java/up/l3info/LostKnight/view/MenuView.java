package up.l3info.LostKnight.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import up.l3info.LostKnight.mvc.View;

public class MenuView extends VBox implements View {

    private final Button graphique;
    private final Button textuelle;
    private final Button levelEditor;
    private final Button quitter;

    private MenuView() {
        super(20);
        setStyle("-fx-background-color:" + DEFAULT_BACKGROUND_COLOR + ";");
        super.setAlignment(Pos.CENTER);
        super.setPadding(new Insets(40));

        Label title = new Label("Lost Knight");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");

        this.graphique = new Button("Mode Graphique");
        this.graphique.setPrefWidth(200);

        this.textuelle = new Button("Mode Textuel");
        this.textuelle.setPrefWidth(200);
        
        this.levelEditor = new Button("Editeur de level");
        this.levelEditor.setPrefWidth(200);
        
        this.quitter = new Button("Quitter");
        this.quitter.setPrefWidth(200);

        super.getChildren().addAll(title, this.graphique, this.textuelle, this.levelEditor, this.quitter);
    }

    public static MenuView create() {
        return new MenuView();
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
    public Button getLevelEditor() {
    	return levelEditor;
    }
    public Button getQuitter(){
        return this.quitter;
    }
}