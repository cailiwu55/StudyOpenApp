package com.blephone.test;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class RobolecticTest {

    @Test
    public void testActivity(){
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        Button button = activity.findViewById(R.id.button);
        TextView result = activity.findViewById(R.id.result);
        button.performClick();

        assertEquals(result.getText().toString(),"robolectric test");
    }
}
