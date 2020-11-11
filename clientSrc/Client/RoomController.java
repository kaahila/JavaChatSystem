package Client;

import java.util.ArrayList;
import java.util.Vector;


public class RoomController {
	
	public RoomController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Methode to change the ChatRoom on Clientside 
	 */
	
	public void changeChatRoom(int id){
		
		if (id>=0 && id <= ChatRoom.getChatRooms().size()) {
			
			String massage = ""+id;
					
			
			ClientMain.getClientMassanger().sendMassage(massageCodes.CHANGECHATROOMREQUEST, massage);
		}
		
	}
}
