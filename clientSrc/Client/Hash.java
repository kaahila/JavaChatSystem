package Client;

import javafx.fxml.FXML;

public class Hash {

	//Variablen
	    private StringBuilder sBuilder = new StringBuilder();
	    private static ApplicationController applicationController;
	    //Minimale Anzahl der Buchstaben des Password
		int minChars = 8;
		
		int hashLaenge = 0;
		
		int sum = 0;
		int teiler = 0;
		int summeAlles = 0;
		int mixMenge = 0;
		int durchgänge = 0;
		
		
		int[] passArr = new int[9], passOutArr = new int[9];
		char[] charArr, charOutArr;
		char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'}; 
		
	
	
	private String outputHash;
	
	//Konstruktor
	public Hash(int newHashlaenge,String newInputPass) {
		hashLaenge = newHashlaenge;
		if (lengthTest(newInputPass)) {
			outputHash = verschlüsselung(newInputPass);
		
		}
		
	}
	
	public Hash() {
		
	}
	
	//Verschlüsselung
	
	public String verschlüsselung(String newInputPass) {
		//VARIABLEN
		String ret = "";
		
		charArr = newInputPass.toCharArray(); 
		
		//Chars zu Int konvertieren und in 3x3 Raster stecken
		teiler = charArr.length % 9;
		
		
			int c = 0;
			for (int p = 0; p < 9; p++) {
				
				if (charArr.length <= c ) {
					
					passArr[p] = 0;
					
				} else {
					
					
					passArr[p] = (int)charArr[c];
					c++;
					
					if (teiler>0 && charArr.length > c ) {
						
						passArr[p] = passArr[p] + (int)charArr[c];					
						teiler--;
						c++;
						
					}
				} 
				
				
				
			}
			
		
		do {
			durchgänge++;
		
			//Addition
			addition();
			
		//Mixxen
			mixxen();
			
		//Dec To Hex
			for(int i = 0; i < passArr.length; i++) {				
		
			charOutArr = decToHex(passArr[i]).toCharArray();
			
			for (int j = 0; j < charOutArr.length && sBuilder.toString().length()<hashLaenge; j++) {
				sBuilder.append(charOutArr[j]);
			}
				
			
			
			
		}
			
		} while (sBuilder.toString().length() < hashLaenge);			
		
		//Output
		ret = sBuilder.toString();		
		
		System.out.println("Hashlänge: "+ret.length());
		
		System.out.println("Hash: "+ret);
		return ret;
	}
	
	//PassTest
	public boolean passTest(String newInputPass) {
		boolean ret = false;
		
		if(verschlüsselung(newInputPass) == getHash()) {
			ret = true;
		}
		
		return ret;
	}
	
	//LengthTest
	public boolean lengthTest(String inPass) {
		boolean ret = false;
		
		if (inPass.length() >= minChars && inPass.length() > 0) {
			ret = true;
		}
		
		
		return ret;
	}
	
	//Mixxen
	private void mixxen() {
		for (int i = 0; i < passArr.length; i++) {
			summeAlles += passArr[i];
		}

		summeAlles = (int)Math.sqrt((double)summeAlles);
		
		mixMenge = summeAlles%3;
	
		
		for (int i = 0; i < mixMenge; i++) {
			int eins = passArr[0];
			int vier = passArr[3];
			int sieben = passArr[6];
			
			//Mixing
			passArr[0] = passArr[1];
			passArr[3] = passArr[4];
			passArr[6] = passArr[7];
			
			passArr[1] = passArr[2];
			passArr[4] = passArr[5];
			passArr[7] = passArr[8];
		
			passArr[2] = eins;
			passArr[5] = vier;
			passArr[8] = sieben;	
			
			addition();
		}

	}
	
	//Addition
	private void addition() {
		//Reihe1
		passArr[0] = passArr[0]+passArr[4]+passArr[8];
		passArr[1] = passArr[1]+passArr[3]+passArr[7];
		passArr[2] = passArr[2]+passArr[4]+passArr[6];
		
		//Reihe2
		passArr[3] = passArr[3]+passArr[7]+passArr[5];
		passArr[4] = passArr[4]+passArr[1]+passArr[6];
		passArr[5] = passArr[5]+passArr[1]+passArr[8];
		
		//Reihe3
		passArr[6] = passArr[6]+passArr[3]+passArr[0];
		passArr[7] = passArr[7]+passArr[0]+passArr[2];
		passArr[8] = passArr[8]+passArr[5]+passArr[2];
	}
	
	//Dezimal to Hexa
	private String decToHex(int input) {
		String ret = "";
		int zahl = 0;
		
		 while(input>0)  
	     {  
	       zahl=input%16;   
	       ret=hexChar[zahl]+ret;   
	       input=input/16;  
	     }  
	
		return ret;
	}
		
	
	//Getter

	public String getHash() {
		return outputHash;
	}
}
