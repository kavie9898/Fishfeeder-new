package com.fishfeeder_new;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class AdminBackground extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog aleartDialog;
    AdminBackground (Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
       // String adminlogin_url = "http://192.168.0.130/android/admin.php";
        //String adminlogin_url = "http://192.168.0.125/android/admin.php";  // raspberry pi ip address
        String adminlogin_url = "http://10.0.2.2/admin.php";

        if(type.equals("admin")) {
            try {
                String username = params[1];
                String password = params[2];
                URL url = new URL(adminlogin_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
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
                    Intent in = new Intent(context,AdminLogin.class);
                    context.startActivity(in);
                }
                else if(result.contains("Login successful"))
                {
                    Intent in = new Intent(context,AdminHomepage.class);
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