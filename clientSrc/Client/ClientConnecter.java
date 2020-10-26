package Client;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientConnecter{
	
	private ApplicationController applicationController = ClientMain.getController();
	
	private InetAddress inet;
	private int acualPort = 0;
	private String acualHostname = "";
	
	public ClientConnecter() {
	}
	
	public void connect(String newHostname, int newPort){
		try {
			//Connect to server
			inet = InetAddress.getByName(newHostname);
			if (serverAdvailableCheck(newHostname, newPort)) {
				ClientMain.setClientSocket(new Socket(newHostname, newPort));
				this.acualHostname = newHostname;
				this.acualPort = newPort;
				System.out.println("Verbindung zu Server: "+acualHostname+"\nauf Port: "+acualPort+" erfolgreich");
				ClientMain.setClientMassanger();
			} else {
			
			applicationController.wrongInetAdressOrPort();
			ApplicationController.setConnecting(false);
			}
			
			
		} catch (Exception e) {
			applicationController.wrongInetAdressOrPort();
			System.out.println("ClienConnecter Error: ");
			e.printStackTrace();
			ApplicationController.setConnecting(false);
		}	
		
	}
	
	public boolean serverAdvailableCheck(String hostName, int port) throws IOException {
		Socket testSocket = null;
		try {
			if(inet.isReachable(1000)) {
			testSocket = new Socket(hostName, port);
			testSocket.close();
			return true;
		
		}
			} catch (IOException se) {
		}
		
		testSocket.close();
		return false;
		
		
	}
}

