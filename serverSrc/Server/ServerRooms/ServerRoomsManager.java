package Server.ServerRooms;

import java.util.Vector;

public class ServerRoomsManager {
	
	private static Vector<ChatRoom> chatRooms = new Vector<ChatRoom>();
	
	//Konstruktor
	public ServerRoomsManager() {
		createChatRoom("Main Room");
	}
	
	public void createChatRoom(String name) {
		chatRooms.add(new ChatRoom(name));
	}
	
	public ChatRoom getChatRoomByName(String name) {
		ChatRoom retChatRoom = null;
		
		for (ChatRoom chatRoom : chatRooms) {
			if (chatRoom.getName().equalsIgnoreCase(name)) {
				retChatRoom = chatRoom;
				break;
			}		
		}
		return retChatRoom;
	}
	
	public static Vector<ChatRoom> getChatRooms() {
		return chatRooms;
	}
	
}
