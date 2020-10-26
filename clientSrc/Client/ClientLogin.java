package Client;

import Server.ServerClient.ClientHandeler.massageCodes;

public class ClientLogin {
	String usernameString = "";
	String passwordString = "";
	String sendString = "";
	
	//Konstruktor
	public ClientLogin() {
		// TODO Auto-generated constructor stub
	}
	
	
	//Methods
	public void login(String username, String password) {
		passwordString = passwordHash(password);
		
		sendString = username + "#" + passwordString;
		
		ClientMain.getClientMassanger().sendMassage(massageCodes.LOGINREQUEST, sendString);
		
	}
	
	public void register(String username, String password) {
		passwordString = passwordHash(password);
		
		sendString = username + "#" + passwordString;
		
		ClientMain.getClientMassanger().sendMassage(massageCodes.REGISTERREQUEST, sendString);
			}
	
	public String passwordHash(String password) {
	String ret = "";
		
		ret = new Hash(64, password).getHash();		
		return ret;
	}
}
