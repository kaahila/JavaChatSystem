package Server.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import jdk.nashorn.internal.runtime.ECMAErrors;

public class FileManager {
	private String key;
	
	private Properties configProperties;
	private File configFile;
	
	private BufferedReader bufferedReader;
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	
	public FileManager() {
		try {
				configProperties = new Properties();
				configFile = new File("config.propeties");
				
			if (!checkFile()) {
				configFile.createNewFile();
			
				fileInputStream = new FileInputStream(configFile);
				fileOutputStream = new FileOutputStream(configFile);
				System.out.println("[FileManager] ConfigFile created");
				storeDefaultConfigPropeties();
			} else {
				System.out.println("[FileManager] ConfigFile Already exist");
				
				fileInputStream = new FileInputStream(configFile);		
			} 
			
			loadConfigPropeties();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.err.println("[FileManager] An error has occurred");
			e.printStackTrace();
		} catch (IOException e2) {
			// TODO: handle exception
			System.err.println("[FileManager] An error has occurred");
			e2.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("[FileManager] ConfigFile loaded");
	}	
	
	/*
	 * Methode that creates the Defaults for the Config File
	 */
	
	public void storeDefaultConfigPropeties() throws Exception {
		/*
		 * Server Properties
		 */
		configProperties.setProperty("serverHostAdress", "127.0.0.1");
		configProperties.setProperty("serverHostPort", "4444");
		/*
		 * MySql Properties
		 */
		configProperties.setProperty("mySqlHostAdress", "127.0.0.1");
		configProperties.setProperty("mySqlPort", "3306");
	
		configProperties.setProperty("mySqlUsername", "root");
		configProperties.setProperty("mySqlPassword", "password");
		
		configProperties.setProperty("mySqlChatServerDatabaseName", "ChatServerDatabase");
		configProperties.setProperty("mySqlClientAccountTableName", "clientAccountTable");

		try {
			configProperties.store(fileOutputStream, "ServerConfigFile");
			fileOutputStream.close();
		} catch (IOException e) {
			System.err.println("[FileManager] An error has occurred");
			e.printStackTrace();
			fileOutputStream.close();
		}
	}
	
	/*
	 *Methode that loads the Config File
	 */
	
	public void loadConfigPropeties() throws Exception {
		try {
			configProperties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException e) {
			System.err.println("[FileManager] An error has occurred");
			e.printStackTrace();
			fileInputStream.close();
		}
		
	}
	
	/*
	 * Check the for Config File and check the contents
	 */
	
	public boolean checkFile(){
		boolean ret = false;
		try {
			
		if (configFile.exists()) {
			bufferedReader = new BufferedReader(new FileReader(configFile));
			
		if (bufferedReader.readLine() != null) {
			ret = true;
			bufferedReader.close();
		}
		}
			
		} catch (Exception e) {
			System.err.println("[FileManager] An error has occurred");
			e.printStackTrace();
		}
		return ret;
	}
	
	/*
	 * Get InetHostadress and Port 
	 */
	
	public String getServerHostname() {
		String retString = "";
		
		if (configProperties.getProperty("serverHostAdress") != null) {
			retString = configProperties.getProperty("serverHostAdress");
		} else {
			System.err.println("[FileManager] config.propeties serverHostAdress not set");
		}
		
		return retString;
	}
	
	public String getServerPort() {
		String retString = "";
		
		if (configProperties.getProperty("serverHostPort") != null) {
			retString = configProperties.getProperty("serverHostPort");
		} else {
			System.err.println("[FileManager] config.propeties hostPort not set");
		}
		
		return retString;
	}
	
	/*
	 * Get MySql hostAdress and Port
	 */
	
	public String getMySqlHostadress() {
		String retString = "";
		
		if (configProperties.getProperty("mySqlHostAdress") != null) {
			retString = configProperties.getProperty("mySqlHostAdress");
		} else {
			System.err.println("[FileManager] config.propeties MySqlHostAdress not set");
		}
		
		return retString;
	}
	
	public String getMySqlPort() {
		String retString = "";
		
		if (configProperties.getProperty("mySqlPort") != null) {
			retString = configProperties.getProperty("mySqlPort");
		} else {
			System.err.println("[FileManager] config.propeties MySqlPort not set");
		}
		
		return retString;
	}
	
	public String getMySqlUsername() {
		String retString = "";
		
		if (configProperties.getProperty("mySqlUsername") != null) {
			retString = configProperties.getProperty("mySqlUsername");
		} else {
			System.err.println("[FileManager] config.propeties mySqlUsername not set");
		}
		
		return retString;
	}
	
	public String getMySqlPassword() {
		String retString = "";
		
		if (configProperties.getProperty("mySqlPassword") != null) {
			retString = configProperties.getProperty("mySqlPassword");
		} else {
			System.err.println("[FileManager] config.propeties mySqlPassword not set");
		}
		
		return retString;
	}
	
	public String getMySqlChatServerDatabaseName() {
		String retString = "";
		
		if (configProperties.getProperty("mySqlChatServerDatabaseName") != null) {
			retString = configProperties.getProperty("mySqlChatServerDatabaseName");
		} else {
			System.err.println("[FileManager] config.propeties mySqlChatServerDatabaseName not set");
		}
		return retString;
	}
	
		public String getMySqlClientAccountTableName() {
			String retString = "";
			
			if (configProperties.getProperty("mySqlClientAccountTableName") != null) {
				retString = configProperties.getProperty("mySqlClientAccountTableName");
			} else {
				System.err.println("[FileManager] config.propeties mySqlClientAccountTableName not set");
			}
		
		return retString;
	}
	}
	
