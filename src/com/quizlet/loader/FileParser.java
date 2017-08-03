package com.quizlet.loader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.quizlet.model.Answer;
import com.quizlet.model.AnswerType;
import com.quizlet.model.QuestionBlock;

public class FileParser implements Parser{
	
	public List<QuestionBlock> parseData(String resource){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(resource)));
		} catch (FileNotFoundException e) {
			// TODO integrate log4j
			System.out.println("Error while reading file " + resource);
		}
		
		String currentLine = null;
		int questionId = 0;
		
		List<QuestionBlock> questionBlockList = new ArrayList<>();
		
		try{
			while((currentLine = reader.readLine()) != null){
				if(isQuestionBlockStart(currentLine)){
					System.out.println("OUTER: " + currentLine);
					String lineWithoutPreffix = clearPreffix(currentLine);
					QuestionBlock questionBlock = createQuestionBlock(++questionId, lineWithoutPreffix, reader);
					questionBlockList.add(questionBlock);
				}
			}
		}catch(IOException e){
			System.out.println("FileParser exception: " + e.getMessage());
		}
		
		return questionBlockList;
	}
	
	private QuestionBlock createQuestionBlock(int questionId, String questionName, BufferedReader reader) throws IOException{
		
		String currentLine = null;
		int answerId = 0;
		
		List<Answer> answerList = new ArrayList<>();
		
		while(!(currentLine = reader.readLine()).isEmpty()){
			System.out.println("INNER:" + currentLine);
			
			String cleanAnswer = clearPreffix(currentLine);
			
			Answer answer = new Answer(++answerId, cleanAnswer);
			
			if(isCorrectAnswer(currentLine)){
				answer = new Answer(++answerId, cleanAnswer, AnswerType.CORRECT);
			}
			
			answerList.add(answer);
		}
		
		QuestionBlock question = new QuestionBlock(questionId,questionName, answerList);

		return question;
	}
	
	private boolean isQuestionBlockStart(String line){
		return line.startsWith("q:");
	}
	
	private String clearPreffix(String line){
		String strWitoutPreffix = "";
		
		if(line.startsWith("q:") | line.startsWith("a:") | line.startsWith("r:"))
			strWitoutPreffix = line.substring(2);
		else
			return line;
		
		return strWitoutPreffix;
	}
	
	private boolean isCorrectAnswer(String line){
		return line.startsWith("r:");
	}
}
