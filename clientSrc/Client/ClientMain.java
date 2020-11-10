package Client;

import java.io.IOException;
import java.net.Socket;

import javax.sound.midi.ControllerEventListener;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import sun.security.jgss.LoginConfigImpl;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class ClientMain extends Application {
	
	//Scene Variablen
	private static FXMLLoader loginLoader = new FXMLLoader();
	private static FXMLLoader scene1Loader = new FXMLLoader();
	private static FXMLLoader textBubbleLoader = new FXMLLoader();
	
	private static AnchorPane loginPane = new AnchorPane();
	private static AnchorPane scene1Pane = new AnchorPane();
	
	
	private static Scene scene;
	
	private static Stage primaryStage = new Stage();
	
	private static ChatBubbleController chatBubbleController = new ChatBubbleController();
	private static ApplicationController controller = new ApplicationController();
	@Override
	public void start(Stage newPrimaryStage) {
		try {
			ClientMain.primaryStage = newPrimaryStage;
			primaryStage.setTitle("JODT CONNECT");
			loginPane = loadLogin();
			scene1Pane = loadScene1();
			controller.setMassagePaneContent();
			scene = new Scene(loginPane,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private AnchorPane loadLogin() {
		AnchorPane ret = null;
		try {
		loginLoader.setLocation(getClass().getResource("Login.fxml"));
		loginLoader.setController(controller);
		
		ret = (AnchorPane)loginLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private AnchorPane loadScene1() {
		AnchorPane ret = null;
		try {
		scene1Loader.setLocation(getClass().getResource("Scene1.fxml"));
		scene1Loader.setController(controller);
		
		ret = (AnchorPane)scene1Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	


	
	//Variablen
	private static ClientConnecter clientConnecter = new ClientConnecter();
	private static ClientMassanger clientMassanger;
	private static Socket clientSocket;
	private static ClientLogin clientLogin = new ClientLogin();
	
	//Methoden
	public static void main(String[] args) {
		launch(args);
		
	}
	
	//Getter
	public static ClientLogin getClientLogin() {
		return clientLogin;
	}
	
	public static ClientConnecter getClientConnecter() {
		return clientConnecter;
	}
	
	public static ClientMassanger getClientMassanger() {
		return clientMassanger;
	}
	
	public static Socket getClientSocket() {
		return clientSocket;
	}
	
	public static ApplicationController getController() {
		return controller;
	}
	
	public static AnchorPane getScene1Pane() {
		return scene1Pane;
	}
	
	//Setter
	public static void setClientMassanger() {
		if (clientSocket != null) {
			ClientMain.clientMassanger = new ClientMassanger(clientSocket);
			ClientMain.clientMassanger.start();
		}
		
	}
	
	public static void setScene(int scene) {
		switch (scene) {
		case 1:
			System.out.println("Scene 1");
			Platform.runLater(new Runnable(){ 
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ClientMain.scene.setRoot(loginPane);
				}
			});
			break;

		case 2:
			System.out.println("Scene 2");
			Platform.runLater(new Runnable(){ 
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ClientMain.scene.setRoot(scene1Pane);
				}
			});
			
			break;
			
		default:
			break;
		}
		
	}
	
	public static void setClientSocket(Socket client) {
		
		ClientMain.clientSocket = client;
	}
}
