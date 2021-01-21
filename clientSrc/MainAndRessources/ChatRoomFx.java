package MainAndRessources;

import Client.ApplicationController;
import com.mysql.cj.xdevapi.Client;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import Client.massageCodes;
import javafx.scene.shape.Circle;

public class ChatRoomFx {
	
	public ChatRoomFx() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Pane load(String text) {
		Pane root = new Pane();

		//Circle ripple = new Circle();



		Button button = new Button();
		button.getStylesheets().add("MainAndRessources/chatRoomButton.css");
		button.getStyleClass().add("chatRoomButton");
		//ripple.getStyleClass().add("ripple");
		button.setText(text);
		button.setMaxHeight(20);
		button.setMaxWidth(ClientMain.getController().getChatRoomList().getWidth());


		button.setOnMouseClicked(event ->
		{


			if (event.getButton() == MouseButton.PRIMARY){
				System.out.println("Button clicked "+ event.getButton());
				ClientMain.getClientMassanger().sendMassage(massageCodes.CHANGECHATROOMREQUEST, String.valueOf(ClientMain.getController().getChatRoomID(root)));
			}
		});
		
		
		root.getChildren().add(button);
		
		return root;
	}
}
