package org.example;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the working directory :");
        String workingDirectory = scanner.nextLine();
        String tMoviesFile = workingDirectory + "\\tMovies.csv";
        String tDirectorsFile = workingDirectory + "\\tDirectors.csv";

        MovieDataReader movieDataReader = new MovieDataReader();
        movieDataReader.mergeFiles(tMoviesFile, tDirectorsFile);

        List<Movie> allMovies = movieDataReader.getMovies(); // get the list of all movies
        List<Movie> filteredMovies = movieDataReader.filterMoviesFrom1993(allMovies);

        // Create and register the listener
        String logFile = workingDirectory + "\\notification_log.log";
        Listener listener = new Listener(logFile);

        //DataBase Name
        String dataBase = "movies";
        //tableName Name
        String tableBase = "movie";
        SQLConnector sqlConnector = new SQLConnector(dataBase, tableBase);
        sqlConnector.connect();

        sqlConnector.register(listener);

        for (Movie movie : filteredMovies){
            sqlConnector.insertMovie(movie);
        }
        sqlConnector.unregister(listener);
        sqlConnector.disconnect();
    }
}