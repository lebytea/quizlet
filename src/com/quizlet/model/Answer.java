package com.quizlet.model;

public class Answer {

	private int id;
	private String text;

	private AnswerType answerType;
	
	public boolean isCorrect(){
		return (answerType ==  AnswerType.CORRECT);
	}
	
	public AnswerType getAnswerType(){
		return answerType;
	}

	public void setAnswerType(boolean trueAnswer){//Write Andriy: цей сетер також мав би напевно бути тут

		if (trueAnswer==true){
			answerType = AnswerType.CORRECT;
		}else answerType = AnswerType.WRONG;


	}


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
