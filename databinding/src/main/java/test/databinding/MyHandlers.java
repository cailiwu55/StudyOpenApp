package test.databinding;

import android.view.View;
import android.widget.Toast;

/**
 * Created by clw on 2017/7/6.
 */

public class MyHandlers {

    public void onClickFriend(View view) {
        System.out.println("onClickFriend view:" + view);
        Toast.makeText(view.getContext(), "onClickFriend", Toast.LENGTH_SHORT).show();
    }
}
