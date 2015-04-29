package com.avene.avene.omilia.sentence;

import com.activeandroid.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class Sentences {

    /**
     * An array of sentence items.
     */
    public static List<Sentence> ITEMS = new ArrayList<Sentence>();

    /**
     * A map of sentence items, by ID.
     */
    public static Map<String, Sentence> ITEM_MAP = new HashMap<String, Sentence>();

    static {
        // Add 3 sample items.
        addItem(new Sentence("1", "Sentence 1"));
        addItem(new Sentence("2", "Sentence 2"));
        addItem(new Sentence("3", "Sentence 3"));
    }

    private static void addItem(Sentence sentence) {
        ITEMS.add(sentence);
        ITEM_MAP.put(sentence.id, sentence);
    }

    /**
     * A object representing a piece of sentence
     */
    public static class Sentence extends Model {
        public String id;
        public String content;

        public Sentence(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
