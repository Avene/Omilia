package com.avene.avene.omilia.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yamai on 4/29/2015.
 */
public class Quiz extends RealmObject {
    @PrimaryKey
    private int quizNo;
    private String question;
    private String answer;
    private Section section;

    public Quiz() {
    }

    public Quiz(int quizNo) {
        this.quizNo = quizNo;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuizNo() {
        return quizNo;
    }

    public void setQuizNo(int quizNo) {
        this.quizNo = quizNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
