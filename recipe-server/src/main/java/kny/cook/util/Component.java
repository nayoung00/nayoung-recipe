package kny.cook.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // 컴파일 이후에도 JVM에 의해서 참조가 가능하다.
public @interface Component {

  String value() default "";
}
