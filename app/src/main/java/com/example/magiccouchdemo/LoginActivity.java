package com.example.magiccouchdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.magiccouchdemo.ui.dashboard.DashboardFragment;

public class LoginActivity extends AppCompatActivity {

    private TextView skip_txt;
    private TextView register_txt;
    private Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        skip_txt = (TextView)findViewById(R.id.skip);
        skip_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_navigation = new Intent(LoginActivity.this, NavigationActivity.class);
                startActivity(intent_navigation);
            }
        });
        register_txt = (TextView)findViewById(R.id.register);
        register_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent_register);
            }
        });
        login_btn = (Button)findViewById(R.id.login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_navigation = new Intent(LoginActivity.this, NavigationActivity.class);
                startActivity(intent_navigation);
            }
        });
    }

}