package storage.vocabulary.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Streak {
    @Id
    private int id;
    private int learnWord,selectAnswer, sortWord;
    
    public Streak() {
    }
    
    public Streak(int id, int learnWord, int selectAnswer, int sortWord) {
        this.id = id;
        this.learnWord = learnWord;
        this.selectAnswer = selectAnswer;
        this.sortWord = sortWord;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
