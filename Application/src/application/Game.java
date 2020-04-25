package application;

public class Game {
	
	// constants for 4x4 mode
	public static final int FIELD_SIZE_4X4 = 4;
	public static final int TILES_SIZE_4X4 = 100;
	public static final int TILES_SPACING_4X4 = 7;
	public static final int TILES_ARCSIZE_4X4 = 40;
	
	// constants for 5x5 mode
	public static final int FIELD_SIZE_5X5 = 5;
	public static final int TILES_SIZE_5X5 = 80;
	public static final int TILES_SPACING_5X5 = 5;
	public static final int TILES_ARCSIZE_5X5 = 30;
	
	// constants for 6x6 mode
	public static final int FIELD_SIZE_6X6 = 6;
	public static final int TILES_SIZE_6X6 = 66;
	public static final int TILES_SPACING_6X6 = 4;
	public static final int TILES_ARCSIZE_6X6 = 30;
	
	private GameBoard board;
	
	Game(Mode mode, int currentIndex) {

		switch (mode) {

		case MODE4X4:
			
			board = new GameBoard(FIELD_SIZE_4X4, TILES_SIZE_4X4, TILES_SPACING_4X4, TILES_ARCSIZE_4X4, currentIndex);
			break;
		case MODE5X5:

			board = new GameBoard(FIELD_SIZE_5X5, TILES_SIZE_5X5, TILES_SPACING_5X5, TILES_ARCSIZE_5X5, currentIndex);
			break;
		case MODE6X6:

			board = new GameBoard(FIELD_SIZE_6X6, TILES_SIZE_6X6, TILES_SPACING_6X6, TILES_ARCSIZE_6X6, currentIndex);
			break;
		}
	}

	public GameBoard getGameBoard() {

		return board;
	}
}
