package com.avene.avene.omilia.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by yamai on 4/29/2015.
 */
@Table(name = "Sentences")
public class Sentence extends Model{
    public Sentence(){
        super();
    }

    public Sentence(String jp, String en) {
        super();
        this.jp = jp;
        this.en = en;
    }

    @Column(name = "Jp")
    public String jp;

    @Column(name = "En")
    public String en;

    @Column(name = "Section", index = true)
    public Section section;

}
