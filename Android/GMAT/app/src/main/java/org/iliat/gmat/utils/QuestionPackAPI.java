package org.iliat.gmat.utils;

import com.google.gson.Gson;

import org.iliat.gmat.dao.GMATAPI;
import org.iliat.gmat.enitity.QuestionSCModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by hungtran on 3/13/16.
 */
public class QuestionPackAPI {
    public static QuestionSCModel getQuestion(){

        try {
            URL url;
            url = new URL(GMATAPI.QUESTION_PACK_API);
            InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
            QuestionSCModel result = new Gson().fromJson(reader, QuestionSCModel.class);
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
