package org.iliat.gmat.enitity;

import android.text.style.QuoteSpan;
import android.util.Log;

import java.util.List;

/**
 * Created by qhuydtvt on 3/14/2016.
 */
public class QuestionPacks {

    private List<QuestionPack> question_packs;

    public List<QuestionPack> getQuestion_packs() {
        return question_packs;
    }

    public List<QuestionPack> getTodayQuestionPacks() {
        return question_packs;
    }

    public void save() {
        inst = this;
    }

    private static QuestionPacks inst;

    public static QuestionPacks getInst() {
        if(inst == null) {
            Log.d("getInst", "inst == null");
        }
        return inst;
    }
}
