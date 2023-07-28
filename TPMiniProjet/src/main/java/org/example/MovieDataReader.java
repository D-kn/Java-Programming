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
            // Skip the first line specially for tMovies file
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
                String[] data = line.split(";");
//            System.out.println(data.length);
            if (data.length == 5) {
                int id = Integer.parseInt(data[0].trim());
                String title = data[1].trim();
                int year = Integer.parseInt(data[2].trim());
                String director = data[4].trim();

//                charger deux fichiers
//                fais un merge entre les deux fichiers

                // Create a Movie object with incomplete data (director will be fetched later)
                Movie movie = new Movie(id, title, year, director);
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
