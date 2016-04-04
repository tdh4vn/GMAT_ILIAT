package org.iliat.gmat.view_model;

import org.iliat.gmat.database.Question;
import org.iliat.gmat.database.QuestionPack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class QuestionPackViewModel implements Serializable{

    private List<QuestionViewModel> questionViewModelList;

    private QuestionPack questionPack;

    private boolean isShowDetail;

    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isShowDetail() {
        return isShowDetail;
    }

    public void setIsShowDetail(boolean isShowDetail) {
        this.isShowDetail = isShowDetail;
    }

    public QuestionPackViewModel(QuestionPack questionPack) {
        this.questionPack = questionPack;
    }

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


    public String getAvailableTime() {return questionPack.getAvailableTime();}



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
