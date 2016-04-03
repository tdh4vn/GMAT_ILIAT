package org.iliat.gmat.view_model;

/**
 * Created by hungtran on 4/4/16.
 */
public class AnswerChoiceViewModel {
    private String text;
    private String explanation;
    private int index;

    public AnswerChoiceViewModel(String text, String explanation, int index) {
        this.text = text;
        this.explanation = explanation;
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
