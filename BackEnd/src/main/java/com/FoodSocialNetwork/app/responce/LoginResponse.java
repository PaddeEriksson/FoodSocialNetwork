package com.FoodSocialNetwork.app.responce;

public class LoginResponse extends DefaultResponse{
	
	private String sessionID;

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
}
