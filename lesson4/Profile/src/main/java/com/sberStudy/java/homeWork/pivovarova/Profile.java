package com.sberStudy.java.homeWork.pivovarova;

public interface Profile {

    public String sendAMessage(int userId, int otherUserId, String massage);

    public String receiveAMessage(int userId, int otherUserId);

    public String showPhoto(int userId, String photoName);

    public String uploadPhoto(int userId, String photoName);

    public String downloadPhoto(int userId, String photoName);


}


/*
    // получить запрос от сервиса
    public void getRequestFromService();
    // отправить ответ сервису
    public void sendAnswerToService();
    // получить ответ от сервера
    public void getAnswerFromServer();
    // отправить запрос на сервер
    public void sendRequestToServer();
 */

