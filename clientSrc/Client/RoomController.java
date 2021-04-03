package Client;

import MainAndRessources.ClientMain;


public class RoomController {

	private int currentRoomId;

	public RoomController() {
		this.currentRoomId = 0;
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Methode to change the ChatRoom on Clientside 
	 */
	
	public void changeChatRoom(int id){
		
		if (id>=0 && id <= ChatRoom.getChatRooms().size()) {
			
			String massage = ""+id;

			this.currentRoomId = id;
			
			ClientMain.getClientMassanger().sendMassage(massageCodes.CHANGECHATROOMREQUEST, massage);

		}
		
	}

	public int getCurrentRoomId() {
		return currentRoomId;
	}
}
