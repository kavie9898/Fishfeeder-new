package com.fishfeeder_new;

/*
 * Created by Kavie on 8/28/2017.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText Username, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.user_name);
        Password = (EditText)findViewById(R.id.password);

        Button register = (Button) findViewById(R.id.btn_register);


        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, register.class));
            }
        });

    }

    public void OnLogin(View view) {
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        String type = "login";
        com.fishfeeder_new.Background background = new Background(this);
        background.execute(type, username, password);
    }



        /*Button feed = (Button) findViewById(R.id.feed_now);
        feed.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String url = "http://192.168.0.130/fyp/feed.php";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });*/





    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){

        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder alertbox=new AlertDialog.Builder(MainActivity.this);
            alertbox.setTitle("Exit Confirmation");
            alertbox.setCancelable(false);
            alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertbox.show();
        }
        return super.onKeyDown(keyCode,event);
    }

    public void AdminLoginActivity(View v)
    {

        Intent i = new Intent(this,AdminLogin.class);
        startActivity(i);

    }
}


