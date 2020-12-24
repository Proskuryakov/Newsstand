package ru.vsu.cs.newsstand.core.db;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.vsu.cs.newsstand.annotation.PostConstruct;
import ru.vsu.cs.newsstand.annotation.Singleton;

import java.sql.*;

@Singleton
public class PostgreDataBase {

    private final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/newsstand";
    private final String USER = "postgres";
    private final String PASS = "root";

    private Connection connection;
    private Statement stmt;

    @SneakyThrows
    @PostConstruct
    public void connect() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public ResultSet execute(String sql) {
        try{
            stmt = connection.createStatement();
            return stmt.executeQuery(sql);
        } catch (Exception e) {
            return null;
        }

    }

}
