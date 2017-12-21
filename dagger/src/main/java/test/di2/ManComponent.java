package test.di2;

/**
 * Created by clw on 2017/8/31.
 */

@ManScope
@dagger.Component(modules = CarModule.class)
public interface ManComponent {
    public void inject(Man man);
    Car car();

    SonComponent.Builder sonComponent();
}
