package com.example.btlmobile.model;

import java.io.Serializable;

public class Vocabulary implements Serializable{
    private int id;
    private String word,meaning,type,accent,image,sentence;

    public Vocabulary() {
    }


    public Vocabulary(int id, String word, String meaning, String type, String accent, String image, String sentence) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.type = type;
        this.accent = accent;
        this.image = image;
        this.sentence = sentence;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccent() {
        return accent;
    }

    public void setAccent(String accent) {
        this.accent = accent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                ", type='" + type + '\'' +
                ", accent='" + accent + '\'' +
                ", sentence='" + sentence + '\'' +
                '}';
    }

    public boolean equals(Vocabulary o) {
        if (o == this) {
            return true;
        }
        Vocabulary c = (Vocabulary) o;

        return this.word.equalsIgnoreCase(o.getWord()) && this.meaning.equalsIgnoreCase(o.getMeaning()) && this.type.equalsIgnoreCase(o.getType());
    }
}
