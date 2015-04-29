package com.avene.avene.omilia.model;


import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yamai on 4/29/2015.
 */
public class Chapter extends RealmObject {
    @PrimaryKey
    private int ChapterNo;
    private String name;
    private RealmList<Section> sections;

    public int getChapterNo() {
        return ChapterNo;
    }

    public void setChapterNo(int chapterNo) {
        ChapterNo = chapterNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Section> getSections() {
        return sections;
    }

    public void setSections(RealmList<Section> sections) {
        this.sections = sections;
    }
}
