package org.iliat.gmat.frangment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import org.iliat.gmat.R;
import org.iliat.gmat.adapter.ListAnswerAdapter;
import org.iliat.gmat.dao.GMATAPI;
import org.iliat.gmat.enitity.QuestionSCModel;
import org.iliat.gmat.enitity.Questions;
import org.iliat.gmat.interf.GitHubService;
import org.iliat.gmat.utils.QuestionPackAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SCQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SCQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SCQuestionFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView mAnswers;
    private WebView mQuestion;
    Questions listQuestion;
    ListAnswerAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SCQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SCQuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SCQuestionFragment newInstance(String param1, String param2) {
        SCQuestionFragment fragment = new SCQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void loadData(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scquestion, container, false);
        connectView(view);
        return view;
    }

    private void connectView(View view){
        mAnswers = (ListView) view.findViewById(R.id.list_answers);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(GMATAPI.QUESTION_PACK_API)
//                .build();
//        GitHubService service = retrofit.create(GitHubService.class);
//        Log.e("TASK",service.listRepos("user"));
        DownloadTask downloadTask = new DownloadTask(listQuestion);
        downloadTask.execute();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
               URL url = new URL(GMATAPI.QUESTION_PACK_API);
//                InputStream inputStream = url.getInputStream();
//                Questions result = (new Gson()).fromJson(new InputStreamReader(inputStream), Questions.class);
//                return result;
////              return
                InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
                Log.d("doInBackground", "get reader");
                Questions questions = new Gson().fromJson(reader, Questions.class);
                Log.d("doInBackground", questions.getVersion());
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
        protected void onProgressUpdate(Questions... values) {
            super.onProgressUpdate(values[0]);
            adapter = new ListAnswerAdapter((Activity)getScreenManager(),R.layout.item_answer
                ,(ArrayList<String>)rs.getQuestions().get(0).getAnswer_choices());
            mAnswers.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

        @Override
        protected void onPostExecute(Questions questions) {
            super.onPostExecute(questions);
        }

    }

}
