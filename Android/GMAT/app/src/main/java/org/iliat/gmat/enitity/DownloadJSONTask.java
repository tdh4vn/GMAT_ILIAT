package org.iliat.gmat.enitity;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.InputMismatchException;

/**
 * Created by qhuydtvt on 3/9/2016.
 */
public class DownloadJSONTask extends AsyncTask<URL, Integer, JSONObject>{

    private HttpURLConnection urlConnection = null;
    private BufferedReader bufferedReader = null;

    private JSONPostDownloadHandler jsonPostDownloadHandler = null;
    private JSONPreDownloadHandler jsonPreDownloadHandler = null;
    private JSONDownloadHandler jsonDownloadHandler = null;

    private String mTag = null;

    public void setJsonPostDownloadHandler(JSONPostDownloadHandler jsonPostDownloadHandler) {
        this.jsonPostDownloadHandler = jsonPostDownloadHandler;
    }

    public DownloadJSONTask() {
    }

    public DownloadJSONTask(JSONPreDownloadHandler preDownloadHandler,
                            JSONDownloadHandler jsonDownloadHandler,
                            JSONPostDownloadHandler jsonPostDownloadHandler,
                            String tag) {
        this.jsonPreDownloadHandler = preDownloadHandler;
        this.jsonDownloadHandler = jsonDownloadHandler;
        this.jsonPostDownloadHandler = jsonPostDownloadHandler;
        this.mTag = tag;
    }

    @Override
    protected void onPreExecute() {
        if(jsonPreDownloadHandler != null) {
            jsonPreDownloadHandler.onPreDownload(mTag);
        }
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(URL... params) {
        Log.d("doInBackground", "Running");
        if (params.length > 0) {
            URL url = params[0];
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) { /* Good! */
                    InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
                    if(jsonDownloadHandler != null) {
                        jsonDownloadHandler.onDownload(reader, mTag);
                    }

                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
                if (bufferedReader != null)
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if(jsonPostDownloadHandler != null) {
            jsonPostDownloadHandler.onPostDownload(jsonObject, mTag);
        }
        super.onPostExecute(jsonObject);
    }
}
