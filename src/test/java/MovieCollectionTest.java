import domainmodel.Movie;
import domainmodel.MovieCollection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class MovieCollectionTest {
    private MovieCollection collection;

    @Test
    void emptyMovieCollection() {

        MovieCollection col = new MovieCollection();
        assertEquals(0, col.getNumberOfMovies());
    }

    @Test
    void addMovie() {

        MovieCollection col = new MovieCollection();
        col.addMovie("Test movie", "Hans", 1987, false, 120, "Drama");
        assertEquals(1, col.getNumberOfMovies());

        ArrayList<Movie> list = col.getMovies();

        assertEquals("Test movie", list.get(0).getTitle());
    }

    @Test
    void editMovie() {
        MovieCollection col = new MovieCollection();
        col.addMovie("Test movie", "Hans", 1987, false, 120, "Drama");

        //for (domainmodel.Movie movie1 : col.getMovies())
        ArrayList<Movie> list = col.getMovies();
        col.editMovie(list.get(0), "new title", "hans", 1987, false, 120, "Drama");

        assertEquals("new title", list.get(0).getTitle());
    }

    @BeforeEach
    void setUp() {
        // Kører før HVER test – så starter alle tests med den samme, friske samling
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");
        collection.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        collection.addMovie("Godzilla", "Ishirō Honda", 1954, false, 96, "Sci-fi");
    }


    @Test
    void searchWithSeveralMatches() {
        //domainmodel.MovieCollection collection = new domainmodel.MovieCollection();
        // "god" findes i både "The Godfather" og "Godzilla"
        ArrayList<Movie> result = collection.searchMovies("god");

        assertEquals(2, result.size());
    }

    @Test
    void deleteMovieRemovesIt() {
        //domainmodel.MovieCollection collection = new domainmodel.MovieCollection();
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        boolean deleted = collection.deleteMovie(casablanca);

        // Assert
        assertTrue(deleted);
        assertEquals(2, collection.getNumberOfMovies());
        assertTrue(collection.searchMovies("Casablanca").isEmpty());
    }
}
