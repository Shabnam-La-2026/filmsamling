package domainmodel;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;



public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();

    }

    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        Movie movie = new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);

        movies.add(movie);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public int getNumberOfMovies() {
        return movies.size();
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        ArrayList<Movie> res = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.matchText(searchText)) {
                res.add(movie);
            }
        }
        return res;
    }

    public Boolean editMovie(Movie movie, String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {

        for (Movie movie1 : movies) {

            if (!(movie == null) && movie1 == movie) {


                movie.setTitle(title);
                movie.setDirector(director);
                movie.setYearCreated(yearCreated);
                movie.setLengthInMinutes(lengthInMinutes);
                movie.setGenre(genre);
                return true;
            }
        }

        return false;
    }


    public boolean deleteMovie(Movie movie) {
        if (movies.contains(movie)) {
            movies.remove(movie);

        }
        return true;
    }

    //        for (domainmodel.Movie movie1 : movies ){
//          if (!(movie1 == null) && movie==movie1){
//
//              movies.remove(movie);
//              return true;
//          }
//      }
//      return false;
//    }
    // getMoviesNotWatchedSince(LocalDate date) : ArrayList<domainmodel.Movie>
    public ArrayList<Movie> getMoviesNotWatchedSince(LocalDate date) {
      ArrayList<Movie>list = new ArrayList<>();
        for (Movie movie : movies) {
            if (!movie.hasBeenWatched() || movie.getLastWatched().isBefore(date)) {
               list.add(movie);

            }
        }
        return list;

    }

    public  ArrayList<Movie>getMovieSorted(Comparable<Movie> comparable){
        ArrayList<Movie> sorted = new ArrayList<>(movies);
        Collections.sort(sorted);
        return sorted;
    }
    public ArrayList<Movie> getMoviesSorted(Comparator<Movie> comparator) {
        ArrayList<Movie> sorted = new ArrayList<>(movies);
        sorted.sort(comparator);
        return sorted;
    }

    //getMoviesSortedByTitle() : ArrayList<Movie>
    //
    //+getMoviesSorted(Comparator<Movie> comparator) : ArrayList<Movie>
}