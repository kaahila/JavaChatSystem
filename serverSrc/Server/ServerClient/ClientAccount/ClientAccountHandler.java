package Server.ServerClient.ClientAccount;

import Server.Server;
import Server.Datenbank.DatenbankManager;
import Server.ServerClient.Hash;
import Server.ServerClient.ClientHandeler.ClientHandeler;
import Server.ServerClient.ClientHandeler.massageCodes;

public class ClientAccountHandler {
	private ServerClient serverClient;
	private final ClientHandeler clientHandeler;
	private DatenbankManager datenbankManager = Server.getDatenbankManager();	
	
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
				clientHandeler.connectToChatRoom(0);
		} else {
			
			retString = "false";
		}		
		
		this.clientHandeler.sendMassage(massageCodes.REGISTERANSWER, retString);
	}

	
	public ServerClient getServerClient() {
		return serverClient;
	}
}
