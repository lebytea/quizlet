package com.quizlet.loader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class ReadFileQuizTxt extends ReadFileQuizAny{

    //reader for Txt-file. For other it must be check\test

    @Override
    public String getStringTextQuiz(String pathSource) {

        String filePath = pathSource;

        String resultStringTextQuiz = "";
        StringBuffer myStringBuff = new StringBuffer();

        try (FileInputStream myFile = new FileInputStream(filePath);
             BufferedInputStream myBuff = new BufferedInputStream(myFile);) {


            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            reader.readLine();

            int ch;
            while ((ch = myBuff.read()) > -1) {
                myStringBuff.append((char) ch);
            }
            resultStringTextQuiz = myStringBuff.toString();

        } catch (IOException e) {
            System.out.println("There some problem with read from file: " + filePath + " \n " + "Attension - IOException >> " + e);

        }
        return resultStringTextQuiz;
    }
}
