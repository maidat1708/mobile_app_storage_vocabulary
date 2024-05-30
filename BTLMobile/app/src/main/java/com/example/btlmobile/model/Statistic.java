package com.example.btlmobile.model;

public class Statistic {
    private String word_type;
    private int total_count;

    public Statistic(){

    }

    public Statistic(String word_type, int total_count) {
        this.word_type = word_type;
        this.total_count = total_count;
    }

    public String getWord_type() {
        return word_type;
    }

    public void setWord_type(String word_type) {
        this.word_type = word_type;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "word_type='" + word_type + '\'' +
                ", total_count=" + total_count +
                '}';
    }
}
