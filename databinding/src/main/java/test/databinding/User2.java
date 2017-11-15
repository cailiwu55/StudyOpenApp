package test.databinding;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by clw on 2017/7/17.
 */

public class User2 {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableInt age = new ObservableInt();
}
