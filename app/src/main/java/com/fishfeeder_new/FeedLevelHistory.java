package com.fishfeeder_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;


public class FeedLevelHistory extends ListActivity {

    //private String URL_ITEMS = "http://192.168.0.130/android/getFeedLevelh.php";
    //private String URL_ITEMS = "http://192.168.0.125/android/getFeedLevelh.php"; //original
    private String URL_ITEMS = "http://10.0.2.2/android/getFeedLevelh.php";
    //  private String URL_ITEMS = "http://192.168.0.125/android/getFeedLevelh.php";
    //private String URL_ITEMS = "http://10.0.2.2/getFeedLevelh.php";
    private static final String TAG_FIXTURE = "feedlevel";
    private static final String TAG_USER = "user";
    private static final String TAG_STATUS = "status";
    private static final String TAG_LEVEL = "level";
    private static final String TAG_DATE = "date";
    private static final String TAG_TIME = "time";
    JSONArray matchFixture = null;
    ArrayList<HashMap<String, String>> matchFixtureList = new ArrayList<HashMap<String, String>>();
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_level_history);
        new GetFixture().execute();
    }
    private class GetFixture extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... arg) {
            ServiceHandler serviceClient = new ServiceHandler();
            Log.d("url: ", "> " + URL_ITEMS);
            String json = serviceClient.makeServiceCall(URL_ITEMS,ServiceHandler.GET);
            // print the json response in the log
            //Log.d("Get match fixture response: ", "> " + json);
            if (json != null) {
                try {
                    Log.d("try", "in the try");
                    JSONObject jsonObj = new JSONObject(json);
                    Log.d("jsonObject", "new json Object");
                    // Getting JSON Array node
                    matchFixture = jsonObj.getJSONArray(TAG_FIXTURE);
                    Log.d("json aray", "user point array");
                    int len = matchFixture.length();
                    Log.d("len", "get array length");
                    for (int i = 0; i < matchFixture.length(); i++) {
                        JSONObject c = matchFixture.getJSONObject(i);
                        String user = c.getString(TAG_USER);
                        Log.d("user", user);
                        String status = c.getString(TAG_STATUS);
                        Log.d("status", status);
                        String level = c.getString(TAG_LEVEL);
                        Log.d("level", level);
                        String date = c.getString(TAG_DATE);
                        Log.d("date", date);
                        String time = c.getString(TAG_TIME);
                        Log.d("time", time);
                        //  hashmap for single match
                        HashMap<String, String> matchFixture = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        matchFixture.put(TAG_USER, user);
                        matchFixture.put(TAG_STATUS, status);
                        matchFixture.put(TAG_LEVEL, level);
                        matchFixture.put(TAG_DATE, date);
                        matchFixture.put(TAG_TIME, time);
                        matchFixtureList.add(matchFixture);
                    }
                }
                catch (JSONException e) {
                    Log.d("catch", "in the catch");
                    e.printStackTrace();
                }
            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(
                    FeedLevelHistory.this, matchFixtureList,
                    R.layout.recyclerview_history, new String[] {
                    TAG_USER, TAG_STATUS,TAG_LEVEL,TAG_DATE,TAG_TIME
            }
                    , new int[] {
                    R.id.user,R.id.status,R.id.level,R.id.date,R.id.time
            }
            );
            setListAdapter(adapter);
        }
    }
}
