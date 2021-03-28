package Server.ServerClient.ClientAccount;

import Server.Datenbank.DatenbankManager;
import Server.Server;
import Server.ServerClient.ClientHandeler.ClientHandeler;
import Server.ServerClient.ClientHandeler.massageCodes;
import Server.ServerClient.Hash;

public class ClientAccountHandler {
	private ServerClient serverClient;
	private final ClientHandeler clientHandeler;
	private final DatenbankManager datenbankManager = Server.getDatenbankManager();
	
	public ClientAccountHandler(ClientHandeler clientHandeler) {
	    this.clientHandeler = clientHandeler;
	}
	
	public void login(String username, String passwordHash) {
		String retString = "";
		
		String[] userInput = new String[2];
		
		userInput[0] = username;
		userInput[1] = new Hash(64, passwordHash).getHash();
		
		serverClient = datenbankManager.loginUser(userInput);
		
		if (serverClient != null) {
			retString = "true";
			clientHandeler.firstConnect();
			clientHandeler.connectToChatRoom(0);

		} else {
			retString = "false";
		}
		
		this.clientHandeler.sendMassage(massageCodes.LOGINANSWER, retString);
		
	}
	
	public void register(String username, String passwordHash) {
		String retString = "";
		
		String[] userInput = new String[2];
		
		userInput[0] = username;
		userInput[1] = new Hash(64, passwordHash).getHash();
		
		if(datenbankManager.addUserToDatabase(userInput)) {
			
				retString = "true";
				System.out.println("[AccountHandler] connecting new User to ChatRoom");
				login(username,  passwordHash);
		} else {
			
			retString = "false";
		}		
		
		this.clientHandeler.sendMassage(massageCodes.REGISTERANSWER, retString);
	}

	
	public ServerClient getServerClient() {
		return serverClient;
	}
}
