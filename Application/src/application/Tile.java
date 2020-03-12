package application;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Tile {

	private static int size = Game2048.tileSize;
	private static int arcSize = Game2048.arcSize;
	Rectangle tile;
	private Color color = Color.THISTLE;
	private int number = 2;
	private Text text;
	private StackPane stackPane;

	Tile() {

		tile = new Rectangle(size, size);
		tile.setArcHeight(arcSize);
		tile.setArcWidth(arcSize);
		
		tile.setFill(color);
		text = new Text(Integer.toString(number));
		
		stackPane = new StackPane();
		stackPane.getChildren().addAll(tile, text);
	}

	public void createNextTile() {

		number *= 2;
		
		text = new Text(Integer.toString(number));

		switch (number) {

		case 4:
			color = Color.PEACHPUFF;
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
			color = Color.PURPLE;
			break;
		case 128:
			color = Color.LIGHTCORAL;
			break;
		case 256:
			color = Color.INDIANRED;
			break;
		case 512:
			color = Color.CRIMSON;
			break;
		case 1024:
			color = Color.FIREBRICK;
			break;
		case 2048:
			color = Color.RED;

		}
		
		tile.setFill(color);
		
	}
	
	public int getNumber() {
		
		return number;
	}
	
	public StackPane getStackPane() {
		
		return stackPane;
	}

}
