package test.md;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mTextView;
    private Button mButton;
    private TextInputLayout mTextInputLayout;
    private EditText mEditText;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.txt);
        mButton = findViewById(R.id.btn);
        mTextInputLayout = findViewById(R.id.text_input_layout);
        mEditText = mTextInputLayout.getEditText();
        mFloatingActionButton = findViewById(R.id.fab);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar();
            }
        });
        mTextInputLayout.setHint("Password");
        mTextInputLayout.setPasswordVisibilityToggleEnabled(true);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, String.format("beforeTextChanged: s=%s,start=%d,count=%d,after=%d", s.toString(), start,
                        count, after));
                if (s.length() <= 4) {
                    mTextInputLayout.setError("password is short!");
                    mTextInputLayout.setErrorEnabled(true);
                } else {
                    mTextInputLayout.setErrorEnabled(false);
                }
                if (s.length() > 10) {
                    mTextInputLayout.setHint("Password 10");
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "FloatingActionBar clicked.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void snackbar() {
        Snackbar.make(mTextView, "snackbar coming...", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.parseColor("#FF0000"))
                .setAction("Click", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "toast coming....", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
