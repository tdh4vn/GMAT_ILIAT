package org.iliat.gmat.enitity;

import android.util.Log;

import java.util.List;

/**
 * Created by qhuydtvt on 3/14/2016.
 */
public class QuestionPackList {

    private List<QuestionPack> list;

    public List<QuestionPack> getList() {
        return list;
    }

    public List<QuestionPack> getTodayQuestionPacks() {
        return list;
    }

    public void save() {
        inst = this;
    }

    private static QuestionPackList inst;

    public static QuestionPackList getInst() {
        if(inst == null) {
            Log.d("getInst", "inst == null");
        }
        return inst;
    }
}
