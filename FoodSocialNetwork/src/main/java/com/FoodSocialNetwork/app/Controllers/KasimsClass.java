package com.FoodSocialNetwork.app.Controllers;

public class KasimsClass {
	private int myInt;

	public KasimsClass(int parameterOne)
	{
		this.myInt = parameterOne;
	}
	
	public KasimsClass getKasim()
	{
		this.hi();
		return new KasimsClass(9);
	}
	
	private void hi()
	{
		
	}
	
	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}
	
	
	
}
