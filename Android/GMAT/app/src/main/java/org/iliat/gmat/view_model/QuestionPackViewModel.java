package org.iliat.gmat.view_model;

import org.iliat.gmat.database.Question;
import org.iliat.gmat.database.QuestionPack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class QuestionPackViewModel {

    private List<QuestionViewModel> questionViewModelList;

    private QuestionPack questionPack;

    public List<QuestionViewModel> getQuestionViewModelList() {
        if(questionViewModelList == null) {
            questionViewModelList = new ArrayList<>();
            List<Question> questionList = questionPack.getQuestionList();
            for(Question question : questionList) {
                questionViewModelList.add(new QuestionViewModel(question));
            }
        }
        return questionViewModelList;
    }



//    private String availableTime;
//    private List<QuestionViewModel> questionViewModelList;
//
//    public String getAvailableTime() {
//        return questionPack.getAvailableTime();
//    }

//    public void setAvailableTime(String availableTime) {
//        this.availableTime = availableTime;
//    }

//    public List<QuestionViewModel> getQuestionViewModelList() {
////        return questionViewModelList;
//    }
//
//    public void setQuestionViewModelList(List<QuestionViewModel> questionViewModelList) {
//        this.questionViewModelList = questionViewModelList;
//    }
}
