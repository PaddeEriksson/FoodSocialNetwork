package com.FoodSocialNetwork.app.database;

public class Recipe {
	
	private long id;
	private String recipeTitle;
	private String instruction;
	private int time;
	private int category;
	private String creator;
	private String IMG;
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getIMG() {
		return IMG;
	}
	public void setIMG(String iMG) {
		IMG = iMG;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean returnValue = false;
		
		if(o instanceof Recipe)
		{
			if(((Recipe) o).getId() == this.getId())
			{
				returnValue = true;
			}
		}

		return returnValue;
	}
	
}
