package test.rxjava;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Java 值传递和应用传递
 * java中只有按值传递，对于引用类型的传递，形参是实参的一个拷贝，他们都指向同一个内存地址值
 * Created by clw on 2017/5/20.
 */

public class JavaReferenceTest {

    @Test
    public void test() {
        String s1 = "hello";
        String s2 = "world";
        assertEquals(s1 + "---" + s2, "hello---world");//true

        change(s1, s2);
        assertEquals(s1 + "---" + s2, "hello---world");//true

        StringBuffer sb1 = new StringBuffer("hello");
        StringBuffer sb2 = new StringBuffer("world");
        assertEquals(sb1 + "---" + sb2, "hello---world");//true
        change(sb1, sb2);
        assertEquals(sb1 + "---" + sb2, "hello---worldworld");//true

    }

    public static void change(StringBuffer sb1, StringBuffer sb2) {
        sb1 = sb2;
        sb2.append(sb1);
    }

    public static void change(String s1, String s2) {
        s1 = s2;
        s2 = s1 + s2;
    }

    class Demo {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Demo call(Demo t) { //将地址传递给引用t
        Demo t2 = new Demo();//新建一个对象，将其地址传递给引用t2
        t2.setName("cba"); //
        t.setName("abc");
        t = t2; //将引用t2给到t
        return t;
    }

    @Test
    public void test2() {
        Demo obj = new Demo();
        System.out.println(call(obj).getName()); //cba
        System.out.println(obj.getName());//abc
    }

    public static int change(int a) {
        a = 50;
        return a;
    }

    @Test
    public void test3() {
        int a = 10;
        System.out.println(a); //10
        System.out.println(change(a));//50
        System.out.println(a);//10
    }

    public static String change(String s) {
        s = "zhangsan";
        return s;
    }

    @Test
    public void test4() {
        String s = new String("lisi");
        System.out.println(s);//lisi
        System.out.println(change(s)); //zhangsan
        System.out.println(s);//lisi
    }

}
