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
public class AdminUpdateUser extends AppCompatActivity {
    //String HttpURL = "http://192.168.0.130/android/crud_user/UpdateUser.php";
    //String HttpURL = "http://192.168.0.125/android/crud_user/UpdateUser.php";
    String HttpURL = "http://10.0.2.2/android/crud_user/UpdateUser.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText UserEmail, UserName, UserStatus;
    Button UpdateUser;
    String IdHolder, EmailHolder, NameHolder, StatusHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_user);
        UserEmail = (EditText)findViewById(R.id.editEmail);
        UserName = (EditText)findViewById(R.id.editName);
        UserStatus = (EditText)findViewById(R.id.editStatus);

        UpdateUser = (Button)findViewById(R.id.UpdateButton);

        // Receive User ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
        IdHolder = getIntent().getStringExtra("Id");
        EmailHolder = getIntent().getStringExtra("Email");
        NameHolder = getIntent().getStringExtra("Name");
        StatusHolder = getIntent().getStringExtra("Status");

        // Setting Received User Name, Phone Number, Class into EditText.
        UserEmail.setText(EmailHolder);
        UserName.setText(NameHolder);
        UserStatus.setText(StatusHolder);

        // Adding click listener to update button .
        UpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
                GetDataFromEditText();

                // Sending User Name, Phone Number, Class to method to update on server.
                StudentRecordUpdate(IdHolder,EmailHolder,NameHolder, StatusHolder);

            }
        });


    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){

        EmailHolder = UserEmail.getText().toString();
        NameHolder = UserName.getText().toString();
        StatusHolder = UserStatus.getText().toString();

    }

    // Method to Update User Record.
    public void StudentRecordUpdate(final String ID, final String U_Email, final String U_Name, final String U_Status){

        class UserRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(AdminUpdateUser.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(AdminUpdateUser.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("id",params[0]);

                hashMap.put("email",params[1]);

                hashMap.put("name",params[2]);

                hashMap.put("status",params[3]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRecordUpdateClass userRecordUpdateClass = new UserRecordUpdateClass();

        userRecordUpdateClass.execute(ID,U_Email,U_Name,U_Status);
    }
}