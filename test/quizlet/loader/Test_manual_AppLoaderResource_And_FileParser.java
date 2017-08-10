package loader;

import com.quizlet.loader.AppLoaderAnyData;
import com.quizlet.loader.FileParser;
import com.quizlet.model.Answer;
import com.quizlet.model.QuestionBlock;
import java.util.Iterator;
import java.util.List;

public class Test_manual_AppLoaderResource_And_FileParser {
    public String pathSource="";

    public static void main(String[] args) {
        System.out.println("|| ===== || ===== || ===== || =====");
        String pathSource = ".\\test\\quizlet\\loader\\test_parser_quiz_1";//мій приклад
        jTest_AppLoaderAnyData(pathSource);//Test clas:AppLoaderAnyData
        jTest_FileParser(pathSource);//Test clas:FileParser
        System.out.println("|| ===== || ===== || ===== || =====");

    }
    static void jTest_FileParser( String pathSource){
        FileParser myInst = new FileParser();

        AppLoaderAnyData myTest = new AppLoaderAnyData();

        List<QuestionBlock> resultQuestionBlock =  myInst.parseData(pathSource);
        toPrintScreen (resultQuestionBlock);
    }



    static void jTest_AppLoaderAnyData( String pathSource){
        AppLoaderAnyData myTest = new AppLoaderAnyData();

        List<QuestionBlock> resultQuestionBlock =  myTest.loadQuizItems(pathSource);
        toPrintScreen (resultQuestionBlock);


    }

    static void toPrintScreen(List<QuestionBlock> resultQuestionBlock){
        Iterator iterator = resultQuestionBlock.iterator();

        String result = "";

        while (iterator.hasNext())
        {
            //получение текущего элемента и переход на следующий
            QuestionBlock  myQuestionBlock = (QuestionBlock) iterator.next();

            System.out.println("Block_Id: " + myQuestionBlock.getId());
            System.out.println("Question: " +myQuestionBlock.getText());
            System.out.println("CorrectAnswer: " +  myQuestionBlock.getCorrectAnswer());
            System.out.println("All Answers ============== " );

            //result += myQuestionBlock.getId()+myQuestionBlock.getText()+myQuestionBlock.getText()+myQuestionBlock.getCorrectAnswer();

            List<Answer>  myBlockAnswer =  myQuestionBlock.getAnswerList();
            Iterator iteratorAnswer = myBlockAnswer.iterator();
            while (iteratorAnswer.hasNext()){
                Answer myTempAnswer = (Answer) iteratorAnswer.next();
                System.out.println("Id = " + myTempAnswer.getId() + " AnswerType =  " + myTempAnswer.getAnswerType() + " myTempAnswer.isCorrect() = " + myTempAnswer.isCorrect() + " Внимание вопрос:  " + myTempAnswer.getText());


            }

        }
    }
}
