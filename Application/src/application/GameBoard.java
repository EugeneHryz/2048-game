package application;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class GameBoard extends Pane {

	private static final int playAreaSize = 400;
	private static final int fieldArcSize = 50;
	
	private static int fieldSize;
	public static int tilesSpacing;
	public static int tilesSize;
	public static int tilesArcSize;

	private Rectangle field;
	
	private Tile[][] board;
	private Point grid[][];
	
	private Direction direction;

	GameBoard(int fieldSize, int tilesSize, int tilesSpacing, int tilesArcSize) {

		GameBoard.fieldSize = fieldSize;
		GameBoard.tilesSize = tilesSize;
		GameBoard.tilesSpacing = tilesSpacing;
		GameBoard.tilesArcSize = tilesArcSize;
	}

	GameBoard(int x, int y, int fieldSize, int tilesSize, int tilesSpacing, int tilesArcSize) {

		this(fieldSize, tilesSize, tilesSpacing, tilesArcSize);
		
		board = new Tile[fieldSize][fieldSize];
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
	
	public boolean moveTile(int row, int column, int hDir, int vDir, Direction dir) {
		
		boolean moved = false;
		
		Tile current = board[row][column];
		if (current == null) {
			return moved;
		}
		int currentRow = row;
		int currentColumn = column;
		boolean canMove = true;
		
		while (checkBounds(currentRow, currentColumn, dir) && canMove) {
			
			if (board[currentRow + vDir][currentColumn + hDir] != null) {

				if (board[currentRow + vDir][currentColumn + hDir].getNumber() == board[currentRow][currentColumn].getNumber() 
						&& !board[currentRow + vDir][currentColumn + hDir].getCombined()) {
					
					board[currentRow + vDir][currentColumn + hDir].getNextTile();
					board[currentRow + vDir][currentColumn + hDir].setCombined(true);
					this.getChildren().remove(board[currentRow][currentColumn]);
					board[currentRow][currentColumn] = null;
					moved = true;
				}
				
				canMove = false;
				continue;
			}
			if (board[currentRow + vDir][currentColumn + hDir] == null) {

				board[currentRow + vDir][currentColumn + hDir] = current;
				board[currentRow][currentColumn] = null;
				moved = true;
			}
			currentRow += vDir;
			currentColumn += hDir;
		}
		if (moved) {
			
			current.setMoveToX(grid[currentRow][currentColumn].x);
			current.setMoveToY(grid[currentRow][currentColumn].y);
			current.moveTo();
		}
		return moved;
	}
	
	public void moveAllTiles(Direction dir) {
		
		int horizontalDirection = 0, verticalDirection = 0;
		boolean canCreate = false;
		
		if (dir == Direction.LEFT) {

			horizontalDirection = -1;
			for (int i = 0; i < fieldSize; i++) {

				for (int j = 0; j < fieldSize; j++) {

					if (!canCreate) {
						canCreate = moveTile(i, j, horizontalDirection, verticalDirection, dir);
					} else
						moveTile(i, j, horizontalDirection, verticalDirection, dir);
				}
			}
		} else if (dir == Direction.RIGHT) {

			horizontalDirection = 1;
			for (int i = 0; i < fieldSize; i++) {

				for (int j = fieldSize - 1; j >= 0; j--) {

					if (!canCreate) {
						canCreate = moveTile(i, j, horizontalDirection, verticalDirection, dir);
					} else
						moveTile(i, j, horizontalDirection, verticalDirection, dir);
				}
			}
		} else if (dir == Direction.UP) {
			
			verticalDirection = -1;
			for (int i = 0; i < fieldSize; i++) {
				
				for (int j = 0; j < fieldSize; j++) {

					if (!canCreate) {
						canCreate = moveTile(i, j, horizontalDirection, verticalDirection, dir);
					} else
						moveTile(i, j, horizontalDirection, verticalDirection, dir);
				}
			}
		} else if (dir == Direction.DOWN) {
			
			verticalDirection = 1;
			for (int i = fieldSize - 1; i >= 0; i--) {
				
				for (int j = 0; j < fieldSize; j++) {

					if (!canCreate) {
						canCreate = moveTile(i, j, horizontalDirection, verticalDirection, dir);
					} else
						moveTile(i, j, horizontalDirection, verticalDirection, dir);
				}
			}
		}
		if (canCreate) {
			
			createTileRandom();
		}
		for (int row = 0; row < fieldSize; row++) {
			
			for (int col = 0; col < fieldSize; col++) {
				
				if (board[row][col] != null) {
					
					board[row][col].setCombined(false);
				}
			}
		}
	}
	
	public void testMethod1() {
		
		Tile testTile1 = new Tile(grid[2][4].x, grid[2][4].y);
		board[2][4] = testTile1;
		this.getChildren().add(testTile1);
		
		Tile testTile2 = new Tile(grid[2][1].x, grid[2][1].y);
		board[2][1] = testTile2;
		this.getChildren().add(testTile2);
	}
	private boolean checkBounds(int row, int column, Direction dir) {

		if (dir == Direction.LEFT) {

			return column > 0;
		}
		if (dir == Direction.RIGHT) {

			return column < fieldSize - 1;
		}
		if (dir == Direction.UP) {

			return row > 0;
		}
		if (dir == Direction.DOWN) {

			return row < fieldSize - 1;
		}
		return false;
	}
	
	public void createTileRandom() {
		
		Random random = new Random();
		
		int number, column, row;
		
		boolean notOccupied = false;
		Tile tile = null;
		while (!notOccupied) {
			
			number = (int)(random.nextDouble() * fieldSize * fieldSize);
			column = number % fieldSize;
			row = number / fieldSize;
			
			if (board[row][column] == null) {
				
				tile = new Tile(grid[row][column].x, grid[row][column].y);
				board[row][column] = tile;
				notOccupied = true;
			}
		}
		this.getChildren().add(tile);
		System.out.println("Translate:" + this.getTranslateX() + " " + this.getTranslateY() + 
				" Layout:" + this.getLayoutX() + " " + this.getLayoutY());
	}
	
}
