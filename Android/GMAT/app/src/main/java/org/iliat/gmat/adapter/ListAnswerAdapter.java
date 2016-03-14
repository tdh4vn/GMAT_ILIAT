package org.iliat.gmat.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import org.iliat.gmat.R;
import org.iliat.gmat.enitity.QuestionCRModel;

import java.util.ArrayList;

/**
 * Created by hungtran on 3/13/16.
 */
public class ListAnswerAdapter extends BaseAdapter {

    private QuestionCRModel question;

    @Override
    public int getCount() {
        return question.getAnswer_choices().size();
    }

    @Override
    public Object getItem(int position) {
        return question.getAnswer_choices().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

        }

        return convertView;
    }
}
