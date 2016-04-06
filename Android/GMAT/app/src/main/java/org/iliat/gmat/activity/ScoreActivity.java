package org.iliat.gmat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;

import org.iliat.gmat.R;
import org.iliat.gmat.adapter.QuestionAnswerSummaryAdapter;
import org.iliat.gmat.database.Question;
import org.iliat.gmat.database.QuestionPack;
import org.iliat.gmat.view_model.QuestionPackViewModel;

public class ScoreActivity extends AppCompatActivity {
    private static final String TAG = ScoreActivity.class.toString();
    public static final String TAG_QUESTION_PACK_VIEW_MODEL = "QUESTION_PACK_VIEW_MODEL";

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
    ListView ltvQuestionAnswerSummary;

    QuestionPackViewModel questionPackViewModel;

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
        getDataFromIntent();
        connectView();//ket noi xml voi java
        fillData();//fill data vao view
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Long questionPackId = getDataFromBundle(bundle);
        QuestionPack questionPack = QuestionPack.findById(QuestionPack.class, questionPackId);
        questionPackViewModel = new QuestionPackViewModel(questionPack);

        Log.d(TAG, questionPackViewModel.getAvailableTime());
    }

    private void connectView(){
        txtCountStar = (TextView) this.findViewById(R.id.txtCountStar);
        txtCountGreyTag = (TextView) this.findViewById(R.id.txtCountGrey);
        txtCountGreenTag = (TextView) this.findViewById(R.id.txtCountGreen);
        txtCountYellowTag = (TextView) this.findViewById(R.id.txtCountYellow);
        txtCountRedTag = (TextView) this.findViewById(R.id.txtCountRed);
        txtCountTimeAverage = (TextView) this.findViewById(R.id.txtTime);
        ltvQuestionAnswerSummary = (ListView) this.findViewById(R.id.ltv_score_question_answer_summary);

        arcProgress = (ArcProgress) this.findViewById(R.id.arc_progress);
        Button btnReview = (Button) this.findViewById(R.id.btn_review);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, QuestionReviewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(TAG_QUESTION_PACK_VIEW_MODEL, questionPackViewModel);
                intent.putExtra(TAG_QUESTION_PACK_VIEW_MODEL, bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * Hàm này để đổ data vào các view
     * @param
     */
    private void fillData(){
        yourScore = questionPackViewModel.getNumberOfCorrectAnswers();
        maxScore = questionPackViewModel.getNumberOfQuestions();


        txtCountStar.setText(String.valueOf(countStar));
        txtCountGreyTag.setText(String.valueOf(countGreyTag));
        txtCountGreenTag.setText(String.valueOf(countGreenTag));
        txtCountRedTag.setText(String.valueOf(countRedTag));
        txtCountYellowTag.setText(String.valueOf(countYellowTag));
        txtCountTimeAverage.setText(String.valueOf(countTimeAverage));

        arcProgress.setBottomText(String.format("%d / %d", yourScore, maxScore));
        arcProgress.setProgress((int) (yourScore * 100.0f / maxScore));

        ltvQuestionAnswerSummary.setAdapter(new QuestionAnswerSummaryAdapter(this,
                R.layout.list_item_score_question_answer_summary,
                questionPackViewModel.getQuestionViewModels()));
    }

    private static final String QUESTION_PACK_VIEW_MODEL_BUNDLE_STRING = "Question_pack_view_model";

    public static Bundle buildBundle(Long questionPackId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(QUESTION_PACK_VIEW_MODEL_BUNDLE_STRING, questionPackId);
        return bundle;
    }

    public static Long getDataFromBundle(Bundle bundle) {
        return (Long)bundle.getSerializable(QUESTION_PACK_VIEW_MODEL_BUNDLE_STRING);
    }
}
