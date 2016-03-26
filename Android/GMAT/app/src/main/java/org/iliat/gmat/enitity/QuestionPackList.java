package org.iliat.gmat.enitity;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by qhuydtvt on 3/14/2016.
 */
public class QuestionPackList {

    @SerializedName("question_packs")
    private List<QuestionPack> list;

    public List<QuestionPack> getList() {
        return list;
    }

    public List<QuestionPack> getTodayQuestionPacks() {
        return list;
    }

    private static QuestionPackList inst;

    public static QuestionPackList getInst() {
        if(inst == null) {
            Log.d("getInst", "inst == null");
        }
        return inst;
    }

    public static void loadFromJson(InputStreamReader reader) {
        inst = (new Gson()).fromJson(reader, QuestionPackList.class);
    }
}
