package com.FoodSocialNetwork.app.postRequest;

import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

public class CreateRecipeRequest {
//	public DefaultResponse createRecipeWithPicture(
//	@RequestParam(value = "session") String session,
//	@RequestParam(value = "title") String title,
//	@RequestParam(value = "time") long time,
//	@RequestParam(value = "instruction") String instruction,
//	@RequestParam(value = "category", defaultValue = "0") long category,
//	@RequestParam(value = "image", defaultValue = "None") MultipartFile image,
//	@RequestParam(value = "ingredients") JSONArray ingridients,
//	@RequestParam(value = "tools", defaultValue = "") String[] tools)

	private String session;
	private String title;
	private long time;
	private String instruction;
	private long category;
	private MultipartFile image;
	private JSONArray ingredients;
	private String[] tools;
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public long getCategory() {
		return category;
	}
	public void setCategory(long category) {
		this.category = category;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public JSONArray getIngredients() {
		return ingredients;
	}
	public void setIngredients(JSONArray ingredients) {
		this.ingredients = ingredients;
	}
	public String[] getTools() {
		return tools;
	}
	public void setTools(String[] tools) {
		this.tools = tools;
	}
	
}
