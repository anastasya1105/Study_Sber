package com.sberStudy.java.homeWork.pivovarova.lesson8;

import java.lang.annotation.*;
import static com.sberStudy.java.homeWork.pivovarova.lesson8.CacheType.IN_MEMORY;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {

    CacheType cacheType() default CacheType.IN_MEMORY;

    boolean zip() default false;

    String fileName() default "";

    int[] identifyByArgNumbers() default {};

    int maxListElementsCached() default 100_000;


}
