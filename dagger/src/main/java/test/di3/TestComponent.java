package test.di3;

/**
 * Created by clw on 2017/12/13.
 */

@dagger.Component(modules = TestModule.class)
public interface TestComponent {
    public void inject(TestActivity activity);

    //@Component.Builder
    //interface Builder{
    //    @BindsInstance
    //    Builder activity(Activity activity);
    //    TestComponent build();
    //}
}
