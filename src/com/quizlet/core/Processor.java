package com.quizlet.core;

import java.util.List;
import java.util.Map;

import com.quizlet.model.Answer;
import com.quizlet.model.QuestionBlock;

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
	public List<QuestionBlock> getAllQuestionBlocks();
	
	/*
	 * Returns Question entity by ID
	 */
	public QuestionBlock getQuestionItemById(int id);
	
	/*
	 * Set user answer to QuestionBlock
	 */
	public boolean setUsersAnswerToQuestion(int questionId, Answer answer);
	
	/*
	 * Returns list of QuestionBlock without answers
	 */
	public List<QuestionBlock> getQuestionListWithoutAnswer();
	
	/*
	 * Process finished quiz and gather statistic information about it
	 */
	public boolean procesQuiz();
	
	public Map<String, String> getStatisticInformation();
}
