package org.iliat.gmat.view_model;

import android.util.Log;

import org.iliat.gmat.database.AnswerChoice;
import org.iliat.gmat.database.Question;
import org.iliat.gmat.database.UserAnswer;

import java.io.Serializable;
import java.util.List;

public class QuestionViewModel implements Serializable {

    public static final int ANSWER_NOT_DONE = -1;
    public static final int ANSWER_INCORRECT = 0;
    public static final int ANSWER_CORRECT = 1;

    private static final String TAG = QuestionViewModel.class.toString();

    private Question question;
    private UserAnswer userAnswer;
    private List<AnswerChoice> answerChoices;

    public QuestionViewModel(Question question) {
        this.question = question;
        answerChoices = AnswerChoice.find(AnswerChoice.class, "question = ?", String.valueOf(question.getId()));
    }

    public UserAnswer getUserAnswer() {
        return userAnswer;
    }

    public List<AnswerChoice> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<AnswerChoice> answerChoices) {
        this.answerChoices = answerChoices;
    }



    public String getStimulus() {return this.question.getStimulus();}

    public String getStem() {return question.getStem();}

    public boolean stemIsEmpty() {
        Log.d(TAG, "STEM: " + String.valueOf(question.getStem()));
        String stem = question.getStem();
        return (stem == null || stem.isEmpty());
    }

    public Question getQuestion() { return question; }

    public AnswerChoiceViewModel getAnswerChoiceViewModel(int index) {
        return new AnswerChoiceViewModel(answerChoices.get(index));
    }

    public int getNumberOAnswerChoices() {
        return answerChoices.size();
    }

    public void selectAnswerChoice(int index) {
        userAnswer.setChoiceIndex(index);
    }

    public void saveUserAnswer(){
        this.userAnswer.save();
    }

    public int getAnswerStatus() {
        if(userAnswer.getChoiceIndex() == UserAnswer.CHOICE_INDEX_UNDONE) {
            return ANSWER_NOT_DONE;
        }
        else {
            return userAnswer.getChoiceIndex() == question.getRightAnswerIndex() ? ANSWER_CORRECT : ANSWER_INCORRECT;
        }
    }

    public void clearUserAnswer() {
        userAnswer.setChoiceIndex(UserAnswer.CHOICE_INDEX_UNDONE);
        userAnswer.save();
    }

}
