package MainAndRessources;

import Client.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.Socket;

public class ClientMain extends Application {

  //Scene Variablen
  private static final FXMLLoader loginLoader = new FXMLLoader();
  private static final FXMLLoader scene1Loader = new FXMLLoader();
  private static final FXMLLoader textBubbleLoader = new FXMLLoader();
  
  private static AnchorPane loginPane = new AnchorPane();
  private static AnchorPane scene1Pane = new AnchorPane();
  
  private static Scene scene;
  
  private static Stage primaryStage = new Stage();
  
  private static final RoomController roomController = new RoomController();
  private static final ChatBubbleController chatBubbleController = new ChatBubbleController();
  private static final ApplicationController controller = new ApplicationController();
  
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
      primaryStage.initStyle(StageStyle.UNDECORATED);
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

    scene1Pane.setOnMousePressed(((event) -> {
      x = event.getSceneX();
      y = event.getSceneY();
    }));

    scene1Pane.setOnMouseDragged(((event) -> {
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setX(event.getScreenX() - x);
      stage.setY(event.getScreenY() - y);
      stage.setOpacity(0.8f);
    }));

    scene1Pane.setOnDragDone(((event) -> {
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setOpacity(1.0f);
    }));

    scene1Pane.setOnMouseReleased(((event) -> {
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setOpacity(1.0f);

    }));
  }

  private AnchorPane loadLogin() {
    AnchorPane ret = null;
    try {
    loginLoader.setLocation(getClass().getResource("Login-GUI.fxml"));
    loginLoader.setController(controller);
    
    ret = loginLoader.load();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return ret;
  }
  
  private AnchorPane loadScene1() {
    AnchorPane ret = null;
    try {
    scene1Loader.setLocation(getClass().getResource("Massage-GUI.fxml"));
    scene1Loader.setController(controller);
    
    ret = scene1Loader.load();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return ret;
  }
  


  
  //Variablen
  private static final ClientConnecter clientConnecter = new ClientConnecter();
  private static ClientMassanger clientMassanger;
  private static Socket clientSocket;
  private static final ClientLogin clientLogin = new ClientLogin();
  
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
