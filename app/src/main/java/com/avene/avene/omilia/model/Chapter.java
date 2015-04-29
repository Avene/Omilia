package com.avene.avene.omilia.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by yamai on 4/29/2015.
 */
@Table(name = "Chapters")
public class Chapter extends Model {
    @Column(name = "Name")
    public String name;

    public Chapter() {
        super();
    }

    public Chapter(String name) {
        super();
        this.name = name;
    }

    public List<Section> sections() {
        return getMany(Section.class, "Chapter");
    }
}
