package test.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.os.Bundle;
import android.os.Handler;

import test.databinding.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        User user = new User("clw", "123456");
        binding.setUser(user);
        binding.setHandlers(new MyHandlers());

        binding.setPresenter(new Presenter());

        User2 user2 = new User2();
        user2.name.set("clx");
        user2.password.set("654321");
        user2.age.set(21);
        binding.setUser2(user2);

        ObservableArrayList<Object> list = new ObservableArrayList<>();
        list.add("clw");
        list.add("123123");
        list.add(111);
        binding.setUserlist(list);

        ObservableArrayMap<String, Object> map = new ObservableArrayMap<>();
        map.put("name", "a");
        map.put("password", "b");
        map.put("age", 101);
        binding.setUsermap(map);

        changeUser(user);
    }

    private void changeUser(final User user) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setName("hhy");
                user.setPassword("xxxxxx");
            }
        }, 3000);

    }



}
