package test.di2;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by clw on 2017/12/12.
 */

/**
 * 自定义Named注解，限定符
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Named {
    String value() default "";
}
