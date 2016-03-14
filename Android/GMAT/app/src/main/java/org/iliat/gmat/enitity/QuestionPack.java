package org.iliat.gmat.enitity;

import java.util.List;

/**
 * Created by qhuydtvt on 3/14/2016.
 */
public class QuestionPack {

    private String available_time;
    private List<String> question_ids;

    public String getAvailable_time() {
        return available_time;
    }

    public void setAvailable_time(String available_time) {
        this.available_time = available_time;
    }

    public List<String> getQuestion_ids() {
        return question_ids;
    }

    public String getFirstQuestionId() {
        if(question_ids.size() > 0 ) return question_ids.get(0);
        return null;
    }

    public String getNextQuestionId(String id) {
        int currentIdx = question_ids.indexOf(id);
        if(currentIdx >= 0) {
            int nextIdx = currentIdx + 1;
            if (nextIdx < question_ids.size()) {
                return question_ids.get(nextIdx);
            }
        }
        return null;
    }

    public void setQuestion_ids(List<String> question_ids) {
        this.question_ids = question_ids;
    }
}
