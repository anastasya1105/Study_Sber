package com.sberStudy.java.homeWork.pivovarova;

public class ProfileImpl implements Profile{

    private final ServerImpl server;

    public ProfileImpl(){
        this.server = new ServerImpl();
    }

    @Override
    public String sendAMessage(int userId, int otherUserId, String massage) {
        return server.sendAMessage(userId, otherUserId, massage);
    }

    @Override
    public String receiveAMessage(int userId, int otherUserId) {
        return server.receiveAMessage(userId, otherUserId);
    }

    @Override
    public String showPhoto(int userId, String photoName) {
        return server.showPhoto( userId, photoName);
    }
    @Override
    public String uploadPhoto(int userId, String photoName) {
        return server.uploadPhoto( userId, photoName);
    }
    @Override
    public String downloadPhoto(int userId, String photoName) {
        return server.downloadPhoto( userId, photoName);
    }

}
