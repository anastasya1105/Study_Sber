package com.sberStudy.java.homeWork.pivovarova.lesson6;

import com.sberStudy.java.homeWork.pivovarova.lesson6.Task2_3_4.Example2;
import org.junit.Test;

public class BeanUtilsTest {
    Example2 example = new Example2( "Ivan", "Russia", "Rostov-on-Don");
    Example2 example2 = new Example2( null, null, null);


    @Test
    public void assignTest() {
        BeanUtils.assign(example, example2);
        example2.printInfo();
    }

}
