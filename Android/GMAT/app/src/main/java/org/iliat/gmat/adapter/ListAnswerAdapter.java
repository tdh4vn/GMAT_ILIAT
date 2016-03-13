package org.iliat.gmat.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;

import org.iliat.gmat.R;
import org.iliat.gmat.enitity.QuestionSCModel;
import org.iliat.gmat.utils.QuestionPackAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungtran on 3/13/16.
 */
public class ListAnswerAdapter extends ArrayAdapter<String> {
    Activity context = null;
    ArrayList<String> answers = null;
    int layoutId;

    public ListAnswerAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        layoutId = resource;
        answers = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=
                context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        WebView wb = (WebView)convertView.findViewById(R.id.web_view_answer);
        wb.loadData(answers.get(position),"text/html", "UTF-8");

        return convertView;
    }

}
