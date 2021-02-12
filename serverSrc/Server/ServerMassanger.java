package Server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMassanger {

	private final ServerSocket server;
	private Socket client;
	
	private InputStream inputStream;
	private OutputStream outputStream;

    private PrintWriter writer;
	private BufferedReader reader;
	
	public ServerMassanger(ServerSocket serverSocket) {
		this.server = serverSocket;
		
	}
	

	
	public void sendMassage(Socket empfaengerClient, ServerSocket senderServer, String massage) {
		try {
			outputStream = empfaengerClient.getOutputStream();
			writer = new PrintWriter(outputStream, true);
			writer.println(massage);
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
}
