package Server.ServerRooms;

import java.util.Vector;

import Server.ServerClient.ClientAccount.ServerClient;
import Server.ServerClient.ClientHandeler.ClientHandeler;
import Server.ServerClient.ClientHandeler.massageCodes;

public class ChatRoom {
	
	
	private String name = "";
	private int id;
	
	private Vector<ClientHandeler> clientsInRoom = new Vector<ClientHandeler>();
	
	//Konstruktor
	public ChatRoom(int id, String name) {
		if(name.length() >= 1) {
		this.name = name;
		this.id = id;
		
		for (ClientHandeler clientHandeler : ClientHandeler.getClientHandelers()) {
			clientHandeler.sendMassage(massageCodes.CHATROOMCREATED, id+"#"+name);
		}
		}
		
	}
	
	/*
	 * Methode to enter when new Massage reviced
	 * It spreads the massage to all users in the ChatRoom
	 */
	
	public void addMassageToChatRoom(ServerClient user, String massage) {
		newMassageInput(user, massage);
	}
	
	public void newMassageInput(ServerClient user, String input) {
		input = user.getUsernameString()+input;
		
		if (clientsInRoom.size() >= 2) {
		for (ClientHandeler clientHandeler : clientsInRoom) {
			if (clientHandeler.getAccountHandler().getServerClient() != user) {
				clientHandeler.sendMassage(massageCodes.SENDTEXTMASSAGETOCLIENT, input);
			}
		}
		} else {
			return;
		}
	}
	
	/*
	 * Methode to call when a Client connects to the Room
	 */
	
	public void connectToRoom(ClientHandeler client) {
		disconnectFromRoom(client);
		System.out.println("[ClientRoom, "+ name +"] Client "+ client.getAccountHandler().getServerClient().getUsernameString() +" connectet");
		clientsInRoom.add(client);
		client.setAktuelleChatRoom(this);
	}
	
	/*
	 * Disconnect the User from the actual ChatRoom
	 */
	
	public void disconnectFromRoom(ClientHandeler client) {
		if (client.getAktuelleChatRoom() == null) {
			return;
		}
		if (client.getAktuelleChatRoom().getClientsInRoom().contains(client)) {
			client.getAktuelleChatRoom().getClientsInRoom().remove(client);
		}
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Vector<ClientHandeler> getClientsInRoom() {
		return clientsInRoom;
	}
}
