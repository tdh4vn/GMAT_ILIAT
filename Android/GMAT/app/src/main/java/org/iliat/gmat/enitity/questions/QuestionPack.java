package org.iliat.gmat.enitity.questions;

import com.google.gson.annotations.SerializedName;

import org.iliat.gmat.enitity.ObjectID;

import java.util.List;

/**
 * Created by qhuydtvt on 3/14/2016.
 */
public class QuestionPack {

    private static final String ID = "_id";
    private static final String AVAILABLE_TIME = "available_time";
    private static final String QUESTION_IDS = "question_ids";

    @SerializedName(ID)
    private ObjectID id;

    @SerializedName(AVAILABLE_TIME)
    private String availableTime;

    @SerializedName(QUESTION_IDS)
    private List<String> questionIds;

    public String getAvailableTime() {
        return availableTime;
    }

    public String getId() {
        return id.getOID();
    }

    public int getQuestionCount() {
        return this.questionIds.size();
    }

    public List<String> getQuestionIds() {
        return questionIds;
    }

    public String getFirstQuestionId() {
        if(questionIds.size() > 0 ) return questionIds.get(0);
        return null;
    }

    public String getNextQuestionId(String id) {
        int currentIdx = questionIds.indexOf(id);
        if(currentIdx >= 0) {
            int nextIdx = currentIdx + 1;
            if (nextIdx < questionIds.size()) {
                return questionIds.get(nextIdx);
            }
        }
        return null;
    }

    public QuestionCRModel getNextQuestion(QuestionCRModel questionCRModel) {
        int currentIdx = questionIds.indexOf(questionCRModel.getId());
        if(currentIdx >= 0) {
            int nextIdx = currentIdx + 1;
            if (nextIdx < questionIds.size()) {
                return QuestionList.getQuestion(questionIds.get(nextIdx));
            }
        }
        return null;
    }

    public boolean isLastQuestion(QuestionCRModel qustQuestionCRModel) {
        String oid = qustQuestionCRModel.getId();
        int idx = questionIds.indexOf(oid);
        return (idx == questionIds.size() - 1);
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }

//    private static QuestionPack currentQuestionPack;
//    public static QuestionPack getActiveQuestionPack() { return currentQuestionPack; }
//    public static void setCurrentQuestionPack(QuestionPack questionPack) { currentQuestionPack = questionPack; }
}
