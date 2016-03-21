package org.iliat.gmat.enitity;

/**
 * Created by qhuydtvt on 3/19/2016.
 */
public class UserChoice {

    private String questionid;
    private int choice = -1;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}
