import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Controller controller;
    private Scanner scanner;


    public UserInterface(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);

    }

    public void printText(String text) {
        System.out.println(text);
    }

    public void menu() {
        boolean exit = false;
        while (!exit) {
            printText("1. Opret ny film.");
            printText("2. Film info.");
            printText("3. Opdatér film.");
            printText("4. Slet film.");
            printText("5.har du set filmen.");
            printText("6.vis alle filme");
            printText("7. Afslut program.");

            System.out.print(">");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    create();
                    break;
                case "2":
                    read();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    markAsSeen();
                    break;
                case "6":
                    showMovies();
                    break;
                case "7":
                    exit = true;
                    break;
                default:
                    printText("Invalid input: " + input);
                    break;
            }

        }
    }

    public void create() {
        System.out.println("\u001B[31mher kan du operete en film:\u001B[0m");
        System.out.print("\u001B[34mTitel: ");
        String title = scanner.nextLine();
        System.out.print("director: ");
        String director = scanner.nextLine();
        System.out.print("yearCreated: ");
        int yearCreated = scanner.nextInt();
        scanner.nextLine();
        System.out.print("inColor: ");
        String input = scanner.nextLine();
        boolean inColor;
        if (input.equalsIgnoreCase("yes")) {
            inColor = true;
        } else if (input.equalsIgnoreCase("no")) {
            inColor = false;
        } else {
            System.out.println("answer with yes or no");
            return;
        }

        System.out.print("lengthMinutes: ");
        int lengthInMinutes = scanner.nextInt();
        scanner.nextLine();
        System.out.print("genre:\u001B[0m ");
        String genre = scanner.nextLine();


        controller.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);


        printText("\u001B[31mDin film er nu oprettet\u001B[0m");
    }

    public void read() {
        System.out.println("\u001B[31mhvad vil du søge? ");
        System.out.println("> \u001B[0m");
        String searchText = scanner.nextLine();
        ArrayList<Movie> list = controller.searchMovies(searchText);
        if (list.isEmpty()) {
            System.out.println("kan ikke finde denne søgning");
        }
        for (Movie movie : list) {


            System.out.println(movie.getTitle() + " er skrevet af " + movie.getDirector() + " i " + movie.getYearCreated());


        }
        printText("\u001B[31 Slut på liste.\u001B[0m");
    }

    public void update() {
        System.out.print("\u001B[31mskriv title på filmen du vil opdatere: ");
        System.out.println("> \u001B[0m");
        String serchText = scanner.nextLine();
        ArrayList<Movie> list = controller.searchMovies(serchText);
        if (list.isEmpty()) {
            System.out.println("\u001B[31der findes ikke filmen med dette title\u001B[0m");
        } else if (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                Movie m = list.get(i);
                System.out.println((i + 1) + " : " + m.getTitle() + " som er fra " + m.getYearCreated());
            }
            System.out.println("hvilket af dem skal opdateres");
            System.out.println("> ");
            int nummer = scanner.nextInt();
            if (nummer >= 1 && nummer <= list.size()) {
                edit(list.get(nummer - 1));
            }
        } else {
            edit(list.get(0));
        }
    }

    private void edit(Movie movie) {
//        System.out.println("\u001B[31hvad vil du ændre dem til? ");
//        System.out.print("> \u001B[0m");
        System.out.println("\u001B[34m vil du ændre title (yes or no): ");
        String INPUT = scanner.nextLine();
        if (INPUT.equalsIgnoreCase("yes")) {
            System.out.println("hvad skal title ændres til");
            movie.setTitle(scanner.nextLine());
        }
        if (INPUT.equalsIgnoreCase("no")){
            System.out.println("title er uændret");
        }
        System.out.println("\u001B[34m vil du ændre dirctor (yes or no): ");
        String INPUT1 = scanner.nextLine();
        if (INPUT1.equalsIgnoreCase("yes")) {
            System.out.println("hvad skal dirctor ændres til");
            movie.setDirector(scanner.nextLine());
        }
        if (INPUT1.equalsIgnoreCase("no")){
            System.out.println("dirctor er uændret");
        }
        System.out.println("\u001B[34m vil du ændre yearCreated (yes or no): ");
        String INPUT2 = scanner.nextLine();
        if (INPUT2.equalsIgnoreCase("yes")) {
            System.out.println("hvad skal yearCreated ændres til");
            movie.setYearCreated(scanner.nextInt());
        }
        if (INPUT2.equalsIgnoreCase("no")){
            System.out.println("yearCreated er uændret");
        }


        scanner.nextLine();

    boolean inColor;
        do

    {


        System.out.println("inColor (yes or no):  ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            inColor = true;
            break;
        } else if (input.equalsIgnoreCase("no")) {
            inColor = false;
            break;
        }

    } while(true);
        System.out.println("\u001B[34m vil du ændre lengthMinutes (yes or no): ");
        String INPUT3 = scanner.nextLine();
        if (INPUT3.equalsIgnoreCase("yes")) {
            System.out.println("hvad skal lengthMinutes ændres til");
            movie.setLengthInMinutes(scanner.nextInt());
        }
        if (INPUT3.equalsIgnoreCase("no")){
            System.out.println("lengthMinutes er uændret");
        }

        scanner.nextLine();
        System.out.println("\u001B[34m vil du ændre genre (yes or no): ");
        String INPUT4 = scanner.nextLine();
        if (INPUT4.equalsIgnoreCase("yes")) {
            System.out.println("hvad skal genre ændres til");
            movie.setGenre(scanner.nextLine());
        }
        if (INPUT4.equalsIgnoreCase("no")){
            System.out.println("genre er uændret");
        }

}

public void delete() {
    System.out.println("\u001B[31hvad vil du delete? ");
    System.out.print(" skriv title> \u001B[0m");
    String title = scanner.nextLine();
    ArrayList<Movie> list = controller.getMovies();

    if (list.isEmpty()) {
        System.out.println("this movie not exist");
    }
    int count = 0;
    for (Movie movie : list) {
        if (list.contains(controller.getMovies())) {
            count++;
            controller.deleteMovie(movie);

        }

        System.out.println(movie.getTitle() + " \u001B[31er slettet\u001B[0m");
    }
    System.out.println("det blevet slettet " + count + " film.");

}

public void markAsSeen() {
    System.out.println("\u001B[31hvad har du set? ");
    System.out.print(" skriv title> \u001B[0m");
    String title = scanner.nextLine();
    ArrayList<Movie> list = controller.getMovies();
    if (list.isEmpty()) {
        System.out.println("the movie not exist");
    }
    int count = 0;
    for (Movie movie : list) {
        if (title.equalsIgnoreCase(movie.getTitle())) {
            ++count;
            controller.markAsWatched(movie);
        }

        System.out.println(movie.getTitle() + "\u001B[31 er markeret\u001B[0m");
    }
    System.out.println("det blevet markeret " + count + " film.");

}

public void showMovies() {
    ArrayList<Movie> movies = controller.getMovies();
    if (movies.isEmpty()) {
        System.out.println("der er ingen film");
    }
    System.out.println("listen over alle film er: ");
    for (Movie movie : movies) {

        System.out.println("title: " + movie.getTitle() + " director: " + movie.getDirector() + " year created: " + movie.getYearCreated() + " genre: " + movie.getGenre() + " length(minutes): " + movie.getLengthInMinutes());

    }
}


public void startProgram() {
    menu();
    printText("Farvel og tak!");
}
}