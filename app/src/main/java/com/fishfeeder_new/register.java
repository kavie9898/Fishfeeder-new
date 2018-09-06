package com.fishfeeder_new;

/*
 * Created by Kavie on 8/28/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.widget.Button;
import android.widget.EditText;


public class register extends AppCompatActivity {
    EditText et_email;
    EditText et_password;
    EditText et_name;
    EditText et_phone;
    EditText et_question;
    EditText et_answer;
    Button btn_register;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_email=(EditText)findViewById(R.id.et_email);
        et_name=(EditText)findViewById(R.id.et_name);
        et_password=(EditText)findViewById(R.id.et_password);
        et_phone=(EditText)findViewById(R.id.et_phone);
        et_question=(EditText)findViewById(R.id.et_question);
        et_answer=(EditText)findViewById(R.id.et_answer);
        btn_register= (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        Button btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(register.this, login.class));
            }
        });
    }



    private void registerUser(){
        String email=et_email.getText().toString().trim().toLowerCase();
        String password=et_password.getText().toString().trim().toLowerCase();
        String name=et_name.getText().toString().trim().toLowerCase();
        String phone=et_phone.getText().toString().trim().toLowerCase();
        String question=et_question.getText().toString().trim().toLowerCase();
        String answer=et_answer.getText().toString().trim().toLowerCase();
        register(email, password, name, phone, question, answer);
    }

    //private static final String REGISTER_URL="http://192.168.0.130/android/register.php";
     //private static final String REGISTER_URL="http://192.168.0.125/android/register.php";
     private static final String REGISTER_URL="http://10.0.2.2/android/register.php";
   // private static final String REGISTER_URL="http://192.168.0.130/android/register.php";

    private void register(String email, String password, String name, String phone, String question, String answer){
        String urlSuffix = "?email=" + email + "&password=" + password + "&name=" + name + "&phone=" + phone + "&question=" + question + "&answer=" + answer;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;
            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader=null;
                try {
                    URL url=new URL(REGISTER_URL+s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    bufferedReader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result = bufferedReader.readLine();
                    return result;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading=ProgressDialog.show(register.this,"Please Wait",null,true,true);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),"Registered", Toast.LENGTH_LONG).show();
            }



        }
        RegisterUser ur=new RegisterUser();
        ur.execute(urlSuffix);

    }


}