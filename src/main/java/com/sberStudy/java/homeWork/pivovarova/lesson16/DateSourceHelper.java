package com.sberStudy.java.homeWork.pivovarova.lesson16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DateSourceHelper {
    public static Connection connection() throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:h2:~/Fibonacci", "sa", "");
    return connection;
    }
    public static void createDb() {
        String sql;
        try {
            sql = new String(Files.readAllBytes(Paths.get("C:\\Users\\serdy\\IdeaProjects\\Study2\\src\\main\\resources\\lesson16\\data.sql")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement statement = DateSourceHelper.connection().prepareStatement(sql)){
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
