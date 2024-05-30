package com.example.btlmobile.model;

public class Streak {
    private int id = 1;
    private int learnWord,selectAnswer, sortWord;

    public Streak() {
        this.id = 1;
    }

    public Streak(int learnWord, int selectAnswer, int sortWord) {
        this.learnWord = learnWord;
        this.selectAnswer = selectAnswer;
        this.sortWord = sortWord;
    }

    public int getId() {
        return id;
    }

    public int getLearnWord() {
        return learnWord;
    }
    public void setLearnWord(int learnWord) {
        this.learnWord = learnWord;
    }
    public int getSelectAnswer() {
        return selectAnswer;
    }
    public void setSelectAnswer(int selectAnswer) {
        this.selectAnswer = selectAnswer;
    }
    public int getSortWord() {
        return sortWord;
    }
    public void setSortWord(int sortWord) {
        this.sortWord = sortWord;
    }

}
