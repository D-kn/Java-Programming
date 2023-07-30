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

        List<Movie> movies = movieDataReader.getMovies(); // get the list of the movies
        SQLConnector sqlConnector = new SQLConnector("movies", "root", "Mysqlmysql1234####");
        sqlConnector.connect();

        for (Movie movie : movies){
            sqlConnector.insertMovie(movie);
        }

        sqlConnector.disconnect();
    }
}