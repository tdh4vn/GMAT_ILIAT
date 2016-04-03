package org.iliat.gmat.fragment.answer_question;

import org.iliat.gmat.enitity.questions.AnswerChoice;

/**
 * Created by qhuydtvt on 4/2/2016.
 */
public class AnswerChoiceVM {

    private AnswerChoice answerChoice;
    private boolean selected;

    public AnswerChoiceVM(AnswerChoice answerChoice, boolean selected) {
        this.answerChoice = answerChoice;
        this.selected = selected;
    }

    public int getIndex() {
        return answerChoice.getIndex();
    }

    public String getChoice() {
        return answerChoice.getChoice();
    }

    public String getExplanation() {
        return answerChoice.getExplanation();
    }

//    public AnswerChoice getAnswerChoice() {
//        return answerChoice;
//    }
//
//    public void setAnswerChoice(AnswerChoice answerChoice) {
//        this.answerChoice = answerChoice;
//    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
