package org.example;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLConnector implements ISQLConnector, INotificationGenerator {
    private Connection connection;
    private Statement statement;
    private String databaseName;
    private String tableName;
    private List<Listener> listeners;

    public SQLConnector(String databaseName, String tableName) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        listeners = new ArrayList<>();
    }

    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + databaseName, "root", "Mysqlmysql1234####");
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            createTableIfNotExists();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        try {
            // Check if the "movie" table exists
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet tableResultSet = meta.getTables(null, null, tableName, null);
            if (!tableResultSet.next()) {
                // if "movie" table does not exist then create the table
                String createTableQuery = "CREATE TABLE " + tableName+ "(" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "title VARCHAR(255) NOT NULL," +
                        "year INT NOT NULL," +
                        "director VARCHAR(255) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableQuery);
                System.out.println("Table '" + tableName + "' created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertMovie(Movie movie) {
        try {
            String insertQuery = "INSERT INTO movie (title, year, director) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setInt(2, movie.getYear());
            preparedStatement.setString(3, movie.getDirector());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            notifyListeners(movie.getTitle());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void register(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregister(Listener listener) {
        listeners.remove(listener);
    }
    public void notifyListeners(String title) {
        Notifications notification = new Notifications(title, this);
        for (Listener listener : listeners) {
            listener.receivedNotification(notification);
        }
    }
}


