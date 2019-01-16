package com.blephone.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        final TextView result = findViewById(R.id.result);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("robolectric test");
            }
        });

    }
}
