package com.fishfeeder_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fishfeeder_new.user.HttpParse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class AdminShowFeedback extends AppCompatActivity {
    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;

    // Http Url For Filter User Data from Id Sent from previous activity.
    //String HttpURL = "http://192.168.0.130/android/crud_feedback/FilterFeedbackData.php";
    //String HttpURL = "http://192.168.0.125/android/crud_feedback/FilterFeedbackData.php";
    String HttpURL = "http://10.0.2.2/android/crud_feedback/FilterFeedbackData.php";

    // Http URL for delete Already Open User Record.
    //String HttpUrlDeleteRecord = "http://192.168.0.130/android/crud_feedback/DeleteFeedback.php";
    //String HttpUrlDeleteRecord = "http://192.168.0.125/android/crud_feedback/DeleteFeedback.php";
    String HttpUrlDeleteRecord = "http://10.0.2.2/android/crud_feedback/DeleteFeedback.php";

    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();
    String FinalJSonObject ;
    TextView EMAIL,NAME,FEEBACK, TOPIC,DETAILS,DATE,REPLY;
    String EmailHolder, NameHolder, FeedbackHolder, TopicHolder,DetailsHolder,DateHolder,ReplyHolder;
    Button UpdateButton, DeleteButton;
    String TempItem;
    ProgressDialog progressDialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_feedback);
        EMAIL = (TextView)findViewById(R.id.textEmail);
        NAME = (TextView)findViewById(R.id.textName);
        FEEBACK = (TextView)findViewById(R.id.textFeedback);
        TOPIC = (TextView)findViewById(R.id.textTopic);
        DETAILS = (TextView)findViewById(R.id.textDetails);
        DATE = (TextView)findViewById(R.id.textDate);
        REPLY = (TextView)findViewById(R.id.textReply);

        UpdateButton = (Button)findViewById(R.id.buttonUpdate);
        DeleteButton = (Button)findViewById(R.id.buttonDelete);

        //Receiving the ListView Clicked item value send by previous activity.
        TempItem = getIntent().getStringExtra("ListViewValue");

        //Calling method to filter User Record and open selected record.
        HttpWebCall(TempItem);


        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminShowFeedback.this,AdminUpdateFeedback.class);

                // Sending User Id, Name, Number and Class to next UpdateActivity.
                intent.putExtra("Id", TempItem);
                intent.putExtra("Email", EmailHolder);
                intent.putExtra("Name", NameHolder);
                intent.putExtra("Feedback", FeedbackHolder);
                intent.putExtra("Topic", TopicHolder);
                intent.putExtra("Details", DetailsHolder);
                intent.putExtra("Date", DateHolder);
                intent.putExtra("Reply", ReplyHolder);

                startActivity(intent);

                // Finishing current activity after opening next activity.
                finish();

            }
        });

        // Add Click listener on Delete button.
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling User delete method to delete current record using User ID.
                StudentDelete(TempItem);

            }
        });

    }

    // Method to Delete User Record
    public void StudentDelete(final String StudentID) {

        class StudentDeleteClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog2 = ProgressDialog.show(AdminShowFeedback.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog2.dismiss();

                Toast.makeText(AdminShowFeedback.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

                finish();

            }

            @Override
            protected String doInBackground(String... params) {

                // Sending STUDENT id.
                hashMap.put("id", params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpUrlDeleteRecord);

                return finalResult;
            }
        }

        StudentDeleteClass studentDeleteClass = new StudentDeleteClass();

        studentDeleteClass.execute(StudentID);
    }


    //Method to show current record Current Selected Record
    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(AdminShowFeedback.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(AdminShowFeedback.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("id",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURL);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }


    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            try
            {
                if(FinalJSonObject != null)
                {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);

                            // Storing User Name, Phone Number, Class into Variables.
                            EmailHolder = jsonObject.getString("email").toString() ;
                            NameHolder = jsonObject.getString("name").toString() ;
                            FeedbackHolder = jsonObject.getString("feedback").toString() ;
                            TopicHolder = jsonObject.getString("topic").toString() ;
                            DetailsHolder = jsonObject.getString("details").toString() ;
                            DateHolder = jsonObject.getString("date").toString() ;
                            ReplyHolder = jsonObject.getString("reply").toString() ;

                        }
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {

            // Setting User Name, email,feedback,topic,details,date,reply Class into TextView after done all process .
            EMAIL.setText(EmailHolder);
            NAME.setText(NameHolder);
            FEEBACK.setText(FeedbackHolder);
            TOPIC.setText(TopicHolder);
            DETAILS.setText(DetailsHolder);
            DATE.setText(DateHolder);
            REPLY.setText(ReplyHolder);

        }
    }

}
