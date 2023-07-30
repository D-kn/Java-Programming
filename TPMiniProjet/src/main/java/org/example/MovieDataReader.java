package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MovieDataReader {
    private List<Movie> movies;

    public MovieDataReader() {
        movies = new ArrayList<>();
    }

    public void mergeFiles(String tMoviesFile, String tDirectorsFile) {
        Map<Integer, String> directorsMap = new HashMap<>();

        // Read data from tDirectors.csv and create a map of DirectorId to DirectorName
        try (BufferedReader directorReader = new BufferedReader(new FileReader(tDirectorsFile))) {
            String directorLine;
            boolean isFirstLine = true;
            while ((directorLine = directorReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header
                }
                String[] directorData = directorLine.split(",");
                if (directorData.length >= 2) {
                    int directorId = Integer.parseInt(directorData[0].trim());
                    String directorName = directorData[1].trim();
                    directorsMap.put(directorId, directorName);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading tDirectors.csv: " + e.getMessage());
            return;
        }

        // Read data from tMovies.csv and merge with Director data from directorsMap
        List<String[]> mergedData = new ArrayList<>();
        try (BufferedReader movieReader = new BufferedReader(new FileReader(tMoviesFile))) {
            String movieLine;
            boolean isFirstLine = true;
            while ((movieLine = movieReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header
                }
                String[] movieData = movieLine.split(";");
                if (movieData.length == 5) {
                    int directorId = Integer.parseInt(movieData[4].trim());
                    String directorName = directorsMap.getOrDefault(directorId, "Unknown");
                    String[] mergedRow = Arrays.copyOf(movieData, 5);
                    mergedRow[4] = directorName; // Replace the DirectorId with Director name
                    mergedData.add(mergedRow);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading tMovies.csv: " + e.getMessage());
            return;
        }

        // Convert the merged data to a list of Movie objects
        for (String[] row : mergedData) {
            String title = row[1].trim();
            int year = Integer.parseInt(row[2].trim());
            String director = row[4].trim();
            Movie movie = new Movie(title, year, director);
            movies.add(movie);
        }
    }


    public List<Movie> getMovies() {
        return movies;
    }


    // Filter movies only from 1993 year's
    public List<Movie> filterMovies(List<Movie> movies) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getYear() >= 1993) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
}
