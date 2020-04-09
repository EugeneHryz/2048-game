package application;

import javafx.scene.input.KeyEvent;

public class Keyboard {
	
	private static final int KEYS_NUMBER = 4;

	private boolean pressedKeys[];
	private boolean refreshed = true;
	
	private static boolean animationPlays = false; 
	
	Keyboard() {
		
		pressedKeys = new boolean[KEYS_NUMBER];
	}
	
	public void setPressedKey(KeyEvent event) {
		
		resetPressedKeys();
		switch (event.getCode()) {
		
		case A:
			pressedKeys[0] = true;
			break;
		case D:
			pressedKeys[1] = true;
			break;
		case W:
			pressedKeys[2] = true;
			break;
		case S:
			pressedKeys[3] = true;
			break;
		}
		if (!animationPlays) {
			
			setRefreshed(true);
		}
	}
	
	private void resetPressedKeys() {
		
		for (int i = 0; i < KEYS_NUMBER; i++) {
			
			pressedKeys[i] = false;
		}
	}
	
	public boolean getRefreshed() {
		
		return refreshed;
	}
	
	public void setRefreshed(boolean value) {
		
		refreshed = value;
	}
	
	public boolean getPressedKey(int i) {
		
		return pressedKeys[i];
	}
	
	public static void setAnimationPlays(boolean value) {
		
		animationPlays = value;
	}
	
}
