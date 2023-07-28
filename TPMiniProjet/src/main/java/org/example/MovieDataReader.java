package org.example;

import java.util.ArrayList;
import java.util.List;

public class MovieDataReader {
    private Reader reader;

    public MovieDataReader(Reader reader) {
        this.reader = reader;
    }

    public List<Movie> readMovies() {
        List<Movie> movies = new ArrayList<>();
        List<String> lines = reader.readLines();

        boolean isFirstLine = true;

        for (String line : lines) {
            // Skip the first line
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
                String[] data = line.split(";");
//            System.out.println(data.length);
            if (data.length == 5) {
                String title = data[1].trim();
                String director = data[3].trim();
                int year = Integer.parseInt(data[2].trim());

                // Create a Movie object with incomplete data (director will be fetched later)
                Movie movie = new Movie(title, year, director);
                movies.add(movie);
            }
        }

        return movies;
    }

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
