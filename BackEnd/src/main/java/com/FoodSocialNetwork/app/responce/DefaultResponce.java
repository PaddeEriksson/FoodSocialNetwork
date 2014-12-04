package com.FoodSocialNetwork.app.responce;


/**
 * THe default response contains a boolean with that tells if the login was success full and a error with the error if any
 * 
 * @author Toshiba
 *
 */
public class DefaultResponse {
	private boolean success;
	private String error;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
