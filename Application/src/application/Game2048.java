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

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import java.awt.Point;

public class Game2048 extends Application {

	private static final int SCENE_SIZEX = 720;
	private static final int SCENE_SIZEY = 500;
	
	private Scene scene;
	private MainMenu mainMenu;
	
	private Game game;
	
	private static Stage window;

	public static void main(String[] args) {

		launch(args);
	}

	public void start(Stage primaryStage) {

		window = primaryStage;
		window.setTitle("2 0 4 8");

		//game = new Game(5, 80, 5, 30);
		//game.setFocusTraversable(true);
		
		mainMenu = new MainMenu();
		
		scene = new Scene(mainMenu, SCENE_SIZEX, SCENE_SIZEY);
		
		window.setScene(scene);

		scene.getStylesheets().add("MyStyles.css");
		window.setResizable(false);
		
		window.show();
		
		mainMenu.requestFocus();
		//game.requestFocus();
	}
	
	public static void closeGame() {
		
		window.close();
	}
}
