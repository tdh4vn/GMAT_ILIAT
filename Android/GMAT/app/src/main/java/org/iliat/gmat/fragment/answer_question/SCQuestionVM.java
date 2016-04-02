package org.iliat.gmat.fragment.answer_question;

import org.iliat.gmat.enitity.UserChoice;
import org.iliat.gmat.enitity.questions.AnswerChoice;
import org.iliat.gmat.enitity.questions.QuestionCRModel;
import org.iliat.gmat.enitity.user_answers.UserAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 4/2/2016.
 */
public class SCQuestionVM {
    private QuestionCRModel questionCRModel;
    private UserAnswer userAnswer;
    private List<AnswerChoiceVM> answerChoiceVMs;
    private UserChoice userChoice;

    public SCQuestionVM(QuestionCRModel questionCRModel,
                        UserAnswer userAnswer,
                        UserChoice userChoice) {
        this.questionCRModel = questionCRModel;
        this.userAnswer = userAnswer;
        this.userChoice = userChoice;

        answerChoiceVMs = new ArrayList<>();

        for (AnswerChoice answerChoice: questionCRModel.getAnswerChoiceList()) {
            AnswerChoiceVM answerChoiceVM = new AnswerChoiceVM(answerChoice, false);
            answerChoiceVMs.add(answerChoiceVM);
        }
    }

    public String getStimulus() { return questionCRModel.getStimulus(); }

    public String getStem() { return questionCRModel.getStem(); }

    public List<AnswerChoiceVM> getAnswerChoiceVMs() { return answerChoiceVMs; }

    public void selectAnswer(AnswerChoiceVM answerChoiceVM) {
        for(AnswerChoiceVM answerChoiceVMInList : answerChoiceVMs) {
            answerChoiceVMInList.setSelected(false);
        }
        answerChoiceVM.setSelected(true);
        userChoice.setChoice(answerChoiceVM.getIndex());
    }
}
