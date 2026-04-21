package up.l3info.LostKnight.view;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import up.l3info.LostKnight.mvc.View;

public class TilesView extends StackPane implements View {
	
	public final int INDEX;
	private String primTexture;
	private String sndTexture;
	private Rectangle primRect;
	private Rectangle sndRect;

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void show() {
		setVisible(true);
	}
	
	private TilesView(String primSrc, String sndTexture, int index) {
		super();
		setMaxSize(64, 64);
		primRect = new Rectangle(64, 64);
		sndRect = new Rectangle(64, 64);		
		this.INDEX = index;
		this.primTexture = primSrc;
		this.sndTexture = sndTexture;
		
		getChildren().add(primRect);
	}
	
	private TilesView(String primSrc, String sndTexture, double size, int index) {
		super();
		setMaxSize(size, size);
		primRect = new Rectangle(size, size);
		sndRect = new Rectangle(size, size);		
		this.INDEX = index;
		this.primTexture = primSrc;
		this.sndTexture = sndTexture;
		
		getChildren().add(primRect);
	}
	
	public static TilesView create(String primSrc, String sndSrc, int index) {
		TilesView tile = new TilesView(primSrc, sndSrc, index);
		tile.init();
		return tile;
	}
	
	public static TilesView create(String primSrc, String sndSrc, double size, int index) {
		TilesView tile = new TilesView(primSrc, sndSrc, size, index);
		tile.init();
		return tile;
	}
	
	private void init() {
		setTile(primTexture);
		if (sndTexture != null) setSndTile(sndTexture);
	}
	
	public void setTile(String imgSrc) {
		ImagePattern img = new ImagePattern(new Image(imgSrc));
		primRect.setFill(img);
	}
	
	public void setSndTile(String imgSrc) {
		ImagePattern img = new ImagePattern(new Image(imgSrc));
		sndRect.setFill(img);
		if (getChildren().size() < 2) getChildren().add(sndRect);
	}
	
	public String getPrimaryTexture() {
		return primTexture;
	}
	
	public String getSecondTexture() {
		return sndTexture;
	}
}
