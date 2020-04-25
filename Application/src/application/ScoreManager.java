package application;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ScoreManager {

	private int fieldSize;
	private int copyBoard[][];

	private int currentScore;
	
	// file
	private static final String fileName = "scores.bin";

	// everything for score displaying and animation
	private static final int SCORE_X = 438;
	private static final int SCORE_Y = 75;

	private static final int FADE_DURATION = 400;
	private static final int TRANSLATE_TRANSITION = 200;

	private Text scoreText = new Text("0");
	private StackPane textLayout = new StackPane();

	private FadeTransition fadeTr;
	private TranslateTransition translateTr;
	private ParallelTransition parallelTr;

	ScoreManager(int fieldSize, Tile[][] board) {

		this.fieldSize = fieldSize;
		copyBoard = new int[this.fieldSize][this.fieldSize];
		if (board != null) {
			
			for (int i = 0; i < fieldSize; i++) {

				for (int j = 0; j < fieldSize; j++) {

					if (board[i][j] != null) {
						copyBoard[i][j] = board[i][j].getNumber();
					} else
						copyBoard[i][j] = 0;
				}
			}
		}
		
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		scoreText.setId("score");
		textLayout.setLayoutX(SCORE_X);
		textLayout.setLayoutY(SCORE_Y);
		textLayout.setMinSize(300, 0);
		textLayout.getChildren().add(scoreText);
		

		fadeTr = new FadeTransition(Duration.millis(FADE_DURATION), scoreText);
		fadeTr.setFromValue(0);
		fadeTr.setToValue(1);

		translateTr = new TranslateTransition(Duration.millis(TRANSLATE_TRANSITION), scoreText);
		translateTr.setFromY(scoreText.getTranslateY() - 20);
		translateTr.setToY(scoreText.getTranslateY());

		parallelTr = new ParallelTransition();
		parallelTr.getChildren().addAll(fadeTr, translateTr);
	}

	public void saveGame(int index) {

		int offset;
		
		if ((offset = getFilePos(index)) != -1) {
			try {
				RandomAccessFile file = new RandomAccessFile(fileName, "rw");

				file.seek(offset);
				
				file.writeInt((2 + fieldSize * fieldSize) * 4);
				file.writeInt(fieldSize);
				file.writeInt(currentScore);
				
				for (int i = 0; i < fieldSize; i++) {
					for (int j = 0; j < fieldSize; j++) {

						file.writeInt(copyBoard[i][j]);
					}
				}
				file.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getNumberOfLines() {

		int number = 0;
		
		try {
			RandomAccessFile file = new RandomAccessFile(fileName, "rw");
			byte[] buffer = new byte[(2 + Game.FIELD_SIZE_6X6 * Game.FIELD_SIZE_6X6) * 4];
			int lineLength;
			
			while (true) {
				
				try {
					lineLength = file.readInt();
				} catch (EOFException e1) {
					break;
				}
				file.read(buffer, 0, lineLength);
				number++;
			}
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return number;
	}

	public boolean getSavedGameInfo(int index) {

		boolean result = true;
		int offset;

		if ((offset = getFilePos(index)) != -1) {
			try {
				RandomAccessFile file = new RandomAccessFile(fileName, "rw");
				file.seek(offset);

				try {

					file.readInt();
					fieldSize = file.readInt();
					currentScore = file.readInt();

					for (int i = 0; i < fieldSize; i++) {
						for (int j = 0; j < fieldSize; j++) {

							copyBoard[i][j] = file.readInt();
						}
					}
				} catch (EOFException e1) {
					result = false;
				}
				file.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private int getFilePos(int index) {

		int position = 0, i = index;

		try {
			RandomAccessFile file = new RandomAccessFile(fileName, "rw");
			int lineLength;
			byte[] buffer = new byte[(2 + Game.FIELD_SIZE_6X6 * Game.FIELD_SIZE_6X6) * 4];

			while (i > 0) {

				try {
					lineLength = file.readInt();
				} catch (EOFException e1) {
					break;
				}
				file.read(buffer, 0, lineLength);
				position += lineLength + 4;
				i--;
			}
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (i != 0) {
			position = -1;
		}
		return position;
	}

	public void setCopyBoard(Tile[][] board) {

		for (int i = 0; i < fieldSize; i++) {
			for (int j = 0; j < fieldSize; j++) {

				if (board[i][j] != null) {
					copyBoard[i][j] = board[i][j].getNumber();
				} else
					copyBoard[i][j] = 0;
			}
		}
	}
	
	public static void deleteFile() {
		
		File file = new File(fileName);
		file.delete();
	}
	
	public int getFieldSize() {
		
		return fieldSize;
	}

	public int getCurrentScore() {

		return currentScore;
	}
	
	public void setCurrentScore(int score) {
		
		currentScore = score;
	}

	public int[][] getCopyBoard() {

		return copyBoard;
	}

	public void displayScore() {

		parallelTr.play();
		scoreText.setText(Integer.toString(currentScore));
	}

	public StackPane getScoreLayout() {

		return textLayout;
	}

	public void addScore(int value) {

		currentScore += value * 10;
	}

	public void resetScore() {

		currentScore = 0;
	}
}
