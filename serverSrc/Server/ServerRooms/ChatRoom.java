package Server.ServerRooms;

import java.util.Vector;

import Server.ServerClient.ClientAccount.ServerClient;
import Server.ServerClient.ClientHandeler.ClientHandeler;
import Server.ServerClient.ClientHandeler.massageCodes;

public class ChatRoom {
	
	
	private String name = "";
	
	private Vector<ClientHandeler> clientsInRoom = new Vector<ClientHandeler>();
	
	//Konstruktor
	public ChatRoom(String name) {
		if(name.length() > 1) {
		this.name = name;
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
		System.out.println("[ClientRoom, "+ name +"] Client "+ client.getAccountHandler().getServerClient().getUsernameString() +" connectet");
		clientsInRoom.add(client);
		client.setAktuelleChatRoom(this);
	}
	
	
	
	public String getName() {
		return name;
	}
}
