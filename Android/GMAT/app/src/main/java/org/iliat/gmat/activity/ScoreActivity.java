package org.iliat.gmat.activity;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.github.lzyzsd.circleprogress.ArcProgress;

import org.iliat.gmat.R;

public class ScoreActivity extends AppCompatActivity {
    private int yourScore;
    private int maxScore;
    ArcProgress arcProgress;
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

//        ProgressBar progressBar = (ProgressBar) this.findViewById(R.id.progressBar_score);
//        ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progressBar_score", 100, 500); // see this max value coming back here, we animale towards that value
//        animation.setDuration (5000); //in milliseconds
//        animation.setInterpolator(new DecelerateInterpolator());
//        animation.start();
    }


}
