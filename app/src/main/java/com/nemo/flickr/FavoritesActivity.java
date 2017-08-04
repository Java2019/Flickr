package com.nemo.flickr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

       /* ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> m;
        for ( int i = 0; i < filelist.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_IMAGE, filelist[i]);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_IMAGE };
        int[] to = { R.id.image};

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.rowlayout, from, to);

        lvSimple = (ListView) findViewById(R.id.LView);
        lvSimple.setAdapter(sAdapter);*/
        //Log.d("456454", filelist[1].getAbsolutePath());
        try {
            File file = new File(folder + "/test.JPG");
            Bitmap myBitmap = BitmapFactory.decodeFile(filelist[0].toString());
            ImageView imageView = (ImageView) findViewById(R.id.LView);
            imageView.setImageBitmap(myBitmap);
        }catch (Exception e){

        }
    }


}
