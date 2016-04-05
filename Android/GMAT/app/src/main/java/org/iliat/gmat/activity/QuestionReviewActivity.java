package org.iliat.gmat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.iliat.gmat.R;

import org.iliat.gmat.database.AnswerChoice;
import org.iliat.gmat.view_model.QuestionPackViewModel;
import org.iliat.gmat.view_model.QuestionViewModel;

/**
 * Khi sử dụng nhớ set QuestionPack cho nó
 */

public class QuestionReviewActivity extends AppCompatActivity {

    //question Pack của cái activity này
    private QuestionPackViewModel mQuestionPack;

    TextView isCorrect;
    TextView txtProcess;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_review_fragment);
        PlaceholderFragment.context = this;
        Intent itent = this.getIntent();
        mQuestionPack = (QuestionPackViewModel)((itent.getBundleExtra(ScoreActivity.TAG_QUESTION_PACK_VIEW_MODEL))
                .getSerializable(ScoreActivity.TAG_QUESTION_PACK_VIEW_MODEL));
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        getRefercenceForView();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mQuestionPack);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    private void getRefercenceForView(){
        isCorrect = (TextView)findViewById(R.id.txt_is_correct);
        txtProcess = (TextView)findViewById(R.id.txt_process);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public QuestionPackViewModel getmQuestionPack() {
            return mQuestionPack;
        }

        public void setmQuestionPack(QuestionPackViewModel mQuestionPack) {
            this.mQuestionPack = mQuestionPack;
        }

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private int position;
        private QuestionPackViewModel mQuestionPack;
        private TextView contentQuestion;
        private ListView listView;
        public static Context context;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.position = sectionNumber - 1;
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_question_review, container, false);
            getRefercence(rootView);
            return rootView;
        }

        private void getRefercence(View view){
            contentQuestion = (TextView) view.findViewById(R.id.question_content);
            QuestionViewModel questionViewModel = (mQuestionPack.getFirstQuestionViewModel());
            contentQuestion.setText(questionViewModel.getStimulus());
            listView = (ListView) view.findViewById(R.id.list_answer_review);
            //item_question_in_question_review layout id để ném vào listView Adapter
            AnswerChoiseInReviewAdapter answerChoiseInReviewAdapter
                    = new AnswerChoiseInReviewAdapter(getActivity(),R.layout.item_question_in_question_review, mQuestionPack.getQuestionViewModels().get(position));
            listView.setAdapter(answerChoiseInReviewAdapter);
        }

        public class AnswerChoiseInReviewAdapter extends ArrayAdapter<AnswerChoice>{

            QuestionViewModel questionViewModel;
            Context context;
            int resourceLayoutID;
            ImageView iconAnswer;
            TextView contentAnswer;

            public AnswerChoiseInReviewAdapter(Context context, int resource, QuestionViewModel object) {
                super(context, resource, object.getAnswerChoices());
                this.context = context;
                questionViewModel = object;
                resourceLayoutID = resource;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater=
                        ((Activity)context).getLayoutInflater();
                convertView=inflater.inflate(resourceLayoutID, null);
                iconAnswer = (ImageView)convertView.findViewById(R.id.img_icon_answer);
                contentAnswer = (TextView)convertView.findViewById(R.id.txt_content_answer);
                switch (position){
                    case 0:
                        iconAnswer.setImageResource(R.drawable.a);
                        contentAnswer.setText(questionViewModel.getAnswerChoices().get(position).getChoice());
                        break;
                    case 1:
                        iconAnswer.setImageResource(R.drawable.b);
                        contentAnswer.setText(questionViewModel.getAnswerChoices().get(position).getChoice());
                        break;
                    case 2:
                        iconAnswer.setImageResource(R.drawable.c);
                        contentAnswer.setText(questionViewModel.getAnswerChoices().get(position).getChoice());
                        break;
                    case 3:
                        iconAnswer.setImageResource(R.drawable.d);
                        contentAnswer.setText(questionViewModel.getAnswerChoices().get(position).getChoice());
                        break;
                }
                return convertView;
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private QuestionPackViewModel questionPack;
        private QuestionViewModel questionCRModel;



        public SectionsPagerAdapter(FragmentManager fm, QuestionPackViewModel questionPack) {
            super(fm);
            this.questionPack = questionPack;
        }

        @Override
        public Fragment getItem(int position) {

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            PlaceholderFragment placeholderFragment = PlaceholderFragment.newInstance(position + 1);
            placeholderFragment.setmQuestionPack(questionPack);
            return placeholderFragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return this.questionPack.getNumberOfQuestions();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return "SECTION 4";
        }
    }

    /**
     * Adapter cho các câu trả lời ở phần Review
     */


}
