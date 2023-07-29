package org.example;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\T450\\Documents\\Msc Data Engineering\\Java\\";
        SQLConnector connector = new SQLConnector("movies");
        MovieReader reader = new MovieReader(path + "movies.tsv");

        connector.connect();
        reader.readMovies(connector);
        connector.disconnect();
    }
}
