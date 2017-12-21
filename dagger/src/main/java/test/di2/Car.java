package test.di2;

/**
 * Created by clw on 2017/8/31.
 */

public class Car {
    String name = "卡车";

    //@Inject
    //public Car() {
    //
    //}

    public Car(String name) {
        this.name = name;
    }

    public void go() {

    }

    @Override
    public String toString() {
        return "Car{" + super.toString() +
                ", name='" + name + '\'' +
                '}';
    }
}
