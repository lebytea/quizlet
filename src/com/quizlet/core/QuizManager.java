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
	
	public List<QuestionItem> getQuizItemList();
	
	public QuestionItem getQuizItemById(int id);
	
	public boolean setUserAnswerForQuizItem(int quizItemId, int answerId);
	
}
