package org.iliat.gmat.enitity.questions;

import com.google.gson.annotations.SerializedName;

import org.iliat.gmat.enitity.*;

import java.util.List;

/**
 * Created by hungtran on 3/13/16.
 */
public class QuestionCRModel {

    private static final String ID = "_id";
    private static final String TYPE = "type";
    private static final String SUB_TYPE = "sub_type";
    private static final String STIMULUS = "stimulus";
    private static final String STEM = "stem";
    private static final String ANSWER_CHOICES = "answer_choices";
    private static final String RIGHT_ANSWER = "right_answer";

    @SerializedName(ID)
    private ObjectID objectID;

    @SerializedName(TYPE)
    private String type;

    @SerializedName(SUB_TYPE)
    private String subType;

    @SerializedName(STIMULUS)
    private String stimulus;

    @SerializedName(STEM)
    private String stem;

    @SerializedName(ANSWER_CHOICES)
    private List<AnswerChoice> answerChoiceList;

    @SerializedName(RIGHT_ANSWER)
    private int rightAnswer;

    public QuestionCRModel() {
    }

    private final int FIXED_ITEMS_COUNT = 2;

    public int getTotalItemsCount() {
        return FIXED_ITEMS_COUNT + answerChoiceList.size();
    }

    public List<AnswerChoice> getAnswerChoiceList() {
        return this.answerChoiceList;
    }

    public AnswerChoice getAnswerChoice(int idx) {
        if(idx < answerChoiceList.size())
            return answerChoiceList.get(idx);
        return null;
    }

    public String getId() { return this.objectID.getOID(); }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public String getStem() {
        return stem;
    }

    public String getStimulus() {
        return stimulus;
    }

    public String getSubType() {
        return subType;
    }

    public String getType() {
        return type;
    }

}
