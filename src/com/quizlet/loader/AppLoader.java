package com.quizlet.loader;
import com.quizlet.model.Answer;
import com.quizlet.model.AnswerType;
import com.quizlet.model.QuestionBlock;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 30.07.2017.
 */
public class AppLoader implements DataLoader {

	//add parser Interface

    //type in branch only_forlocal_test and safe
    //=============== in development
// ==second in only_forlocal_test 
	
   @Override
   public List<QuestionBlock> loadQuizItems(String pathSource) {

       String filePath = pathSource;

       //create burrefedread from file
       //StringBuilder is better 
       StringBuffer myStringBuff = new StringBuffer();
       String stringQuiz = "";

       try (FileInputStream myFile = new FileInputStream(filePath);
            BufferedInputStream myBuff = new BufferedInputStream(myFile);) {

    	   
    	   BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
    	   reader.readLine();
    	   
    	   
           int ch;
           while ((ch = myBuff.read()) > -1) {
               myStringBuff.append((char) ch);
           }
           stringQuiz = myStringBuff.toString();

       } catch (IOException e) {
           System.out.println("There some problem with read from file: " + filePath + " \n " + "Attension - IOException >> " + e);

       }
       //method to parse data
       //====================
       List<QuestionBlock> resultQuestionBlock = new ArrayList<>(); //results Block[]
       List<Answer> answerList = null;
       QuestionBlock tempQuestionBlock = null;

       int IdBlock = -1;
       int IdAnswer = -1;

       //for control first and end bits of a piece from data-string
       int numberInfoLineStart = 0;
       int numberInfoLineFinish = 0;
       String textInfoLine = "";
       String textPrewQuestion = "";

       String flagWhatIsLine = ""; // q: , a: , r:
       String flagWhatIsPrewLine = "";// q: , a: , r:
       int corectorFinish = 0;//for correct read finish chars in string_data_file

       boolean trueAnswer = false;
       boolean prewTrueAnswer = false;

       //start from 1 - ok
       for (int charNumberNext = 1; charNumberNext < stringQuiz.length(); ++charNumberNext) {

           if (stringQuiz.charAt(charNumberNext - 1) == 'q' && stringQuiz.charAt(charNumberNext) == ':') {

               ++IdBlock;

               if (IdBlock == 0) {

                   numberInfoLineStart = charNumberNext + 1;
                   flagWhatIsLine = "";
                   flagWhatIsPrewLine = "q:";
                   answerList = new ArrayList<>();
               } else {
                   flagWhatIsLine = "q:";
               }

           }

           if (stringQuiz.charAt(charNumberNext - 1) == 'a' && stringQuiz.charAt(charNumberNext) == ':') {

               flagWhatIsLine = "a:";
               trueAnswer = false;
           }
           if (stringQuiz.charAt(charNumberNext - 1) == 'r' && stringQuiz.charAt(charNumberNext) == ':') {

               flagWhatIsLine = "r:";
               trueAnswer = true;
           }
           //генерируем начало условного нового блока вопроса, что-бы алгоритм понимал, что должен закрыть последний блок вопроса
           if (charNumberNext == stringQuiz.length() - 1) {
               ++IdBlock;
               flagWhatIsLine = "q:";
               corectorFinish = 4;
           }

           if (!flagWhatIsLine.equals("")) {//при першому знаходженні мітки\вказівника - в цей блок не заходимо

               numberInfoLineFinish = charNumberNext - 3 + corectorFinish;

               //забираємо ентер (пусту лінію) між блоками
               int bitLine = 0;
               if (flagWhatIsLine.equals("q:")) {
                   bitLine = 2;
               }

               //Формуємо дані - строку питання чи відповіді.
               for (int tempTextLine = numberInfoLineStart; tempTextLine < numberInfoLineFinish - bitLine; ++tempTextLine) {
                   textInfoLine += stringQuiz.charAt(tempTextLine);
               }

               //запамятовуємо текст питання активного блока
               if (flagWhatIsPrewLine.equals("q:")) {
                   textPrewQuestion = textInfoLine;
               }

               //формуємо об"єкт "відповідь" для активного блоку "питання"
               if (!flagWhatIsPrewLine.equals("q:")) {
                   ++IdAnswer;
                   AnswerType prewTrueAnswerAdd ;
                   if (trueAnswer==true){
                       prewTrueAnswerAdd = AnswerType.CORRECT;
                   }else prewTrueAnswerAdd = AnswerType.WRONG;
                   Answer answerListTempX = new Answer(IdAnswer,textInfoLine, prewTrueAnswerAdd );

                   //Закрив, бо раніше клас працював через сеттери
                   /*answerListTempX.setId(IdAnswer);
                   answerListTempX.setText(textInfoLine);
                   answerListTempX.setAnswerType(prewTrueAnswer);*/
                   answerList.add(answerListTempX);
                   System.out.println();
               }

               //формуємо об"єкт "Блок" для активного блоку "питання"
               if (flagWhatIsLine.equals("q:")) {
                   tempQuestionBlock = new QuestionBlock(IdBlock, textPrewQuestion, answerList);

                   //створюємо коллекцію для наступного блоку відповідей
                   answerList = new ArrayList<>();
                   IdAnswer = -1;
               }

               //Встановлюэмо стартовий стан
               numberInfoLineStart = charNumberNext + 1;
               flagWhatIsPrewLine = flagWhatIsLine;
               flagWhatIsLine = "";
               textInfoLine = "";

               prewTrueAnswer = trueAnswer;//запамятовуємо флаг вірності активної відповіді
               trueAnswer = false;
           }
       }
       return resultQuestionBlock;
   }
}














