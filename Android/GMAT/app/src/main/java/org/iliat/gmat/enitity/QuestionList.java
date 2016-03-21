package org.iliat.gmat.enitity;

import android.util.Log;

import java.util.List;

/**
 * Created by hungtran on 3/13/16.
 */
public class QuestionList {

    /*private id _id;*/
    private String version;
    private List<QuestionCRModel> list;

    public QuestionList() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<QuestionCRModel> getList() {
        return list;
    }

    public void save() {
        inst = this;
    }

    public static QuestionCRModel getQuestion(String oid) {
        Log.d("getQuestion - match", oid);
        for(QuestionCRModel questionCRModel : inst.getList()) {
            Log.d("getQuestion", questionCRModel.getOid());
            if(questionCRModel.getOid().equals(oid)) {
                return questionCRModel;
            }
        }
        return null;
    }

    private static QuestionList inst;

    public static QuestionList getInst() {
        if(inst == null) {
            Log.d("getInst", "inst == null");
        }
        return inst;
    }
}
