package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private Connection connection;
    private static final String DB_URL = "jdbc:sqlite:src/test/resources/db/test-data.db";

    private DatabaseConnection(){
        try{
            connection = DriverManager.getConnection(DB_URL);
        }catch (SQLException e){
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
