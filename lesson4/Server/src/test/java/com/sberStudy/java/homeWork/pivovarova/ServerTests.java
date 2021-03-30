package com.sberStudy.java.homeWork.pivovarova;


import static org.junit.Assert.*;
import org.junit.Test;


public class ServerTests {
    ServerImpl x = new ServerImpl();


    @Test
    public void showPhotoTest_1(){
        String namePhoto =  x.showPhoto(3,"Фотография 2");
        assertEquals("showPhotoTest_1 должна быть получена  Фотография 2", namePhoto, "showPhoto: Фотография 2");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void showPhotoTest_2(){
        String namePhoto =  x.showPhoto(1234,"Фотография 2");
    }

    @Test
    public void sendAMessageTest() {
        String massage = x.sendAMessage(3, 2, "Hello");
    }
}
