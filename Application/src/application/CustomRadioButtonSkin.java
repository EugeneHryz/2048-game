package application;

import javafx.animation.ScaleTransition;
import javafx.scene.control.RadioButton;
import javafx.scene.control.skin.RadioButtonSkin;
import javafx.util.Duration;

public class CustomRadioButtonSkin extends RadioButtonSkin {

	private static final int SCALE_DURATION = 50;
	
	private ScaleTransition scaleTrEntered;
	private ScaleTransition scaleTrExited;
	
	private ScaleTransition scaleTrPressed;
	private ScaleTransition scaleTrReleased;
	
	public CustomRadioButtonSkin(RadioButton radioButton) {
		super(radioButton);
		
		scaleTrEntered = new ScaleTransition(Duration.millis(SCALE_DURATION));
		scaleTrEntered.setNode(radioButton);
		scaleTrEntered.setToX(1.05);
		scaleTrEntered.setToY(1.05);
		radioButton.setOnMouseEntered(e -> scaleTrEntered.playFromStart());
		
		scaleTrExited = new ScaleTransition(Duration.millis(SCALE_DURATION));
		scaleTrExited.setNode(radioButton);
		scaleTrExited.setToX(1);
		scaleTrExited.setToY(1);
		radioButton.setOnMouseExited(e -> scaleTrExited.playFromStart());
		
		scaleTrPressed = new ScaleTransition(Duration.millis(SCALE_DURATION));
		scaleTrPressed.setNode(radioButton);
		scaleTrPressed.setToX(0.95);
		scaleTrPressed.setToY(0.95);
		radioButton.setOnMousePressed(e -> scaleTrPressed.playFromStart());
		
		scaleTrReleased = new ScaleTransition(Duration.millis(SCALE_DURATION));
		scaleTrReleased.setNode(radioButton);
		scaleTrReleased.setToX(1.05);
		scaleTrReleased.setToY(1.05);
		radioButton.setOnMouseReleased(e -> scaleTrReleased.playFromStart());
	}

}
