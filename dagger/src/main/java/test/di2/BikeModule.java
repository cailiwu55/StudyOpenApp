package test.di2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by clw on 2017/12/19.
 */

@Module
public class BikeModule {

    @Provides
    public Bike providerBike(){
        return new Bike();
    }
}
