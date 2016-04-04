package org.iliat.gmat.view_model;

import org.iliat.gmat.database.Question;
import org.iliat.gmat.database.UserAnswer;

import java.util.List;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class QuestionViewModel {

    private Question question;
    private UserAnswer userAnswer;

    public QuestionViewModel(Question question) {
        this.question = question;
    }

    public Question getQuestion() { return question; }

}
