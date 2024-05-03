package ait.cohort34.post.service.logging;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//RUNTIME - в процессе работы аппликации
@Target(ElementType.METHOD)//над чем можно поставить аннотацию  (METHOD-над методом)
public @interface PostLogger {
    boolean enabled() default true;
}
