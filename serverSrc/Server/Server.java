package Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import com.sun.corba.se.impl.orbutil.StackImpl;

import java.util.Scanner;
import Server.Datenbank.DatenbankManager;
import Server.FileManager.FileManager;
import Server.ServerClient.ClientHandeler.ClientListener;
import Server.ServerRooms.ChatRoom;
import Server.ServerRooms.ServerRoomsManager;




public class Server {
	
	private static int port;
	private static String hostname;
	
	private static FileManager fileManager = new FileManager();
	private static ServerRoomsManager serverRoomsManager = new ServerRoomsManager();
	private static ServerSocket server;
	private static DatenbankManager datenbankManager = new DatenbankManager();
	
	public static void main(String[] args) {
		
		hostname =fileManager.getServerHostname();
		port = Integer.parseInt(fileManager.getServerPort());
		
		try {
			server = new ServerSocket(port);
			new ClientListener(server).start();
			System.out.println("Server is waiting for Client: "+server);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[Server] Server Start Error: ");
			e.printStackTrace();
			
			System.out.println("[Server] stopping Server");
			System.exit(0);
		}
		

	}
	
	//Getter
	public static int getPort() {
		return port;
	}
	
	public static String getHostname() {
		return hostname;
	}
	
	public static ServerRoomsManager getServerRoomsManager() {
	return serverRoomsManager;
	}
	
	public static FileManager getFileManager() {
		return fileManager;
	}
	
	public static DatenbankManager getDatenbankManager() {
		return datenbankManager;
	}
	
}
