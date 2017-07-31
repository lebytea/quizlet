package com.quizlet.loader;
import com.quizlet.model.Answer;
import com.quizlet.model.QuestionBlock;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 30.07.2017.
 */
public class AppLoader implements DataLoader {

   @Override
   public List<QuestionBlock> loadQuizItems(String pathSource) {

       String filePath = pathSource;
        /*
        Файл з блоками питаннь має мати чітку символьну структуру
        q:Текст запитання.
        a:відповідь.
        a:відповідь.
        r:правильна відповідь
        a:відповідь.

        Примітка_1:(правильна відповідь з міткою "r:" не має значення на якій саме позиції вона знаходиться,
            хоча для спрощення стоворення алгоритму парсера, можна було її поставити пунктом 1.:(
        Примітка_2: Відділення блоків питань між собою через ентер. Між блоками одна пуста лінія, яка створюється ентером і не повинна містити ніяких символів.

         */

       //create burrefedread from file
       StringBuffer myStringBuff = new StringBuffer();
       String stringQuiz = "";

       try (FileInputStream myFile = new FileInputStream(filePath);
            BufferedInputStream myBuff = new BufferedInputStream(myFile);) {

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
                   Answer answerListTempX = new Answer();
                   answerListTempX.setId(IdAnswer);
                   answerListTempX.setText(textInfoLine);
                   answerListTempX.setAnswerType(prewTrueAnswer);
                   answerList.add(answerListTempX);
               }

               //формуємо об"єкт "Блок" для активного блоку "питання"
               if (flagWhatIsLine.equals("q:")) {
                   tempQuestionBlock = new QuestionBlock();
                   tempQuestionBlock.setId(IdBlock);
                   tempQuestionBlock.setText(textPrewQuestion);
                   tempQuestionBlock.setAnswerList(answerList);
                   resultQuestionBlock.add(tempQuestionBlock);

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














