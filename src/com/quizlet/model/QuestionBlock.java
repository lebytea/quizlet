package com.quizlet.model;

import java.util.List;

/**
 * 
 * @author jarc0der
 * Class describes one item of all quiz.
 *
 */

public class QuestionBlock extends Item{

	private List<Answer> answerList;
	private Answer userAnswer;
	
	public QuestionBlock(int id, String question, List<Answer> answerList, Answer userAnswer) {
		super(id, question);
		this.answerList = answerList;
	}
	
	public QuestionBlock(int id, String question, List<Answer> answerList) {
		super(id, question);
		this.answerList = answerList;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
	
	public Answer getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(Answer userAnswer) {
		this.userAnswer = userAnswer;
	}

	public Answer getCorrectAnswer(){
		Answer correctAnswer = null;
		
		for(Answer currentAnswer: answerList){
			if(currentAnswer.isCorrect())
				correctAnswer = currentAnswer;
		}
		
		return correctAnswer;
	}
	
	public boolean hasUserAnswer(){
		return userAnswer != null;
	}
	
	public boolean isUserAnswerCorrect(){
		return userAnswer.isCorrect();
	}
}
