package MainAndRessources;

import java.io.IOException;
import java.net.Socket;

import javax.sound.midi.ControllerEventListener;

import Client.ApplicationController;
import Client.ClientConnecter;
import Client.ClientLogin;
import Client.ClientMassanger;
import Client.RoomController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sun.security.jgss.LoginConfigImpl;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
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
	
	private static RoomController roomController = new RoomController();
	private static ChatBubbleController chatBubbleController = new ChatBubbleController();
	private static ApplicationController controller = new ApplicationController();
	
	@Override
	public void start(Stage newPrimaryStage) {
		try {
			ClientMain.primaryStage = newPrimaryStage;
			primaryStage.setTitle("JODT CONNECT");
			primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("logo.png")));
			loginPane = loadLogin();
			scene1Pane = loadScene1();
			scene = new Scene(loginPane,loginPane.getPrefWidth(),loginPane.getPrefHeight());
			scene.getStylesheets().add(getClass().getResource("chatRoomButton.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(primaryStage.getStyle().UNDECORATED);
			makeDragable();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	//Inspiered by: https://github.com/V3rsion9/DarkLoginFX/blob/master/src/darklogin/AppController.java

	double x = 0, y = 0;

	private void makeDragable() {
		loginPane.setOnMousePressed(((event) -> {
			x = event.getSceneX();
			y = event.getSceneY();
		}));

		loginPane.setOnMouseDragged(((event) -> {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
			stage.setOpacity(0.8f);
		}));

		loginPane.setOnDragDone(((event) -> {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setOpacity(1.0f);
		}));

		loginPane.setOnMouseReleased(((event) -> {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setOpacity(1.0f);

		}));
	}

	private AnchorPane loadLogin() {
		AnchorPane ret = null;
		try {
		loginLoader.setLocation(getClass().getResource("Login-GUI.fxml"));
		loginLoader.setController(controller);
		
		ret = (AnchorPane) loginLoader.load();
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
	
	public static ChatBubbleController getChatBubbleController() {
		return chatBubbleController;
	}
	
	public static RoomController getRoomController() {
		return roomController;
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
