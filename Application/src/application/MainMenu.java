package application;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu extends StackPane {

	private static final int TITLE_X = 180;
	private static final int TITLE_Y = 140;

	// main menu button coordinates
	private static final int MAIN_MENU_BUTTON_X = 275;
	private static final int MAIN_MENU_NEW_GAME_BUTTON_Y = 220;
	private static final int MAIN_MENU_CONTINUE_BUTTON_Y = 300;
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
	private static final int CONTINUE_BUTTON_Y = 250;
	private static final int END_GAME_BUTTON_Y = 330;
	private static final int SCORE_X = 535;
	private static final int SCORE_Y = BORDER_OFFSET;
	
	private static final int GAME_OVER_TEXT_X = 130;
	private static final int GAME_OVER_TEXT_Y = 260;
	
	// main menu buttons
	private Button mainMenuNewGameButton = new Button("New game");
	private Button mainMenuContinueButton = new Button("Continue");
	private Button quitButton = new Button("Quit");
	
	// select mode buttons
	private RadioButton modeButton4x4 = new RadioButton("4x4");
	private RadioButton modeButton5x5 = new RadioButton("5x5");
	private RadioButton modeButton6x6 = new RadioButton("6x6");
	
	private Button backButton = new Button("Back");
	private Button playButton = new Button("Play");
	
	// game window elements
	private Button newGameButton = new Button("New game");
	private Button endGameButton = new Button("End");
	private Button continueButton = new Button("Continue");
	private Text scoreText = new Text("Score");
	
	private Text gameOverText = new Text("Game over");
	
	// dialog window
	private static final int SCENE_SIZE_X = 400;
	private static final int SCENE_SIZE_Y = 350;
	
	private Stage dialogStage = new Stage();
	private VBox vBox = new VBox();
	private Scene dialogScene = new Scene(vBox, SCENE_SIZE_X, SCENE_SIZE_Y);
	
	private TableView<SavedGame> table = new TableView<SavedGame>();
	private ObservableList<SavedGame> data = FXCollections.observableArrayList();
	
	private HBox hBox = new HBox();
	private Button loadGameButton = new Button("Load");
	private Button clearButton = new Button("Clear");
	
	
	private GameBoard board;
	
	private ToggleGroup group = new ToggleGroup();
	
	private Text titleMainMenu = new Text("2 0 4 8");
	
	private Pane mainMenu = new Pane();
	private Pane selectMode = new Pane();
	private Pane gameWindow = new Pane();
	
	private Game game;
	
	private Keyboard keyboard = new Keyboard();
	
	private AnimationTimer timer;
	private PauseTransition pause;

	public MainMenu() {
		
		// main menu
		titleMainMenu.setId("title-text");
		titleMainMenu.setLayoutX(TITLE_X);
		titleMainMenu.setLayoutY(TITLE_Y);
		
		mainMenuNewGameButton.setLayoutX(MAIN_MENU_BUTTON_X);
		mainMenuNewGameButton.setLayoutY(MAIN_MENU_NEW_GAME_BUTTON_Y);
		mainMenuNewGameButton.setId("new-game-button");
		mainMenuNewGameButton.setSkin(new CustomButtonSkin(mainMenuNewGameButton));
		mainMenuNewGameButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				slideToVertical(selectMode, mainMenu, 1);
			}
		});
		
		mainMenuContinueButton.setLayoutX(MAIN_MENU_BUTTON_X);
		mainMenuContinueButton.setLayoutY(MAIN_MENU_CONTINUE_BUTTON_Y);
		mainMenuContinueButton.setId("continue-button");
		mainMenuContinueButton.setSkin(new CustomButtonSkin(mainMenuContinueButton));
		mainMenuContinueButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				populateList();
				dialogStage.show();
			}
		});
		
		quitButton.setLayoutX(MAIN_MENU_BUTTON_X);
		quitButton.setLayoutY(QUIT_BUTTON_Y);
		quitButton.setId("quit-button");
		quitButton.setSkin(new CustomButtonSkin(quitButton));
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Game2048.closeGame();
			}
		});
		
		// select mode
		modeButton4x4.setToggleGroup(group);
		modeButton4x4.setId("mode-button");
		modeButton4x4.setSkin(new CustomRadioButtonSkin(modeButton4x4));
		modeButton4x4.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/SourceCodePro-SemiBold.ttf"), 35));
		modeButton4x4.setLayoutX(MODE_BUTTON_X);
		modeButton4x4.setLayoutY(MODE_BUTTON1_Y);
		modeButton4x4.setSelected(true);
		
		modeButton5x5.setId("mode-button");
		modeButton5x5.setSkin(new CustomRadioButtonSkin(modeButton5x5));
		modeButton5x5.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/SourceCodePro-SemiBold.ttf"), 35));
		modeButton5x5.setToggleGroup(group);
		modeButton5x5.setLayoutX(MODE_BUTTON_X);
		modeButton5x5.setLayoutY(MODE_BUTTON2_Y);
		
		modeButton6x6.setId("mode-button");
		modeButton6x6.setSkin(new CustomRadioButtonSkin(modeButton6x6));
		modeButton6x6.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/SourceCodePro-SemiBold.ttf"), 35));
		modeButton6x6.setToggleGroup(group);
		modeButton6x6.setLayoutX(MODE_BUTTON_X);
		modeButton6x6.setLayoutY(MODE_BUTTON3_Y);
		
		backButton.setId("play-button");
		backButton.setSkin(new CustomButtonSkin(backButton));
		backButton.setLayoutX(BACK_BUTTON_X);
		backButton.setLayoutY(BUTTON_Y);
		backButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				slideToVertical(mainMenu, selectMode, -1);
			}
		});
		
		playButton.setId("play-button");
		playButton.setSkin(new CustomButtonSkin(playButton));
		playButton.setLayoutX(PLAY_BUTTON_X);
		playButton.setLayoutY(BUTTON_Y);
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				if (modeButton4x4.isSelected()) {

					game = new Game(Mode.MODE4X4, -1);
					
				} else if (modeButton5x5.isSelected()) {

					game = new Game(Mode.MODE5X5, -1);

				} else if (modeButton6x6.isSelected()) {

					game = new Game(Mode.MODE6X6, -1);
				}
				board = game.getGameBoard();
				board.createTileRandom();
				board.createTileRandom();
				
				gameWindow.getChildren().addAll(board, board.getScoreManager().getScoreLayout());
				slideToVertical(gameWindow, selectMode, 1);
				gameWindow.requestFocus();
				timer.start();
			}
		});
		
		// game window
		newGameButton.setId("new-game-button");
		newGameButton.setSkin(new CustomButtonSkin(newGameButton));
		newGameButton.setLayoutX(BUTTON_X);
		newGameButton.setLayoutY(NEW_GAME_BUTTON_Y);
		newGameButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				
				switch(board.getFieldSize()) {
				case 4:
					game = new Game(Mode.MODE4X4, -1);
					break;
				case 5:
					game = new Game(Mode.MODE5X5, -1);
					break;
				case 6:
					game = new Game(Mode.MODE6X6, -1);
					break;
				}
				board.setEffect(null);
				gameWindow.getChildren().removeAll(board, board.getScoreManager().getScoreLayout(), gameOverText);
				board = game.getGameBoard();
				board.createTileRandom();
				board.createTileRandom();
				
				gameWindow.getChildren().addAll(board, board.getScoreManager().getScoreLayout());
				Keyboard.setAnimationPlays(false);
			}
		});

		continueButton.setId("continue-button");
		continueButton.setSkin(new CustomButtonSkin(continueButton));
		continueButton.setLayoutX(BUTTON_X);
		continueButton.setLayoutY(CONTINUE_BUTTON_Y);

		endGameButton.setId("quit-button");
		endGameButton.setSkin(new CustomButtonSkin(endGameButton));
		endGameButton.setLayoutX(BUTTON_X);
		endGameButton.setLayoutY(END_GAME_BUTTON_Y);
		endGameButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				timer.stop();
				slideToVertical(mainMenu, gameWindow, -1);
				Keyboard.setAnimationPlays(false);
			}
		});

		scoreText.setId("score-text");
		scoreText.setLayoutX(SCORE_X);
		scoreText.setLayoutY(SCORE_Y);
		
		gameOverText.setId("game-over-text");
		gameOverText.setLayoutX(GAME_OVER_TEXT_X);
		gameOverText.setLayoutY(GAME_OVER_TEXT_Y);
		gameOverText.setOpacity(0);
		
		gameWindow.setOnKeyPressed(new EventHandler<KeyEvent> () {
			public void handle(KeyEvent e) {
				keyboard.setPressedKey(e);
			}
		});
		
		pause = new PauseTransition(Duration.millis(Tile.MOVE_DURATION + 25));
		pause.setOnFinished(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				if (board.checkLosing()) {
					gameOverAnimation();
					Keyboard.setAnimationPlays(true);
				}
			}
		});
		
		// dialog window
		TableColumn<SavedGame, String> modeColumn = new TableColumn<SavedGame, String>("Mode");
		modeColumn.setCellValueFactory(new PropertyValueFactory<>("mode"));
		modeColumn.setMinWidth(190);
		
		TableColumn<SavedGame, String> scoreColumn = new TableColumn<SavedGame, String>("Score");
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
		scoreColumn.setMinWidth(190);
		
		table.setItems(data);
		table.setEditable(false);
		table.getColumns().addAll(modeColumn, scoreColumn);
		
		loadGameButton.setId("dialog-button");
		loadGameButton.setSkin(new CustomButtonSkin(loadGameButton));
		loadGameButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {

				if (!table.getSelectionModel().isEmpty()) {

					String mode = table.getSelectionModel().getSelectedItem().getMode();
					int index = table.getSelectionModel().getSelectedIndex();
					switch (mode) {

					case "4x4":
						game = new Game(Mode.MODE4X4, index);
						break;
					case "5x5":
						game = new Game(Mode.MODE5X5, index);
						break;
					case "6x6":
						game = new Game(Mode.MODE6X6, index);
						break;
					}
					board = game.getGameBoard();
					board.loadGame();

					dialogStage.close();
					gameWindow.getChildren().addAll(board, board.getScoreManager().getScoreLayout());
					slideToVertical(gameWindow, mainMenu, 1);
					gameWindow.requestFocus();
					timer.start();
					if (board.checkLosing()) {
						gameOverAnimation();
						Keyboard.setAnimationPlays(true);
					}
				}
			}
		});
		
		clearButton.setId("dialog-button");
		clearButton.setSkin(new CustomButtonSkin(clearButton));
		clearButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent e) {
				if (!data.isEmpty()) {
					ScoreManager.deleteFile();
					data.clear();
				}
			}
		});
		
		hBox.getChildren().addAll(clearButton, loadGameButton);
		hBox.setSpacing(145);
		
		vBox.getChildren().addAll(table, hBox);
		vBox.setId("dialog-window");
		vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
		
		dialogScene.getStylesheets().add("MyStyles.css");
		dialogStage.setScene(dialogScene);
		dialogStage.setResizable(false);
		
		
		timer = new AnimationTimer() {
			public void handle(long time) {
				update();
			}
		};
		
		mainMenu.getChildren().addAll(titleMainMenu, mainMenuNewGameButton, mainMenuContinueButton, quitButton);
		  
		selectMode.getChildren().addAll(modeButton4x4, modeButton5x5, modeButton6x6, backButton, playButton);
		
		gameWindow.getChildren().addAll(newGameButton, continueButton, endGameButton, scoreText);
		
		selectMode.setId("menu-pane");
		mainMenu.setId("menu-pane");
		gameWindow.setId("menu-pane");
		// PaleVioletRed
		
		this.getChildren().add(mainMenu);
	}

	private void update() {
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
			pause.play();
		}
		keyboard.setRefreshed(false);
	}
	
	public void populateList() {
		
		int index = 0;
		ScoreManager scoreManager = new ScoreManager(6, null);
		String mode;
		data.clear();
		
		while (scoreManager.getSavedGameInfo(index)) {

			mode = new String(Integer.toString(scoreManager.getFieldSize()));
			SavedGame savedGame = new SavedGame(mode + "x" + mode,
					Integer.toString(scoreManager.getCurrentScore()));

			data.add(savedGame);
			index++;
		}
	}
	
	private void gameOverAnimation() {
		
		GaussianBlur blur = new GaussianBlur(0);
		ColorAdjust color = new ColorAdjust(0, 0, 0, 0);
		gameOverText.setOpacity(0);
		gameWindow.getChildren().remove(gameOverText);
		
		KeyFrame start = new KeyFrame(Duration.ZERO, new KeyValue(blur.radiusProperty(), 0), 
				new KeyValue(color.brightnessProperty(), 0), new KeyValue(gameOverText.opacityProperty(), 0));
		KeyFrame end = new KeyFrame(Duration.millis(1000), new KeyValue(blur.radiusProperty(), 15), 
				new KeyValue(color.brightnessProperty(), -0.17), new KeyValue(gameOverText.opacityProperty(), 1));
		Timeline animation = new Timeline(start, end);
	
		
		gameWindow.getChildren().add(gameOverText);
		
		color.setInput(blur);
		board.setEffect(color);
		animation.play();
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
				if (oldPane == gameWindow) {
					oldPane.getChildren().removeAll(board, board.getScoreManager().getScoreLayout(), gameOverText);
				}
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
	
	public static class SavedGame {
		
		private final SimpleStringProperty mode;
		private final SimpleStringProperty score;
		
		public SavedGame(String mode, String score) {
			
			this.mode = new SimpleStringProperty(mode);
			this.score = new SimpleStringProperty(score);
		}

		public String getMode() {
			
			return mode.get();
		}

		public String getScore() {
			
			return score.get();
		}

	}

}
