package com.quizlet.model;

public class Answer {

	private AnswerType answerType;
	
	public boolean isCorrect(){
		return answerType ==  AnswerType.CORRECT;
	}
	
	public AnswerType getAnswerType(){
		return answerType;
	}
}
