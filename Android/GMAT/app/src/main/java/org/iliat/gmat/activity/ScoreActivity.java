package org.iliat.gmat.activity;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;

import org.iliat.gmat.R;

public class ScoreActivity extends AppCompatActivity {
    private int yourScore = 10;//so cau tra loi dung
    private int maxScore = 16;//so cau hoi toi da
    private int countStar = 0;//so cau danh dau sao
    private int countGreyTag = 0;//tam thoi truyen vao 0
    private int countGreenTag = 0;//nhu tren
    private int countYellowTag = 0;//nt
    private int countRedTag = 0;//nt
    private int countTimeAverage = 0;//thoi gian lam trung binh 1 cau
    ArcProgress arcProgress;
    TextView txtCountStar;
    TextView txtCountGreyTag;
    TextView txtCountGreenTag;
    TextView txtCountYellowTag;
    TextView txtCountRedTag;
    TextView txtCountTimeAverage;
    public int getYourScore() {
        return yourScore;
    }

    public void setYourScore(int yourScore) {
        this.yourScore = yourScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        connectView();//ket noi xml voi java
        fillData();//fill data vao view
    }

    private void connectView(){
        txtCountStar = (TextView) this.findViewById(R.id.txtCountStar);
        txtCountGreyTag = (TextView) this.findViewById(R.id.txtCountGrey);
        txtCountGreenTag = (TextView) this.findViewById(R.id.txtCountGreen);
        txtCountYellowTag = (TextView) this.findViewById(R.id.txtCountYellow);
        txtCountRedTag = (TextView) this.findViewById(R.id.txtCountRed);
        txtCountTimeAverage = (TextView) this.findViewById(R.id.txtTime);
        arcProgress = (ArcProgress) this.findViewById(R.id.arc_progress);
    }

    /**
     * Hàm này để đổ data vào các view
     * @param
     */
    private void fillData(){
        txtCountStar.setText(String.valueOf(countStar));
        txtCountGreyTag.setText(String.valueOf(countGreyTag));
        txtCountGreenTag.setText(String.valueOf(countGreenTag));
        txtCountRedTag.setText(String.valueOf(countRedTag));
        txtCountYellowTag.setText(String.valueOf(countYellowTag));
        txtCountTimeAverage.setText(String.valueOf(countTimeAverage));

        arcProgress.setBottomText(String.format("%d / %d", yourScore, maxScore));
        arcProgress.setProgress((int)(yourScore * 100.0f / maxScore));
    }

}
