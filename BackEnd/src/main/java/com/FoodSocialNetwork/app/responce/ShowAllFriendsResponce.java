package com.FoodSocialNetwork.app.responce;

public class ShowAllFriendsResponce extends DefaultResponse{
	
	private ShowFriendListResponce[] friends;

	public ShowFriendListResponce[] getFriends() {
		return friends;
	}

	public void setFriends(ShowFriendListResponce[] friends) {
		this.friends = friends;
	}

}
