package com.quizlet.core;

import java.util.List;

import com.quizlet.model.QuestionItem;

/**
 * 
 * @author jarc0der
 * QuizManager controls all quiz process, such as start, finish quiz
 *	
 */
public interface QuizManager {

	public boolean beginQuiz();
	
	public boolean finishQuiz();
	
	public List<QuestionItem> getQuestionList();
	
	public QuestionItem selectQuestionById(int questionId);
	
	public boolean enterAnswer(int answerId);
}
