package Server.ServerClient.ClientHandeler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;


import Server.Server;
import Server.ServerClient.ClientAccount.ClientAccountHandler;
import Server.ServerRooms.ChatRoom;
import Server.ServerRooms.ServerRoomsManager;

public class ClientHandeler extends Thread {
	
	private ServerRoomsManager serverRoomsManager = Server.getServerRoomsManager();
	private Socket clientSocket;
	private DataInputStream input;
	private DataOutputStream output;
	private ClientAccountHandler accountHandler;
	
	private String revicedString;
	private massageCodes massageCode;
	
	private String usernameString;
	private String passwordString;
	
	private static Vector<ClientHandeler> clientHandelers = new Vector<ClientHandeler>();
	
	private int charIndex;
	
	private ChatRoom aktuelleChatRoom;
	
	public ClientHandeler(Socket newClientSocket , DataInputStream newInput, DataOutputStream newOutput) {
		this.clientSocket = newClientSocket;
		this.input = newInput;
		this.output = newOutput;	
		clientHandelers.add(this);
		accountHandler = new ClientAccountHandler(this);
	}
	
	
	@Override
	public void run() {
		System.out.println("[ClientHandeler] ClientHandeler for "+clientSocket+" created.");

		while (true) {
			try {
				revicedString = input.readUTF();
				massageCheck(revicedString);
				
				
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					if (!clientSocket.isConnected()) {
						input.close();
						output.close();
						clientSocket.close();
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			
		}
		
		System.out.println("ClientHandler closed");
	}
	
	private void massageCheck(String reviced) {
		if (reviced != null) {
		
		massageCode = massageCodes.toEnum(reviced.substring(0, reviced.indexOf('#')));
		
		reviced = reviced.substring(reviced.indexOf('#')+1);
		
		/*if (accountHandler.getServerClient() == null && (massageCode != massageCodes.LOGINREQUEST || massageCode != massageCodes.REGISTERREQUEST)) {
			return;
		}*/
		
			switch (massageCode) {
			case LOGINREQUEST:  //Login
				
				charIndex = reviced.lastIndexOf('#');
				
				usernameString = reviced.substring(0, charIndex);
				passwordString = reviced.substring(charIndex+1);
				
				System.out.println(""+usernameString);
				System.out.println(""+passwordString);
				
				accountHandler.login(usernameString, passwordString);
				break;
				
			case REGISTERREQUEST: //Register
				charIndex = reviced.lastIndexOf('#');
				
				usernameString = reviced.substring(0, charIndex);
				passwordString = reviced.substring(charIndex+1);
				
				System.out.println(""+usernameString);
				System.out.println(""+passwordString);
				
				accountHandler.register(usernameString, passwordString);
				break;
				
			case SENDTEXTMASSAGETOSERVER: //Massage
				this.aktuelleChatRoom.addMassageToChatRoom(accountHandler.getServerClient() , reviced);
				break;
				
			case CHANGECHATROOMREQUEST: //Massage

				int id = Integer.parseInt(reviced);

				System.out.println("[ClienHandeler | "+clientSocket+"] ChangeChatRoomRequest "+id);


				
				serverRoomsManager.changeRoom(id, this);
				
				break;
				
			case CHATROOMCREATIONANSWER:
				
				
				
				if (reviced.contains("true")) {
					
				} else if(reviced.contains("false")){
					try {
						clientSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			default:
					System.err.println("[ClientHandeler] ClientHandeler "+clientSocket+" Code is wrong");
				break;
			
		}	
		}
		}
	
	
	public void sendMassage(massageCodes massageCode, String massage) {
		System.out.println("[ClientHandeler] new Massage "+massageCode.toString()+"#"+massage);
		try {
			switch (massageCode) {
			case LOGINANSWER: //LoginServerAnswer
				massage = massageCode.toString()+"#"+massage;
				break;
				
			case REGISTERANSWER: //RegisterServerAnswer		
				massage = massageCode.toString()+"#"+massage;
				break;
			
			case SENDTEXTMASSAGETOCLIENT: //SendMassage
				massage = massageCode.toString()+"#"+massage;
				break;
				
			case CHANGECHATROOMANSWER: //CHANGECHATROOM
				massage = massageCode.toString()+"#"+massage;
				break;
				
			case CHATROOMCREATED:
				massage = massageCode.toString()+"#"+massage;

				break;
				
			case CHATROOMREMOVED:
				massage = massageCode.toString()+"#"+massage;

				break;
			default:
				System.err.println("[ClientHandeler] Wrong massageCode in sendMassage() of "+clientSocket);
				return;
			}
			
			this.output.writeUTF(massage);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[ClientHandeler] ClientMassanger Error: "+clientSocket);
			e.printStackTrace();
		}
	}
	
	public ClientAccountHandler getAccountHandler() {
		return accountHandler;
	}
	
	public static Vector<ClientHandeler> getClientHandelers() {
		return clientHandelers;
	}
	
	public void setAktuelleChatRoom(ChatRoom aktuelleChatRoom) {
		this.aktuelleChatRoom = aktuelleChatRoom;
	}
	
	public ChatRoom getAktuelleChatRoom() {
		
			return aktuelleChatRoom;
		
		
	}
	boolean firstConnect = true;
	public void firstConnect(){
		if(firstConnect){

			Server.getServerRoomsManager().connectUserToChatRoom(this);

			firstConnect = false;
		}
	}

	public void connectToChatRoom(int index) {
		ServerRoomsManager.getChatRooms().get(index).connectToRoom(this);

	}
}
