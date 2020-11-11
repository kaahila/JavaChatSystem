package Server.ServerRooms;

import java.util.Vector;

import Server.ServerClient.ClientHandeler.ClientHandeler;
import Server.ServerClient.ClientHandeler.massageCodes;

public class ServerRoomsManager {
	
	private static Vector<ChatRoom> chatRooms = new Vector<ChatRoom>();
	
	//Konstruktor
	public ServerRoomsManager() {
	}
	
	public void createChatRoom(String name) {
		chatRooms.add(new ChatRoom(chatRooms.size(), name));
		
	}
	
	/*public void removeChatRoom(int id) {
		for (ClientHandeler clientHandeler : chatRooms.get(id).getClientsInRoom()) {
			if (clientHandeler.getAccountHandler().getServerClient() != user) {
				clientHandeler.sendMassage(massageCodes.SENDTEXTMASSAGETOCLIENT, input);
			}
		}
	}*/
	
	
	public void changeRoom(int id, ClientHandeler client) {
		getChatRooms().get(id).connectToRoom(client);	
		}
	
	public static Vector<ChatRoom> getChatRooms() {
		return chatRooms;
	}
	
}
