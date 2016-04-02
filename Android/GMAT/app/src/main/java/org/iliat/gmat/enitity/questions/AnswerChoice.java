package org.iliat.gmat.enitity.questions;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qhuydtvt on 3/28/2016.
 */
public class AnswerChoice {

    private static final String INDEX = "index";
    private static final String CHOICE = "choice";
    private static final String EXPLANATION = "explanation";

    @SerializedName(INDEX)
    private int index;

    @SerializedName(CHOICE)
    private String choice;

    @SerializedName(EXPLANATION)
    private String explanation;

    public boolean matchIndex(int index) { return index == this.index; }

    public int getIndex() {
        return index;
    }

    public String getChoice() { return choice; }

    public String getExplanation() {
        return explanation;
    }
}
