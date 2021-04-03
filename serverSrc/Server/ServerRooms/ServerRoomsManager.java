package Server.ServerRooms;

import Server.ServerClient.ClientHandeler.ClientHandeler;
import Server.ServerClient.ClientHandeler.massageCodes;

import java.util.Vector;

public class ServerRoomsManager {
	
	private static final Vector<ChatRoom> chatRooms = new Vector<ChatRoom>();
	private static final Vector<ClientHandeler> users = new Vector<ClientHandeler>();
	//Konstruktor
	public ServerRoomsManager() {
		createChatRoom("Main Room");
		createChatRoom("Room 1");
		createChatRoom("Room 2");
	}
	
	public void createChatRoom(String name) {
		System.out.println("Chatroom "+name +" created");
		ChatRoom chatRoom = new ChatRoom(chatRooms.size(), name);
		chatRooms.add(chatRoom);

		if(users.size()>0) {
			for (ClientHandeler client : users) {
				client.sendMassage(massageCodes.CHATROOMCREATED, chatRoom.getId() + "#" + chatRoom.getName());
			}
		}
	}



	/*public void removeChatRoom(int id) {
		for (ClientHandeler clientHandeler : chatRooms.get(id).getClientsInRoom()) {
			if (clientHandeler.getAccountHandler().getServerClient() != user) {
				clientHandeler.sendMassage(massageCodes.SENDTEXTMASSAGETOCLIENT, input);
			}
		}
	}*/

	//First Connect
	public void connectUserToChatRoom(ClientHandeler client){
		Thread t = new Thread(
			new Runnable(){
				@Override
				public void run() {
					System.out.println("LETS GOOOO");
					users.add(client);
					System.out.println("[ServerClient] "+client.getAccountHandler().getServerClient().getUsernameString()+" connected");
					for (ChatRoom chatRoom : chatRooms) {
						client.sendMassage(massageCodes.CHATROOMCREATED, chatRoom.getId() + "#" + chatRoom.getName());
					}

				}
			});
		t.start();

	}

	//Disconnect User from Server
	public void disconnectUser(ClientHandeler client){
		users.remove(client);
	}

	//Sending Massage to Users
	public void sendMessage(String username, int roomId, String input, ClientHandeler user){

		String massage = username+"#"+roomId+"#"+input;
		System.out.println("New Massage: "+massage);
		if (users.size() >= 2) {

		Thread t = new Thread(
				new Runnable(){
				@Override
				public void run(){

						for (ClientHandeler client : users) {

							if (client != user) {
								client.sendMassage(massageCodes.SENDTEXTMASSAGETOCLIENT, massage);
							}

						}


		}
				});
			t.start();
	} else {
		return;
		}

	}
	
	public void changeRoom(int id, ClientHandeler client) {
		getChatRooms().get(id).connectToRoom(client);	
		}
	
	public static Vector<ChatRoom> getChatRooms() {
		return chatRooms;
	}

	public static Vector<ClientHandeler> getUsers() {
		return users;
	}
}
