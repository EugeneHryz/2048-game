package application;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainMenu extends StackPane {

	private static final int TITLE_X = 180;
	private static final int TITLE_Y = 140;

	// main menu button coordinates
	private static final int MAIN_MENU_BUTTON_X = 275;
	private static final int MAIN_MENU_NEW_GAME_BUTTON_Y = 220;
	private static final int MAIN_MENU_LEADERBOARDS_BUTTON_Y = 300;
	private static final int QUIT_BUTTON_Y = 380;
	
	// select mode button coordinates
	private static final int MODE_BUTTON_X = 280;
	private static final int MODE_BUTTON1_Y = 100;
	private static final int MODE_BUTTON2_Y = 180;
	private static final int MODE_BUTTON3_Y = 260;
	
	private static final int BUTTON_Y = 405;
	private static final int BACK_BUTTON_X = 35;
	private static final int PLAY_BUTTON_X = 540;
	
	// game window button coordinates
	private static final int BORDER_OFFSET = 50;
	private static final int BUTTON_X = 500;
	private static final int NEW_GAME_BUTTON_Y = 170;
	private static final int LEADERBOARDS_BUTTON_Y = 250;
	private static final int END_GAME_BUTTON_Y = 330;
	private static final int SCORE_X = 535;
	private static final int SCORE_Y = BORDER_OFFSET;
	
	//private static final int computerButtonX = 285;
	
	// main menu buttons
	private Button mainMenuNewGameButton = new Button("New game");
	private Button mainMenuLeaderboardsButton = new Button("Leaderboards");
	private Button quitButton = new Button("Quit");
	
	// select mode buttons
	private RadioButton modeButton4x4 = new RadioButton("4x4");
	private RadioButton modeButton5x5 = new RadioButton("5x5");
	private RadioButton modeButton6x6 = new RadioButton("6x6");
	
	private Button backButton = new Button("Back");
	private Button playButton = new Button("Play");
	//private Button computerButton = new Button("Computer");
	
	// game window buttons
	private Button newGameButton = new Button("New game");
	private Button endGameButton = new Button("End");
	private Button leaderboardsButton = new Button("Leaderboard");
	private Text scoreText = new Text("Score");
	
	private GameBoard board;
	
	private ToggleGroup group = new ToggleGroup();
	
	//private Font titleFont = new Font("helvetica", 110);
	
	private Text titleMainMenu = new Text("2 0 4 8");
	
	private Pane mainMenu = new Pane();
	private Pane selectMode = new Pane();
	private Pane gameWindow = new Pane();
	
	private Game game;
	
	private Keyboard keyboard = new Keyboard();
	
	private AnimationTimer timer;
	
	MainMenu() {
		
		// main menu
		titleMainMenu.setId("title-text");
		titleMainMenu.setLayoutX(TITLE_X);
		titleMainMenu.setLayoutY(TITLE_Y);
		
		mainMenuNewGameButton.setLayoutX(MAIN_MENU_BUTTON_X);
		mainMenuNewGameButton.setLayoutY(MAIN_MENU_NEW_GAME_BUTTON_Y);
		mainMenuNewGameButton.setId("button1");
		mainMenuNewGameButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				slideToVertical(selectMode, mainMenu, 1);
			}
		});
		
		mainMenuLeaderboardsButton.setLayoutX(MAIN_MENU_BUTTON_X);
		mainMenuLeaderboardsButton.setLayoutY(MAIN_MENU_LEADERBOARDS_BUTTON_Y);
		mainMenuLeaderboardsButton.setId("button2");
		
		quitButton.setLayoutX(MAIN_MENU_BUTTON_X);
		quitButton.setLayoutY(QUIT_BUTTON_Y);
		quitButton.setId("button3");
		quitButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				Game2048.closeGame();
			}
		});
		
		// select mode
		modeButton4x4.setToggleGroup(group);
		modeButton4x4.setId("mode-button");
		modeButton4x4.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/SourceCodePro-SemiBold.ttf"), 35));
		modeButton4x4.setLayoutX(MODE_BUTTON_X);
		modeButton4x4.setLayoutY(MODE_BUTTON1_Y);
		modeButton4x4.setSelected(true);
		
		modeButton5x5.setId("mode-button");
		modeButton5x5.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/SourceCodePro-SemiBold.ttf"), 35));
		modeButton5x5.setToggleGroup(group);
		modeButton5x5.setLayoutX(MODE_BUTTON_X);
		modeButton5x5.setLayoutY(MODE_BUTTON2_Y);
		
		modeButton6x6.setId("mode-button");
		modeButton6x6.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/SourceCodePro-SemiBold.ttf"), 35));
		modeButton6x6.setToggleGroup(group);
		modeButton6x6.setLayoutX(MODE_BUTTON_X);
		modeButton6x6.setLayoutY(MODE_BUTTON3_Y);
		
		backButton.setId("button4");
		backButton.setLayoutX(BACK_BUTTON_X);
		backButton.setLayoutY(BUTTON_Y);
		backButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				slideToVertical(mainMenu, selectMode, -1);
			}
		});
		
		playButton.setId("button4");
		playButton.setLayoutX(PLAY_BUTTON_X);
		playButton.setLayoutY(BUTTON_Y);
		playButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				
				if (modeButton4x4.isSelected()) {
	
					game = new Game(Mode.MODE4X4);
					board = game.getGameBoard();
					gameWindow.getChildren().add(board);
					slideToVertical(gameWindow, selectMode, 1);
					gameWindow.requestFocus();
					timer.start();

				} else if (modeButton5x5.isSelected()) {

					game = new Game(Mode.MODE5X5);
					board = game.getGameBoard();
					gameWindow.getChildren().add(board);
					slideToVertical(gameWindow, selectMode, 1);
					gameWindow.requestFocus();
					timer.start();
					
				} else if (modeButton6x6.isSelected()) {
					
					game = new Game(Mode.MODE6X6);
					board = game.getGameBoard();
					gameWindow.getChildren().add(board);
					slideToVertical(gameWindow, selectMode, 1);
					gameWindow.requestFocus();
					timer.start();
				}
			}
		});
		
		// game window
		newGameButton.setId("button1");
		newGameButton.setLayoutX(BUTTON_X);
		newGameButton.setLayoutY(NEW_GAME_BUTTON_Y);
		newGameButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				board.newGame();
			}
		});

		leaderboardsButton.setId("button2");
		leaderboardsButton.setLayoutX(BUTTON_X);
		leaderboardsButton.setLayoutY(LEADERBOARDS_BUTTON_Y);

		endGameButton.setId("button3");
		endGameButton.setLayoutX(BUTTON_X);
		endGameButton.setLayoutY(END_GAME_BUTTON_Y);
		endGameButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				timer.stop();
				slideToVertical(mainMenu, gameWindow, -1);	
			}
		});

		scoreText.setId("score-text");
		scoreText.setLayoutX(SCORE_X);
		scoreText.setLayoutY(SCORE_Y);
		
		gameWindow.setOnKeyPressed(new EventHandler<KeyEvent> () {
			public void handle(KeyEvent e) {
				keyboard.setPressedKey(e);
			}
		});
		
		timer = new AnimationTimer() {
			public void handle(long time) {
				if (keyboard.getRefreshed()) {
					
					if (keyboard.getPressedKey(0)) {
						board.moveAllTiles(Direction.LEFT);
						
					} else if (keyboard.getPressedKey(1)) {
						board.moveAllTiles(Direction.RIGHT);
						
					} else if (keyboard.getPressedKey(2)) {
						board.moveAllTiles(Direction.UP);
						
					} else if (keyboard.getPressedKey(3)) {
						
						board.moveAllTiles(Direction.DOWN);
					}
				}
				keyboard.setRefreshed(false);
			}
		};
		 
		mainMenu.getChildren().addAll(titleMainMenu, mainMenuNewGameButton, mainMenuLeaderboardsButton, quitButton);
		  
		selectMode.getChildren().addAll(modeButton4x4, modeButton5x5, modeButton6x6, backButton, playButton);
		
		gameWindow.getChildren().addAll(newGameButton, leaderboardsButton, endGameButton, scoreText);
		
		selectMode.setId("main-menu-pane");
		mainMenu.setId("main-menu-pane");
		gameWindow.setId("game-pane");
		// PaleVioletRed
		
		this.getChildren().add(mainMenu);
	}
	
	private void update() {
		
		
	}
	
	private void slideToVertical(Pane newPane, Pane oldPane, int direction) {
		
		this.getChildren().add(newPane);
		double height = this.getHeight();
		
		KeyFrame start = new KeyFrame(Duration.ZERO, new KeyValue(newPane.translateYProperty(), direction * height),
				new KeyValue(oldPane.translateYProperty(), 0));
		
		KeyFrame end = new KeyFrame(Duration.millis(400), new KeyValue(newPane.translateYProperty(), 0),
				new KeyValue(oldPane.translateYProperty(), -direction * height));
		
		Timeline animation = new Timeline(start, end);
		animation.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				removePane(oldPane);
			}
		});
		animation.play();
	}
	
	private void removePane(Pane oldPane) {
		
		this.getChildren().remove(oldPane);
	}

	private void removeGameWindow() {
		
		this.getChildren().remove(gameWindow);
	}
	
	private void addGameWindow() {
		
		this.getChildren().add(gameWindow);
	}

}
