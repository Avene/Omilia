package com.avene.avene.omilia;

/**
 * Created by yamai on 4/29/2015.
 */
public class Sentence {
    public Sentence(String jp, String en) {
        this.jp = jp;
        this.en = en;
    }

    private String jp;
    private String en;

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
