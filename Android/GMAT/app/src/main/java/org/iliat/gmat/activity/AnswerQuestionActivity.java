package org.iliat.gmat.activity;

import android.app.DialogFragment;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.interf.ScreenManager;

public class AnswerQuestionActivity extends AppCompatActivity implements ScreenManager{
    TextView countDownTime;
    TextView progressText;
    ProgressBar progressBarDoing;
    FrameLayout fragmentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        connectView();
    }

    private void connectView(){
        countDownTime = (TextView)this.findViewById(R.id.textView_count_down);
        progressText = (TextView)this.findViewById(R.id.text_progress);
        progressBarDoing = (ProgressBar)this.findViewById(R.id.doing_progressBar);
        fragmentView = (FrameLayout)this.findViewById(R.id.fl_view_of_answer_question);
    }

    @Override
    public void openFragment(Fragment fragment, boolean addToBackStack) {

    }

    @Override
    public void showDialogFragment(DialogFragment dialogFragment, String tag) {

    }

    @Override
    public boolean back() {
        return false;
    }

    @Override
    public void setTitleOfActionBar(String titles) {

    }

    @Override
    public void goToActivity(Class activityClass) {

    }
}
