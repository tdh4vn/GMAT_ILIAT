package org.iliat.gmat.frangment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.adapter.ListAnswerChoiceAdapter;
import org.iliat.gmat.enitity.QuestionCRModel;
import org.iliat.gmat.enitity.QuestionPack;
import org.iliat.gmat.enitity.Questions;

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
    private ListView mAnswerChoices;
    private TextView mWvQuestionStem;
    private TextView mWvStimulus;
    private Button btnSubmit;

    private QuestionPack mQuestionPack;

    private QuestionCRModel mQuestionCRModel;

    /*Questions listQuestion;*/
    ListAnswerChoiceAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SCQuestionFragment() {
        // Required empty public constructor
    }

    public void setQuestionPack(QuestionPack questionPack) {
        mQuestionPack = questionPack;
    }

    public void setQuestion(QuestionCRModel question) {
        mQuestionCRModel = question;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_question, container, false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        mWvStimulus = (TextView)view.findViewById(R.id.wv_stimulus);
        mWvQuestionStem = (TextView)view.findViewById(R.id.wv_stem);
        mAnswerChoices = (ListView) view.findViewById(R.id.list_answer_choices);
        btnSubmit = (Button)view.findViewById(R.id.btnSubmit);


        if(mQuestionCRModel == null) { /* The first Question fragment */
            Log.d("initLayout - oid", mQuestionPack.getFirstQuestionId());
            mQuestionCRModel = Questions.getQuestion(mQuestionPack.getFirstQuestionId());
        }

        /*mWvStimulus.loadData(mQuestionCRModel.getStimulus(),"text/html", "utf-8");
        mWvQuestionStem.loadData(mQuestionCRModel.getStem(),"text/html", "utf-8");*/

        mWvStimulus.setText(mQuestionCRModel.getStimulus());
        mWvQuestionStem.setText(mQuestionCRModel.getStem());

        mAnswerChoices.setAdapter(new ListAnswerChoiceAdapter(getActivity().getLayoutInflater(),
                mQuestionCRModel));

        if(isLastQuestion()) {
            btnSubmit.setText(getString(R.string.submit_question_pack));
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLastQuestion()) {
                    /* Go to last screen */
                }
                else {
                    gotoNextQuestion();
                }
            }
        });
    }

    private  boolean isLastQuestion() {
        return mQuestionPack.isLastQuestion(mQuestionCRModel);
    }

    private void gotoNextQuestion() {
        QuestionCRModel nextQuestion = mQuestionPack.getNextQuestion(mQuestionCRModel);
        if(nextQuestion != null) {
            Log.d("gotoNextQuestion", nextQuestion.getOid());
            getScreenManager().openFragment(create(mQuestionPack,
                    nextQuestion), true);
        }
    }

/*    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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

    public static SCQuestionFragment create(QuestionPack questionPack, QuestionCRModel questionCRModel) {
        SCQuestionFragment fragment = new SCQuestionFragment();
        fragment.setQuestionPack(questionPack);
        fragment.setQuestion(questionCRModel);
        return fragment;
    }
}
