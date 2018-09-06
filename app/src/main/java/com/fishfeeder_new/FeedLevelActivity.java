package com.fishfeeder_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.net.Uri;

public class FeedLevelActivity extends AppCompatActivity implements View.OnClickListener {


    private CardView btnfeedlevel, btnwaterlevel, btnfeedhistory, btnimage, btnfeednow, btncaptureimage, btncheckfeedlevel, btncheckwaterlevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_level);


        btnfeedlevel = (CardView) findViewById(R.id.btn_feedlevel);
        btnwaterlevel = (CardView) findViewById(R.id.btn_waterlevel);
        btnfeedhistory = (CardView) findViewById(R.id.btn_feedhistory);
        btnimage = (CardView) findViewById(R.id.btn_image);
        btnfeednow = (CardView) findViewById(R.id.btn_feednow);
        btncaptureimage = (CardView) findViewById(R.id.btn_captureimage);
        btncheckfeedlevel = (CardView) findViewById(R.id.btn_checkfeed);
        btncheckwaterlevel = (CardView) findViewById(R.id.btn_checkwater);

        btnfeedlevel.setOnClickListener(this);
        btnwaterlevel.setOnClickListener(this);
        btnfeedhistory.setOnClickListener(this);
        btnimage.setOnClickListener(this);
        btnfeednow.setOnClickListener(this);
        btncaptureimage.setOnClickListener(this);
        btncheckfeedlevel.setOnClickListener(this);
        btncheckwaterlevel.setOnClickListener(this);


 /*       Button WaterLevelHistory = (Button) findViewById(R.id.bt_water_level);
        WaterLevelHistory.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                startActivity(new Intent(FeedLevelActivity.this, WaterLevelHistory.class));
            }

        });

        Button FeedLevelHistory = (Button) findViewById(R.id.bt_feed_level);
        FeedLevelHistory.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                startActivity(new Intent(FeedLevelActivity.this,FeedLevelHistory.class));
            }

        });

        Button Images = (Button) findViewById(R.id.bt_image);
        Images.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                startActivity(new Intent(FeedLevelActivity.this,Images.class));
            }

        });

        Button feed = (Button) findViewById(R.id.feed_now);
        feed.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 String url = "http://192.168.0.130/fyp/feed.php";
               //  String url = "http://192.168.0.125/fyp/feed.php";
               // String url = "http://10.0.2.2/feed.php";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button waterlevel = (Button) findViewById(R.id.bt_checkwater);
        waterlevel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = "http://192.168.0.130/fyp/waterlevel.php";
               // String url = "http://192.168.0.125/fyp/waterlevel.php";
               // String url = "http://10.0.2.2/waterlevel.php";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button feedlevel = (Button) findViewById(R.id.bt_checkfeed);
        feedlevel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = "http://192.168.0.130/fyp/feedlevel.php";
                // String url = "http://192.168.0.125/fyp/feedlevel.php";
               // String url = "http://10.0.2.2/feedlevel.php";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button capture = (Button) findViewById(R.id.bt_capture);
        capture.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = "http://192.168.0.130/fyp/camera/capture.php";
               // String url = "http://192.168.0.125/fyp/camera/capture.php";
                // String url = "http://10.0.2.2/feedlevel.php";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        }); */
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btn_feedlevel : i = new Intent(this,FeedLevelHistory.class);startActivity(i); break;
            case R.id.btn_waterlevel : i = new Intent(this,WaterLevelHistory.class);startActivity(i); break;
            case R.id.btn_feedhistory : i = new Intent(this,ViewFeedHistory.class);startActivity(i); break;
            case R.id.btn_image : i = new Intent(this,Images.class);startActivity(i); break;
            //case R.id.btn_feednow : String url ="http://192.168.0.130/fyp/feed.php";
            case R.id.btn_feednow : String url ="http://192.168.0.125/fyp/feed.php";
                Intent a = new Intent(Intent.ACTION_VIEW);
                a.setData(Uri.parse(url));
                startActivity(a);
                break;
            //case R.id.btn_captureimage : String urlb ="http://192.168.0.130/fyp/camera/capture.php";
            //case R.id.btn_captureimage : String urlb ="http://192.168.0.125/fyp/camera/capture.php"; //ori
            case R.id.btn_captureimage : String urlb ="http://10.0.2.2/fyp/camera/capture.php";
                Intent b = new Intent(Intent.ACTION_VIEW);
                b.setData(Uri.parse(urlb));
                startActivity(b);
                break;
            //case R.id.btn_checkfeed : String urlc ="http://192.168.0.130/fyp/feedlevel.php";
            //case R.id.btn_checkfeed : String urlc ="http://192.168.0.125/fyp/feedlevel.php";
            case R.id.btn_checkfeed : String urlc ="http://10.0.2.2/fyp/feedlevel.php";
                Intent c = new Intent(Intent.ACTION_VIEW);
                c.setData(Uri.parse(urlc));
                startActivity(c);
                break;
            //case R.id.btn_checkwater : String urld ="http://192.168.0.130/fyp/waterlevel.php";
            //case R.id.btn_checkwater : String urld ="http://192.168.0.125/fyp/waterlevel.php";
            case R.id.btn_checkwater : String urld ="http://10.0.2.2/fyp/waterlevel.php";
                Intent d = new Intent(Intent.ACTION_VIEW);
                d.setData(Uri.parse(urld));
                startActivity(d);
                break;
            default:break;
        }
    }
}
