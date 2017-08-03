package com.nemo.flickr;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.hintdesk.core.utils.JSONHttpClient;
import com.nemo.flickr.flickrutil.JSONObject.PhotosJSON;
import com.nemo.flickr.flickrutil.Size;

public class MainActivity extends AppCompatActivity {

    public static final String flickr_photosets_getPhotos = "https://www.flickr.com/services/rest/?method=flickr.photosets.getPhotos&format=json&api_key=73fc40a69463a80783a192152e174078&photoset_id=72157623480041879";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadImagesFromFlickrTask loadImagesFromFlickrTask = new LoadImagesFromFlickrTask();
        loadImagesFromFlickrTask.execute();

    }

    class LoadImagesFromFlickrTask extends AsyncTask<String, Integer, List> {
        private ProgressDialog progressDialog;
        private Integer totalCount, currentIndex;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //progressDialog.setMessage(String.format("Loading images from Flickr %s/%s. Please wait...", values[0], values[1]));
        }

        @Override
        protected List doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String resultJson = "";

            try{
                URL url = new URL(flickr_photosets_getPhotos);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            }catch (Exception e){

            }

            JSONHttpClient jsonHttpClient = new JSONHttpClient();
            PhotosJSON photosJSON  = jsonHttpClient.Get(flickr_photosets_getPhotos, new ArrayList<NameValuePair>(), PhotosJSON.class);
            List<Size>  sizes = photosJSON.getSizes().getSize();
            return new ArrayList();
        }


        protected void onPostExecute(ArrayList s) {
            super.onPostExecute(s);
        }
    }

}
