package com.sberStudy.java.homeWork.pivovarova.lesson5.bank;


import java.sql. *;


public class ServerDB {
    private static final String INSERT = "INSERT INTO SERVERDB VALUES (?, ?, ?, ?)";
    private static final String SELECT_BALANCE = "SELECT * FROM SERVERDB WHERE CardNumber = ? AND PIN = ?";
    private static final String UPDATE_BALANCE = "UPDATE SERVERDB SET BALANCE = ? WHERE CardNumber = ? AND PIN = ?";
    private static final String SELECT_PIN = "SELECT * FROM SERVERDB WHERE CardNumber = ?";
    private MessagesImpl messages = new MessagesImpl();


    public int getBalance(int cardNumber, int pin) {
        int balance = 0;
        try (PreparedStatement st = DateSourceHelper.connection().prepareStatement(SELECT_BALANCE)){
            st.setInt(1, cardNumber);
            st.setInt(2, pin);
            st.execute();
            ResultSet result = st.getResultSet();
            while (result.next()) {
                balance = result.getInt("Balance");
            }
        } catch (SQLException throwables) {
            messages.printMessages("Не удалось соединиться с БД");
            throwables.printStackTrace();
        }
        return balance;
    }

    public void UpdateBalance(int cardNumber, int pin, int newBalance) {
        try (PreparedStatement st = DateSourceHelper.connection().prepareStatement(UPDATE_BALANCE)){
            st.setInt(1, newBalance);
            st.setInt(2, cardNumber);
            st.setInt(3, pin);
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveClient(int cardNumber, int pin, String fullName, int balance) {
        try (PreparedStatement st = DateSourceHelper.connection().prepareStatement(INSERT)){
            st.setInt(1, cardNumber);
            st.setInt(2, pin);
            st.setString(3, fullName);
            st.setInt(4, balance);
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getPin(int cardNumber) {
        int pinCode = 0;
        try (PreparedStatement st = DateSourceHelper.connection().prepareStatement(SELECT_PIN)){
            st.setInt(1, cardNumber);
            st.execute();
            ResultSet result = st.getResultSet();
            if (result.next()) {
                pinCode= result.getInt("PIN");
            }
        } catch (SQLException throwables) {
            messages.printMessages("Не удалось соединиться с БД");
            throwables.printStackTrace();
        }
        return pinCode;
    }

}
