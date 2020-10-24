package ru.vsu.cs.newsstand.core.db;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.vsu.cs.newsstand.annotation.PostConstruct;
import ru.vsu.cs.newsstand.annotation.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @SneakyThrows
    public ResultSet execute(String sql) {
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

}
