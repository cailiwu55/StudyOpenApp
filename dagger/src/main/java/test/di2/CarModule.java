package test.di2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by clw on 2017/12/11.
 */

@Module(subcomponents = SonComponent.class)
public class CarModule {
    //private Car mCar;
    //
    //public CarModule(Car car) {
    //    mCar = car;
    //}

    @Provides
    public Car provideCar() {
        return new Car("car1");
    }

    @Provides
    @Named("car2")
    public Car provideCar2() {
        return new Car("car2");
    }
}
