package com.sberStudy.java.homeWork.pivovarova;

public interface Server {
    public String sendAMessage(int userId, int otherUserId, String massage);

    public String receiveAMessage(int userId, int otherUserId);

    public String showPhoto(int userId, String photoName);

    public String uploadPhoto(int userId, String photoName);

    public String downloadPhoto(int userId, String photoName);


}
