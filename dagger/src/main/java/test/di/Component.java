package test.di;

/**
 * Created by clw on 2017/8/31.
 */

@dagger.Component
public interface Component {
    public void inject(Man man);
}
