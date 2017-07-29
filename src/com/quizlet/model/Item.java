package com.quizlet.model;

/**
 * 
 * @author jarc0der
 *	Domain class Item for Question and Answer. They have common fields like id and text
 */

public class Item {
	
	private int id;
	
	private String text;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
