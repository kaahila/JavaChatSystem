package Server;

import Server.Datenbank.DatenbankManager;
import Server.FileManager.FileManager;
import Server.ServerClient.ClientHandeler.ClientListener;
import Server.ServerRooms.ServerRoomsManager;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;




public class Server {
	
	private static int port;
	private static String hostname;
	
	private static final FileManager fileManager = new FileManager();
	private static final ServerRoomsManager serverRoomsManager = new ServerRoomsManager();
	private static ServerSocket server;
	private static final DatenbankManager datenbankManager = new DatenbankManager();
	
	public static void main(String[] args) {
		
		hostname =fileManager.getServerHostname();
		port = Integer.parseInt(fileManager.getServerPort());
		
		try {
			server = new ServerSocket(port, 0, InetAddress.getByName(hostname));
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
