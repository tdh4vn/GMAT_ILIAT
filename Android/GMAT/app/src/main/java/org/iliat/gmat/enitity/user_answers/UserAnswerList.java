package org.iliat.gmat.enitity.user_answers;

import org.iliat.gmat.enitity.questions.QuestionPack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 3/29/2016.
 */
public class UserAnswerList {

    private List<UserAnswer> list;

    public List<UserAnswer> getList() { return list; }

    public  UserAnswerList(QuestionPack questionPack) {
        list = new ArrayList<>();
        for(String questionId : questionPack.getQuestionIds()) {
            list.add(new UserAnswer(questionId));
        }
    }
}
