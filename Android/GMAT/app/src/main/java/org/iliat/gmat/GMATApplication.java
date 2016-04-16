package org.iliat.gmat;

import android.app.Application;
import android.util.Log;

import com.orm.SugarContext;


import java.util.List;

/**
 * Created by qhuydtvt on 4/4/2016.
 */
public class GMATApplication extends Application {

    @Override
    public void onCreate() {
        SugarContext.init(this);
        super.onCreate();


//        List<QuestionPack> questionPackList = QuestionPack.getAllQuestionPacks();
//        Log.d("GMATApplication", "getAllQuestionPacks: " + String.valueOf(questionPackList.size()));
//
//        for(QuestionPack questionPack : questionPackList) {
//            List<Question> questionList = questionPack.getQuestionList();
//            for(Question question : questionList) {
//                Log.d("GMATApplication", "question in pack: " +
//                        String.valueOf(question.getIdInServer()));
//            }
//        }
    }
}
