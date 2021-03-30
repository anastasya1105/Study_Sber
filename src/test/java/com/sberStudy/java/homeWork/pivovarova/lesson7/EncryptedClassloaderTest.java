package com.sberStudy.java.homeWork.pivovarova.lesson7;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EncryptedClassloaderTest {
    EncryptedClassloader encryptedClassloader;


    @Before
    public void doBefore() {
        String key = "sdgndognadon02n4v039nviodn";
        String dir = "C://Users//serdy//IdeaProjects//Study2//src/test/resources/EncryptedTest";
        File file = new File(dir);
        assertTrue(file.exists());
        encryptedClassloader = new EncryptedClassloader(key,file,ClassLoader.getSystemClassLoader());
    }

    @Test
    public void testFindClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        encryptedClassloader.encryptClass(Date.class);
        Class clazz = encryptedClassloader.decrypt("Date");
        encryptedClassloader.findClass("Date");
        System.out.println(encryptedClassloader);
        System.out.println(clazz.newInstance());
        assertEquals("Íå òîò êëàññ!","java.util.Date",clazz.getName());
    }
}
