import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {
    private MovieCollection movieCollection;
    private FileHandler fileHandler;

    public Controller() {
        this.movieCollection = new MovieCollection();
        this.fileHandler = new FileHandler("Movies.txt");
    }

    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {

        movieCollection.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
    }

    public ArrayList<Movie> getMovies() {
        return movieCollection.getMovies();
    }

    public ArrayList<Movie> searchMovies(String searchText) {


        return movieCollection.searchMovies(searchText);

    }


    public Boolean editMovie(Movie movie, String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        return movieCollection.editMovie(movie, title, director, yearCreated, inColor, lengthInMinutes, genre);
    }


    public boolean deleteMovie(Movie movie) {

        return movieCollection.deleteMovie(movie);

    }

    public void markAsWatched(Movie movie) {
        movie.markAsWatched(LocalDate.now());
    }

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days) {
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.getMoviesNotWatchedSince(limit);
    }

    public boolean saveMovies() throws FileNotFoundException {
        return fileHandler.saveMoviesToFile(movieCollection);
    }
    public void readFile () throws FileNotFoundException {
         movieCollection=fileHandler.readMovieFromFile();
    }


}
