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

    public QuestionCRModel getNextQuestion(QuestionCRModel questionCRModel) {
        int currentIdx = question_ids.indexOf(questionCRModel.getOid());
        if(currentIdx >= 0) {
            int nextIdx = currentIdx + 1;
            if (nextIdx < question_ids.size()) {
                return Questions.getQuestion(question_ids.get(nextIdx));
            }
        }
        return null;
    }

    public boolean isLastQuestion(QuestionCRModel qustQuestionCRModel) {
        String oid = qustQuestionCRModel.getOid();
        int idx = question_ids.indexOf(oid);
        return (idx == question_ids.size() - 1);
    }

    public void setQuestion_ids(List<String> question_ids) {
        this.question_ids = question_ids;
    }
}
