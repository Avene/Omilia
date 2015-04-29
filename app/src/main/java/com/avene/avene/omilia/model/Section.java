package com.avene.avene.omilia.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by yamai on 4/29/2015.
 */

@Table(name = "Sections")
public class Section extends Model {
    @Column(name = "Name")
    public String name;

    @Column(name = "Chapter", index = true)
    public Chapter chapter;

    public Section(){
        super();
    }

    public Section(String name) {
        super();
        this.name = name;
    }

    public List<Sentence> sentences(){
        return getMany(Sentence.class, "Section");
    }

}
