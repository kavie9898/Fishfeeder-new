package com.fishfeeder_new;

/*
 * Created by Kavie on 8/28/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.fishfeeder_new.user.JSONParser;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fishfeeder_new.user.HttpParse;
import android.view.View;

import static com.fishfeeder_new.ServiceHandler.response;


public class Homepage extends AppCompatActivity implements View.OnClickListener {
    Button btn_userpage;

    Button btn_managefeed;
    Button btn_sendf;
    Button btn_viewf;
    Button btn_images;
    Button btn_sign_out;

    EditText editTimer;
    Button refresh;

    TextView feed_level,feed_status,water_level,water_status,timeleft, settime;
    private static final String TAG_ID = "feedlevel";
    private static final String TAG_ID2 = "waterlevel";
    private static final String TAG_FLEVEL = "feedlvl";
    private static final String TAG_FSTATUS = "feedstatus";
    private static final String TAG_WLEVEL = "waterlvl";
    private static final String TAG_WSTATUS = "waterstatus";

    private static final String TAG_ID3 = "currenttime";
    private static final String TAG_SETTIME = "settime";
    private static final String TAG_TIMELEFT = "timeleft";

    JSONArray feedlevel = null;
    JSONArray waterlevel = null;
    JSONArray currenttime = null;



    // private static String url = "http://192.168.0.130/android/current_feedlevel.php";
    // private static String url = "http://192.168.0.125/android/current_feedlevel.php";
    private static String url = "http://10.0.2.2/android/current_feedlevel.php";

    // private static String url2 = "http://192.168.0.130/android/current_waterlevel.php";
    // private static String url2 = "http://192.168.0.125/android/current_waterlevel.php";
    private static String url2 = "http://10.0.2.2/android/current_waterlevel.php";

    // private static String url3 = "http://192.168.0.130/android/timeinfo.php";
    // private static String url3 = "http://192.168.0.125/android/timeinfo.php";
    private static String url3 = "http://10.0.2.2/android/timeinfo.php";




    private CardView btnprofile, btnmanage, btnfeedback, btnviewfeedback,btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        new JSONParse().execute();
        new JSONParse2().execute();
        new JSONParse3().execute();

        btnprofile = (CardView) findViewById(R.id.btn_profile);
        btnmanage = (CardView) findViewById(R.id.btn_manage);
        btnfeedback = (CardView) findViewById(R.id.btn_feedback);
        btnviewfeedback = (CardView) findViewById(R.id.btn_viewfeedback);
        btnlogout = (CardView) findViewById(R.id.btn_logout);

        editTimer=(EditText)findViewById(R.id.editTimer);

        btnprofile.setOnClickListener(this);
        btnmanage.setOnClickListener(this);
        btnfeedback.setOnClickListener(this);
        btnviewfeedback.setOnClickListener(this);
        btnlogout.setOnClickListener(this);

       /* Button btn_logout= (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
                startActivity(new Intent(Homepage.this, login.class));
            }
        });*/



       /* btn_managefeed = (Button) findViewById(R.id.btn_manage);
        btn_managefeed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(Homepage.this, FeedLevelActivity.class));
            }
        });
        btn_sendf= (Button) findViewById(R.id.btn_feedback);
        btn_viewf= (Button) findViewById(R.id.btn_viewfeedback);

        btn_sign_out= (Button) findViewById(R.id.btn_logout);

        Button getfeedback = (Button) findViewById(R.id.btn_viewfeedback);
        getfeedback.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Homepage.this, ViewFeedback.class));
            }
        });*/

        refresh = (Button)findViewById(R.id.btn_get);
        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new JSONParse().execute();
                new JSONParse2().execute();
                new JSONParse3().execute();

            }
        });

    }
    public void update_timer(View view){
        String str_time = editTimer.getText().toString();
        String type = "updatetime";
        com.fishfeeder_new.Background background = new Background(this);
        background.execute(type, str_time);
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            feed_level = (TextView)findViewById(R.id.feed_level);
            feed_status = (TextView)findViewById(R.id.feed_status);

            pDialog = new ProgressDialog(Homepage.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                feedlevel = json.getJSONArray(TAG_ID);
                JSONObject c = feedlevel.getJSONObject(0);


                // Storing  JSON item in a Variable
               String feedlevel = c.getString(TAG_FLEVEL);
               String feedstatus = c.getString(TAG_FSTATUS);


                //Set JSON Data in TextView
                feed_level.setText(feedlevel);
                feed_status.setText(feedstatus);





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private class JSONParse2 extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            water_level = (TextView)findViewById(R.id.water_level);
            water_status = (TextView)findViewById(R.id.water_status);
            pDialog = new ProgressDialog(Homepage.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONObject json1 = jParser.getJSONFromUrl(url2);
            return json1;
        }
        @Override
        protected void onPostExecute(JSONObject json1) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                waterlevel = json1.getJSONArray(TAG_ID2);
                JSONObject d = waterlevel.getJSONObject(0);
                String waterlevel = d.getString(TAG_WLEVEL);
                String waterstatus = d.getString(TAG_WSTATUS);

                water_level.setText(waterlevel);
                water_status.setText(waterstatus);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private class JSONParse3 extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            settime = (EditText)findViewById(R.id.editTimer);
            //settime = (TextView) findViewById(R.id.settim);
            timeleft = (TextView)findViewById(R.id.timeleft);
            pDialog = new ProgressDialog(Homepage.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONObject json2 = jParser.getJSONFromUrl(url3);
            return json2;
        }
        @Override
        protected void onPostExecute(JSONObject json2) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                currenttime = json2.getJSONArray(TAG_ID3);
                JSONObject d = currenttime.getJSONObject(0);
                String currentset = d.getString(TAG_SETTIME);
                long currentleft = d.getLong(TAG_TIMELEFT);

                int hours = (int) currentleft/ 3600;
                int temp = (int) currentleft- hours * 3600;
                int mins = temp / 60;
                temp = temp - mins * 60;
                int secs = temp;
                settime.setText(currentset);
                String time = hours+ ": "+mins+": "+secs;
                timeleft.setText(time);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {
    Intent i;

    switch (v.getId()){
        case R.id.btn_profile : i = new Intent(this,UserProfile.class);startActivity(i); break;
        case R.id.btn_manage : i = new Intent(this,FeedLevelActivity.class);startActivity(i); break;
        case R.id.btn_feedback : i = new Intent(this,feedback.class);startActivity(i); break;
        case R.id.btn_viewfeedback : i = new Intent(this,ViewFeedback.class);startActivity(i); break;
        case R.id.btn_logout : i = new Intent(this,login.class);
        finish();
        startActivity(i); break;
        default:break;
    }
    }


    /*public void FeedLevelActivity(View v)
    {
        Intent i = new Intent(this,FeedLevelActivity.class);
        startActivity(i);
    }

    public void FeedbackActivity(View v)
    {
        Intent i = new Intent(this,feedback.class);
        startActivity(i);
    }*/


}
