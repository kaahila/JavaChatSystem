package Client;

import java.util.ArrayList;

public class ChatRoom {
	
	private static final ArrayList<ChatRoom> chatRooms = new ArrayList<ChatRoom>();
	
	private final String name;
	private final int id;
	
	public ChatRoom(String name, int id) {
		this.name = name;
		this.id = id;
		
		chatRooms.add(this);
	}
	
	public static ArrayList<ChatRoom> getChatRooms() {
		return chatRooms;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
