package org.iliat.gmat.enitity.questions;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by hungtran on 3/13/16.
 */
public class QuestionList {

    private static final String VERSION = "version";
    private static final String QUESTTIONS= "questions";

    @SerializedName(VERSION)
    private String version;

    @SerializedName(QUESTTIONS)
    private List<QuestionCRModel> list;

    public QuestionList() {
    }

    public String getVersion() {
        return version;
    }

    public List<QuestionCRModel> getList() {
        return list;
    }

    public static QuestionCRModel getQuestion(String oid) {
        Log.d("getQuestion - match", oid);
        for(QuestionCRModel questionCRModel : inst.getList()) {
            Log.d("getQuestion", questionCRModel.getId());
            if(questionCRModel.getId().equals(oid)) {
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

    public static void loadQuestionList(InputStreamReader reader) {
        inst = (new Gson()).fromJson(reader, QuestionList.class);
    }
}
