package application;

import java.util.*;
import java.awt.Point;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameBoard extends Pane {

	private static final int PLAY_AREA_SIZE = 400;
	private static final int FIELD_ARC_SIZE = 50;
	
	private static final int LAYOUTX = 50;
	private static final int LAYOUTY = 50;
	
	private int fieldSize;
	private int tilesSpacing;
	private int tilesSize;
	private int tilesArcSize;

	private Rectangle field;
	
	private Tile[][] board;
	private Point grid[][];
	
	private PauseTransition pause;
	private List<Tile> tilesToRemove;
	private List<Tile> tilesToCombine;
	private Iterator iterRemoveList;
	private Iterator<Tile> iterCombineList;
	
	private boolean canCreate = false;

	GameBoard(int fieldSize, int tilesSize, int tilesSpacing, int tilesArcSize) {

		this.fieldSize = fieldSize;
		this.tilesSize = tilesSize;
		this.tilesSpacing = tilesSpacing;
		this.tilesArcSize = tilesArcSize;
		
		board = new Tile[fieldSize][fieldSize];
		grid = new Point[fieldSize][fieldSize];

		field = new Rectangle(PLAY_AREA_SIZE + tilesSpacing, PLAY_AREA_SIZE + tilesSpacing);
		field.setArcHeight(FIELD_ARC_SIZE);
		field.setArcWidth(FIELD_ARC_SIZE);
		field.setFill(Color.ALICEBLUE);
		field.setLayoutX(-tilesSpacing);
		field.setLayoutY(-tilesSpacing);
		
		pause = new PauseTransition(Duration.millis(Tile.moveDuration));
		pause.setOnFinished(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				createTileRandom();
				combineTiles();
				removeTiles();
				Keyboard.setAnimationPlays(false);
			}
		});
		
		tilesToRemove = new ArrayList<>();
		tilesToCombine = new ArrayList<>();
		
		this.getChildren().add(field);
		this.setLayoutX(LAYOUTX);
		this.setLayoutY(LAYOUTY);
		
		for (int i = 0; i < fieldSize; i++) {

			for (int j = 0; j < fieldSize; j++) {

				Point point = new Point(j * tilesSize, i * tilesSize);
				grid[i][j] = point;
			}
		}

	}

	private boolean moveTile(int row, int column, int hDir, int vDir, Direction dir) {
		
		boolean moved = false;
		
		Tile current = board[row][column];
		if (current == null) {
			return moved;
		}
		int currentRow = row;
		int currentColumn = column;
		boolean canMove = true;
		boolean delete = false;
		
		while (checkBounds(currentRow, currentColumn, dir) && canMove) {
			
			if (board[currentRow + vDir][currentColumn + hDir] != null) {

				if (board[currentRow + vDir][currentColumn + hDir].getNumber() == board[currentRow][currentColumn].getNumber() 
						&& !board[currentRow + vDir][currentColumn + hDir].getCombined()) {
					
					tilesToCombine.add(board[currentRow + vDir][currentColumn + hDir]);
					//board[currentRow + vDir][currentColumn + hDir].getNextTile();
					board[currentRow + vDir][currentColumn + hDir].setCombined(true);
					//this.getChildren().remove(board[currentRow][currentColumn]);
					//board[currentRow][currentColumn] = null;
					currentRow += vDir;
					currentColumn += hDir;
					delete = true;
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
			if (delete) {
				
				tilesToRemove.add(board[currentRow - vDir][currentColumn - hDir]);
				board[currentRow - vDir][currentColumn - hDir] = null;
			}
		}
		return moved;
	}
	
	public void moveAllTiles(Direction dir) {
		
		int horizontalDirection = 0, verticalDirection = 0;
		
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

			Keyboard.setAnimationPlays(true);
			pause.play();
		}
		for (int row = 0; row < fieldSize; row++) {
			
			for (int col = 0; col < fieldSize; col++) {
				
				if (board[row][col] != null) {
					
					board[row][col].setCombined(false);
				}
			}
		}
		
	}
	
	private void removeTiles() {
		
		iterRemoveList = tilesToRemove.iterator();
		while (iterRemoveList.hasNext()) {
			
			this.getChildren().remove(iterRemoveList.next());
		}
		tilesToRemove.clear();
	}
	
	private void combineTiles() {
		
		iterCombineList = tilesToCombine.iterator();
		while (iterCombineList.hasNext()) {
			
			iterCombineList.next().getNextTile();
		}
		tilesToCombine.clear();
	}
	
	public void testMethod1() {
		
		Tile testTile1 = new Tile(grid[2][4].x, grid[2][4].y, tilesSize, tilesArcSize);
		board[2][4] = testTile1;
		this.getChildren().add(testTile1);
		
		Tile testTile2 = new Tile(grid[2][1].x, grid[2][1].y, tilesSize, tilesArcSize);
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
				
				tile = new Tile(grid[row][column].x, grid[row][column].y, tilesSize - tilesSpacing, tilesArcSize);
				board[row][column] = tile;
				notOccupied = true;
			}
		}
		this.getChildren().add(tile);
	}
	
	public void newGame() {
		
		for (int i = 0; i < fieldSize; i++) {
			
			for (int j = 0; j < fieldSize; j++) {
				
				if (board[i][j] != null) {
					
					this.getChildren().remove(board[i][j]);
					board[i][j] = null;
				}
			}
		}
		createTileRandom();
		createTileRandom();
	}

}
