package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.CubicCurve; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game2048 extends Application {

	public static final int xScene = 700;
	public static final int yScene = 500;
	public static final int bOffset = 50;
	public static final int fieldSize = 400;
	public static final int tileSize = 80;
	public static final int arcSize = 25;
	
	private static Pane myPane = new Pane();

	public static void main(String[] args) {

		launch(args);
	}

	public void start(Stage primaryStage) {

		primaryStage.setTitle("2 0 4 8");

		Rectangle field = new Rectangle(fieldSize, fieldSize);
		field.setX(bOffset);
		field.setY(bOffset);
		field.setArcHeight(50);
		field.setArcWidth(50);
		field.setFill(Color.ALICEBLUE);	
		
		
		Button newGame = new Button();
	
		newGame.setText("New game");
		
		/*
		 * newGame.setStyle("-fx-background-color: \r\n" + "#F0F8FF,\r\n" +
		 * "        rgba(0,0,0,0),\r\n" +
		 * "        linear-gradient(#483D8B, #483D8B);\r\n" +
		 * "    -fx-background-insets: 0,9 9 8 9,3,10,11;\r\n" +
		 * "    -fx-background-radius: 50;\r\n" + "    -fx-padding: 15 30 15 30;\r\n" +
		 * "    -fx-font-family: \"Helvetica\";\r\n" + "    -fx-font-size: 24px;\r\n" +
		 * "    -fx-text-fill: #F0F8FF;\r\n" +
		 * "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);"
		 * );
		 */
		
		
		
		newGame.setLayoutX(500);
		newGame.setLayoutY(bOffset + 90);

		Button endGame = new Button();
		endGame.setText("End");
		
		/*
		 * endGame.setStyle("-fx-background-color: \r\n" + "#F0F8FF,\r\n" +
		 * "        rgba(0,0,0,0),\r\n" +
		 * "        linear-gradient(#483D8B, #483D8B);\r\n" +
		 * "    -fx-background-insets: 0,9 9 8 9,3,10,11;\r\n" +
		 * "    -fx-background-radius: 50;\r\n" + "    -fx-padding: 15 67 15 67;\r\n" +
		 * "    -fx-font-family: \"Helvetica\";\r\n" + "    -fx-font-size: 24px;\r\n" +
		 * "    -fx-text-fill: #F0F8FF;\r\n" +
		 * "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);"
		 * );
		 */
		 
		endGame.setLayoutX(500);
		endGame.setLayoutY(bOffset + 190);

		Text score = new Text("Score");
		score.setStyle("-fx-font: 36 helvetica");

		score.setLayoutX(530);
		score.setLayoutY(45);
		
		Tile testTile = new Tile();

		Text tileText = new Text("2");

		StackPane testStack = new StackPane();
		testStack.getChildren().addAll(testTile.tile, tileText);

		testStack.setLayoutX(100);
		testStack.setLayoutY(100);
		
		StackPane testStack1 = new StackPane();
		testTile.createNextTile();
		Text tileText1 = new Text("4");
		testStack1.getChildren().addAll(testTile.tile, tileText1);
		testStack1.setLayoutX(150);
		testStack1.setLayoutY(150);

		StackPane stackTest = testTile.getStackPane();

		myPane.getChildren().addAll(field, newGame, endGame, score, testStack, testStack1);
		myPane.setStyle("-fx-background-color: #6495ED");

		Scene scene = new Scene(myPane, xScene, yScene);
		
		primaryStage.setScene(scene);
		
		scene.getStylesheets().add("NewFile.css");
		primaryStage.show();
		
		

	}

}
