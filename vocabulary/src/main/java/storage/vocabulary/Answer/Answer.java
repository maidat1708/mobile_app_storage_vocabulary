package storage.vocabulary.Answer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Answer {
    @Id
    private int id;
    private String question,ans1,ans2,ans3,ans4;
    private int idListen;


    public Answer() {
    }

    public Answer(int id, String question, String ans1, String ans2, String ans3, String ans4, int idListen) {
        this.id = id;
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.idListen = idListen;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAns1() {
        return ans1;
    }
    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }
    public String getAns2() {
        return ans2;
    }
    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }
    public String getAns3() {
        return ans3;
    }
    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }
    public String getAns4() {
        return ans4;
    }
    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }



    public int getIdListen() {
        return idListen;
    }



    public void setIdListen(int idListen) {
        this.idListen = idListen;
    }

    
}
