package application;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Tile extends StackPane {

	private static int size;
	private static int arcSize;

	private int number = 2;
	private Rectangle tile;
	private Color color = Color.PEACHPUFF;
	private Text text;
	
	private int x;
	private int y;
	
	private int moveToX;
	private int moveToY;
	private Path path;
	private PathTransition pathTr;
	
	public static final int MOVE_DURATION = 400;
	private static final int SCALE_DURATION1 = 120;
	private static final int SCALE_DURATION2 = 40;
	
	private ScaleTransition scaleTr1;
	private ScaleTransition scaleTr2;
	private SequentialTransition sequenceTr;
	
	private boolean combined = false;

	Tile (int x, int y, int size, int arcSize) {

		tile = new Rectangle(size, size);
		tile.setArcHeight(arcSize);
		tile.setArcWidth(arcSize);
		tile.setFill(color);

		text = new Text(Integer.toString(number));
		if (size > (Game.TILES_SIZE_5X5 - Game.TILES_SPACING_5X5)) {
			
			text.setId("tile-large-text");
		} else if (size < (Game.TILES_SIZE_5X5 - Game.TILES_SPACING_5X5)) {
			
			text.setId("tile-small-text");
		} else {
			
			text.setId("tile-medium-text");
		}
		
		this.getChildren().addAll(tile, text);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.x = x;
		this.y = y;
		this.size = size;
		this.arcSize = arcSize;

		path = new Path();

		pathTr = new PathTransition();
		pathTr.setNode(this);
		pathTr.setDuration(Duration.millis(MOVE_DURATION));
		pathTr.setCycleCount(1);
		pathTr.setAutoReverse(false);
		
		pathTr.setOnFinished(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				setLayout();
			}
		});

		scaleTr1 = new ScaleTransition(Duration.millis(SCALE_DURATION1), tile);
		scaleTr1.setFromX(0.95);
		scaleTr1.setFromY(0.95);
		scaleTr1.setToX(1.05);
		scaleTr1.setToY(1.05);
		
		scaleTr2 = new ScaleTransition(Duration.millis(SCALE_DURATION2), tile);
		scaleTr2.setFromX(1.05);
		scaleTr2.setFromY(1.05);
		scaleTr2.setToX(1);
		scaleTr2.setToY(1);
		
		sequenceTr = new SequentialTransition();
		sequenceTr.getChildren().addAll(scaleTr1, scaleTr2);
	}
	
	

	public void getNextTile() {

		number *= 2;

		text.setText(Integer.toString(number));

		switch (number) {

		case 4:
			color = Color.THISTLE;
			break;
		case 8:
			color = Color.PINK;
			break;
		case 16:
			color = Color.PLUM;
			break;
		case 32:
			color = Color.PALEVIOLETRED;
			break;
		case 64:
			color = Color.MEDIUMVIOLETRED;
			break;
		case 128:
			color = Color.MEDIUMTURQUOISE;
			if (size > (Game.TILES_SIZE_5X5 - Game.TILES_SPACING_5X5)) {
				
				text.setStyle("-fx-font-size: 46px");
			} else if (size < (Game.TILES_SIZE_5X5 - Game.TILES_SPACING_5X5)) {
				
				text.setStyle("-fx-font-size: 30px");
			} else {
				
				text.setStyle("-fx-font-size: 36px");
			}
			break;
		case 256:
			color = Color.LIGHTSEAGREEN;
			break;
		case 512:
			color = Color.TEAL;
			break;
		case 1024:
			color = Color.DARKCYAN;
			if (size > (Game.TILES_SIZE_5X5 - Game.TILES_SPACING_5X5)) {
				
				text.setStyle("-fx-font-size: 36px");
			} else if (size < (Game.TILES_SIZE_5X5 - Game.TILES_SPACING_5X5)) {
				
				text.setStyle("-fx-font-size: 24px");
			} else {
				
				text.setStyle("-fx-font-size: 28px");
			}
			break;
		case 2048:
			color = Color.CYAN;
			break;
		}

		tile.setFill(color);
	}
	public void setMoveToX(int x) {
		
		moveToX = x - this.x + size / 2;
	}
	
	public void setMoveToY(int y) {
		
		moveToY = y - this.y + size / 2; 
	}

	public int getNumber() {

		return number;
	}
	public void setLayout() {
		
		this.setTranslateX(0);
		this.setTranslateY(0);
		this.relocate(x, y);
	}
	public void setCombined(boolean value) {
		
		combined = value;
	}
	public boolean getCombined() {
		
		return combined;
	}
	
	public void moveTo() {

		path.getElements().clear();
		path.getElements().add(new MoveTo(size / 2, size / 2));
		path.getElements().add(new LineTo(moveToX, moveToY));

		pathTr.setPath(path);

		x += moveToX - size / 2;
		y += moveToY - size / 2;

		pathTr.play();
	}
	
	public void playAnimation() {
		
		sequenceTr.play();
	}
}
