package org.iliat.gmat.enitity;

/**
 * Created by qhuydtvt on 3/14/2016.
 */
public class QuestionPack {

    private String available_time;
    private String[] question_ids;

    public String getAvailable_time() {
        return available_time;
    }

    public void setAvailable_time(String available_time) {
        this.available_time = available_time;
    }

    public String[] getQuestion_ids() {
        return question_ids;
    }

    public void setQuestion_ids(String[] question_ids) {
        this.question_ids = question_ids;
    }
}
