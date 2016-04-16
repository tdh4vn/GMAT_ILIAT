package org.iliat.gmat.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.iliat.gmat.R;

import org.iliat.gmat.animation.ExpandAnimation;
import org.iliat.gmat.database.AnswerChoice;
import org.iliat.gmat.interf.ScreenManager;
import org.iliat.gmat.view_model.QuestionPackViewModel;
import org.iliat.gmat.view_model.QuestionViewModel;

import java.util.ArrayList;

/**
 * Khi sử dụng nhớ set QuestionPack cho nó
 */
/*TODO*/
public class QuestionReviewActivity extends AppCompatActivity implements ScreenManager {

    //question Pack của cái activity này
    private QuestionPackViewModel mQuestionPack;
    private android.app.FragmentManager mFragmentManager;
    TextView isCorrect;
    TextView txtProcess;
    Button btnExit;
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
        mQuestionPack = (QuestionPackViewModel) ((itent.getBundleExtra(ScoreActivity.TAG_QUESTION_PACK_VIEW_MODEL))
                .getSerializable(ScoreActivity.TAG_QUESTION_PACK_VIEW_MODEL));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        getRefercenceForView();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mQuestionPack);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        addListenerForTabChange();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getBundleExtra(ScoreActivity.TAG_QUESTION_PACK_VIEW_MODEL);
        int position = bundle.getInt(ScoreActivity.SCOREACTIIVTY_POSITION);
        if (position != -1) {// chuyen review sang cau hoi thu position
            Log.d("TAG TAG TAG", String.valueOf(position));
            mViewPager.setCurrentItem(position);
        }
    }

    private void getRefercenceForView() {
        isCorrect = (TextView) findViewById(R.id.txt_is_correct);
        isCorrect.setTypeface(Typeface.DEFAULT_BOLD);
        txtProcess = (TextView) findViewById(R.id.txt_process);
        mFragmentManager = getFragmentManager();
        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionReviewActivity.this.finish();
            }
        });
    }

    /**
     * thêm listener cho sự kiện change tab
     */
    private void addListenerForTabChange() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //kiem tra neu dap an dung thi thay doi text
                if (mQuestionPack.getQuestionViewModels().get(position).getUserAnswer().getChoiceIndex()
                        == mQuestionPack.getQuestionViewModels().get(position).getQuestion().getRightAnswerIndex()) {
                    isCorrect.setText("Correct");
                } else {
                    isCorrect.setText("Incorrect");
                }
                txtProcess.setText(String.format("%d / %d", position + 1, mQuestionPack.getQuestionViewModels().size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void openFragment(android.app.Fragment fragment, boolean addToBackStack) {

    }

    @Override
    public void showDialogFragment(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(mFragmentManager, tag);
    }

    @Override
    public boolean back() {
        return false;
    }

    @Override
    public void setTitleOfActionBar(String titles) {

    }

    @Override
    public void goToActivity(Class activityClass, Bundle bundle) {

    }

    /*TODO*/

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public QuestionPackViewModel getmQuestionPack() {
            return mQuestionPack;
        }

        public void setQuestionPack(QuestionPackViewModel mQuestionPack) {
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

        private void getRefercence(View view) {
            contentQuestion = (TextView) view.findViewById(R.id.question_content);
            final QuestionViewModel questionViewModel = (mQuestionPack.getQuestionViewModels().get(position));
            contentQuestion.setText(Html.fromHtml(questionViewModel.getStimulus()));
            listView = (ListView) view.findViewById(R.id.list_answer_review);
            AnswerChoiseInReviewAdapter answerChoiseInReviewAdapter
                    = new AnswerChoiseInReviewAdapter(getActivity(), R.layout.item_question_in_question_review, mQuestionPack.getQuestionViewModels().get(position));
            answerChoiseInReviewAdapter.userChoise = mQuestionPack.getQuestionViewModels().get(position).getUserAnswer().getChoiceIndex();
            answerChoiseInReviewAdapter.rightAnswer = mQuestionPack.getQuestionViewModels().get(position).getQuestion().getRightAnswerIndex();
            Log.d("User/Right", String.format("%d-%d,%d", position, answerChoiseInReviewAdapter.userChoise, answerChoiseInReviewAdapter.rightAnswer));
            listView.setAdapter(answerChoiseInReviewAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textViewQuestion = (TextView) view.findViewById(R.id.txt_content_answer);
                    TextView textView = (TextView) view.findViewById(R.id.txt_explanation);
                    View line = view.findViewById(R.id.line_between_question_explanation);
//                    ExpandAnimation expandAnimationExplanation = new ExpandAnimation(textView, 500);
//                    ExpandAnimation expandAnimationLine = new ExpandAnimation(line, 500);
//                    textView.startAnimation(expandAnimationExplanation);
//                    line.startAnimation(expandAnimationLine);
//                    textViewQuestion.setTypeface(textViewQuestion.getTypeface(), Typeface.BOLD);
                    if (textView.getVisibility() == View.GONE) {//nếu đang ẩn thì hiện nó lên
                        textViewQuestion.setTypeface(Typeface.DEFAULT_BOLD);
                        line.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                    } else {
                        textViewQuestion.setTypeface(Typeface.DEFAULT);
                        line.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                    }
                }
            });
        }

        /*TODO*/
        public class AnswerChoiseInReviewAdapter extends ArrayAdapter<AnswerChoice> {
            int userChoise;
            int rightAnswer;
            QuestionViewModel questionViewModel;
            Context context;
            int resourceLayoutID;
            ImageView iconAnswer;
            TextView contentAnswer;
            TextView explanationAnswer;
            View line;

            public AnswerChoiseInReviewAdapter(Context context, int resource, QuestionViewModel object) {
                super(context, resource, object.getAnswerChoices());
                this.context = context;
                questionViewModel = object;
                resourceLayoutID = resource;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater =
                        ((Activity) context).getLayoutInflater();

                convertView = inflater.inflate(resourceLayoutID, null);
                iconAnswer = (ImageView) convertView.findViewById(R.id.img_icon_answer);
                contentAnswer = (TextView) convertView.findViewById(R.id.txt_content_answer);
                line = convertView.findViewById(R.id.line_between_question_explanation);
                explanationAnswer = (TextView) convertView.findViewById(R.id.txt_explanation);
                explanationAnswer.setText(questionViewModel.getAnswerChoices().get(position).getExplanation());
                explanationAnswer.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                switch (position) {
                    case 0:
                        iconAnswer.setImageResource(R.drawable.a);
                        contentAnswer.setText(questionViewModel.getAnswerChoices().get(position).getChoice());
                        if (userChoise == 0) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_red_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_red_500));
                        }
                        if (rightAnswer == 0) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_green_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_green_500));
                        }
                        break;
                    case 1:
                        iconAnswer.setImageResource(R.drawable.b);
                        contentAnswer.setText(questionViewModel.getAnswerChoices().get(position).getChoice());
                        if (userChoise == 1) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_red_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_red_500));
                        }
                        if (rightAnswer == 1) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_green_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_green_500));
                        }
                        break;
                    case 2:
                        iconAnswer.setImageResource(R.drawable.c);
                        contentAnswer.setText(questionViewModel.getAnswerChoices().get(position).getChoice());
                        if (userChoise == 2) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_red_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_red_500));
                        }
                        if (rightAnswer == 2) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_green_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_green_500));
                        }
                        break;
                    case 3:
                        iconAnswer.setImageResource(R.drawable.d);
                        contentAnswer.setText(questionViewModel.getAnswerChoices().get(position).getChoice());
                        if (userChoise == 3) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_red_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_red_500));
                        }
                        if (rightAnswer == 3) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_green_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_green_500));
                        }
                        break;
                    case 4:
                        iconAnswer.setImageResource(R.drawable.e);
                        contentAnswer.setText(questionViewModel.getAnswerChoices().get(position).getChoice());
                        if (userChoise == 4) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_red_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_red_500));
                        }
                        if (rightAnswer == 4) {
                            iconAnswer.setColorFilter(getResources().getColor(R.color.color_green_500));
                            contentAnswer.setTextColor(getResources().getColor(R.color.color_green_500));
                        }
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
            placeholderFragment.setQuestionPack(questionPack);
            return placeholderFragment;
        }

        @Override
        public int getCount() {
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
