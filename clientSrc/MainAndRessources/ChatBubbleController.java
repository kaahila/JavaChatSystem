package MainAndRessources;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChatBubbleController {

	//Lable
	@FXML
	private Label nameText;
	
	@FXML
	private Label massageText; 
	
	//setText
	public void setNameText(String text) {
		if (text.length() > 0) {
			nameText.setText(text);
		}
	}
	
	public void setMassageText(String text) {
		if (text.length() > 0) {
			massageText.setText(text);
			massageText.setWrapText(true);
		}
	}
		
}
