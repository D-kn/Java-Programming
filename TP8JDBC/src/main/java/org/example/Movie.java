package org.example;

import java.util.List;

public class Movie {
    private String id;
    private String type;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private int startYear;
    private int endYear;
    private int runtime;
    private List<String> genres;

    public Movie() {
        // Constructeur par défaut
    }
    public Movie(String id, String type, String primaryTitle, String originalTitle, boolean isAdult,
                 int startYear, int endYear, int runtime, List<String> genres) {
        this.id = id;
        this.type = type;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtime = runtime;
        this.genres = genres;
    }


    // Getter methods
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<String> getGenres() {
        return genres;
    }

    // Setter methods
    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }


    public void init(String id, String type, String primaryTitle, String originalTitle, boolean isAdult,
                     int startYear, int endYear, int runtime, List<String> genres) {
        this.id = id;
        this.type = type;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtime = runtime;
        this.genres = genres;
    }

    public void displayMovieInfo() {
        System.out.println("Movie: " + primaryTitle);
        System.out.println("Type: " + type);
        System.out.println("Start Year: " + startYear);
        System.out.println("Genres: " + genres);
    }
}


