package com.example.servingwebcontent.model;
import java.util.ArrayList;


public class MovieList {
    ArrayList<Movie> movies = new ArrayList<>();

    public ArrayList<Movie> addMovie(Movie movie) {
        movies.add(movie);
        return movies;
    }

    public ArrayList<Movie> getEditMovie(String name, int movieId) {
        for (int i = 0; i < movies.size(); i++) {
            if (Integer.parseInt(movies.get(i).getId()) == movieId) {
                movies.get(i).setTitle(name);
                break;
            }
        }
        return movies;
    }

    public ArrayList<Movie> getDeleteMovie(int movieId) {
        for (int i = 0; i < movies.size(); i++) {
            if (Integer.parseInt(movies.get(i).getId()) == movieId) {
                movies.remove(i);
                break;
            }
        }
        return movies;
    }

    public void printMovieList() {
        for (Movie movie : movies) {
            System.out.println("Movie ID: " + movie.getId());
            System.out.println("Movie Title: " + movie.getTitle());
            System.out.println("Show Time: " + movie.getShowTime());
            System.out.println("Duration: " + movie.getDuration());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Age: " + movie.getAge());
            System.out.println("-------------------------");
        }
    }
}
