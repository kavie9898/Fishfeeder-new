package com.fishfeeder_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminLogin extends AppCompatActivity {

    EditText Username, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Username = (EditText)findViewById(R.id.et_admin_name);
        Password = (EditText)findViewById(R.id.et_admin_password);
    }

    public void OnLogin(View view) {
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        String type = "admin";
        com.fishfeeder_new.AdminBackground adminBackground = new AdminBackground(this);
        adminBackground.execute(type, username, password);
    }
}
