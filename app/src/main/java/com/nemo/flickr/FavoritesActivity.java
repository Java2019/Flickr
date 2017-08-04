package com.nemo.flickr;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FavoritesActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_IMAGE = "image";

    private ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "FlickrImages");
        File[] filelist = folder.listFiles();

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(filelist.length);
        Map<String, Object> m;
        for (File file : filelist) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_IMAGE, file);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_IMAGE };
        int[] to = { R.id.image};

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.rowlayout, from, to);

        lvSimple = (ListView) findViewById(R.id.LView);
        lvSimple.setAdapter(sAdapter);

    }
}
