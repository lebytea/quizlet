package com.quizlet.model;

public class Answer extends Item{

	public Answer(int id, String answer, AnswerType answerType) {
		super(id, answer);
		this.answerType = answerType;
	}
	
	public Answer(int id, String answer){
		super(id, answer);
		this.answerType = AnswerType.WRONG;
	}

	//Ці змінні по ходу не потрібні, бо ти їх екстендиш з Item і аналогічно закоментував сетери і гетери для них унизу, бо ти їх також екстендиш з Item
	//private int id;
	//private String text;


	private AnswerType answerType;

	
	public boolean isCorrect(){
		return (answerType ==  AnswerType.CORRECT);
	}
	
	public AnswerType getAnswerType(){
		return answerType;
	}

	//Write Andriy: цей сетер також мав би напевно бути тут
	// дописано 01-08 - Ярослав вніс зміни в клас до того як побачив дані зміни, і тому напевно їх можна удаляти
	/*
	public void setAnswerType(boolean trueAnswer){

		if (trueAnswer==true){
			answerType = AnswerType.CORRECT;
		}else answerType = AnswerType.WRONG;
	}
	*/


	/*public int getId() {
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
	}*/
}
