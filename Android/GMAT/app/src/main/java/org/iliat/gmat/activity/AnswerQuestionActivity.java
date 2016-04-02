package org.iliat.gmat.activity;

import android.app.DialogFragment;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button btnNext;
    View.OnClickListener btnNextListennerOnFragment;

    public void setBtnNextListennerOnFragment(View.OnClickListener btnNextListennerOnFragment) {
        this.btnNextListennerOnFragment = btnNextListennerOnFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        getViewReferences();
        createTimer();
        fillData();
    }

    private void getViewReferences(){
        txtCountTime = (TextView)this.findViewById(R.id.textView_count_down);
        progressText = (TextView)this.findViewById(R.id.text_progress);
        progressBarDoing = (ProgressBar)this.findViewById(R.id.doing_progressBar);
        fragmentView = (FrameLayout)this.findViewById(R.id.fragment_view_of_answer_question);
        btnNext = (Button)this.findViewById(R.id.btn_next);
        progressBarDoing.setMax(maxQuestion);
        addListeners();
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

    /**
     * Hàm này để add listener cho cái button NEXT, listener có 2 cái, 1 cái ở activity 1 cái ở fragment
     */
    private void addListeners(){
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //btnNextListennerOnFragment.onClick(v);//goi ham onClick o Fragment
                countAnswer++;//tang so cau da tra loi len 1
                fillData();
            }
        });
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
    public void goToActivity(Class activityClass, Bundle bundle) {

    }
}
