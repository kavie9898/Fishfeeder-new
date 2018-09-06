package com.fishfeeder_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import com.fishfeeder_new.m_MySQL.Downloader;




public class Images extends AppCompatActivity {

    //final static String urlAddress="http://192.168.0.125/android/images.php"; //ori
    final static String urlAddress="http://10.0.2.2/android/images.php";
    //final static String urlAddress="http://192.168.0.130/android/images.php";
    // final static String urlAddress="http://10.112.1.154/android/images.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final ListView lv= (ListView) findViewById(R.id.lv);
        new Downloader(Images.this,urlAddress,lv).execute();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Downloader(Images.this,urlAddress,lv).execute();
            }
        });
    }


}