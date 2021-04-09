package com.sberStudy.java.homeWork.pivovarova.lesson16;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLite {
    private static final String INSERT = "INSERT INTO fibonacci (number) VALUES (?)";
    private static final String SELECT = "SELECT * FROM fibonacci f WHERE f.id = ?";
    private static final String SELECT_LIST = "SELECT * FROM fibonacci f WHERE f.id < ?";
    private static final String DROP = "DROP TABLE IF EXISTS fibonacci;";
    private static final String SELECT_MAX = "SELECT * FROM fibonacci WHERE id = (SELECT MAX(ID)  FROM fibonacci)";
    private static final String INSERT_O = "INSERT INTO fibonacci (number) VALUES (0);";

    public synchronized boolean thisItem(int n) {
        try (PreparedStatement st = DateSourceHelper.connection().prepareStatement(SELECT)){
            st.setInt(1, n);
            st.execute();
            ResultSet result = st.getResultSet();
            if (result.next()) {
                String num = result.getString("number");
                if (num != null) {
                    return true;
                }
            }

        } catch (SQLException throwables) {
            System.out.println("Не удалось thisItem");
            throwables.printStackTrace();
        }
        return false;
    }

    public Object receiveList(int n) {
        List<Integer> list = new ArrayList<>();
        try (PreparedStatement st = DateSourceHelper.connection().prepareStatement(SELECT_LIST)){
            st.setInt(1, n+1);
            st.execute();
            ResultSet result = st.getResultSet();
            while (result.next()) {
                String number = result.getString("number");
                list.add(Integer.parseInt(number));
            }
        } catch (SQLException throwables) {
            System.out.println("Не удалось receiveList");
            throwables.printStackTrace();
        }
        return list;
    }

    public void addNumber(Object result) {
        List<Integer> list = (List) result;
        int index = 0;
        String number;
        if (!thisItem(1)) {
            try (PreparedStatement st = DateSourceHelper.connection().prepareStatement(INSERT_O)){
                st.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try (PreparedStatement st = DateSourceHelper.connection().prepareStatement(SELECT_MAX)){
            st.execute();
            ResultSet rs = st.getResultSet();
            rs.next();
            index = rs.getInt("id");
            for (int i = index; i < list.size(); i++) {
                number = list.get(i).toString();
                PreparedStatement statement = DateSourceHelper.connection().prepareStatement(INSERT);
                statement.setString(1, number);
                statement.execute();
            }
        } catch (SQLException throwables) {
            System.out.println("Не удалось addNumber");
            throwables.printStackTrace();
        }

    }
    public void dropTable() {
        try (PreparedStatement st = DateSourceHelper.connection().prepareStatement(DROP)){
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
