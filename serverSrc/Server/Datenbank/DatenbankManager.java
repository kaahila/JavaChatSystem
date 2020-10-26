package Server.Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import Server.Server;
import Server.ServerClient.ClientAccount.ServerClient;

public class DatenbankManager {
	
	/*
	 * The Variables for the DatabaseManager
	 */
	
	DatenbankChecker datenbankChecker;
	
	private Connection mysqlConnection;
	
	private String hostAdress;
	private String port;
	
	private String username;
	private String password;
	
	private String databaseName;
	private String userTabelName;
	
	public DatenbankManager() {
		hostAdress = Server.getFileManager().getMySqlHostadress();
		port = Server.getFileManager().getMySqlPort();
		
		username = Server.getFileManager().getMySqlUsername();
		password = Server.getFileManager().getMySqlPassword();
		
		databaseName = Server.getFileManager().getMySqlChatServerDatabaseName();
		userTabelName = Server.getFileManager().getMySqlClientAccountTableName();
		
		try {
			mysqlConnection = DriverManager.getConnection("jdbc:mysql://"
							+ hostAdress + ":" + port + "/?serverTimezone=UTC",
								username, password);
			
			if (isConnected()) {
				datenbankChecker = new DatenbankChecker(mysqlConnection);
				mysqlConnection.close();
			}
			
		} catch (SQLException e) {
			System.err.println("[DatenbankManager] Connection to Database failed");
			
			e.printStackTrace();
			
			System.out.println("[DatenbankManager] stopping Server");
			System.exit(0);
		}
		
		

				
	}
	
	private boolean isConnected() {
		if (mysqlConnection != null) {
			return true;
		}
		return false;
	}
	
	public ServerClient loginUser(String[] userInput) {
		
		ServerClient retClient = null;
		
		String usernameInput = userInput[0];
		String passwordInput = userInput[1];
		
		boolean usernameAvailable = false;
		
		
		try {
			mysqlConnection = DriverManager.getConnection("jdbc:mysql://" + hostAdress + ":" + port +
									"/" + databaseName + "?serverTimezone=UTC", username, password);
			
			ResultSet resultSet = mysqlConnection.createStatement().executeQuery("SELECT * FROM "+userTabelName+" WHERE username='"+usernameInput+"'");
			
				if (resultSet.next() && resultSet.getString(1).equalsIgnoreCase(usernameInput)) {
					usernameAvailable = true;				
			}
			
			if (usernameAvailable) {
				if (resultSet.getString(2).equals(passwordInput)) {
					System.out.println("[DatenbankManager] Login von User "+ usernameInput+" erfolgreich");
					
					retClient = new ServerClient(usernameInput);
				
				}
				
			}
			resultSet.close();
			mysqlConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("[DatenbankManager] loginUser() Error");
			e.printStackTrace();
		}
		return retClient;
	}
	
	public boolean addUserToDatabase(String[] userInput) {
		boolean ret = false;
		
		String usernameInput = userInput[0];
		String passwordInput = userInput[1];
		boolean usernameAvailable = true;
		
		try {
			mysqlConnection = DriverManager.getConnection("jdbc:mysql://" + hostAdress + ":" + port + 
									"/" + databaseName + "?serverTimezone=UTC", username, password);
			
			ResultSet resultSet = mysqlConnection.createStatement().executeQuery("SELECT username FROM "+userTabelName+" WHERE username='"+usernameInput+"'");
			
				if (resultSet.next() && resultSet.getString(1).equalsIgnoreCase(usernameInput)) {
					usernameAvailable = false;
					
				}
				
				resultSet.next();
			
			
			if (usernameAvailable) {
				mysqlConnection.createStatement().executeUpdate("INSERT INTO " + userTabelName + 
																"(username, password) VALUES ('"+usernameInput+"', '"+passwordInput+"')");
				System.out.println("[DatenbankManager] user added");
				ret = true;
				
			} else {
				System.out.println("[DatenbankManager] the username is used");
				ret = false;
			}
			
			resultSet.close();
			mysqlConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("[DatenbankManager] addUserToDatabase() Error");
			e.printStackTrace();
		}
		return ret;
	}
	
	
}
