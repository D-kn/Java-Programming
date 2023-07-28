package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the working directory :");
//        String workingDirectory = scanner.nextLine();
        String workingDirectory = "C:\\Users\\T450\\Documents\\Msc Data Engineering\\Java\\Projets\\";
//        String fileName1 = workingDirectory + "\\tMovies.csv";
//        System.out.println(fileName1);
        Reader reader = new Reader(workingDirectory + "tMovies.csv");
        MovieDataReader movieDataReader  = new MovieDataReader(reader);
        List<Movie> movies = movieDataReader.readMovies(); // all movies
        List<Movie> filteredMovies = movieDataReader.filterMovies(movies); // only movie from 1993

        int count = 0;
        for(Movie movie : filteredMovies){
            System.out.println(movie.getTitle() + " - " + movie.getYear() + " - " + movie.getDirector());
            count +=1;
        }
        System.out.println(count);
    }
}