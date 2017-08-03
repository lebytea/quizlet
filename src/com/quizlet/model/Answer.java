package com.quizlet.model;

public class Answer extends Item{
	
	private AnswerType answerType;

	public Answer(int id, String answer, AnswerType answerType) {
		super(id, answer);
		this.answerType = answerType;
	}
	
	public Answer(int id, String answer){
		super(id, answer);
		this.answerType = AnswerType.WRONG;
	}

	public boolean isCorrect(){
		return (answerType ==  AnswerType.CORRECT);
	}
	
	public AnswerType getAnswerType(){
		return answerType;
	}
}
