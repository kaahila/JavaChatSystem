package Client;






import Server.ServerClient.ClientHandeler.massageCodes;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class ApplicationController {
	
	private static boolean connecting = false;
	Hash hash = new Hash();
	
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
				ClientMain.getClientConnecter().connect(serverAdressField.getText(), getServerPortTextField());
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
		if (serverPortField.getText().length() > 0) {
			ret = Integer.parseInt(serverPortField.getText());
		} else {
			ret = 4444;
		}
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
	
	
	
	//TextArea Controlls
	public void addMassage(String username, String massage) {
		if (massage.length() >= 1) {
			Platform.runLater(new Runnable() {
	
				@Override
				public void run() {
					// TODO Auto-generated method stub
						textArea.appendText(massage);
						textArea.appendText("\n");
				}
			});
			
		}
	}

}
