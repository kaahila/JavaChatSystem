package MainAndRessources;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ChatBubble {
	private final FXMLLoader fxmlLoader = new FXMLLoader();
	private final ChatBubbleController chatBubbleController = new ChatBubbleController();
	private Pane pane = new Pane();
	private static GridPane bubblesGroup;	
	
	public ChatBubble(ChatBubbleMode chatBubbleMode, String usernameText, String massageText) {
		try {
			
			fxmlLoader.setLocation(getClass().getResource("ChatBubble.fxml"));
			fxmlLoader.setController(chatBubbleController);
			
			
			
			pane = fxmlLoader.load();
			pane.maxWidth(30);
			
			
			chatBubbleController.setNameText(usernameText);
			chatBubbleController.setMassageText(massageText);
			
			//addBubble(this, chatBubbleMode);
			
			
		} catch (IOException e) {
			System.err.println("[ChatBubble] Error while loading the ChatBubble.fxml");
			e.printStackTrace();
		}
		
	}
	
	//Getter
	public ChatBubbleController getChatBubbleController() {
		return chatBubbleController;
	}
	
	public Pane getBubble() {
		return pane;
	}
	
	
	public static GridPane getBubblesGroup() {
		return bubblesGroup;
	}
	
		
	
	
	/*
	 * JavaFx Methode that creates the raw Bubble
	 */
 	
	public void createBubble(String usernameInput, String massageInput) {
		
	}
	

}