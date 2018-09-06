package com.fishfeeder_new;
/*
 * Created by Kavie on 8/28/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

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
                startActivity(new Intent(login.this, register.class));
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
}
