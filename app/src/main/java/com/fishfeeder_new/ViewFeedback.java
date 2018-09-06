package com.fishfeeder_new;

//import android.support.v7.app.AppCompatActivity;
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

public class ViewFeedback extends ListActivity {

    //private String URL_ITEMS = "http://192.168.0.130/android/getFeedback.php";
    //private String URL_ITEMS = "http://192.168.0.125/android/getFeedback.php"; //ori
    private String URL_ITEMS = "http://10.0.2.2/android/getFeedback.php";
   // private String URL_ITEMS = "http://10.112.1.154/android/getFeedback.php";
    private static final String TAG_FIXTURE = "feedback";
    private static final String TAG_USER = "email";
    private static final String TAG_STATUS = "name";
    private static final String TAG_LEVEL = "feedback";
    private static final String TAG_DATE = "topic";
    private static final String TAG_TIME = "details";
    private static final String TAG_REPLY = "reply";
    private static final String TAG_DTIME = "date";

    JSONArray matchFixture = null;
    ArrayList<HashMap<String, String>> matchFixtureList = new ArrayList<HashMap<String, String>>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        // Call Async task to get the match fixture
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
                        String email = c.getString(TAG_USER);
                        Log.d("email", email);
                        String name = c.getString(TAG_STATUS);
                        Log.d("name", name);
                        String feedback = c.getString(TAG_LEVEL);
                        Log.d("feedback", feedback);
                        String topic = c.getString(TAG_DATE);
                        Log.d("topic", topic);
                        String details = c.getString(TAG_TIME);
                        Log.d("details", details);
                        String reply = c.getString(TAG_REPLY);
                        Log.d("details", details);
                        String date = c.getString(TAG_DTIME);
                        Log.d("details", details);
                        //  hashmap for single match
                        HashMap<String, String> matchFixture = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        matchFixture.put(TAG_USER, email);
                        matchFixture.put(TAG_STATUS, name);
                        matchFixture.put(TAG_LEVEL, feedback);
                        matchFixture.put(TAG_DATE, topic);
                        matchFixture.put(TAG_TIME, details);
                        matchFixture.put(TAG_REPLY, reply);
                        matchFixture.put(TAG_DTIME, date);
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
                    ViewFeedback.this, matchFixtureList,
                    R.layout.recyclerview_feedback, new String[] {
                    TAG_USER, TAG_STATUS,TAG_LEVEL,TAG_DATE,TAG_TIME,TAG_REPLY,TAG_DTIME
            }
                    , new int[] {
                    R.id.email,R.id.name,R.id.feedback,R.id.topic,R.id.details,R.id.reply,R.id.date
            }
            );
            setListAdapter(adapter);
        }
    }
}
