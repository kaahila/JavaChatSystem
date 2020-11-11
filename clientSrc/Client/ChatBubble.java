package Client;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;

public class ChatBubble {
	private  FXMLLoader fxmlLoader = new FXMLLoader();
	private ChatBubbleController chatBubbleController = new ChatBubbleController();
	private Pane pane = new Pane();
	private static GridPane bubblesGroup;	
	
	public ChatBubble(ChatBubbleMode chatBubbleMode, String usernameText, String massageText) {
		try {
			
				fxmlLoader.setLocation(getClass().getResource("ChatBubble.fxml"));
			fxmlLoader.setController(chatBubbleController);
			
			
			
			pane = (Pane)fxmlLoader.load();
			
			
			
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
	
		
	}
	

