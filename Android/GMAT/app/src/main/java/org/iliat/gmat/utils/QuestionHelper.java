package org.iliat.gmat.utils;


import io.realm.Realm;

/**
 * Created by hungtran on 5/4/16.
 */
public class QuestionHelper {
    private Realm realm;

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public QuestionHelper(Realm realm) {
        this.realm = realm;
    }

    public void updateQuestionInServer(){

    }
}
