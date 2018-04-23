package com.blephone.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(Parameterized.class)
public class JunitTest {

    private String a;

    public JunitTest(String a) {
        this.a = a;
    }

    @Parameters
    public static Collection<String> data() {
        //return Arrays.asList(new String[]{"1", "2", null});
        return Arrays.asList(new String[]{"1", "2", "3"});
    }

    @Rule
    public JunitRule mJunitRule = new JunitRule();

    //@Before
    //public void setUp() {
    //    System.out.println("测试开始");
    //}
    //
    //@After
    //public void tearDown() {
    //    System.out.println("测试结束");
    //}

    @Test
    public void print() {
        assertNotNull(a);

        assertThat(a, IsNull.notNullValue());
    }

    //@Test(expected = ParseException.class)
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        assertNotEquals(2, 2 - 1);

        assertArrayEquals(new int[]{1, 2}, new int[]{1, 2});

        String a = null;
        assertNull(a);

        a = "hello world";
        assertNotNull(a);

        boolean b = true;
        assertTrue(b);

        assertFalse(!b);

        assertSame(b, b);

        assertNotSame(a, b);

        assertThat(b, CoreMatchers.not(false));

        assertThat(a, CoreMatchers.both(CoreMatchers.startsWith("hello")).and(CoreMatchers.endsWith("world")));

        assertThat(a, CoreMatchers.allOf(CoreMatchers.startsWith("hello"), CoreMatchers.endsWith("world")));
        assertThat(a, CoreMatchers.anyOf(CoreMatchers.startsWith("Hello"), CoreMatchers.endsWith("world")));
    }
}