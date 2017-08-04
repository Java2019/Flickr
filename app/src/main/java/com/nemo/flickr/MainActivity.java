package com.nemo.flickr;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.hintdesk.core.utils.JSONHttpClient;
import com.nemo.flickr.flickrutil.JSONObject.PhotosJSON;
import com.nemo.flickr.flickrutil.Size;

public class MainActivity extends AppCompatActivity {

    public static final String flickr_photosets_getPhotos = "https://www.flickr.com/services/rest/?method=flickr.photosets.getPhotos&format=json&nojsoncallback=1&api_key=73fc40a69463a80783a192152e174078&photoset_id=72157623480041879";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadImagesFromFlickrTask loadImagesFromFlickrTask = new LoadImagesFromFlickrTask();
        loadImagesFromFlickrTask.execute();

    }

    class LoadImagesFromFlickrTask extends AsyncTask<Void, Void, Bitmap> {
        private ProgressDialog progressDialog;
        private Integer totalCount, currentIndex;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //progressDialog.setMessage(String.format("Loading images from Flickr %s/%s. Please wait...", values[0], values[1]));
        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String resultJson = "";
            String linkPhoto = "";
            Bitmap bitmap = null;

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
            try{
            JSONObject json = new JSONObject(resultJson);
            JSONObject photos = json.getJSONObject("photoset");
            JSONArray photo = photos.getJSONArray("photo");
            if (photo.length() > 0) {

                JSONObject first = photo.getJSONObject(0);

                String FARMID = first.getString("farm");
                String SERVERID = first.getString("server");
                String SECRET = first.getString("secret");
                String ID = first.getString("id");

                StringBuilder sb = new StringBuilder();

                sb.append("http://farm");
                sb.append(FARMID);
                sb.append(".static.flickr.com/");
                sb.append(SERVERID);
                sb.append("/");
                sb.append(ID);
                sb.append("_");
                sb.append(SECRET);
                sb.append("_m");
                sb.append(".jpg");

                linkPhoto = sb.toString();

                bitmap = BitmapFactory.decodeStream((InputStream)new URL(linkPhoto).getContent());

            } } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            ImageView i = (ImageView)findViewById(R.id.image);
            i.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
