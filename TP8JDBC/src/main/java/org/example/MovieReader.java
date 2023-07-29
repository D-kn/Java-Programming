package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieReader {
    private String fileName;

    public MovieReader(String fileName) {
        this.fileName = fileName;
    }

    public void readMovies(SQLConnector sqlConnector) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {

                Movie movie = createMovieFromLine(line);
                sqlConnector.insertMovie(movie);
//                movie.displayMovieInfo();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Movie createMovieFromLine(String line) {
        String[] values = line.split("\t");

        for (int i = 0; i < values.length; i++) {
            if (values[i].equals("\\N")) {
                if (i == 4) {
                    values[i] = "false";
                } else if (i > 4 && i < 8) {
                    values[i] = "-1";
                } else {
                    values[i] = null;
                }
            }
        }
//        System.out.println(values[5]);
        String id = values[0];
        String type = values[1];
        String primaryTitle = values[2];
        String originalTitle = values[3];
        boolean isAdult = Boolean.parseBoolean(values[4]);
        int startYear = Integer.parseInt(values[5]);
        int endYear = Integer.parseInt(values[6]);
        int runtime = Integer.parseInt(values[7]);
        List<String> genres = new ArrayList<>();
        if (values[8] == null) {
            genres = null;
        } else {
            genres = Arrays.asList(values[8].split(","));
        }
        Movie movie = new Movie();
        movie.init(id, type, primaryTitle, originalTitle, isAdult, startYear, endYear, runtime, genres);

        return movie;
    }
}

