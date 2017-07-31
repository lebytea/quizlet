package com.quizlet.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.quizlet.loader.DataLoader;
import com.quizlet.model.Answer;
import com.quizlet.model.QuestionBlock;

public class ProcessorImpl implements Processor{

	private DataLoader quizLoader;
	private List<QuestionBlock> questionBlockList;
	
	public ProcessorImpl(List<QuestionBlock> list){
		System.out.println("Processor init...");
//		questionBlockList = quizLoader.loadQuizItems();
		questionBlockList = list;
	}
	
	@Override
	public List<QuestionBlock> getAllQuestionBlocks() {
		return questionBlockList;
	}

	@Override
	public QuestionBlock getQuestionItemById(int id) {
		QuestionBlock questionBlock = null;
		
		for(QuestionBlock currentQuestionItem : questionBlockList){
			if(currentQuestionItem.getId() == id)
				questionBlock = currentQuestionItem;
		}
		
		return questionBlock;
	}

	@Override
	public boolean setUsersAnswerToQuestion(int questionId, Answer answer) {
		QuestionBlock questionBlock = getQuestionItemById(questionId);
		
		return 	questionBlock.setUserAnswer(answer);
	}

	@Override
	public List<QuestionBlock> getQuestionListWithoutAnswer() {
		return questionBlockList.stream().filter(q -> !q.hasAnswer()).collect(Collectors.toList());
	}

	@Override
	public boolean procesQuiz() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, String> getStatisticInformation() {
		Map<String, String> statistic = new HashMap<>();
		
		statistic.put("Correct", countCorrectAnswers() + "");
		
		return statistic;
	}
	
	public int countCorrectAnswers(){
		int correctAnswerCount = (int)questionBlockList.stream().filter(q -> q.isUserAnswerCorrect()).count();
		
		return correctAnswerCount;
	}


}
