package org.iliat.gmat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.enitity.questions.QuestionCRModel;
import org.iliat.gmat.enitity.UserChoice;

/**
 * Created by qhuydtvt on 3/19/2016.
 */
public class QuestionAnswerAdapter extends BaseAdapter {

    private QuestionCRModel mQuestionCRModel;
    private final int FIXED_ITEMS_CNT = 2;
    private LayoutInflater mLayoutInflater;
    private UserChoice mUserChoice;

    public QuestionAnswerAdapter(QuestionCRModel questionCRModel, UserChoice userChoice, LayoutInflater layoutInflater) {
        mQuestionCRModel = questionCRModel;
        mUserChoice  = userChoice;
        mLayoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return FIXED_ITEMS_CNT + mQuestionCRModel.getAnswerChoiceList().size();
    }

    @Override
    public Object getItem(int position) {
        switch (position) {
            case 0:
                return mQuestionCRModel.getStimulus();
            case 1:
                return "<b>" + mQuestionCRModel.getStem() + "</b>";
            default:
                int idx = position - FIXED_ITEMS_CNT;
                return mQuestionCRModel.getAnswerChoice(idx).getChoice();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private int getAnswerLabelImvId (int answerChoice) {
        switch (answerChoice) {
            case 0: return R.drawable.a;
            case 1: return R.drawable.b;
            case 2: return R.drawable.c;
            case 3: return R.drawable.d;
            case 4: return R.drawable.e;
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            int layoutId;
            if (position < FIXED_ITEMS_CNT) {
                layoutId = R.layout.list_item_question_prompt;
            } else {
                layoutId = R.layout.list_item_question_answer_choice;
            }

            convertView = mLayoutInflater.inflate(
                    layoutId, parent, false);
        }

        String content = (String)getItem(position);
        if (position < FIXED_ITEMS_CNT) {
            WebView wvContent = (WebView)convertView.findViewById(R.id.wv_content);
            wvContent.loadData(content, "text/html", "utf-8");
        } else {
            TextView textView = (TextView) convertView.findViewById(R.id.wv_content);
            textView.setText(content);

            ImageView imvLabel = (ImageView) convertView.findViewById(R.id.iv_answer_label);
            int imgId = getAnswerLabelImvId(position - FIXED_ITEMS_CNT);
            if (imgId != -1) {
                imvLabel.setImageResource(imgId);
            }
        }

        return convertView;
    }
}
