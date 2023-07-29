package org.example;

import java.sql.*;

public class SQLConnector {
    private Connection connection;
    private Statement statement;
    private String databaseName;

    public SQLConnector(String databaseName) {
        this.databaseName = databaseName;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(databaseName);
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + databaseName,
                                                     "root", "Mysqlmysql1234####");
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM movies");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String type = resultSet.getString("type");
                String primaryTitle = resultSet.getString("primaryTitle");

                Movie movie = new Movie();
                movie.setId(id);
                movie.setType(type);
                movie.setPrimaryTitle(primaryTitle);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMovie(Movie movie) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movies");
            resultSet.moveToInsertRow();

            resultSet.updateString("id", movie.getId());
            resultSet.updateString("type", movie.getType());
            resultSet.updateString("primaryTitle", movie.getPrimaryTitle());

            resultSet.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
