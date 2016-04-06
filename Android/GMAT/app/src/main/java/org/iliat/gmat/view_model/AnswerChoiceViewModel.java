package org.iliat.gmat.view_model;

import com.orm.SugarRecord;

import org.iliat.gmat.database.AnswerChoice;

/**
 * Created by hungtran on 4/4/16.
 */
public class AnswerChoiceViewModel {

    private AnswerChoice answerChoice;

    public AnswerChoiceViewModel(AnswerChoice answerChoice) {
        this.answerChoice = answerChoice;
    }

    public String getChoice() {
        return answerChoice.getChoice();
    }

    public String getExplanation() {
        return answerChoice.getExplanation();
    }

    public int getIndex() {
        return answerChoice.getIdx();
    }

    public boolean isCorrectAnswerChoice() {
        return answerChoice.getIdx() == answerChoice.getQuestion().getRightAnswerIndex();
    }
}
