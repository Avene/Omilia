package com.avene.avene.omilia.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yamai on 4/29/2015.
 */

public class Section extends RealmObject {
    @PrimaryKey
    private int sectionNo;
    private String name;
    private Chapter chapter;
    private RealmList<Sentence> sentences;

    public int getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(int sectionNo) {
        this.sectionNo = sectionNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public RealmList<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(RealmList<Sentence> sentences) {
        this.sentences = sentences;
    }
}
