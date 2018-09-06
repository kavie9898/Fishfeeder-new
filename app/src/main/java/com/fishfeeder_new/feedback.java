package com.fishfeeder_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class feedback extends AppCompatActivity {

    EditText et_email,et_feedback,et_topic,et_details;
    Button bt_feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        et_email=(EditText)findViewById(R.id.et_email);
        et_topic=(EditText)findViewById(R.id.et_topic);
        et_feedback=(EditText)findViewById(R.id.et_feedback);
        et_details=(EditText)findViewById(R.id.et_details);
    }

    public void send_feedback(View view){
        String str_email = et_email.getText().toString();
        String str_topic = et_topic.getText().toString();
        String str_feedback = et_feedback.getText().toString();
        String str_details = et_details.getText().toString();

        String type = "feedback";
        com.fishfeeder_new.Background background = new Background(this);
        background.execute(type, str_email, str_topic, str_feedback, str_details);
    }
}
