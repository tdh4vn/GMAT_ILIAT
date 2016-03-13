package org.iliat.gmat.enitity;

import java.util.List;

/**
 * Created by hungtran on 3/13/16.
 */
public class Questions {
    private id _id;
    private String version;
    private List<QuestionSCModel> questions;

    public Questions() {
    }

    public id get_id() {
        return _id;
    }

    public void set_id(id _id) {
        this._id = _id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<QuestionSCModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionSCModel> questions) {
        this.questions = questions;
    }
}
