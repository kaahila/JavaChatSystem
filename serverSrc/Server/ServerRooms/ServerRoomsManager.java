package Server.ServerRooms;

import Server.ServerClient.ClientHandeler.ClientHandeler;
import Server.ServerClient.ClientHandeler.massageCodes;

import java.util.Vector;

public class ServerRoomsManager {
	
	private static final Vector<ChatRoom> chatRooms = new Vector<ChatRoom>();
	
	//Konstruktor
	public ServerRoomsManager() {
		createChatRoom("Main Room");
		createChatRoom("sssss");
		createChatRoom("Penis");
	}
	
	public void createChatRoom(String name) {
		System.out.println("Chatroom "+name +" created");
		chatRooms.add(new ChatRoom(chatRooms.size(), name));
		
	}
	
	/*public void removeChatRoom(int id) {
		for (ClientHandeler clientHandeler : chatRooms.get(id).getClientsInRoom()) {
			if (clientHandeler.getAccountHandler().getServerClient() != user) {
				clientHandeler.sendMassage(massageCodes.SENDTEXTMASSAGETOCLIENT, input);
			}
		}
	}*/

	public void connectUserToChatRoom(ClientHandeler client){
		Thread t = new Thread(
			new Runnable(){
				@Override
				public void run() {
					System.out.println("LETS GOOOO");
					for (ChatRoom chatRoom : chatRooms) {
						client.sendMassage(massageCodes.CHATROOMCREATED, chatRoom.getId() + "#" + chatRoom.getName());
					}

				}
			});
		t.start();

	}
	
	public void changeRoom(int id, ClientHandeler client) {
		getChatRooms().get(id).connectToRoom(client);	
		}
	
	public static Vector<ChatRoom> getChatRooms() {
		return chatRooms;
	}
	
}
