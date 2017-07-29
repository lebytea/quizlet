package com.quizlet.model;

import java.util.List;

/**
 * 
 * @author jarc0der
 * Class describes one item of all quiz.
 *
 */

public class QuestionItem extends Item{
	
	private List<Answer> answerList;

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
	
	public Answer getCorrectAnswer(){
		Answer correctAnswer = null;
		
		for(Answer currentAnswer: answerList){
			if(currentAnswer.isCorrect())
				correctAnswer = currentAnswer;
		}
		
		return correctAnswer;
	}
}
