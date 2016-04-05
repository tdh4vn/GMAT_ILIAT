package org.iliat.gmat.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.database.Question;
import org.iliat.gmat.enitity.UserChoice;
import org.iliat.gmat.view_model.QuestionViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 3/19/2016.
 */
public class QuestionAnswerAdapter extends BaseAdapter {

    private static final String TAG = QuestionAnswerAdapter.class.toString();

    private final int FIXED_ITEMS_CNT = 2;
    private LayoutInflater mLayoutInflater;

    private QuestionViewModel mQuestionViewModel;
    private Context mContext;

    public QuestionAnswerAdapter(QuestionViewModel questionViewModel, Context context, LayoutInflater layoutInflater) {
        mQuestionViewModel = questionViewModel;
        mLayoutInflater = layoutInflater;
        mContext = context;
    }

    @Override
    public int getCount() {
        return FIXED_ITEMS_CNT + mQuestionViewModel.getNumberOAnswerChoices();
    }

    @Override
    public Object getItem(int position) {
        switch (position) {
            case 0:
                return mQuestionViewModel.getQuestion().getStimulus();
            case 1:
                return "<b>" + mQuestionViewModel.getQuestion().getStem() + "</b>";
            default:
                int idx = position - FIXED_ITEMS_CNT;
                return mQuestionViewModel.getAnswerChoiceViewModel(idx).getChoice();
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

    private List<TextView> answerTextViewList = new ArrayList<>();

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
            int  answerIdx = position - FIXED_ITEMS_CNT;

            TextView textView = (TextView) convertView.findViewById(R.id.wv_content);
            textView.setText(content);
            answerTextViewList.add(textView);

            ImageView imvLabel = (ImageView) convertView.findViewById(R.id.iv_answer_label);
            int imgId = getAnswerLabelImvId(answerIdx);
            if (imgId != -1) {
                imvLabel.setImageResource(imgId);
            }
            convertView.setTag(answerIdx);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer answerIdx = (Integer) v.getTag();
                    mQuestionViewModel.selectAnswerChoice(answerIdx);
                    TextView textView = (TextView) v.findViewById(R.id.wv_content);
                    for(TextView txv : answerTextViewList){
                        txv.setTextColor(ContextCompat.getColor(mContext, R.color.color_normal_answer));
                    }
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.color_selected_answer));
                    Log.d(TAG, "View click");
                }
            });
        }

        return convertView;
    }
}
