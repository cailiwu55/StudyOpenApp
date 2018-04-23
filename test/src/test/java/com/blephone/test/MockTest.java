package com.blephone.test;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by clw on 2018/4/3.
 */

//创建方法3
//@RunWith(MockitoJUnitRunner.class)
public class MockTest {

    //创建方法4
    //@Rule
    //public MockitoRule mMockitoRule = MockitoJUnit.rule();

    //创建方法2
    //@Mock
    IDemo demo;

    //@Before
    //public void setUp() throws Exception {
    //    MockitoAnnotations.initMocks(this);
    //}

    @Test
    public void testAdd() {

        //创建方法1
        IDemo demo = mock(IDemo.class);

        demo.add(1);
        verify(demo).add(1);

        when(demo.add(-1)).thenReturn("a");
        String ret = demo.add(-1);
        assertEquals("a", ret);

        //Mockito.doNothing().when(demo).remove(1);

        when(demo.add(1)).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return invocation.getArgument(0) + "真好吃";
            }
        });

        System.out.println(demo.add(1));

    }

    @Test
    public void testVerify() {
        IDemo demo = mock(IDemo.class);
        when(demo.add(1)).thenReturn("add 1");
        demo.add(1);
        demo.remove(1);

        //verify(demo, Mockito.only()).remove(1);

        verify(demo, after(100)).add(1);
        demo.remove(1);

        verify(demo, Mockito.atLeast(2)).remove(1);
        verify(demo, Mockito.times(2)).remove(1);
        //verify(demo,Mockito.never()).add(1);

        verify(demo, timeout(100)).add(anyInt());

        when(demo.eat(any(String.class))).thenReturn("面条");
        when(demo.eat(anyString())).thenReturn("香喷喷");
        System.out.println(demo.eat("屎"));

        when(demo.eat(contains("小"))).thenReturn("好吃");
        System.out.println(demo.eat("小米"));

        when(demo.eat(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                return argument.endsWith("豆");
            }
        }))).thenReturn("不好吃");
        System.out.println(demo.eat("小黄豆"));

    }

}

interface IDemo {
    public String add(int a);

    public void remove(int a);

    public String eat(String food);

}
