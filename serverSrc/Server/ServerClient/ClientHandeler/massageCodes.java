package Server.ServerClient.ClientHandeler;

public enum massageCodes {
	
	/*
	 * Login Codes
	 */
	
	LOGINREQUEST("LOGINREQUEST"),
	LOGINANSWER("LOGINANSWER"),

	/*
	 * Register Codes
	 */

	REGISTERREQUEST("REGISTERREQUEST"),
	REGISTERANSWER("REGISTERANSWER"),

	/*
	 * TextMassage Codes
	 */

	SENDTEXTMASSAGETOCLIENT("SENDTEXTMASSAGETOCLIENT"),
	SENDTEXTMASSAGETOSERVER("SENDTEXTMASSAGETOSERVER"),
	
	/*
	 * ChangeChatRoomCodes
	 */
	
	CHANGECHATROOMREQUEST("CHANGECHATROOMREQUEST"),
	CHANGECHATROOMANSWER("CHANGECHATROOMANSWER"),
	
	/*
	 * ChatRoomCreationCodes
	 */
	
	CHATROOMCREATED("CHATROOMCREATED"),
	CHATROOMREMOVED("CHATROOMCREATED"),
	CHATROOMCREATIONANSWER("CHATROOMCREATIONANSWER")

	;
	
	//Construktor
	private final String massage;
	private massageCodes(final String massage) {
		this.massage = massage;
	}
	
	/*
	 * the toString() that returns the String of the Enums
	 */
	
	@Override
	public String toString() {
		return this.massage;
	}
	
	public static massageCodes toEnum(String input) {
		massageCodes ret = null;
		
		ret = massageCodes.valueOf(input);
		
		return ret;
	}
}
