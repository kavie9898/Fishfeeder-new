package com.fishfeeder_new;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.content.Intent;
import android.view.View;
import android.net.Uri;
import android.widget.Button;

public class AdminHomepage extends AppCompatActivity implements View.OnClickListener {


    private CardView btnuserlist, btnviewfeedback, btnfeedlevel, btnwaterlevel, btnimage, btnfeednow, btncaptureimage, btncheckfeedlevel, btncheckwaterlevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);


        btnuserlist = (CardView) findViewById(R.id.btn_userlist);
        btnviewfeedback = (CardView) findViewById(R.id.btn_viewfeedback);
        btnfeedlevel = (CardView) findViewById(R.id.btn_feedlevel);
        btnwaterlevel = (CardView) findViewById(R.id.btn_waterlevel);
        btnimage = (CardView) findViewById(R.id.btn_image);
        btnfeednow = (CardView) findViewById(R.id.btn_feednow);
        btncaptureimage = (CardView) findViewById(R.id.btn_captureimage);
        btncheckfeedlevel = (CardView) findViewById(R.id.btn_checkfeed);
        btncheckwaterlevel = (CardView) findViewById(R.id.btn_checkwater);



        btnuserlist.setOnClickListener(this);
        btnviewfeedback.setOnClickListener(this);
        btnfeedlevel.setOnClickListener(this);
        btnwaterlevel.setOnClickListener(this);
        btnimage.setOnClickListener(this);
        btnfeednow.setOnClickListener(this);
        btncaptureimage.setOnClickListener(this);
        btncheckfeedlevel.setOnClickListener(this);
        btncheckwaterlevel.setOnClickListener(this);

        CardView btnlogout= (CardView) findViewById(R.id.btn_logout);
        btnlogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
                startActivity(new Intent(AdminHomepage.this, login.class));
            }
        });
/*
        Button ManageUser = (Button) findViewById(R.id.btn_userlist);
        ManageUser.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                startActivity(new Intent(AdminHomepage.this, AdminManageUser.class));
            }

        });

        Button ManageFeedback = (Button) findViewById(R.id.btn_viewfeedback);
        ManageFeedback.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                startActivity(new Intent(AdminHomepage.this, AdminManageFeedback.class));
            }

        });*/
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){


            case R.id.btn_userlist : i = new Intent(this,AdminManageUser.class);startActivity(i); break;
            case R.id.btn_viewfeedback : i = new Intent(this,AdminManageFeedback.class);startActivity(i); break;

            case R.id.btn_feedlevel : i = new Intent(this,FeedLevelHistory.class);startActivity(i); break;
            case R.id.btn_waterlevel : i = new Intent(this,WaterLevelHistory.class);startActivity(i); break;
            case R.id.btn_image : i = new Intent(this,Images.class);startActivity(i); break;
            //case R.id.btn_feednow : String url ="http://192.168.0.130/fyp/feed.php";
            case R.id.btn_feednow : String url ="http://192.168.0.125/fyp/feed.php";
                Intent a = new Intent(Intent.ACTION_VIEW);
                a.setData(Uri.parse(url));
                startActivity(a);
                break;
            //case R.id.btn_captureimage : String urlb ="http://192.168.0.130/fyp/camera/capture.php";
            case R.id.btn_captureimage : String urlb ="http://192.168.0.125/fyp/camera/capture.php";
                Intent b = new Intent(Intent.ACTION_VIEW);
                b.setData(Uri.parse(urlb));
                startActivity(b);
                break;
            //case R.id.btn_checkfeed : String urlc ="http://192.168.0.130/fyp/feedlevel.php";
            case R.id.btn_checkfeed : String urlc ="http://192.168.0.125/fyp/feedlevel.php";
                Intent c = new Intent(Intent.ACTION_VIEW);
                c.setData(Uri.parse(urlc));
                startActivity(c);
                break;
            //case R.id.btn_checkwater : String urld ="http://192.168.0.130/fyp/waterlevel.php";
            case R.id.btn_checkwater : String urld ="http://192.168.0.125/fyp/waterlevel.php";
                Intent d = new Intent(Intent.ACTION_VIEW);
                d.setData(Uri.parse(urld));
                startActivity(d);
                break;


            default:break;
        }
    }
}

