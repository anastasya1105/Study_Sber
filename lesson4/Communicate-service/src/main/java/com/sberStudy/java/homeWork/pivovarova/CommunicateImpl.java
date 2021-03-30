package com.sberStudy.java.homeWork.pivovarova;

public class CommunicateImpl implements Communicate{
    private final ProfileImpl userClient;
    private int userId;

    public CommunicateImpl(ProfileImpl userClient, int otherUserId) {
        this.userClient = userClient;
    }
    public void authorization(int userId){
        this.userId = userId;
    }

    @Override
    public String sendAMessage(int otherUserId, String massage) {
        return userClient.sendAMessage(userId, otherUserId, massage);
    }

    @Override
    public String receiveAMessage(int otherUserId) {
        return userClient.receiveAMessage(userId, otherUserId);
    }

}
