package Client;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;

public class ChatBubble {
	private FXMLLoader fxmlLoader = new FXMLLoader();
	private ChatBubbleController chatBubbleController = new ChatBubbleController();
	private SplitPane splitPane = new SplitPane();
	private static Pane bubblesGroup = new Pane();	
	private Translate translate = new Translate();
	
	public ChatBubble(ChatBubbleMode chatBubbleMode, String usernameText, String massageText) {
		try {
			fxmlLoader.setLocation(getClass().getResource("ChatBubble.fxml"));
			fxmlLoader.setController(chatBubbleController);
			
			this.setPosition(chatBubbleMode);
			
			splitPane = (SplitPane)fxmlLoader.load();
			
			
			
			chatBubbleController.setNameText(usernameText);
			chatBubbleController.setMassageText(massageText);
			
			addBubble(this);
			
			
		} catch (IOException e) {
			System.err.println("[ChatBubble] Error while loading the ChatBubble.fxml");
			e.printStackTrace();
		}
	}
	
	/*
	 * Set the position of the Bubble
	 */
	
	public void setPosition(ChatBubbleMode chatBubbleMode) {
		
		if (chatBubbleMode == ChatBubbleMode.SENDBYME) {
			translate.setX(200);
			translate.setY(200);
			
			splitPane.setLayoutY(200);
			splitPane.setLayoutX(200);
		} else if (chatBubbleMode == ChatBubbleMode.SENDBYANOTHERUSER) {
			splitPane.setLayoutX(0);
		} 
		
		splitPane.getTransforms().add(translate);
	}
	
	//Getter
	public ChatBubbleController getChatBubbleController() {
		return chatBubbleController;
	}
	
	public SplitPane getBubble() {
		return splitPane;
	}
	
	public static void initGroup() {
		bubblesGroup.prefHeight(500);
		bubblesGroup.prefWidth(535);
		bubblesGroup.setManaged(false);
	}
	
	public static Pane getBubblesGroup() {
		return bubblesGroup;
	}
	
	public static void addBubble(ChatBubble chatBubble) {		
		bubblesGroup.getChildren().add(chatBubble.getBubble());
	}
	
}
