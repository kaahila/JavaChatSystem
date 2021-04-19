package Server.Datenbank;

import Server.Server;
import Server.ServerClient.ClientAccount.ServerClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DatenbankManager {
	
	/*
	 * The Variables for the DatabaseManager
	 */
	
	DatenbankChecker datenbankChecker;
	
	private Connection mysqlConnection;


	private final String hostAdress;
	private final String port;
	
	private final String username;
	private final String password;
	
	private final String databaseName;
	private final String userTabelName;
	
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
        return mysqlConnection != null;
    }
	
	public ServerClient loginUser(String[] userInput) {
		
		ServerClient retClient = null;
		
		String usernameInput = userInput[0];
		String passwordInput = userInput[1];
		
		boolean usernameAvailable = false;
		
		
		try {
			mysqlConnection = DriverManager.getConnection("jdbc:mysql://" + hostAdress + ":" + port +
									"/" + databaseName + "?serverTimezone=UTC", username, password);

			PreparedStatement ps = mysqlConnection.prepareStatement("SELECT * FROM "+userTabelName+" WHERE username=?");
			ps.setString(1, usernameInput);
			ResultSet resultSet = ps.executeQuery();

				if (resultSet.next() && resultSet.getString(1).equalsIgnoreCase(usernameInput)) {
					usernameAvailable = true;				
			}
			
			if (usernameAvailable) {
				if (resultSet.getString(2).equals(passwordInput)) {
					System.out.println("[DatenbankManager] Login von User "+ usernameInput+" erfolgreich");
					
					retClient = new ServerClient(usernameInput);
				
				}
				
			}
			ps.close();
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
			PreparedStatement ps = mysqlConnection.prepareStatement("SELECT username FROM "+userTabelName+" WHERE username='"+usernameInput+"'");
			ps.setString(1,usernameInput);
			ResultSet resultSet = ps.executeQuery();

				if (resultSet.next() && resultSet.getString(1).equalsIgnoreCase(usernameInput)) {
					usernameAvailable = false;

				}

				resultSet.next();


			if (usernameAvailable) {

				PreparedStatement preparedStatement = mysqlConnection.prepareStatement("INSERT INTO " + userTabelName +
																"(username, password) VALUES (?, ?)");
				preparedStatement.setString(1, usernameInput);
				preparedStatement.setString(2, passwordInput);
				preparedStatement.execute();

				System.out.println("[DatenbankManager] user added");
				ret = true;


			} else {
				System.out.println("[DatenbankManager] the username is used");
				ret = false;
			}

			ps.close();
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
