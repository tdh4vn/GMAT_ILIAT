package org.iliat.gmat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iliat.gmat.R;
import org.iliat.gmat.activity.AnswerQuestionActivity;
import org.iliat.gmat.adapter.ListQuestionPackAdapter;
import org.iliat.gmat.enitity.questions.QuestionPack;
import org.iliat.gmat.fragment.answer_question.SCQuestionFragment;

public class QuestionPackFragment extends BaseFragment implements ListQuestionPackAdapter.OnListQuestionPackListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String TAG = QuestionPackFragment.class.toString();

    // TODO: Customize parameters
    private int mColumnCount = 1;

//    private ListQuestionPackAdapter.OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuestionPackFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static QuestionPackFragment newInstance(int columnCount) {
        QuestionPackFragment fragment = new QuestionPackFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_pack_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));

            }

            ListQuestionPackAdapter listQuestionPackAdapter = new ListQuestionPackAdapter();
            listQuestionPackAdapter.setQuestionPackListener(this);
            recyclerView.setAdapter(listQuestionPackAdapter);
        }
        return view;
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof ListQuestionPackAdapter.OnListFragmentInteractionListener) {
//            mListener = (ListQuestionPackAdapter.OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    @Override
    public void onQuestionPackInteraction(QuestionPack item) {
        Log.d(TAG, "Item click " + item.getAvailableTime());
        getScreenManager().goToActivity(AnswerQuestionActivity.class);
//        SCQuestionFragment scQuestionFragment = new SCQuestionFragment();
//        scQuestionFragment.setQuestionPack(item);
//        getScreenManager().openFragment(scQuestionFragment, true);

    }
}
