package com.sberStudy.java.homeWork.pivovarova.lesson11;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public interface ThreadPool {
    void start();
    void execute(Runnable runnable);

}
