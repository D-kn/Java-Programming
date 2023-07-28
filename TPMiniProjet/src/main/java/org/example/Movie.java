package org.example;

public class Movie {
    private String title;
    private int year;
    private String director;

    public Movie() {

    }

    public Movie(String title, int year, String director) {
        this.title = title;
        this.year = year;
        this.director = director;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


}
