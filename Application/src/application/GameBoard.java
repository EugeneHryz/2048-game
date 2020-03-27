package application;

import java.awt.Point;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;

public class GameBoard extends Pane {

	private static final int playAreaSize = 400;
	private static final int fieldArcSize = 50;
	
	private static int fieldSize;
	public static int tilesSpacing;
	public static int tilesSize;
	public static int tilesArcSize;

	private Rectangle field;

	private Point grid[][]; 

	GameBoard(int fieldSize, int tilesSize, int tilesSpacing, int tilesArcSize) {

		GameBoard.fieldSize = fieldSize;
		GameBoard.tilesSize = tilesSize;
		GameBoard.tilesSpacing = tilesSpacing;
		GameBoard.tilesArcSize = tilesArcSize;
	}

	GameBoard(int x, int y, int fieldSize, int tilesSize, int tilesSpacing, int tilesArcSize) {

		this(fieldSize, tilesSize, tilesSpacing, tilesArcSize);
		
		grid = new Point[fieldSize][fieldSize];

		field = new Rectangle(playAreaSize + tilesSpacing, playAreaSize + tilesSpacing);
		field.setArcHeight(fieldArcSize);
		field.setArcWidth(fieldArcSize);
		field.setFill(Color.ALICEBLUE);
		field.setLayoutX(-tilesSpacing);
		field.setLayoutY(-tilesSpacing);
		
		this.getChildren().add(field);
		this.setLayoutX(x);
		this.setLayoutY(y);
		
		for (int i = 0; i < fieldSize; i++) {

			for (int j = 0; j < fieldSize; j++) {

				Point point = new Point(j * tilesSize, i * tilesSize);
				grid[i][j] = point;
			}
		}
	}
	
	public void createTile(int row, int column) {
		
		Tile testTile = new Tile(grid[row][column].x, grid[row][column].y);
		
		this.getChildren().add(testTile);
	}
	
	public void moveTile(int row, int column) {
		
		//Tile testTile1 = new Tile(grid[row][column])
	}


}
