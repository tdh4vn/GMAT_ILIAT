package org.iliat.gmat.view_model;

import org.iliat.gmat.database.Question;
import org.iliat.gmat.database.UserAnswer;

import java.util.List;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class QuestionViewModel {

    public static final int QUESTION_STATUS_NOT_DONE = -1;
    public static final int QUESTION_STATUS_INCORRECT = 0;
    public static final int QUESTION_STATUS_CORRECT = 1;



    private Question question;
    private UserAnswer userAnswer;



    public QuestionViewModel(Question question) {
        this.question = question;
    }

    public Question getQuestion() {return question;}

    public int getQuestionStatus() {
        if (userAnswer == null) {
            List<UserAnswer> userAnswers = UserAnswer.find(UserAnswer.class, "QUESTION=?",
                    String.valueOf(question.getId()));
            if(userAnswers.size() > 0) {
                userAnswer = userAnswers.get(0);
            }
        }

        if (userAnswer.getChoiceIndex() == -1) {
            return QUESTION_STATUS_NOT_DONE;
        }
        return (userAnswer.getChoiceIndex() == question.getRightAnswerIndex()) ?
                QUESTION_STATUS_CORRECT : QUESTION_STATUS_INCORRECT;
    }

}
