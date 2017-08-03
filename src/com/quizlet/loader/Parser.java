package com.quizlet.loader;

import java.util.List;

import com.quizlet.model.QuestionBlock;

/**
 * 
 * @author jarc0der
 *
 */
public interface Parser {

	List<QuestionBlock> parseData(String resource);
	
}
