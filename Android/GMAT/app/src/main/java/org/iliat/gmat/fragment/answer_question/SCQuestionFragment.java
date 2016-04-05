package org.iliat.gmat.fragment.answer_question;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.adapter.ListAnswerChoiceAdapter;
import org.iliat.gmat.adapter.QuestionAnswerAdapter;
import org.iliat.gmat.enitity.UserChoice;
import org.iliat.gmat.fragment.BaseFragment;
import org.iliat.gmat.fragment.DialogFragmentExplantionQuestion;
import org.iliat.gmat.view_model.QuestionViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SCQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SCQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SCQuestionFragment extends BaseFragment implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    boolean isReview = true;
    private final int ANSWER_CHOICE_NUM = 5;

//    private ListView mAnswerChoices;
//    private TextView mWvQuestionStem;
//    private TextView mWvStimulus;

    private ListView ltvQuestion;
    private Button btnSubmit;

//    private QuestionPack mQuestionPack;

    private QuestionViewModel mQuestionCRModel;

//    private TextView[] tvAnswers;
//    private ViewHolder[] viewHolders;

    /*QuestionList listQuestion;*/
    ListAnswerChoiceAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SCQuestionFragment() {
        // Required empty public constructor
    }

//    public void setQuestionPack(QuestionPack questionPack) {
//        mQuestionPack = questionPack;
//    }

    public void setQuestion(QuestionViewModel question) {
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
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
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

//        if(mQuestionCRModel == null) { /* The first Question fragment */
//            mQuestionCRModel = QuestionList.getQuestion(mQuestionPack.getFirstQuestionId());
//        }


//        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);

        ltvQuestion = (ListView) view.findViewById(R.id.ltv_question);
        UserChoice userChoice = new UserChoice();
        userChoice.setChoice(0);
        ltvQuestion.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ltvQuestion.setAdapter(new QuestionAnswerAdapter(mQuestionCRModel, getActivity(), getActivity().getLayoutInflater()));

//        if(isLastQuestion()) {
//            btnSubmit.setText(getString(R.string.submit_question_pack));
//        } else {
//            btnSubmit.setText(getString(R.string.next_question));
//        }

//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isLastQuestion()) {
//                    /* Go to last screen */
//                    gotoNextQuestion();
//                }else {
//                    getScreenManager().goToActivity(ScoreActivity.class, null);
//                }
//            }
//        });

//        mWvStimulus = (TextView)view.findViewById(R.ObjectID.tv_stimulus);
//        mWvQuestionStem = (TextView)view.findViewById(R.ObjectID.tv_stem);
//        mAnswerChoices = (ListView) view.findViewById(R.ObjectID.list_answer_choices);
//        btnSubmit = (Button)view.findViewById(R.ObjectID.btnSubmit);

//        viewHolders = new ViewHolder[] {
//                new ViewHolder((TextView)view.findViewById(R.ObjectID.tv_answer_a),
//                        (TextView)view.findViewById(R.ObjectID.tv_label_a), 0),
//                new ViewHolder((TextView)view.findViewById(R.ObjectID.tv_answer_b),
//                        (TextView)view.findViewById(R.ObjectID.tv_label_b), 1),
//                new ViewHolder((TextView)view.findViewById(R.ObjectID.tv_answer_c),
//                        (TextView)view.findViewById(R.ObjectID.tv_label_c), 2),
//                new ViewHolder((TextView)view.findViewById(R.ObjectID.tv_answer_d),
//                        (TextView)view.findViewById(R.ObjectID.tv_label_d), 3),
//                new ViewHolder((TextView)view.findViewById(R.ObjectID.tv_answer_e),
//                        (TextView)view.findViewById(R.ObjectID.tv_label_e), 4)
//        };
//        tvAnswers = new TextView[]{
//                (TextView)view.findViewById(R.ObjectID.tv_answer_a),
//                (TextView)view.findViewById(R.ObjectID.tv_answer_b),
//                (TextView)view.findViewById(R.ObjectID.tv_answer_c),
//                (TextView)view.findViewById(R.ObjectID.tv_answer_d),
//                (TextView)view.findViewById(R.ObjectID.tv_answer_e)
//        };


        /*mWvStimulus.loadData(mQuestionCRModel.getStimulus(),"text/html", "utf-8");
        mWvQuestionStem.loadData(mQuestionCRModel.getStem(),"text/html", "utf-8");*/

//        mWvStimulus.setText(mQuestionCRModel.getStimulus());
//        mWvQuestionStem.setText(mQuestionCRModel.getStem());
//
//        List<String> answerChoices = mQuestionCRModel.getAnswerChoiceList();
//        for(int idx = 0; idx < answerChoices.size(); idx++ ){
//            tvAnswers[idx].setText(answerChoices.get(idx));
//        }

//        mAnswerChoices.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        mAnswerChoices.setOnItemSelectedListener(this);
//
//        mAnswerChoices.setAdapter(
//                new ListAnswerChoiceAdapter(getActivity().getLayoutInflater(),
//                mQuestionCRModel));
//        UIUtils.setListViewHeightBasedOnChildren(mAnswerChoices);
//
////        setListViewHeightBasedOnChildren(mAnswerChoices);
//
//        if(isLastQuestion()) {
//            btnSubmit.setText(getString(R.string.submit_question_pack));
//        }
//
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isLastQuestion()) {
//                    /* Go to last screen */
//                }
//                else {
//                    gotoNextQuestion();
//                }
//            }
//        });
    }

//    private  boolean isLastQuestion() {
//        return mQuestionPack.isLastQuestion(mQuestionCRModel);
//    }

//    private void gotoNextQuestion() {
//        QuestionCRModel nextQuestion = mQuestionPack.getNextQuestion(mQuestionCRModel);
//        if(nextQuestion != null) {
//            Log.d("gotoNextQuestion", nextQuestion.getId());
//            getScreenManager().openFragment(create(mQuestionPack,
//                    nextQuestion), true);
//        }
//    }

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView txvItem = (TextView)view.findViewById(R.id.txv_answer_choice);
        txvItem.setTextColor(ContextCompat.getColor(this.getActivity(), R.color.color_selected_answer));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**
     * Hàm này nhận sự kiện ấn nut NEXT từ thằng Activity gọi vào
     * @param v
     */
    @Override
    public void onClick(View v) {

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

//    public static SCQuestionFragment create(QuestionPack questionPack, QuestionCRModel questionCRModel) {
//        SCQuestionFragment fragment = new SCQuestionFragment();
//        fragment.setQuestionPack(questionPack);
//        fragment.setQuestion(questionCRModel);
//        return fragment;
//    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private class ViewHolder {
        TextView tvLabel;
        TextView tvContent;
        int index;

        public ViewHolder(TextView tvLabel, TextView tvContent, int index) {
            this.tvLabel = tvLabel;
            this.tvContent = tvContent;
            this.index = index;
        }
    }


}


