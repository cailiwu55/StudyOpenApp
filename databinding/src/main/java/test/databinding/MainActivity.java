package test.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import test.databinding.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        User user = new User("clw","123456");
        binding.setUser(user);
        binding.setHandlers(new MyHandlers());


    }
}
