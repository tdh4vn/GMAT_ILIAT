package org.iliat.gmat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.enitity.QuestionCRModel;

/**
 * Created by hungtran on 3/13/16.
 */
public class ListAnswerChoiceAdapter extends BaseAdapter {

    private QuestionCRModel mQuestion;
    private LayoutInflater mLayoutInflater;

    public ListAnswerChoiceAdapter(LayoutInflater layoutInflater, QuestionCRModel question) {
        this.mLayoutInflater = layoutInflater;
        this.mQuestion = question;
    }

    @Override
    public int getCount() {
        return mQuestion.getAnswer_choices().size();
    }

    @Override
    public Object getItem(int position) {
        return mQuestion.getAnswer_choices().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String answerChoice = mQuestion.getAnswer_choices().get(position);

        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_answer_choice, parent, false);
        }

        TextView txvAnswerChoice = (TextView)convertView.findViewById(R.id.txv_answer_choice);
        txvAnswerChoice.setText(answerChoice);

        return convertView;
    }
}
