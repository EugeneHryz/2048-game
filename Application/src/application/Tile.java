package application;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Tile extends StackPane {

	private static int size = GameBoard.tilesSize - GameBoard.tilesSpacing;
	private static int arcSize = GameBoard.tilesArcSize;

	private int x;
	private int y;
	private Rectangle tile;
	private Color color = Color.PEACHPUFF;
	private int number = 2;
	private Text text;

	private Path path;
	private PathTransition pathTr;
	private static int moveDuration = 1500;

	Tile(int x, int y) {

		this.x = x;
		this.y = y;

		tile = new Rectangle(size, size);
		tile.setArcHeight(arcSize);
		tile.setArcWidth(arcSize);
		tile.setFill(color);

		text = new Text(Integer.toString(number));
		text.setFill(Color.ALICEBLUE);
		text.setId("tile-text1");
		
		this.getChildren().addAll(tile, text);
		this.setLayoutX(x);
		this.setLayoutY(y);

		path = new Path();

		pathTr = new PathTransition();
		pathTr.setNode(this);
		pathTr.setDuration(Duration.millis(moveDuration));
		pathTr.setCycleCount(1);
		pathTr.setAutoReverse(false);

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
			text.setId("tile-text2");
			break;
		case 256:
			color = Color.LIGHTSEAGREEN;
			break;
		case 512:
			color = Color.TEAL;
			break;
		case 1024:
			color = Color.DARKCYAN;
			text.setId("tile-text3");
			break;
		case 2048:
			color = Color.CYAN;
			break;

		}

		tile.setFill(color);

	}

	public int getNumber() {

		return number;
	}

	public void setX(int x) {

		this.x = x;
	}

	public void setY(int y) {

		this.y = y;
	}

	public void moveTile(int offsetX, int offsetY) {

		path.getElements().clear();
		path.getElements().add(new MoveTo(size / 2, size / 2));
		path.getElements().add(new LineTo(offsetX + size / 2, offsetY + size / 2));

		pathTr.setPath(path);

		pathTr.play();
	}

	
}
