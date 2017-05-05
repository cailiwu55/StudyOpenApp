package test.studyopenapp;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by clw on 2017/4/28.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoUnitTest {
    private static final String appName = "hello world!";
    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContenxt() {
        //当调用context.getString时模拟返回设定值
        when(mMockContext.getString(R.string.app_name)).thenReturn(appName);
        Demo demo = new Demo(mMockContext);
        String result = demo.getString();
        assertThat(result, is(appName));
    }

    class Demo {
        private Context mContext;

        public Demo(Context context) {
            mContext = context;
        }

        public String getString() {
            return mContext.getString(R.string.app_name);
        }
    }
}


