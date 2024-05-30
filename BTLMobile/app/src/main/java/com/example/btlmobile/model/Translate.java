package com.example.btlmobile.model;

public class Translate {
    private int id;
    private String fromLang,toLang,text,translatedText;

    public Translate() {
    }

    public Translate(int id, String fromLang, String toLang, String text, String translatedText) {
        this.id = id;
        this.fromLang = fromLang;
        this.toLang = toLang;
        this.text = text;
        this.translatedText = translatedText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getFromLang() {
        return fromLang;
    }


    public void setFromLang(String fromLang) {
        this.fromLang = fromLang;
    }


    public String getToLang() {
        return toLang;
    }


    public void setToLang(String toLang) {
        this.toLang = toLang;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Translate{" +
                "fromLang='" + fromLang + '\'' +
                ", toLang='" + toLang + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
