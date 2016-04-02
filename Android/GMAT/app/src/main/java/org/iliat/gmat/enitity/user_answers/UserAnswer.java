package org.iliat.gmat.enitity.user_answers;

import org.iliat.gmat.enitity.questions.QuestionCRModel;

/**
 * Created by qhuydtvt on 3/29/2016.
 */
public class UserAnswer {

    private String questionId;
    private int answerChoice;
    private String note;

    private UserAnswer(String questionId, int answerChoice) {
        this.questionId = questionId;
        this.answerChoice = answerChoice;
    }

    public UserAnswer(String questionId) {
        this(questionId, -1);
    }

    public String getQuestionId() { return questionId; }

    public int getAnswerChoice() {
        return answerChoice;
    }

    public void setAnswerChoice(int answerChoice) {
        this.answerChoice = answerChoice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
