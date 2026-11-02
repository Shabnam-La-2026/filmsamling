import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public boolean saveMoviesToFile(MovieCollection movies)
            throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(fileName));
        System.out.println("ToDo: gem til filen: " + fileName);
        ArrayList<Movie> list = movies.getMovies();
        for (Movie m : list) {
            output.println(m.getTitle() + ";" +
                    m.getDirector() + ";" +
                    m.getYearCreated() + ";" +
                    m.getLengthInMinutes() + ";" +
                    m.getIsInColor() + ";" +
                    m.getGenre());
        }
        output.close();
        return true;
    }

    public MovieCollection readMovieFromFile()
            throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        MovieCollection result = new MovieCollection();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isBlank()) {
                continue;

            }
            String[] date = line.split(";");
            String title = date[0];
            String director = date[1];
            int yearCreated = Integer.parseInt(date[2]);
            int lengthInMinutes = Integer.parseInt(date[3]);
            boolean inColor = Boolean.parseBoolean(date[4]);
            String genre = date[5];
            result.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
        }
        input.close();
        return result;

    }

}

