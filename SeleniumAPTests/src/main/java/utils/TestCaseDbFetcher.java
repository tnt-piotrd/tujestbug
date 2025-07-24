package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestCaseDbFetcher {
    public static Object[][] getTestCases(String query){
        List<Object[]> data = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                data.add(row);
            }
        } catch (SQLException e){
            throw new RuntimeException("Failed to fetch test cases from the database", e);
        }
        return data.toArray(new Object[0][]);
    }
}
