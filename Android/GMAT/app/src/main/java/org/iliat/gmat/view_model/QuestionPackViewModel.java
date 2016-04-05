package org.iliat.gmat.view_model;

import org.iliat.gmat.database.Question;
import org.iliat.gmat.database.QuestionPack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class QuestionPackViewModel implements Serializable {

    private List<QuestionViewModel> questionViewModels;
    private boolean isShowDetail;
    private boolean isChecked;

    private QuestionPack questionPack;

    private void initQuestionViewModels() {
        if (questionViewModels == null) {
            questionViewModels = new ArrayList<>();
            List<Question> questionList = questionPack.getQuestionList();
            for (Question question : questionList) {
                questionViewModels.add(new QuestionViewModel(question));
            }
        }
    }

    public Long getId() {
        return questionPack.getId();
    }


    public List<QuestionViewModel> getQuestionViewModels() {
        initQuestionViewModels();
        return questionViewModels;
    }

    public QuestionPackViewModel(QuestionPack questionPack) {
        this.questionPack = questionPack;
    }

    public String getAvailableTime() {return questionPack.getAvailableTime();}

    public QuestionPack getQuestionPack(){return questionPack;}

    public QuestionViewModel getFirstQuestionViewModel() {
        Question question = questionPack.getFirstQuestion();
        if(question != null) return new QuestionViewModel(question);
        return null;
    }

    public QuestionViewModel getNextQuestionViewModel(QuestionViewModel questionViewModel) {
        Question nextQuestion =  questionPack.getNextQuestion(questionViewModel.getQuestion());
        if(nextQuestion != null) return new QuestionViewModel(nextQuestion);
        return null;
    }


    public boolean isLastQuestionInPack(QuestionViewModel questionViewModel) {
        return questionPack.isLastQuestionInPack(questionViewModel.getQuestion());
    }


    public boolean isShowDetail() {
        return isShowDetail;
    }

    public void setIsShowDetail(boolean isShowDetail) {
        this.isShowDetail = isShowDetail;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getNumberOfCorrectAnswers() {
        int ret = 0;
        initQuestionViewModels();
        for(QuestionViewModel questionViewModel : questionViewModels) {
            if(questionViewModel.getAnswerStatus() == QuestionViewModel.ANSWER_CORRECT)
                ret++;
        }
        return ret;
    }

    public int getNumberOfQuestions() {
        return questionPack.getNumberOfQuestions();
    }


    public void saveUserAnswers() {
        initQuestionViewModels();
        for(QuestionViewModel questionViewModel : questionViewModels) {
            questionViewModel.saveUserAnswer();
        }
    }
}
