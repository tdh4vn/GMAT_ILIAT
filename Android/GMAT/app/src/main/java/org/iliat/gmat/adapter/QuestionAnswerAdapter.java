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
import org.iliat.gmat.view_model.QuestionViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhuydtvt on 3/19/2016.
 */
public class QuestionAnswerAdapter extends BaseAdapter {

    private static final String TAG = QuestionAnswerAdapter.class.toString();

    private int fixedItemsCnt = 2;
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

        if(mQuestionViewModel.stemIsEmpty())
        {
            fixedItemsCnt = 1;
        }
        else {
            fixedItemsCnt = 2;
        }
        Log.d(TAG, "FixedItemsCnt : " + String.valueOf(fixedItemsCnt));
        return fixedItemsCnt + mQuestionViewModel.getNumberOAnswerChoices();
    }

    @Override
    public Object getItem(int position) {
        if (position < fixedItemsCnt) {
            if (position == 0)
                return mQuestionViewModel.getQuestion().getStimulus();
            return "<b>" + mQuestionViewModel.getQuestion().getStem() + "</b>";
        }

        int idx = position - fixedItemsCnt;
        Log.d(TAG, String.valueOf(idx));
        return mQuestionViewModel.getAnswerChoiceViewModel(idx).getChoice();
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
            if (position < fixedItemsCnt) {
                layoutId = R.layout.list_item_question_prompt;
            } else {
                layoutId = R.layout.list_item_question_answer_choice;
            }

            convertView = mLayoutInflater.inflate(
                    layoutId, parent, false);
        }

        String content = (String)getItem(position);
        if (position < fixedItemsCnt) {
            WebView wvContent = (WebView) convertView.findViewById(R.id.wv_content);
            wvContent.loadData(content, "text/html", "utf-8");
        } else {
            int  answerIdx = position - fixedItemsCnt;

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
