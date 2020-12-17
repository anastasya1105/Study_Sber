package study.lesson2;

import java.util.Comparator;

public class Word implements Comparable<Word> {
    public String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getWord().equals(((Word)obj).getWord());
    }
    @Override
    public int compareTo(Word w) {
        return this.word.length() - w.getWord().length();
    }
    @Override
    public String toString() {
        return word;
    }
}
