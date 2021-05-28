package com.example.magiccouchdemo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView verify_img;//验证码图片
    private EditText verify_txt;//验证码填入框
    private Button verify_btn,verify_submit;//验证码获取按钮、提交按钮
    private String codeStr;//验证码条
    private CodeUtils codeUtils;//验证码验证类

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        verify_img = (ImageView) findViewById(R.id.verify_img);
        verify_txt = (EditText) findViewById(R.id.verify_txt);
        verify_btn  = (Button) findViewById(R.id.verify_btn);
        verify_submit = (Button) findViewById(R.id.verify_submit);
        verify_btn.setOnClickListener((View.OnClickListener) this);
        verify_submit.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verify_btn:
                codeUtils = CodeUtils.getInstance();
                Bitmap bitmap = codeUtils.createBitmap();
                verify_img.setImageBitmap(bitmap);

                break;
            case R.id.verify_submit:
                codeStr = verify_txt.getText().toString().trim();
                Log.e("codeStr", codeStr);
                if (null == codeStr || TextUtils.isEmpty(codeStr)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = codeUtils.getCode();
                Log.e("code", code);
                if (code.equalsIgnoreCase(codeStr)) {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent_navigation = new Intent(RegisterActivity.this, NavigationActivity.class);
                    startActivity(intent_navigation);
                } else {
                    Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

}
