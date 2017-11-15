package test.fragmentdemo;

import java.text.DecimalFormat;

/**
 * Created by clw on 2017/10/27.
 */

public class Test {
    public static void main(String[] args) {
        DecimalFormat format = new DecimalFormat("0.#");
        float f = 5*10f/8 ;
        System.out.println("5 fotmat:"+format.format(5));
        System.out.println("5.0 format:"+format.format(f));
    }
}
