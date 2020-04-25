package application;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.skin.ButtonSkin;
import javafx.util.Duration;

public class CustomButtonSkin extends ButtonSkin {
	
	private static final int SCALE_DURATION = 65;
	
	private ScaleTransition scaleTrEntered;
	private ScaleTransition scaleTrExited;
	
	private ScaleTransition scaleTrPressed;
	private ScaleTransition scaleTrReleased;
	
	CustomButtonSkin(Button button) {
		super(button);
		
		scaleTrEntered = new ScaleTransition(Duration.millis(SCALE_DURATION));
		scaleTrEntered.setNode(button);
		scaleTrEntered.setToX(1.05);
		scaleTrEntered.setToY(1.05);
		button.setOnMouseEntered(e -> scaleTrEntered.playFromStart());
		
		scaleTrExited = new ScaleTransition(Duration.millis(SCALE_DURATION));
		scaleTrExited.setNode(button);
		scaleTrExited.setToX(1);
		scaleTrExited.setToY(1);
		button.setOnMouseExited(e -> scaleTrExited.playFromStart());
		
		scaleTrPressed = new ScaleTransition(Duration.millis(SCALE_DURATION));
		scaleTrPressed.setNode(button);
		scaleTrPressed.setToX(0.95);
		scaleTrPressed.setToY(0.95);
		button.setOnMousePressed(e -> scaleTrPressed.playFromStart());
		
		scaleTrReleased = new ScaleTransition(Duration.millis(SCALE_DURATION));
		scaleTrReleased.setNode(button);
		scaleTrReleased.setToX(1.05);
		scaleTrReleased.setToY(1.05);
		button.setOnMouseReleased(e -> scaleTrReleased.playFromStart());
	}
}
