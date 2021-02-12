package Server.Datenbank;

import Server.Server;

import java.sql.Connection;
import java.sql.ResultSet;

public class DatenbankChecker {

	/*
	 * The Variables for the DatabaseChecker
	 */
	
	private final Connection mySqlConnection;
	private final String mySqlDatabaseName;
	private final String mySqlClientAccountTableName;
		
	public DatenbankChecker(Connection connection) {
		mySqlConnection = connection;
		mySqlDatabaseName = Server.getFileManager().getMySqlChatServerDatabaseName();
		mySqlClientAccountTableName = Server.getFileManager().getMySqlClientAccountTableName();
		
		/*
		 * Database check an creation
		 */
		
		if (checkForDatabase(mySqlDatabaseName)) {
			System.out.println("[DatenbankChecker] "+mySqlDatabaseName+" Database founded");
					
		} else {
			System.out.println("[DatenbankChecker] Creating the "+mySqlDatabaseName+" Database");
			createDatabase(mySqlDatabaseName);
		}
		
		/*
		 * Table check and creation 
		 */
		
		if (checkForTable(mySqlClientAccountTableName, mySqlDatabaseName)) {
			System.out.println("[DatenbankChecker] "+mySqlClientAccountTableName+" Table founded");
			
		} else {
			System.out.println("[DatenbankChecker] Creating the "+mySqlClientAccountTableName+" Table");
			createTable(mySqlClientAccountTableName, mySqlDatabaseName );
		}
		
		/*
		 * Last Check
		 */
		
		if (checkForDatabase(mySqlDatabaseName) && checkForTable(mySqlClientAccountTableName, mySqlDatabaseName)) {
			System.out.println("[DatenbankChecker] Database is ready to work");
		} else {
			System.err.println("[DatenbankChecker] Last Check Error");
			System.exit(0);
		}
		
		
	}
	
	/*
	 * The CheckMethodes
	 */
	
	public boolean checkForDatabase(String name) {
		boolean ret = false;
		try {
			ResultSet resultSet = mySqlConnection.createStatement().executeQuery("SHOW DATABASES LIKE '"+name+"'");
			
			if (resultSet.isBeforeFirst()) {
				ret = true;
			}			
			
		} catch (Exception e) {
			System.out.println("[DatenbankChecker] checkForDatabase Error");
			e.printStackTrace();
		}	
		return ret;
	}
	
	public boolean checkForTable(String tablename, String databaseName) {
		boolean ret = false;
		try {
			ResultSet resultSet;
			 resultSet = mySqlConnection.createStatement().executeQuery("USE "+ databaseName );
			 resultSet = mySqlConnection.createStatement().executeQuery("SHOW TABLES LIKE '"+tablename+"'");
			
			if (resultSet.isBeforeFirst()) {
				ret = true;
			}			
			
		} catch (Exception e) {
			System.out.println("[DatenbankChecker] checkForTable() Error");
			e.printStackTrace();
		}	
		
		
		return ret;
	}
	
	/*
	 * If the check result is false one of these Methodes will be started.
	 */
	
	public void createDatabase(String name) {
		try {
			mySqlConnection.createStatement().executeUpdate("CREATE DATABASE "+name);		
		} catch (Exception e) {
			System.out.println("[DatenbankChecker] createDatabase() Error");
			e.printStackTrace();
		}	
	}
	
	public void createTable(String tableName, String databaseName) {
		try {
			ResultSet resultSet;
			resultSet = mySqlConnection.createStatement().executeQuery("USE "+ databaseName );
			mySqlConnection.createStatement().executeUpdate("CREATE TABLE "+tableName+"(username VARCHAR(20), password VARCHAR(64))");		
		} catch (Exception e) {
			System.out.println("[DatenbankChecker] createTable() Error");
			e.printStackTrace();
		}
	}
}
