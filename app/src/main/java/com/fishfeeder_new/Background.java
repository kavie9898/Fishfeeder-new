package com.fishfeeder_new;

/*
 * Created by Kavie on 8/28/2017.
 */
import android.content.Intent;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.net.URL;
import android.content.DialogInterface;
import android.os.AsyncTask;
import java.util.TimerTask;
import android.os.Handler;
import java.util.Timer;
import android.app.AlertDialog;
import android.content.Context;

import java.io.BufferedWriter;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.OutputStream;

import java.net.URL;
import java.util.TimerTask;
import static android.os.SystemClock.sleep;


public class Background extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog aleartDialog;
    Background (Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        //String login_url = "http://192.168.0.125/login.php";  // raspberry pi ip address
       // String login_url = "http://192.168.0.130/android/login.php";  // localhost ip address
        String login_url = "http://10.0.2.2/android/login.php";  // localhost ip address
        //String login_url = "http://192.168.0.125/android/login.php";  // localhost ip address


       // String feedback_url = "http://192.168.0.130/android/feedback.php";
       // String feedback_url = "http://localhost/android/feedback.php";
        String feedback_url = "http://10.0.2.2/android/feedback.php";
        //String feedback_url = "http://192.168.0.125/android/feedback.php";
        if(type.equals("login"))
            try {
                String username = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            else if(type.equals("feedback")){
            try {
                String et_email = params[1];
                String et_topic = params[2];
                String et_feedback = params[3];
                String et_details = params[4];
                URL url = new URL(feedback_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(et_email,"UTF-8")+"&"
                        + URLEncoder.encode("topic","UTF-8")+"="+URLEncoder.encode(et_topic,"UTF-8")+"&"
                + URLEncoder.encode("feedback","UTF-8")+"="+URLEncoder.encode(et_feedback,"UTF-8")+"&"
                        + URLEncoder.encode("details","UTF-8")+"="+URLEncoder.encode(et_details,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;

    }


    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }

    @Override
    public void onPostExecute(final String result) { // checks if php file has similar text

        aleartDialog.setMessage(result);
        aleartDialog.show();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if (result == null)
                    {}
                    else if(result.equals("Login not successful"))
                    {
                        Intent in = new Intent(context,login.class);
                        context.startActivity(in);
                    }
                    else if(result.contains("Login successful"))
                    {
                        Intent in = new Intent(context,Homepage.class);
                        context.startActivity(in);
                }
            }
            },1000);

    }

    @Override
    public void onPreExecute() {

        aleartDialog = new AlertDialog.Builder(context).create();
        aleartDialog.setTitle("Login Status");
    }


}