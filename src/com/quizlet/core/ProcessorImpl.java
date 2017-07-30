package com.quizlet.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.quizlet.loader.DataLoader;
import com.quizlet.model.Answer;
import com.quizlet.model.QuestionItem;

public class ProcessorImpl implements Processor{

	private DataLoader quizLoader;
	private List<QuestionItem> questionBlockList;
	
	public ProcessorImpl(List<QuestionItem> list){
		System.out.println("Processor init...");
//		questionBlockList = quizLoader.loadQuizItems();
		questionBlockList = list;
	}
	
	@Override
	public List<QuestionItem> getAllQuestionBlocks() {
		return questionBlockList;
	}

	@Override
	public QuestionItem getQuestionItemById(int id) {
		QuestionItem questionBlock = null;
		
		for(QuestionItem currentQuestionItem : questionBlockList){
			if(currentQuestionItem.getId() == id)
				questionBlock = currentQuestionItem;
		}
		
		return questionBlock;
	}

	@Override
	public boolean setUsersAnswerToQuestion(int questionId, Answer answer) {
		QuestionItem questionBlock = getQuestionItemById(questionId);
		
		return 	questionBlock.setUserAnswer(answer);
	}

	@Override
	public List<QuestionItem> getQuestionListWithoutAnswer() {
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
