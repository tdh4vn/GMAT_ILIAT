package org.iliat.gmat.database;

import com.orm.SugarRecord;

import org.iliat.gmat.view_model.QuestionPackViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class QuestionPack extends SugarRecord {
    private String idInServer;
    private String availableTime;

    public QuestionPack(String idInServer, String availableTime) {
        this.idInServer = idInServer;
        this.availableTime = availableTime;
    }

    public QuestionPack(){}

    public String getIdInServer() {
        return idInServer;
    }

    public void setIdInServer(String idInServer) {
        this.idInServer = idInServer;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public List<Question> getQuestionList() {
        List<QuestionQuestionPackRel> questionQuestionPackRelList =
                QuestionQuestionPackRel.find(QuestionQuestionPackRel.class,
                        "QUESTION_PACK = ?",
                        String.valueOf(this.getId()));

        List<Question> questionList = new ArrayList<>();
        for (QuestionQuestionPackRel qqpRel : questionQuestionPackRelList) {
            List<Question> tempQuestionList = Question.find(Question.class, "ID=?",
                    String.valueOf(qqpRel.getQuestion().getId()));
            questionList.addAll(tempQuestionList);
        }

        return questionList;
    }

    public static List<QuestionPack> getAllQuestionPacks() {
        return QuestionPack.listAll(QuestionPack.class);
    }
}
