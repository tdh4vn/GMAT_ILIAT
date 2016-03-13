package org.iliat.gmat.enitity;

import java.util.List;

/**
 * Created by hungtran on 3/13/16.
 */
public class QuestionSCModel {
    private ID _id;
    private List<String> answer_choices;
    private String explanation;
    private int right_answer;
    private String stem;
    private String stimulus;
    private String sub_type;
    private String type;


    public QuestionSCModel() {
    }

    public QuestionSCModel(ID _id, List<String> answer_choices, String explanation, int right_answer, String stem, String stimulus, String sub_type, String type) {
        this._id = _id;
        this.answer_choices = answer_choices;
        this.explanation = explanation;
        this.right_answer = right_answer;
        this.stem = stem;
        this.stimulus = stimulus;
        this.sub_type = sub_type;
        this.type = type;
    }

    public ID get_id() {
        return _id;
    }

    public void set_id(ID _id) {
        this._id = _id;
    }

    public List<String> getAnswer_choices() {
        return answer_choices;
    }

    public void setAnswer_choices(List<String> answer_choices) {
        this.answer_choices = answer_choices;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(int right_answer) {
        this.right_answer = right_answer;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getStimulus() {
        return stimulus;
    }

    public void setStimulus(String stimulus) {
        this.stimulus = stimulus;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
