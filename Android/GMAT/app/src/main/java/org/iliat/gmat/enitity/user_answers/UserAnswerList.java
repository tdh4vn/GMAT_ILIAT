package org.iliat.gmat.enitity.user_answers;

import org.iliat.gmat.enitity.questions.QuestionPack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 3/29/2016.
 */
public class UserAnswerList {

    private String answerTime;

    private List<UserAnswer> list;

    public List<UserAnswer> getList() { return list; }

    public  UserAnswerList(List<String> questionIds) {
        list = new ArrayList<>();
        for(String questionId : questionIds) {
            list.add(new UserAnswer(questionId));
        }
    }
}
