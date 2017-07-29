package com.quizlet.core;

import java.util.Map;

/**
 * 
 * @author jarc0der
 *	QuizProcessor should process user's answers and return statistic information
 */


public interface Processor {

	public boolean procesQuiz();
	
	public Map<String, String> getStatisticInformation();
}
