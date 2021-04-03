package Client;


import MainAndRessources.ChatBubble;
import MainAndRessources.ChatBubbleMode;
import MainAndRessources.ChatRoomFx;
import MainAndRessources.ClientMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Vector;


public class ApplicationController {
	
	private static boolean connecting = false;
	Hash hash = new Hash();

	//Close Buttons
	@FXML
	public Button closeButton;

	public void closeButtonAction(ActionEvent e){
		e.consume();

		System.out.println("Application closed");
		System.exit(0);

	}

	//Login Buttons
	@FXML
	public Button registerButton;
	public void registerButtonAction(ActionEvent e) {
		e.consume();
		
		try {
			
			if (!connecting) {
				connecting = true;
				ClientMain.getClientConnecter().connect(serverAdressField.getText(), getServerPortTextField());
			}		
			
			} catch (Exception e2) {
			System.out.println("ApplicationController Error: ");
			e2.printStackTrace();
		}
		
		if (shortUsername()) {
			if (hash.lengthTest(passwordField.getText())) {
				ClientMain.getClientLogin().register(usernameField.getText(), passwordField.getText());
			} else {
				shortPassword();
			}
		}
	}
	
	@FXML
	public Button loginButton;
	
	
	@FXML
	public void loginButtonAction(ActionEvent e) {
		e.consume();
		
		try {
			
			if (!connecting) {
				connecting = true;
				ClientMain.getClientConnecter().connect(getServerAdressTextField(), getServerPortTextField());
			}		
			
			} catch (Exception e2) {
			System.out.println("ApplicationController Error: ");
			e2.printStackTrace();
		}
		
		if (shortUsername()) {
			if (hash.lengthTest(passwordField.getText())) {
				ClientMain.getClientLogin().login(usernameField.getText(), passwordField.getText());
			} else {
				shortPassword();
			}
			
		}
		
	}
	
	//Massage Buttons
	@FXML
	public Button sendMassage;
	
	@FXML
	public void sendMassageAction(ActionEvent e) throws Exception {
		e.consume();
		if (massageField.getText().length() > 0) {
			ClientMain.getClientMassanger().sendMassage(massageCodes.SENDTEXTMASSAGETOSERVER,massageField.getText());
			sendMassage.setDisable(true);
			Thread.sleep(1000);
			sendMassage.setDisable(false);
		}
				}
	
	//Login TextFields
	@FXML
	public TextField serverAdressField;
	
	@FXML
	public TextField serverPortField;
	
	@FXML
	public TextField usernameField;
	
	@FXML
	public PasswordField passwordField;
	
	@FXML
	public TextArea textArea;
	
	//Massage TextFields
	@FXML
	public TextField massageField;

	//Label
	@FXML
	public Label loginLabel;
	
	@FXML
	public Label connectionLable;
	
	//Getter
	public int getServerPortTextField() {
		int ret = 4444;
		if (!(serverPortField.getText().trim().isEmpty() || serverPortField.getText() == null )) {
			ret = Integer.parseInt(serverPortField.getText().trim());
		}
		System.out.println(""+ret);
		return ret;
	}

	public String getServerAdressTextField() {
		String ret = "127.0.0.1";
		if (!(serverAdressField.getText().trim().isEmpty() || serverAdressField.getText() == null )){
			ret = serverAdressField.getText().trim();

		}
		System.out.println(ret);
		return ret;
	}
	
	public boolean getConnectingStatus() {
		return connecting;
	}
	
	//Setter
	public static void setConnecting(boolean connecting) {
		if (connecting != ApplicationController.connecting) {
			ApplicationController.connecting = connecting;
		    
		}
		
	}
	
	//LableMethods
	
	//InetAdress and Port
	public void wrongInetAdressOrPort() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				connectionLable.setVisible(true);
				connectionLable.setText("Wrong IP or Port");
				connectionLable.setTextFill(Color.web("#d40015"));
			}
		});
	
	}
	
	//Password and Username
	public void wrongUsernameOrPassword() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				loginLabel.setVisible(true);
				loginLabel.setText("Wrong Username or Password");
				loginLabel.setTextFill(Color.web("#d40015"));
			}
		});
	
	}
	
	public void shortPassword() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				loginLabel.setVisible(true);
				loginLabel.setText("Password to short (8-64)");
				loginLabel.setTextFill(Color.web("#d40015"));
			}
		});
	
	}
	
	public boolean shortUsername() {
		
	
		
		boolean ret = false;
		if (usernameField.getText().length() >= 4) {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					loginLabel.setVisible(false);
					loginLabel.setText("");
				}
			});
			ret = true;
		} else {
			
		
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					loginLabel.setVisible(true);
					loginLabel.setText("Username to short (4-20)");
					loginLabel.setTextFill(Color.web("#d40015"));
				}
			});
		
		ret = false;
		}

		return ret;
	}
	
	public boolean longUsername() {
		boolean ret = false;
		if (usernameField.getText().length() <= 20) {
	Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					loginLabel.setVisible(false);
					loginLabel.setText("");
				}
			});
			
			ret = true;
		} else {
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					loginLabel.setVisible(true);
					loginLabel.setText("Username to long (4-20)");
					loginLabel.setTextFill(Color.web("#d40015"));
				}
			});
	
		ret = false;
		}

		return ret;
	}
	
	public void usernameUsed() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				loginLabel.setVisible(true);
				loginLabel.setText("Username is not available");
				loginLabel.setTextFill(Color.web("#d40015"));
			}
		});
		
	}
	
	public boolean enterCheck() {
		
		
		
		return false;
	}
	//TextField Animation
	
	@FXML
	GridPane massagePane;
	
	private static int rowIndex = 0;
	
	//TextArea Controlls	
	public void addMassage(ChatBubbleMode chatBubbleMode ,String username, String massage, int roomId) {
		if (massage.length() >= 1) {		
			Platform.runLater(new Runnable() {
	
				final ChatBubble chatBubble = new ChatBubble(chatBubbleMode, username, massage);

				ScrollPane s = (ScrollPane)getTabById(roomId).getContent();
				GridPane g = (GridPane)s.getContent();



				@Override
				public void run() {



						switch (chatBubbleMode) {
						case SENDBYME:
						
							g.add(chatBubble.getBubble(), 1, rowIndex);
							
							rowIndex++;
							break;
						case SENDBYANOTHERUSER:
							g.add(chatBubble.getBubble(), 0, rowIndex);
							rowIndex++;

							break;
						default:
							break;
						}
				}
			});
			
		}
	}
	
	/*
	 * ChatRoomList
	 */
	
	@FXML
	VBox chatRoomList;

	@FXML
	TabPane tabPane;

	public VBox getChatRoomList() {
		return chatRoomList;
	}
		
		private final Vector<Tab> tabList= new Vector<Tab>();
	
	public void addChatRoomToList(String text, int id) {

		System.out.println("ChatRoomFx: "+text+" added");
		Tab t = new ChatRoomFx().load(text);
		tabPane.getTabs().add(t);

		tabList.add(t);


	}

	public void clearChat(){
		System.out.println("ClearChat()");
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				massagePane.getChildren().remove(0, massagePane.getChildren().size());
			}
		});

	}


	public Tab getTabById(int id){
		Tab ret = null;
		ret = tabList.get(id);
		return ret;
	}

	public int getCurrentRoomID(){
		int ret = 0;
		try {
			Tab t = tabPane.getSelectionModel().getSelectedItem();
			ret = tabList.indexOf(t);
			System.out.println("[Controller]: getCurrentRoomID: " + ret);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
}
