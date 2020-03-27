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
import java.lang.Object;
import java.awt.Point;

public class Game2048 extends Application {

	public static final int xScene = 700;
	public static final int yScene = 500;
	public static final int bOffset = 50;

	public static final int buttonX = 500;
	public static final int button1Y = 3 * bOffset;
	public static final int button2Y = 4 * bOffset + 40;
	public static final int scoreX = 535;
	public static final int scoreY = bOffset;

	public static final int fieldSize = 5;

	public static final int tilesSize = 80; 		// variables for 5x5 mode
	public static final int tilesArcSize = 30;
	public static final int tilesSpacing = 5;

	private Pane mainPane = new Pane();
	private GameBoard board;

	public static void main(String[] args) {

		launch(args);
	}

	public void start(Stage primaryStage) {

		primaryStage.setTitle("2 0 4 8");

		Button newGameButton = new Button("New game");

		newGameButton.setId("button1");

		newGameButton.setLayoutX(buttonX);
		newGameButton.setLayoutY(button1Y);

		Button endGameButton = new Button("End");
		endGameButton.setId("button2"); 		// was slateblue color

		endGameButton.setLayoutX(buttonX);
		endGameButton.setLayoutY(button2Y);

		Text score = new Text("Score");
		score.setStyle("-fx-font: 40 helvetica");

		score.setLayoutX(scoreX);
		score.setLayoutY(scoreY);

		newGameButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
			}
		});
		endGameButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
			}
		});
		
		board = new GameBoard(bOffset, bOffset, fieldSize, tilesSize, tilesSpacing, tilesArcSize);
		
		board.createTile(0, 1);

		mainPane.getChildren().addAll(newGameButton, endGameButton, score, board);
		mainPane.setStyle("-fx-background-color: #6495ED");

		Scene scene = new Scene(mainPane, xScene, yScene);

		primaryStage.setScene(scene);

		scene.getStylesheets().add("MyStyles.css");
		primaryStage.show();

	}

}
