package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.Path;

import javafx.animation.*;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.animation.PathTransition;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

public class Game2048 extends Application {

	public static final int xScene = 720;
	public static final int yScene = 500;
	public static final int bOffset = 50;
	public static final int playAreaSize = 400;
	public static final int fieldArcSize = 50;

	public static final int buttonX = 2 * bOffset + playAreaSize;
	public static final int button1Y = 3 * bOffset;
	public static final int button2Y = 4 * bOffset + 40;
	public static final int scoreX = 2 * bOffset + playAreaSize + 37;
	public static final int scoreY = bOffset;

	public static final int tileSize = 80;
	public static final int tileArcSize = fieldArcSize - 20;
	public static final int tilesSpacing = 5;
	
	public static final int fieldSize = 5;
	public static Point2D grid[][] = new Point2D[fieldSize][fieldSize];

	private static Pane myPane = new Pane();
	
	public static void main(String[] args) {

		launch(args);
	}

	public void start(Stage primaryStage) {

		primaryStage.setTitle("2 0 4 8");
		
		Rectangle field = new Rectangle(playAreaSize + tilesSpacing, playAreaSize + tilesSpacing);
		field.setX(bOffset - tilesSpacing);
		field.setY(bOffset - tilesSpacing);
		field.setArcHeight(fieldArcSize);
		field.setArcWidth(fieldArcSize);
		field.setFill(Color.ALICEBLUE);

		Button newGameButton = new Button("New game");
		
		newGameButton.setId("button1");

		newGameButton.setLayoutX(buttonX);
		newGameButton.setLayoutY(button1Y);

		Button endGameButton = new Button("End");
		endGameButton.setId("button2");					// was slateblue color

		endGameButton.setLayoutX(buttonX);
		endGameButton.setLayoutY(button2Y);

		Text score = new Text("Score");
		score.setStyle("-fx-font: 40 helvetica");

		score.setLayoutX(scoreX);
		score.setLayoutY(scoreY);

		Tile testTile1 = new Tile();

		Tile testTile2 = new Tile();
		for (int i = 0; i < 2; i++)
			testTile2.getNextTile();
		
		Tile testTile3 = new Tile();
		for (int i = 0; i < 4; i++)
			testTile3.getNextTile();
		
		Tile testTile4 = new Tile();
		for (int i = 0; i < 3; i++)
			testTile4.getNextTile();
		
		Tile testTile5 = new Tile();
		for (int i = 0; i < 1; i++)
			testTile5.getNextTile();

		testTile1.pane.setLayoutX(grid[4][0].getX());
		testTile1.pane.setLayoutY(grid[4][0].getY());
		
		testTile2.pane.setLayoutX(grid[3][0].getX());
		testTile2.pane.setLayoutY(grid[3][0].getY());
		
		testTile3.pane.setLayoutX(grid[4][1].getX());
		testTile3.pane.setLayoutY(grid[4][1].getY());
		
		testTile4.pane.setLayoutX(grid[4][3].getX());			// was at grid[4][3]
		testTile4.pane.setLayoutY(grid[4][3].getY());
		
		testTile5.pane.setLayoutX(grid[1][0].getX());
		testTile5.pane.setLayoutY(grid[1][0].getY());

		newGameButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				Path testPath = new Path();
				testPath.getElements().add(new MoveTo(40, 40));
				testPath.getElements().add(new LineTo(40, -280));

				PathTransition testTr = new PathTransition();
				testTr.setDuration(Duration.millis(1500));
				testTr.setPath(testPath);
				testTr.setNode(testTile4.pane);
				testTr.setCycleCount(Timeline.INDEFINITE);
				testTr.setAutoReverse(true);
				testTr.play();
			}
		});
		
		endGameButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
			}

		});

		myPane.getChildren().addAll(field, newGameButton, endGameButton, score, testTile1.pane, testTile2.pane,
				testTile3.pane, testTile4.pane, testTile5.pane);
		myPane.setStyle("-fx-background-color: #6495ED");

		Scene scene = new Scene(myPane, xScene, yScene);

		primaryStage.setScene(scene);

		scene.getStylesheets().add("MyStyles.css");
		primaryStage.show();

	}
	
	{
		for (int i = 0; i < fieldSize; i++) {

			for (int j = 0; j < fieldSize; j++) {

				Point2D point = new Point2D(j * tileSize + bOffset, i * tileSize + bOffset);
				grid[i][j] = point;
			}
		}

	}

}
