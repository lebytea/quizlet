package quizlet;

import org.junit.Test;

import com.quizlet.model.Answer;
import com.quizlet.model.AnswerType;
import com.quizlet.model.QuestionItem;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class MainFunctionProcessorTest {

	public List<QuestionItem> questionItems = Arrays.asList(
			new QuestionItem(1, "What is u name?", Arrays.asList(
					new Answer(1, "Yarik", AnswerType.CORRECT))));
	
	
	@Test
	public void testQuestionListCreate(){
		
	}
	
}
