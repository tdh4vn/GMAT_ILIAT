package org.iliat.gmat.enitity.user_answers;

import android.Manifest;

import org.iliat.gmat.enitity.questions.QuestionPack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by qhuydtvt on 3/29/2016.
 */
public class UserAnswerList {

    private String answerTime;

    private List<UserAnswer> list;

    public List<UserAnswer> getList() { return list; }

    public UserAnswerList() {
        list = new ArrayList<>();
    }

    public void updateList(QuestionPack questionPack) {
        list.clear();
        answerTime = Calendar.getInstance().getTime().toString();
        for(String questionId : questionPack.getQuestionIds()) {
            list.add(new UserAnswer(questionId));
        }
    }

    private static UserAnswerList inst = new UserAnswerList();
    public static UserAnswerList getInst() {
        return inst;
    }
}
