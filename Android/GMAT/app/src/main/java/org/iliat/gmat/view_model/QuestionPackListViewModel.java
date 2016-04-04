package org.iliat.gmat.view_model;


import org.iliat.gmat.database.QuestionPack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class QuestionPackListViewModel {

    private List<QuestionPackViewModel> questionPackViewModelList;

    private List<QuestionPack> questionPackList;

    public QuestionPackListViewModel(List<QuestionPack> questionPackList) {
        this.questionPackList = questionPackList;
        this.questionPackViewModelList = new ArrayList<>();
        for(QuestionPack questionPack : questionPackList) {
            questionPackViewModelList.add(
                    new QuestionPackViewModel(questionPack)
            );
        }
    }

    public List<QuestionPackViewModel> getQuestionPackViewModelList() {
        return questionPackViewModelList;
    }
}
