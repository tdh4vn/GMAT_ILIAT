package org.iliat.gmat.fragment.answer_question;

import org.iliat.gmat.enitity.questions.AnswerChoice;
import org.iliat.gmat.enitity.questions.QuestionCRModel;
import org.iliat.gmat.enitity.user_answers.UserAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 4/2/2016.
 */
public class SCQuestionItem {
    private QuestionCRModel questionCRModel;
    private UserAnswer userAnswer;
    private List<AnswerChoiceItem> answerChoiceItems;

    public SCQuestionItem(QuestionCRModel questionCRModel, UserAnswer userAnswer) {
        this.questionCRModel = questionCRModel;
        this.userAnswer = userAnswer;

        answerChoiceItems = new ArrayList<>();
        for (AnswerChoice answerChoice: questionCRModel.getAnswerChoiceList()) {
            AnswerChoiceItem answerChoiceItem = new AnswerChoiceItem(answerChoice, false);
            answerChoiceItems.add(answerChoiceItem);
        }
    }

    public String getStimulus() { return questionCRModel.getStimulus(); }

    public String getStem() { return questionCRModel.getStem(); }

    public List<AnswerChoiceItem> getAnswerChoiceItems() { return answerChoiceItems; }

    public void selectAnswer(int index) {
        for(AnswerChoiceItem answerChoiceItem : answerChoiceItems) {
            if(answerChoiceItem.getAnswerChoice().matchIndex(index)) {
                answerChoiceItem.setSelected(true);
            }
            else {
                answerChoiceItem.setSelected(false);
            }
        }
    }
}
