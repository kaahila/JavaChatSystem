package Server.ServerClient.ClientAccount;

import java.util.Vector;

import Server.ServerClient.Hash;
import Server.ServerRooms.ChatRoom;

public class ServerClient {
	
	private String usernameString;
	private ChatRoom chatRoom;
	
	
	private static Vector<ServerClient> clients = new Vector<ServerClient>();
	
	
	public ServerClient(String inputUsernameString) {
		this.usernameString = inputUsernameString;
		
		clients.add(this);
		
		System.out.println("[ServerClient] "+usernameString+" connected");
	}
	
	//Methodes
	
	private void changeChatRoom(int ChatRoomId) {
		
	}
	
	//Getter
	public static Vector<ServerClient> getClients() {
		return clients;
	}
	
	public String getUsernameString() {
		return usernameString;
	}
	
	public ChatRoom getChatRoom() {
		return chatRoom;
	}
	
	
	
	
}
