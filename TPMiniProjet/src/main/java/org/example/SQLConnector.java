package org.example;


import java.sql.*;

//public class SQLConnector implements INotificationListener, ISQLConnector {
public class SQLConnector implements ISQLConnector {
    private Connection connection;
    private Statement statement;
    private String databaseName;
    private String user;
    private String password;

    public SQLConnector(String databaseName, String user, String password) {
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
    }

    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + databaseName,user, password);
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            // Check if the "movie" table exists
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet tableResultSet = meta.getTables(null, null, "movie", null);
            if (!tableResultSet.next()) {
                // "movie" table does not exist, create the table
                String createTableQuery = "CREATE TABLE movie (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "title VARCHAR(255) NOT NULL," +
                        "year INT NOT NULL," +
                        "director VARCHAR(255) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableQuery);
                System.out.println("Table 'movie' created successfully.");
            }

            ResultSet resultSet = statement.executeQuery("SELECT * FROM movie");

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                String director = resultSet.getString("director");

                Movie movie = new Movie();
                movie.setTitle(title);
                movie.setYear(year);
                movie.setDirector(director);
            }
        } catch (ClassNotFoundException | SQLException e) {
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
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM movie");
            resultSet.moveToInsertRow();
            resultSet.updateString("title", movie.getTitle());
            resultSet.updateInt("year", movie.getYear());
            resultSet.updateString("director", movie.getDirector());
            resultSet.insertRow();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}