package com.quizlet.model;

/**
 * Created by Home on 09.08.2017.
 */
public class MarkType {

    public String   markQuestion;
    public  String   markAnswer;
    public String   markWriteAnswer;

    public MarkType (){
        this.markQuestion  = "q:";
        this.markAnswer  = "a:";
        this.markWriteAnswer  = "r:";
    }
}
