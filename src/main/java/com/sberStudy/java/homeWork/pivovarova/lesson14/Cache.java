package com.sberStudy.java.homeWork.pivovarova.lesson14;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {

    CacheType cacheType() default CacheType.IN_MEMORY;

    boolean zip() default false;

    String fileName() default "";

    int[] identifyByArgNumbers() default {};

    int maxListElementsCached() default 100_000;
}
