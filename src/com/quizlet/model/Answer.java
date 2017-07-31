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

	private AnswerType answerType;
	
	public boolean isCorrect(){
		return answerType ==  AnswerType.CORRECT;
	}
	
	public AnswerType getAnswerType(){
		return answerType;
	}
}
