package com.FoodSocialNetwork.app.database;

public class Ingredient {

	
	
	private String recipeTitle;
	private String name;
	private int isOptional;
	private float amount;
	private String amountType;
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsOptional() {
		return isOptional;
	}
	public void setIsOptional(int isOptional) {
		this.isOptional = isOptional;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getAmountType() {
		return amountType;
	}
	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}
	
}
