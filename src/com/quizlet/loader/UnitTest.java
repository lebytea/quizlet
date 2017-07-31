package com.quizlet.loader;

import com.quizlet.model.Answer;
import com.quizlet.model.QuestionBlock;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Home on 31.07.2017.
 */
public class UnitTest {
    public static void main(String[] args) {
        unitTest();
    }

    static void unitTest(){
        AppLoader myTest = new AppLoader();
        String pathSource = "C:\\quiz_1.txt";//мій приклад
        List<QuestionBlock> resultQuestionBlock =  myTest.loadQuizItems(pathSource);

        Iterator iterator = resultQuestionBlock.iterator();

        while (iterator.hasNext())
        {
            //получение текущего элемента и переход на следующий
            QuestionBlock  myQuestionBlock = (QuestionBlock) iterator.next();

            System.out.println("Block_Id: " + myQuestionBlock.getId());
            System.out.println("Question: " +myQuestionBlock.getText());
            System.out.println("CorrectAnswer: " +  myQuestionBlock.getCorrectAnswer());
            System.out.println("All Answers ============== " );

            List<Answer>  myBlockAnswer =  myQuestionBlock.getAnswerList();
            Iterator iteratorAnswer = myBlockAnswer.iterator();
            while (iteratorAnswer.hasNext()){
                Answer myTempAnswer = (Answer) iteratorAnswer.next();
                System.out.println("Id = " + myTempAnswer.getId() + " AnswerType =  " + myTempAnswer.getAnswerType() + " myTempAnswer.isCorrect() = " + myTempAnswer.isCorrect() + " Внимание вопрос:  " + myTempAnswer.getText());

            }

        }
    }
}
