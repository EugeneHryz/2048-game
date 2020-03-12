package application;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Tile {

	private static int size = Game2048.tileSize - Game2048.tilesSpacing;
	private static int arcSize = Game2048.tileArcSize;
	private Rectangle tile;
	private Color color = Color.PEACHPUFF;
	private int number = 2;
	private Text text;
	StackPane pane;

	Tile() {

		tile = new Rectangle(size, size);
		tile.setArcHeight(arcSize);
		tile.setArcWidth(arcSize);
		tile.setFill(color);

		text = new Text(Integer.toString(number));
		text.setFill(Color.ALICEBLUE);
		text.setId("tile-text1");

		pane = new StackPane();
		pane.getChildren().addAll(tile, text);
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

}
