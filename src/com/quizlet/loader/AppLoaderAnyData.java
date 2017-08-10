package com.quizlet.loader;

import com.quizlet.model.Answer;
import com.quizlet.model.AnswerType;
import com.quizlet.model.MarkType;
import com.quizlet.model.QuestionBlock;

import java.util.ArrayList;
import java.util.List;

import static com.quizlet.model.AnswerType.CORRECT;
import static com.quizlet.model.AnswerType.WRONG;

public class AppLoaderAnyData extends AppLoaderResource {

    MarkType markType = new MarkType();

    @Override
    public List<QuestionBlock> loadQuizItems(String pathSource) {
        List<QuestionBlock> resultQuestionBlock = new ArrayList<>();

        String stringTextQuiz = new ReadFileQuizTxt().getStringTextQuiz(pathSource); //read text-data of quiz - from txt.file
        int positionChar = 0;
        String markOfTypeLine = "";
        String textQuestion = "";
        int idBlock = 0;

        while (true) {
            //find x-Item_question
            markOfTypeLine = "" + stringTextQuiz.charAt(positionChar) + stringTextQuiz.charAt(positionChar + 1);
            if (markOfTypeLine.equals(markType.markQuestion)) {
                textQuestion = readAllTextOfTheMark(positionChar, stringTextQuiz);
                positionChar += textQuestion.length();
            }

            List<Object> result = doListAnswer(positionChar, stringTextQuiz);
            positionChar = (int) result.get(1);

            List<Answer> answerList = (List<Answer>) result.get(0); //doListAnswer ,find x(i)-Item_Answer
            QuestionBlock questionBlock = new QuestionBlock(idBlock++, textQuestion, answerList); //create block
            resultQuestionBlock.add(questionBlock);//add block(s)

            if (++positionChar >= stringTextQuiz.length() - 2) {
                break;
            }
        }
        return resultQuestionBlock;
    }

    private List<Object> doListAnswer(int positionChar, String stringTextQuiz) {
        List<Object> result = new ArrayList<>();
        List<Answer> answerList = new ArrayList<>();

        Answer answer;
        int id = 0;
        String stringAnswer;
        AnswerType answerType;

        String equalMarkType;

        while (true) {

            equalMarkType = "" + stringTextQuiz.charAt(positionChar) + stringTextQuiz.charAt(positionChar + 1);

            if (equalMarkType.equals(markType.markWriteAnswer) || equalMarkType.equals(markType.markAnswer)) {
                stringAnswer = readAllTextOfTheMark(positionChar, stringTextQuiz);
                positionChar = positionChar + stringAnswer.length();
                if (equalMarkType.equals(markType.markWriteAnswer)) {
                    answerType = CORRECT;
                } else {
                    answerType = WRONG;
                }
                answer = new Answer(id++, stringAnswer, answerType);
                answerList.add(answer);
            }

            if (equalMarkType.equals(markType.markQuestion) || positionChar == stringTextQuiz.length() - 2) {
                break;
            }
            ++positionChar;
        }

        result.add(answerList);
        result.add(positionChar - 1);
        return result;
    }

    private String readAllTextOfTheMark(int positionChar, String stringTextQuiz) {

        String resultAllTextType = "";
        String equalMarkType;

        for (positionChar += 3; positionChar < stringTextQuiz.length() - 1; ++positionChar) {
            equalMarkType = "" + stringTextQuiz.charAt(positionChar) + stringTextQuiz.charAt(positionChar + 1);
            if (equalMarkType.equals(markType.markQuestion) || equalMarkType.equals(markType.markAnswer) || equalMarkType.equals(markType.markWriteAnswer)) {
                break;
            }
            resultAllTextType += stringTextQuiz.charAt(positionChar - 1);
        }
        return resultAllTextType;
    }

}
