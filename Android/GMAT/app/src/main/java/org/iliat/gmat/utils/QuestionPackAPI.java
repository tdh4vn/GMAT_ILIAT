package org.iliat.gmat.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.iliat.gmat.dao.GMATAPI;
import org.iliat.gmat.enitity.QuestionSCModel;
import org.iliat.gmat.enitity.Questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by hungtran on 3/13/16.
 */
public class QuestionPackAPI {
    public static Questions getQuestion(){

        try {
            HttpURLConnection url = (HttpURLConnection) (new URL(GMATAPI.QUESTION_PACK_API)).openConnection();
            InputStreamReader reader = new InputStreamReader(url.getInputStream(), "UTF-8");
            int responseCode = url.getResponseCode();
            BufferedReader bufferedReader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuffer response = new StringBuffer();
                bufferedReader = new BufferedReader(reader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                Log.e("asdasd",response.toString());
                Questions result = (new Gson()).fromJson(reader, Questions.class);
                reader.close();
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
