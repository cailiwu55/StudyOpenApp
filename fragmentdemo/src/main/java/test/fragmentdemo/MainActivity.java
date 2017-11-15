package test.fragmentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private Button mShowBtn, mHideBtn, mBtn, mBtn1, mBtn2;
    private View mContainer;
    private Fragment mFragment;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowBtn = (Button) findViewById(R.id.show);
        mHideBtn = (Button) findViewById(R.id.hide);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mContainer = findViewById(R.id.container);
        mManager = getSupportFragmentManager();
        mFragment = mManager.findFragmentById(R.id.fragment);
        testShowHideFragment();
        testAddFragment();

    }

    private void testShowHideFragment() {
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mManager.beginTransaction().show(mFragment).commit();
            }
        });
        mHideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mManager.beginTransaction().hide(mFragment).commit();
            }
        });
    }

    private void testAddFragment() {
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment1 = Fragment1.newInstance("fragment1");
                FragmentTransaction transaction = mManager.beginTransaction();
                transaction.add(R.id.container, fragment1, "fragment1");
                transaction.addToBackStack("fragment1");
                transaction.commit();
                int backStackCount = mManager.getBackStackEntryCount();
                Log.d(TAG, "onClick: backStackCount:" + backStackCount);
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = Fragment2.newInstance("fragment2");
                FragmentTransaction transaction = mManager.beginTransaction();
                transaction.add(R.id.container, fragment2, "fragment2");
                transaction.addToBackStack("fragment2");
                transaction.commit();
            }
        });
    }

    private void testReplaceFragment() {
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment1 = Fragment1.newInstance("fragment1");
                FragmentTransaction transaction = mManager.beginTransaction();
                transaction.replace(R.id.container, fragment1, "fragment1");
                transaction.commit();
                int backStackCount = mManager.getBackStackEntryCount();
                Log.d(TAG, "onClick: backStackCount:" + backStackCount);
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = Fragment2.newInstance("fragment2");
                FragmentTransaction transaction = mManager.beginTransaction();
                transaction.replace(R.id.container, fragment2, "fragment2");
                transaction.commit();
            }
        });
    }

}
