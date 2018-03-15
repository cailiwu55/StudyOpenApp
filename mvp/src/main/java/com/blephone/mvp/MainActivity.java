package com.blephone.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.blephone.mvp.contract.MainContract;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private EditText mAccountView;
    private EditText mPwdView;
    private Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAccountView = findViewById(R.id.account_text);
        mPwdView = findViewById(R.id.password_text);
        mSubmitBtn = findViewById(R.id.submit_btn);
    }
}
