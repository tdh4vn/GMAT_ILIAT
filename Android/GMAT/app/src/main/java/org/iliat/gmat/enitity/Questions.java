package org.iliat.gmat.enitity;

import android.util.Log;

import java.util.List;

/**
 * Created by hungtran on 3/13/16.
 */
public class Questions {

    /*private id _id;*/
    private String version;
    private List<QuestionCRModel> questions;

    public Questions() {
    }

    /*public id get_id() {
        return _id;
    }

    public void set_id(id _id) {
        this._id = _id;
    }*/

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<QuestionCRModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionCRModel> questions) {
        this.questions = questions;
    }

    public void save() {
        inst = this;
    }

    public static QuestionCRModel getQuestion(String oid) {
        Log.d("getQuestion - match", oid);
        for(QuestionCRModel questionCRModel : inst.getQuestions()) {
            Log.d("getQuestion", questionCRModel.getOid());
            if(questionCRModel.getOid().equals(oid)) {
                return questionCRModel;
            }
        }
        return null;
    }

    private static Questions inst;

    public static Questions getInst() {
        if(inst == null) {
            Log.d("getInst", "inst == null");
        }
        return inst;
    }
}
