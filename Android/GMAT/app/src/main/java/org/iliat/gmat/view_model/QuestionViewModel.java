package org.iliat.gmat.view_model;

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

    public UserAnswer getUserAnswer() {
        return userAnswer;
    }

    public List<AnswerChoice> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<AnswerChoice> answerChoices) {
        this.answerChoices = answerChoices;
    }

    public QuestionViewModel(Question question) {
        this.question = question;
        answerChoices = AnswerChoice.find(AnswerChoice.class,
                "QUESTION=?",
                String.valueOf(question.getId()));

        List<UserAnswer> userAnswers = UserAnswer.find(UserAnswer.class, "QUESTION=?",
                String.valueOf(question.getId()));
        if(userAnswers.size() > 0) {
            userAnswer = userAnswers.get(0);
        }
        else {
            userAnswer = new UserAnswer(question);
        }
    }

    public String getStimulus() {return this.question.getStimulus();}

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
        if(userAnswer.getChoiceIndex() == -1) {
            return ANSWER_NOT_DONE;
        }
        else {
            return userAnswer.getChoiceIndex() == question.getRightAnswerIndex() ? ANSWER_CORRECT : ANSWER_INCORRECT;
        }
    }

}
