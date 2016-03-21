package org.iliat.gmat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.enitity.QuestionCRModel;
import org.iliat.gmat.enitity.UserChoice;

/**
 * Created by qhuydtvt on 3/19/2016.
 */
public class QuestionAdapter extends BaseAdapter {

    private QuestionCRModel mQuestionCRModel;
    private final int FIXED_ITEMS_CNT = 2;
    private LayoutInflater mLayoutInflater;
    private UserChoice mUserChoice;

    public QuestionAdapter(QuestionCRModel questionCRModel, UserChoice userChoice, LayoutInflater layoutInflater) {
        mQuestionCRModel = questionCRModel;
        mUserChoice  = userChoice;
        mLayoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return FIXED_ITEMS_CNT + mQuestionCRModel.getAnswer_choices().size();
    }

    @Override
    public Object getItem(int position) {
        switch (position) {
            case 0: return mQuestionCRModel.getStimulus();
            case 1: return "<b>" +  mQuestionCRModel.getStem() + "</b>";
            default:
                int idx = position - FIXED_ITEMS_CNT;
//                if(idx == mUserChoice.getChoice()){
//                    return "<div style=\"color:red;\">" + mQuestionCRModel.getAnswerChoice(idx) + "</div>";
//                }
                return mQuestionCRModel.getAnswerChoice(idx);
        }
    }


    @Override
    public long getItemId(int position) {
        return position;
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
            TextView textView = (TextView)convertView.findViewById(R.id.wv_content);
            textView.setText(content);
        }





//        if(position >= FIXED_ITEMS_CNT ) {
//            int answerChoiceIdx = FIXED_ITEMS_CNT - position;
//            wvContent.setTag(position - FIXED_ITEMS_CNT);
//            convertView.setTag(position - FIXED_ITEMS_CNT);
//            convertView.setTag(position - FIXED_ITEMS_CNT);
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mUserChoice.setChoice((Integer)v.getTag());
//                    notifyDataSetChanged();
//                }
//            });
//        }

        return convertView;
    }
}