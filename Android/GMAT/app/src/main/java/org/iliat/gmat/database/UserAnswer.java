package org.iliat.gmat.database;

import com.orm.SugarRecord;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class UserAnswer extends SugarRecord {
    private int choiceIndex;
    private Question question;

    public UserAnswer() {}

    public int getChoiceIndex() {
        return choiceIndex;
    }

    public void setChoiceIndex(int choiceIndex) {
        this.choiceIndex = choiceIndex;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
