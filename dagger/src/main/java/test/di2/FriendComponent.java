package test.di2;

import dagger.Component;

/**
 * Created by clw on 2017/12/19.
 */

@FriendScope
@Component(dependencies = ManComponent.class)
public interface FriendComponent  {
    void inject(Friend friend);
}
