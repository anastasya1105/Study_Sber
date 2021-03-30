package com.sberStudy.java.homeWork.pivovarova;

public interface Communicate {

    public String sendAMessage(int otherUserId, String massage);

    public String receiveAMessage(int otherUserId);

}
