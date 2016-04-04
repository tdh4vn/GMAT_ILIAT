package org.iliat.gmat.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import org.iliat.gmat.R;
import org.iliat.gmat.enitity.QuestionPackList;
import org.iliat.gmat.enitity.questions.QuestionCRModel;
import org.iliat.gmat.enitity.questions.QuestionPack;

/**
 * Khi sử dụng nhớ set QuestionPack cho nó
 */

public class QuestionReviewActivity extends AppCompatActivity {

    //question Pack của cái activity này
    private QuestionPack mQuestionPack;

    public QuestionPack getmQuestionPack() {
        return mQuestionPack;
    }

    public void setmQuestionPack(QuestionPack mQuestionPack) {
        this.mQuestionPack = mQuestionPack;
    }

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
        mQuestionPack = QuestionPackList.getInst().getList().get(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_review_fragment);
        PlaceholderFragment.context = this;

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mQuestionPack);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public QuestionPack getmQuestionPack() {
            return mQuestionPack;
        }

        public void setmQuestionPack(QuestionPack mQuestionPack) {
            this.mQuestionPack = mQuestionPack;
        }

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        private QuestionPack mQuestionPack;
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
            contentQuestion.setText(mQuestionPack.getListQuestionOnPack().get(0).getStimulus());
            listView = (ListView) view.findViewById(R.id.list_answer_review);
            //item_question_in_question_review layout id để ném vào listView Adapter
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private QuestionPack questionPack;
        private QuestionCRModel questionCRModel;



        public SectionsPagerAdapter(FragmentManager fm, QuestionPack questionPack) {
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
            return this.questionPack.getListQuestionOnPack().size();
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
