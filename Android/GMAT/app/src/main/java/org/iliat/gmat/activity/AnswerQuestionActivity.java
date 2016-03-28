package org.iliat.gmat.activity;

import android.app.DialogFragment;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.interf.CallBackAnswerQuestion;
import org.iliat.gmat.interf.ScreenManager;

import java.util.Timer;
import java.util.TimerTask;

public class AnswerQuestionActivity extends AppCompatActivity implements ScreenManager, CallBackAnswerQuestion{
    long countTime = 0;
    int countAnswer = 12;
    int maxQuestion = 16;
    TextView txtCountTime;
    TextView progressText;
    ProgressBar progressBarDoing;
    FrameLayout fragmentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        connectView();
        createTimer();
        fillData();
    }

    /**
     * Ham nay de fill data vao view
     * mỗi khi làm 1 câu thì thay gọi lại hàm này để cập nhật
     * ở fragment có thêm cái CallBackAnswerQuestion rồi truyền cái class này vào đấy
     *
     */
    public void fillData(){
        progressText.setText(String.format("%d / %d", countAnswer, maxQuestion));
        progressBarDoing.setProgress(countAnswer);
    }
    private void connectView(){
        txtCountTime = (TextView)this.findViewById(R.id.textView_count_down);
        progressText = (TextView)this.findViewById(R.id.text_progress);
        progressBarDoing = (ProgressBar)this.findViewById(R.id.doing_progressBar);
        fragmentView = (FrameLayout)this.findViewById(R.id.fragment_view_of_answer_question);

        progressBarDoing.setMax(maxQuestion);
    }

    /**
     * Ham nay de tao dong do dem gio
     */
    private void createTimer(){
        Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if(countTime/3600 >= 1){
                            txtCountTime.setText(String.format("%02d:%02d:%02d",countTime/3600, (countTime%3600) / 60, countTime % 60));
                        } else {
                            txtCountTime.setText(String.format("%02d:%02d",countTime/60, countTime % 60));
                        }

                        countTime++;
                    }
                });
            }
        }, 1000, 1000);
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
