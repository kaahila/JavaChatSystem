package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Client.massageCodes;


public class ClientMassanger extends Thread {
	private DataInputStream input;
	private DataOutputStream output;
	
	private ApplicationController applicationController;
	
	private Socket clientSocket;
	
	private String revicedString;
	private String outputString;
	private massageCodes massageCode;
	
	private String usernameString;
	private String passwordString;
	
	private int charIndex;
	
	public ClientMassanger(Socket newClientSocket) {
	applicationController = ClientMain.getController();
		this.clientSocket = newClientSocket;
		try {
			this.input = new DataInputStream(this.clientSocket.getInputStream());
			this.output = new DataOutputStream(this.clientSocket.getOutputStream());
		} catch (Exception e) {
			System.out.println("ClientMassanger Konstruktor Error: ");
			e.printStackTrace();
		}
		System.out.println("ClientMassanger erfolgreich erstellt.");
	}
	
	@Override
	public void run() {
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
						ClientMain.getClientSocket().close();
						ApplicationController.setConnecting(false);
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
			
		}
		
		System.out.println("ClientMassanger closed");
	}
	
	
	public void sendMassage(massageCodes massageCode, String massage) {
		try {
			switch (massageCode) {
			case LOGINREQUEST: //Login 
				massage = massageCode.toString()+"#"+massage;
				break;
				
			case REGISTERREQUEST: //Register
				massage = massageCode.toString()+"#"+massage;
				break;
				
			case SENDTEXTMASSAGETOSERVER: //Massage
				
				applicationController.addMassage(ChatBubbleMode.SENDBYME,"Me" ,massage);

				massage = massageCode.toString()+"#"+massage;
				
				break;
				
			case CHANGECHATROOMREQUEST: //Change ChatRoom
				massage = massageCode.toString()+"#"+massage;
				break;
				
			default:
				System.err.println("[ClientMassanger] Wrong massageCode in sendMassage() of "+clientSocket);
				return;
			}
			
			this.output.writeUTF(massage);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[ClientMassanger] Error: ");
			e.printStackTrace();
		}
	}
	
	private void massageCheck(String reviced) {
		if (reviced != null) {
			System.out.println(""+reviced);
			massageCode = massageCodes.toEnum(reviced.substring(0, reviced.indexOf('#')));
			
			reviced = reviced.substring(reviced.indexOf('#')+1);
			
			switch (massageCode) {
			case LOGINANSWER: //LoginServerAnswer
				if (reviced.contains("true")) {
					System.out.println("Login: "+reviced);
					ClientMain.setScene(2);
				} else if (reviced.contains("false")) {
					applicationController.wrongUsernameOrPassword();
					break;
				} else {
					System.out.println("Login Answer Error");
					break;
				}
				break;
			case REGISTERANSWER: //RegisterServerAnswer
				
				switch (reviced) {
				case "true":
					System.out.println("Register: "+reviced);
					ClientMain.setScene(2);
					break;
				case "false":
					applicationController.usernameUsed();
					break;
				default:
					System.out.println("Register Answer Error");
					break;
				}	
				break;
				
						
			case SENDTEXTMASSAGETOCLIENT: //ReviceMassage
				int index = reviced.lastIndexOf('#');
				
				System.out.println("[ClientMassanger] New Massage reviced");

				applicationController.addMassage(ChatBubbleMode.SENDBYANOTHERUSER ,reviced.substring(1, index), reviced.substring(index+1));
				break;
				
			case CHANGECHATROOMANSWER:
					
				break;
			default:
				
			
		}	
		}
		}
	}

