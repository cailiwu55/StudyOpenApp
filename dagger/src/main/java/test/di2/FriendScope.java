package test.di2;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.releasablereferences.CanReleaseReferences;

/**
 * Created by clw on 2017/12/12.
 */

/**
 * 等价于Singleton
 * @see javax.inject.Singleton @Singleton
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Scope
//可释放引用
@CanReleaseReferences
public @interface FriendScope {
}
