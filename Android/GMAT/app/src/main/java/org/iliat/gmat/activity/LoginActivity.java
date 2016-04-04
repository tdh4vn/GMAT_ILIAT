package org.iliat.gmat.activity;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import org.iliat.gmat.R;
import org.iliat.gmat.database.AnswerChoice;
import org.iliat.gmat.database.Question;
import org.iliat.gmat.database.QuestionPack;
import org.iliat.gmat.database.QuestionQuestionPackRel;
import org.iliat.gmat.network.APIUrls;
import org.iliat.gmat.enitity.DownloadJSONTask;

import org.iliat.gmat.enitity.JSONParser;
import org.iliat.gmat.enitity.JSONPostDownloadHandler;
import org.iliat.gmat.enitity.JSONPreDownloadHandler;
import org.iliat.gmat.network.JSONAnswerChoice;
import org.iliat.gmat.network.JSONQuestion;
import org.iliat.gmat.network.JSONQuestionList;
import org.iliat.gmat.network.JSONQuestionPack;
import org.iliat.gmat.network.JSONQuestionPackList;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Callback;

public class LoginActivity extends AppCompatActivity implements JSONPreDownloadHandler,
        JSONPostDownloadHandler, JSONParser {

    private OkHttpClient client;

    private static final String TAG = LoginActivity.class.toString();

    private static final String TAG_QUESION_PACK_DOWNLOAD = "question pack download";
    private static final String TAG_QUESION_DOWNLOAD = "question download";

    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button mLoginButton;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private CoordinatorLayout mCoordinatorLayout;
    private Snackbar mSnackbar;
    private OkHttpClient mHttpClient;

    private final String DOWNLOAD_QUESTION_TAG = "Download question";
    private final String DOWNLOAD_QUESTION_PACK_TAG = "Download question pack";

    private boolean mQuestionDownloadCompleted = false;
    private boolean mQuestionPackDownloadCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        List<Question> questionList = Question.listAll(Question.class);
//        for(Question q : questionList) {
//            Log.d(TAG, q.getIdInServer());
//        }
//
//        Log.d(TAG, "----------------------------------------------------------");
//
//        List<AnswerChoice> answerChoiceList = AnswerChoice.listAll(AnswerChoice.class);
//        for(AnswerChoice a: answerChoiceList) {
//            Log.d(TAG, a.getQuestion().getIdInServer());
//        }

        this.initUtils();
        this.initLayout();
        this.registerEvents();
    }

    private void initUtils() {
        this.mHttpClient = new OkHttpClient();
    }

    private void initLayout() {
        inputLayoutEmail = (TextInputLayout) this.findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) this.findViewById(R.id.input_layout_password);
        mEmailEditText = (EditText) this.findViewById(R.id.input_email);
        mPasswordEditText = (EditText) this.findViewById(R.id.input_password);
        mEmailEditText.addTextChangedListener(new MyTextWatcher(mEmailEditText));
        mPasswordEditText.addTextChangedListener(new MyTextWatcher(mPasswordEditText));
        mLoginButton = (Button) findViewById(R.id.button_login);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mSnackbar =
                Snackbar.make(mCoordinatorLayout,
                        getString(R.string.logging_in),
                        Snackbar.LENGTH_INDEFINITE);
        mHttpClient = new OkHttpClient();

    }

    private void registerEvents() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long questionCount = Question.count(Question.class);
                if (questionCount <= 0) {
                    downloadQuestions();
                } else {
                    goToMainActivity();
                }
            }
        });
    }

    private void checkAndDownloadData() {
        try {
            DownloadJSONTask downloadQuestionTask = new DownloadJSONTask(this, this, this,
                    DOWNLOAD_QUESTION_TAG);
            downloadQuestionTask.execute(new URL(APIUrls.QUESTIONS_API));
            Log.d(TAG, "Questions download started");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            DownloadJSONTask downloadQuestionPackTask = new DownloadJSONTask(this, this, this,
                    DOWNLOAD_QUESTION_PACK_TAG);
            downloadQuestionPackTask.execute(new URL(APIUrls.QUESTION_PACKS_API));
            Log.d(TAG, "Question packs download started");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    private boolean validateEmail() {
        String email = mEmailEditText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(mEmailEditText);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (mPasswordEditText.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(mPasswordEditText);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void downloadQuestionPacks() {
        mSnackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        mSnackbar.show();

        String url = APIUrls.QUESTION_PACKS_API;
        Request request = new Request.Builder()
                .url(url)
                .build();

        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onJSONDownloadFinished(TAG_QUESION_PACK_DOWNLOAD, false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONQuestionPackList jsonQuestionPackList = (
                        new Gson()).fromJson(response.body().charStream(),
                        JSONQuestionPackList.class);
                Log.d(TAG, String.valueOf(jsonQuestionPackList.getList().size()));
                saveQuestionPacks(jsonQuestionPackList);

                for(JSONQuestionPack jsonQuestionPack : jsonQuestionPackList.getList()) {
                    Log.d(TAG, jsonQuestionPack.getId());
                }

                onJSONDownloadFinished(TAG_QUESION_PACK_DOWNLOAD, true);
            }
        });
    }

    private  void downloadQuestions() {
        mSnackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        mSnackbar.show();

        String url = APIUrls.QUESTIONS_API;
        Request request = new Request.Builder()
                .url(url)
                .build();

        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onJSONDownloadFinished(TAG_QUESION_DOWNLOAD, false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONQuestionList jsonQuestionList = (
                        new Gson()).fromJson(response.body().charStream(),
                        JSONQuestionList.class);

                saveQuestions(jsonQuestionList);

                Log.d(TAG, "Download question " + jsonQuestionList.getList().size());
                onJSONDownloadFinished(TAG_QUESION_DOWNLOAD, true);
            }
        });
    }

    private void saveQuestions(JSONQuestionList jsonQuestionList) {
        long questionCount = Question.count(Question.class);
        if (questionCount == 0) {
            for (JSONQuestion jsonQuestion : jsonQuestionList.getList()) {
                Question question = new Question(jsonQuestion.getId(), jsonQuestion.getType(),
                        jsonQuestion.getSubType(), jsonQuestion.getStimulus(), jsonQuestion.getStem(), -1);
                question.save();
                for (JSONAnswerChoice jsonAnswerChoice : jsonQuestion.getAnswerChoiceList()) {
                    AnswerChoice answerChoice = new AnswerChoice(jsonAnswerChoice.getIndex(),
                            jsonAnswerChoice.getChoice(), jsonAnswerChoice.getExplanation(), question);
                    answerChoice.save();
                }
            }

        }
    }

    private void saveQuestionPacks(JSONQuestionPackList jsonQuestionPackList) {
        Log.d(TAG, "JSONQuestionPackSize: " + String.valueOf(jsonQuestionPackList.getList().size()));

        for(JSONQuestionPack jsonQuestionPack : jsonQuestionPackList.getList()) {
            QuestionPack questionPack = new QuestionPack(
                    jsonQuestionPack.getId(),
                    jsonQuestionPack.getAvailableTime());
            questionPack.save();
            for(String id : jsonQuestionPack.getQuestionIds()) {
                List<Question> questionsByServerId = Question.find(Question.class, "ID_IN_SERVER=?", id);
                if(questionsByServerId.size() > 0) {
                    Question question = questionsByServerId.get(0);
                    QuestionQuestionPackRel questionQuestionPackRel = new QuestionQuestionPackRel(
                            question, questionPack
                    );
                    questionQuestionPackRel.save();
                }
            }
        }

        List<QuestionPack> questionPackList = QuestionPack.getAllQuestionPacks();
        Log.d(TAG, "QuestionPackSize: " + String.valueOf(questionPackList.size()));
    }

    private void onJSONDownloadFinished(String tag, boolean result) {
        if(!result){
            mSnackbar.setText(getString(R.string.download_failed));
            mSnackbar.setDuration(Snackbar.LENGTH_LONG);
        } else {
            mSnackbar.dismiss();
            if(tag == TAG_QUESION_PACK_DOWNLOAD) {
                mQuestionPackDownloadCompleted = true;
            }
            else if(tag == TAG_QUESION_DOWNLOAD) {
                mQuestionDownloadCompleted = true;
                downloadQuestionPacks();
            }
            if(mQuestionDownloadCompleted && mQuestionPackDownloadCompleted) {
                /* Good, move to next screen */
                goToMainActivity();
            }
        }
    }

    @Override
    public void onDownload(InputStreamReader inputStreamReader, String tag) {
//        switch (tag) {
//            case DOWNLOAD_QUESTION_TAG:
//                JSONQuestionList.loadQuestionList(inputStreamReader);
//                Log.d(TAG, tag + " " + String.valueOf(JSONQuestionList.getInst().getList().size()));
//                Log.d(TAG, tag + " version " + JSONQuestionList.getInst().getVersion());
//                break;
//
//            case DOWNLOAD_QUESTION_PACK_TAG:
//                JSONQuestionPackList.loadFromJson(inputStreamReader);
//                Log.d(TAG, tag + " " + String.valueOf(JSONQuestionPackList.getInst().getList().size()));
//                break;
//        }
    }

    @Override
    public void onPostDownload(JSONObject jsonObject, String tag) {
        switch(tag) {
            case DOWNLOAD_QUESTION_TAG:
                mQuestionDownloadCompleted = true;
                break;
            case DOWNLOAD_QUESTION_PACK_TAG:
                mQuestionPackDownloadCompleted = true;
                break;
        }

        if(mQuestionDownloadCompleted && mQuestionPackDownloadCompleted) {
            goToMainActivity();
        }
    }

    @Override
    public void onPreDownload(String tag) {
        switch (tag) {
            case DOWNLOAD_QUESTION_TAG:
                break;
            case DOWNLOAD_QUESTION_PACK_TAG:
                break;
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }
}
