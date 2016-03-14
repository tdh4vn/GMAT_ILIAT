package org.iliat.gmat.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
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
import org.iliat.gmat.dao.GMATAPI;
import org.iliat.gmat.enitity.DownloadJSONTask;
import org.iliat.gmat.enitity.JSONDownloadHandler;
import org.iliat.gmat.enitity.JSONPostDownloadHandler;
import org.iliat.gmat.enitity.JSONPreDownloadHandler;
import org.iliat.gmat.enitity.QuestionPacks;
import org.iliat.gmat.enitity.Questions;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginActivity extends AppCompatActivity implements JSONPreDownloadHandler,
        JSONPostDownloadHandler, JSONDownloadHandler {
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnLogin;
    private CoordinatorLayout coordinatorLayout;
    private LoginActivity loginActivity;

    private DownloadJSONTask downloadJSONTask;

    private ProgressDialog progressDialog;

    private final String DOWNLOAD_QUESTION_TAG = "Download question";
    private final String DOWNLOAD_QUESTION_PACK_TAG = "Download question pack";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.connectLayout();
    }

    private void connectLayout(){
        inputLayoutEmail = (TextInputLayout)this.findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout)this.findViewById(R.id.input_layout_password);
        inputEmail = (EditText)this.findViewById(R.id.input_email);
        inputPassword = (EditText)this.findViewById(R.id.input_password);
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        btnLogin = (Button) findViewById(R.id.button_login);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, getString(R.string.login), Snackbar.LENGTH_LONG);
//
//                snackbar.show();
                checkAndDownloadData();
//                goToMainActivity();
            }
        });
    }

    private void checkAndDownloadData() {
        try {
            DownloadJSONTask downloadQuestionTask = new DownloadJSONTask(null, this, null,
                    DOWNLOAD_QUESTION_TAG);
            downloadQuestionTask.execute(new URL(GMATAPI.QUESTIONS_API));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            DownloadJSONTask downloadQuestionPackTask = new DownloadJSONTask(null, this, null,
                    DOWNLOAD_QUESTION_PACK_TAG);
            downloadQuestionPackTask.execute(new URL(GMATAPI.QUESTION_PACKS_API));
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
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
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

    @Override
    public void onDownload(InputStreamReader inputStreamReader, String tag) {
        switch (tag) {

            case DOWNLOAD_QUESTION_TAG:
                Questions questions = (new Gson()).fromJson(inputStreamReader,
                        Questions.class);
                Log.d("onDownload", tag + " " + String.valueOf(questions.getQuestions().size()) );
                Log.d("onDownload", tag + " version " + questions.getVersion());
                break;

            case DOWNLOAD_QUESTION_PACK_TAG:
                QuestionPacks questionPacks = (new Gson()).fromJson(inputStreamReader,
                        QuestionPacks.class);
                Log.d("onDownload", tag + " " + String.valueOf(questionPacks.getQuestion_packs().size()) );
                break;
        }
    }

    @Override
    public void onPostDownload(JSONObject jsonObject, String tag) {
        switch (tag) {

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

    private class DownloadTask extends AsyncTask<Void,Questions,Questions> {
        private Questions rs;
        public DownloadTask(Questions rs){
            this.rs = rs;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Questions doInBackground(Void... params) {
            try {
                URL url = new URL(GMATAPI.QUESTIONS_API);
//                InputStream inputStream = url.getInputStream();
//                Questions result = (new Gson()).fromJson(new InputStreamReader(inputStream), Questions.class);
//                return result;
////              return
                InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
                Log.d("doInBackground", "get reader");
                Questions questions = new Gson().fromJson(reader, Questions.class);
                Log.d("doInBackground", String.valueOf(questions.getQuestions().size()));
                return questions;

//                BufferedReader bufferedReader;
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    StringBuffer response = new StringBuffer();
//                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    String line;
//                    while ((line = bufferedReader.readLine()) != null) {
//                        response.append(line);
//                        Log.e("asdasd",line);
//                    }
//                    Log.e("asdasd",response.toString());
////                    Questions result = (new Gson()).fromJson(reader, Questions.class);
////                    reader.close();
//                    return null;
//                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }


        @Override
        protected void onPostExecute(Questions questions) {
            super.onPostExecute(questions);
        }

    }
}
