import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MovieCollectionTest {
    @Test
    void addMovie() {

        MovieCollection col = new MovieCollection();
        assertEquals(0, col.getNumberOfMovies());
        col.addMovie("Test movie", "Hans", 1987, false, 120, "Drama");
        assertEquals(1, col.getNumberOfMovies());

        ArrayList<Movie> list = col.getMovies();

        assertEquals("Test movie", list.get(0).getTitle());
    }
    @Test
    void editMovie(){
        MovieCollection col = new MovieCollection();
        col.addMovie("Test movie", "Hans", 1987, false, 120, "Drama");

        //for (Movie movie1 : col.getMovies())
        ArrayList<Movie> list = col.getMovies();
        col.editMovie(list.get(0),"new title", "hans", 1987,false , 120 ,"Drama");

        assertEquals("new title", list.get(0).getTitle());
    }
}
