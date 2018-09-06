package com.fishfeeder_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fishfeeder_new.user.HttpParse;

import java.util.HashMap;

public class AdminUpdateFeedback extends AppCompatActivity {
    //String HttpURL = "http://192.168.0.130/android/crud_feedback/UpdateFeedback.php";
    //String HttpURL = "http://192.168.0.125/android/crud_feedback/UpdateFeedback.php";
    String HttpURL = "http://10.0.2.2/android/crud_feedback/UpdateFeedback.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText UserName, FeedbackDetails, FeedbackReply;
    Button UpdateFeedback;
    String IdHolder, NameHolder, FeedbackHolder, ReplyHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_feedback);
        UserName = (EditText)findViewById(R.id.editName);
        FeedbackDetails = (EditText)findViewById(R.id.editDetails);
        FeedbackReply = (EditText)findViewById(R.id.editReply);

        UpdateFeedback = (Button)findViewById(R.id.UpdateButton);

        // Receive User ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
        IdHolder = getIntent().getStringExtra("Id");
        NameHolder = getIntent().getStringExtra("Name");
        FeedbackHolder = getIntent().getStringExtra("Details");
        ReplyHolder = getIntent().getStringExtra("Reply");

        // Setting Received User Name, Phone Number, Class into EditText.
        UserName.setText(NameHolder);
        FeedbackDetails.setText(FeedbackHolder);
        FeedbackReply.setText(ReplyHolder);

        // Adding click listener to update button .
        UpdateFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
                GetDataFromEditText();

                // Sending User Name, Phone Number, Class to method to update on server.
                StudentRecordUpdate(IdHolder,NameHolder,FeedbackHolder, ReplyHolder);

            }
        });


    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){

        NameHolder = UserName.getText().toString();
        FeedbackHolder = FeedbackDetails.getText().toString();
        ReplyHolder = FeedbackReply.getText().toString();

    }

    // Method to Update User Record.
    public void StudentRecordUpdate(final String ID, final String F_Name, final String F_Details, final String F_Reply){

        class FeedbackRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(AdminUpdateFeedback.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(AdminUpdateFeedback.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("id",params[0]);
                hashMap.put("name",params[1]);
                hashMap.put("details",params[2]);
                hashMap.put("reply",params[3]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        FeedbackRecordUpdateClass feedbackRecordUpdateClass = new FeedbackRecordUpdateClass();

        feedbackRecordUpdateClass.execute(ID,F_Name,F_Details,F_Reply);
    }
}