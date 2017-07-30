package com.quizlet.core;

import java.util.List;
import java.util.Map;

import com.quizlet.model.Answer;
import com.quizlet.model.QuestionItem;

/**
 * 
 * @author jarc0der
 *	QuizProcesso process all quiz that's why he should know how to
 *	
 */

public interface Processor {

	/*
	 * Method returns list of QuestionBlock	
	 */
	public List<QuestionItem> getAllQuestionBlocks();
	
	/*
	 * Returns Question entity by ID
	 */
	public QuestionItem getQuestionItemById(int id);
	
	/*
	 * Set user answer to QuestionBlock
	 */
	public boolean setUsersAnswerToQuestion(int questionId, Answer answer);
	
	/*
	 * Returns list of QuestionBlock without answers
	 */
	public List<QuestionItem> getQuestionListWithoutAnswer();
	
	/*
	 * Process finished quiz and gather statistic information about it
	 */
	public boolean procesQuiz();
	
	public Map<String, String> getStatisticInformation();
}
