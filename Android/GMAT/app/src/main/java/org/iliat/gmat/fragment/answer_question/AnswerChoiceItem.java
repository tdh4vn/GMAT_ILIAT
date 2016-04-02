package org.iliat.gmat.fragment.answer_question;

import org.iliat.gmat.enitity.questions.AnswerChoice;

/**
 * Created by qhuydtvt on 4/2/2016.
 */
public class AnswerChoiceItem {

    private AnswerChoice answerChoice;
    private boolean selected;

    public AnswerChoiceItem(AnswerChoice answerChoice, boolean selected) {
        this.answerChoice = answerChoice;
        this.selected = selected;
    }

    public AnswerChoice getAnswerChoice() {
        return answerChoice;
    }

    public void setAnswerChoice(AnswerChoice answerChoice) {
        this.answerChoice = answerChoice;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
