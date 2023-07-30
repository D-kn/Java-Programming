package org.example;

public interface ISQLConnector {
    void connect();

    void disconnect();

    void insertMovie(Movie movie);

}
