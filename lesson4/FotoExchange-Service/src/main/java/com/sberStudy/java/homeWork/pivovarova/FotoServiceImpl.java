package com.sberStudy.java.homeWork.pivovarova;

public class FotoServiceImpl implements FotoService{
    private final ProfileImpl userClient;
    private int userId;

    public FotoServiceImpl(ProfileImpl client){
        this.userClient = new ProfileImpl();
    }


    public void authorization(int userId){
        this.userId = userId;
    }

    @Override
    public String showPhoto(String photoName) {
        return userClient.showPhoto( userId, photoName);
    }
    @Override
    public String uploadPhoto(String photoName) {
        return userClient.uploadPhoto( userId, photoName);
    }

    @Override
    public String downloadPhoto(String photoName) {
        return userClient.downloadPhoto( userId, photoName);
    }

}
