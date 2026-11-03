package domainmodel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Movie implements Comparable<Movie> {
    private LocalDate lastWatched;
    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;

    public Movie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.inColor = inColor;
        this.lengthInMinutes = lengthInMinutes;
        this.yearCreated = yearCreated;


    }

    public String getTitle() {
        return title;
    }

    public boolean getIsInColor() {
        return inColor;
    }

    public String getDirector() {
        return director;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public String getGenre() {
        return genre;
    }

    public boolean matchText(String text) {
        String lt = text.toLowerCase();
        return title.toLowerCase().contains(lt) ||
                director.toLowerCase().contains(lt) ||
                genre.toLowerCase().contains(lt) ||
                ("" + yearCreated).contains(lt);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public void setInColor(boolean inColor) {
        this.inColor = inColor;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getLastWatched() {
        return lastWatched;
    }

    public boolean hasBeenWatched() {
        return lastWatched != null;
    }

    public void markAsWatched(LocalDate date) {
        lastWatched = date;
    }

    public long getDaysSinceWatched(LocalDate today) {
        return ChronoUnit.DAYS.between(lastWatched, today);
    }

    @Override
    public int compareTo(Movie other) {
        return title.compareToIgnoreCase(other.title);
    }
}
