package org.iliat.gmat.database;

import com.orm.SugarRecord;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class UserAnswer extends SugarRecord {
    private int choiceIndex;
    private Question question;

    public UserAnswer() {}
}
