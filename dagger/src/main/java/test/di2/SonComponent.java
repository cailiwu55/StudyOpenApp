package test.di2;

import dagger.Subcomponent;

/**
 * Created by clw on 2017/12/19.
 */

@SonScope
@Subcomponent(modules = BikeModule.class)
public interface SonComponent {
    void inject(Son son);

    @Subcomponent.Builder
    interface Builder{
        SonComponent build();
    }
}
