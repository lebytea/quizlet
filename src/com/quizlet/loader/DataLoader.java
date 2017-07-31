package com.quizlet.loader;

import java.util.List;

import com.quizlet.model.QuestionBlock;

public interface DataLoader {

	/*
	 * Method returns parsed list of QuizItems
	 * @see QuizItem.java
	 */
	public List<QuestionBlock> loadQuizItems();
}
