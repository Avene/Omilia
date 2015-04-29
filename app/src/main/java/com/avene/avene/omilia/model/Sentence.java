package com.avene.avene.omilia.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yamai on 4/29/2015.
 */
public class Sentence extends RealmObject {
    @PrimaryKey
    private int sentenceNo;
    private String jp;
    private String en;
    private Section section;

    public Sentence() {
    }

    public Sentence(int sentenceNo) {
        this.sentenceNo = sentenceNo;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public int getSentenceNo() {
        return sentenceNo;
    }

    public void setSentenceNo(int sentenceNo) {
        this.sentenceNo = sentenceNo;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
