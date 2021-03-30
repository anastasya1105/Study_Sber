package com.sberStudy.java.homeWork.pivovarova.lesson7;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class BrowserClassLoader extends URLClassLoader {

    public BrowserClassLoader(URL[] urls) {
        super(urls);
    }
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            return findClass(name);
        } catch (Exception e) {
        }
        return super.loadClass(name);
    }

}
