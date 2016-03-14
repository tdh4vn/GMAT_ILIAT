package org.iliat.gmat.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;

import org.iliat.gmat.R;

import java.util.ArrayList;

/**
 * Created by hungtran on 3/13/16.
 */
public class ListAnswerAdapter extends ArrayAdapter<String> {
    private Activity context = null;
    private ArrayList<String> answers = null;
    private int layoutId;

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
