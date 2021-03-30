package com.sberStudy.java.homeWork.pivovarova;

import java.util.ArrayList;

public class ServerImpl implements Server{
    private static ArrayList<ArrayList> userData ;
    private static ArrayList<String> PhotoData ;

    static{
        PhotoData = new ArrayList<String>();
        PhotoData.add(0,"Фотография 0");
        PhotoData.add(1,"Фотография 1");
        PhotoData.add(2,"Фотография 2");

        userData =  new ArrayList();
        userData.add(null);
        userData.add(null);
        userData.add(null);
        userData.add(null);
        userData.set(3,PhotoData);
    }

    @Override
    public String sendAMessage(int userId, int otherUserId, String massage) {
        return "sendAMessage тестовое сообщение: кому - UserId " + otherUserId + " текст сщщбщения: " + massage;
    }

    @Override
    public String receiveAMessage(int userId, int otherUserId) {
        return "receiveAMessage тестовое сообщение: от кого - UserId " + otherUserId;
    }

    @Override
    public String showPhoto(int userId, String photoName) {
        if(checkAuth(userId)){
            ArrayList<String> usersPhoro =  userData.get(userId);
            for (String photo : usersPhoro) {
                if (photoName.equals(photo)){
                    return "showPhoto: " + photo;
                }
            }
            return "showPhoto: Ничего не найдено";
        }
        return "";

    }
    @Override
    public String uploadPhoto(int userId, String photoName) {
        return "uploadPhoto тестовое сообщение";
    }
    @Override
    public String downloadPhoto(int userId, String photoName) {
        return "downloadPhoto тестовое сообщение";
    }


    private boolean checkAuth(int userId){
        if (userData.get(userId) == null) {// здесь криво
            System.out.println("showPhoto: Вы не автризованы");
            return false;
        }

        return true;
    }



}
