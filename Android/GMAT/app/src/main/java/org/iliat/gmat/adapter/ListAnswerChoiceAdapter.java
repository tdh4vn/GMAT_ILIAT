package org.iliat.gmat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.enitity.AnswerChoice;
import org.iliat.gmat.enitity.QuestionCRModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungtran on 3/13/16.
 */
public class ListAnswerChoiceAdapter extends BaseAdapter {

    private QuestionCRModel mQuestion;
    private ArrayList<AnswerChoice> answerChoiceList;
    private LayoutInflater mLayoutInflater;

    public ListAnswerChoiceAdapter(LayoutInflater layoutInflater, QuestionCRModel question) {
        this.mLayoutInflater = layoutInflater;
        this.mQuestion = question;

        answerChoiceList = new ArrayList<>();
        List<String> answer_choices = mQuestion.getAnswer_choices();
        for(int idx = 0; idx < answer_choices.size(); idx++) {
            answerChoiceList.add(
                    new AnswerChoice(mQuestion.getOid(),
                    idx,
                    answer_choices.get(idx) ));
        }
    }

    @Override
    public int getCount() {
        return answerChoiceList.size();
    }

    @Override
    public Object getItem(int position) {
        return answerChoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AnswerChoice answerChoice = answerChoiceList.get(position);

        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_answer_choice, parent, false);
        }

        TextView txvAnswerChoice = (TextView)convertView.findViewById(R.id.txv_answer_choice);
        txvAnswerChoice.setText(answerChoice.getText());

        convertView.setTag(answerChoice);

        return convertView;
    }

}
