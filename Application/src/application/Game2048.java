package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.animation.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game2048 extends Application {

	private static final int SCENE_SIZE_X = 720;
	private static final int SCENE_SIZE_Y = 500;
	
	private static final int FADE_DURATION = 670;
	
	private Scene scene;
	private MainMenu mainMenu;
	
	private static Stage window;
	
	private FadeTransition fadeTr;

	public static void main(String[] args) {

		launch(args);
	}

	public void start(Stage primaryStage) {

		window = primaryStage;
		window.setTitle("2 0 4 8");
		
		mainMenu = new MainMenu();
		
		fadeTr = new FadeTransition(Duration.millis(FADE_DURATION), mainMenu);
		fadeTr.setFromValue(0);
		fadeTr.setToValue(1);
		
		scene = new Scene(mainMenu, SCENE_SIZE_X, SCENE_SIZE_Y);
		window.setScene(scene);

		scene.getStylesheets().add("MyStyles.css");
		window.setResizable(false);
		
		window.show();
		fadeTr.play();
		mainMenu.requestFocus();
	}
	
	public static void closeGame() {
		
		window.close();
	}
}
