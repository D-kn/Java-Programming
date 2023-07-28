package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the working directory :");
//        String workingDirectory = scanner.nextLine();
        String workingDirectory = "C:\\Users\\T450\\Documents\\Msc Data Engineering\\Java\\Projets\\";
        String tMoviesFile = workingDirectory + "tMovies.csv";
        String tDirectorsFile = workingDirectory + "tDirectors.csv";
        String outputFile = workingDirectory + "merged_output.csv";
        // Read data from tDirectors.csv and store in a map with DirectorId as the key

        Map<Integer, String> directorsMap = new HashMap<>();
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
            System.out.println(directorsMap);
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
                    mergedRow[3] = directorName; // Replace the DirectorId with Director name
                    mergedData.add(mergedRow);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading tMovies.csv: " + e.getMessage());
            return;
        }


        // check if the outfile already exists
        Path outputPath = Paths.get(outputFile);
        if (Files.exists(outputPath)) {
            try {
                Files.delete(outputPath);
                System.out.println("Existing outputFile has been deleted.");
            } catch (IOException e) {
                System.err.println("Error deleting existing outputFile: " + e.getMessage());
            }
        }


        // Write the merged data to the output CSV file (merged_output.csv)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Write the header
            writer.write("MovieId;Title;Year;Director;DirectorId");
            writer.newLine();

            // Write the merged data rows
            for (String[] row : mergedData) {
                writer.write(String.join(";", row));
                writer.newLine();
            }

            System.out.println("Merged data has been written to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing merged data to " + outputFile + ": " + e.getMessage());
        }



        Reader reader = new Reader(outputFile);
        MovieDataReader movieDataReader  = new MovieDataReader(reader);


        List<Movie> movies = movieDataReader.readMovies(); // all movies
//        List<Movie> filteredMovies = movieDataReader.filterMovies(movies); // only movie from 1993
//
        for(Movie movie : movies){
            System.out.println(movie.getTitle() + " - " + movie.getYear() + " - " + movie.getDirector());
        }
    }
}