package com.example.magiccouchdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();// 隐藏标题栏ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_start);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);


        Intent intent = new Intent(this,LoginActivity.class);
        Timer timer = new Timer();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent); //执行
            }
        };
        timer.schedule(task, 1000*2); //2秒后自动跳转*/
    }
}