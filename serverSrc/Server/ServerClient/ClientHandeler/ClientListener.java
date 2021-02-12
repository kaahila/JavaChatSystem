package Server.ServerClient.ClientHandeler;

import Server.Server;
import Server.ServerMassanger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientListener extends Thread{
	
	private Socket clientSocket;
	private static ServerSocket serverSocket;
	private Server server;
	private ClientHandeler clientHandeler;
	
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	
	private static final ServerMassanger massanger = new ServerMassanger(serverSocket);
	
	
	public ClientListener(ServerSocket newServerSocket) {
		serverSocket = newServerSocket;
	}
	
	
public void run() {
	
		while (true) {
			clientSocket = null;
			clientHandeler = null;
		try {	
			clientSocket = serverSocket.accept();
			
			inputStream = new DataInputStream(clientSocket.getInputStream());
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			
			clientHandeler = new ClientHandeler( clientSocket, inputStream, outputStream);
			clientHandeler.start();
			/*if (clientHandeler!=null) {
				server.addClientHandeler(clientHandeler);
			}*/
			
			
			
		System.out.println("new Client connected");
	
	} catch (Exception e) {
		// TODO: handle exception
		System.out.print("ClientListener Error: ");
		e.printStackTrace();
	}	
	}
		
}
}