

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MovieTest {
    @Test
    void setTitleChangesTitle() {
        // Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");

        // Act
        movie.setTitle("Dødens gab");

        // Assert
        assertEquals("Dødens gab", movie.getTitle());
    }
}
