package org.iliat.gmat.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.enitity.questions.QuestionCRModel;
import org.iliat.gmat.view_model.QuestionViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungtran on 4/3/16.
 */
public class ListQuestionDetailAdapter extends ArrayAdapter<QuestionViewModel>{
    Context context=null;
    List<QuestionViewModel>arrQuestionCRModel=null;
    int layoutId;
    public ListQuestionDetailAdapter(Context context, int resource, List<QuestionViewModel> objects) {
        super(context, resource, objects);
        this.context=context;
        this.arrQuestionCRModel = objects;
        this.layoutId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);

        ImageView icon = (ImageView)convertView.findViewById(R.id.img_icon_question_detail);
        TextView txtView = (TextView)convertView.findViewById(R.id.txt_name_question_detail);
        ImageView isDone = (ImageView)convertView.findViewById(R.id.img_isdone_question_detail);

        //QuestionCRModel questionCRModel = arrQuestionCRModel.get(position);
        QuestionViewModel questionCRModel = arrQuestionCRModel.get(position);

        /**
         * Them code doi mau vao cho nay
         */
        txtView.setText("Question " + String.valueOf(position));

        return convertView;
    }
}
