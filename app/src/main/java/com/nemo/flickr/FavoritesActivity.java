package com.nemo.flickr;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "FlickrImages");
        File[] filelist = folder.listFiles();

    }
}
