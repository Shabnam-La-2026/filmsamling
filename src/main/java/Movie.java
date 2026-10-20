public class Movie {
    private String title;

    private String director;


    private int yearCreated;

    private boolean inColor;


    private int lengthInMinutes;


    private String genre;

    public  Movie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre){
        this.title=title;
        this.director=director;
        this.genre=genre;
        this.inColor=inColor;
        this.lengthInMinutes=lengthInMinutes;
        this.yearCreated=yearCreated;

    }

    public String getTitle() {
        return title;
    }

    public boolean isInColor() {
        return inColor;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
