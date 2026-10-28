import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
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
            printText("5. marker som set.");
            printText("6. listen over alle film");
            printText("7. listen over film du ikke har set ");
            printText("8. Afslut program.");

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
                    lastWatched();
                    break;
                case "8":
                    exit = true;
                    break;
                default:
                    printText("Invalid input: " + input);
                    break;
            }

        }
    }

    public void create() {
        System.out.println("\u001B[31mhvad vil du operate?\u001B[0m");
        String title;
        while (true) {
            try {
                System.out.print("\u001B[34mTitel:\u001B[0m ");
                title = scanner.nextLine();

                if (title.isEmpty() || title.matches("[-+]?\\d+")) {
                    throw new IllegalArgumentException("\u001B[31mindput skal ikke være tom eller et tal\u001B[0m");
                }
                break;

            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());

            }

        }
        System.out.print("\u001B[31mdirector:\u001B[0m ");
        String director;
        while (true) {
            try {
                director = scanner.nextLine();
                if (director.isEmpty()||director.matches("[-+]?\\d+")){
                    throw new IllegalArgumentException("\u001B[31mindput skal ikke være tom eller et tal\u001B[0m ");
                }
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }


        System.out.print("\u001B[34myearCreated:\u001B[0m");
        int yearCreated;
        while (true) {
            try {
                yearCreated = scanner.nextInt();
                scanner.nextLine();
                if (yearCreated<1888 || yearCreated>2026 ) {
                    throw new IllegalArgumentException("\u001B[31mugyldig årstal. indtast venligt et år meelem 1888 og 2026\u001B[0m");
                }
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("\u001B[31msvar med tal\u001B[0m");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }


        System.out.print("\u001B[34minColor:\u001B[0m ");

        boolean inColor;
        //String input;
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                inColor = true;
                break;
            } else if (input.equalsIgnoreCase("no")) {
                inColor = false;
                break;
            } else {
                System.out.println("\u001B[31msvar med yes or no\u001B[0m");

            }

        }

        System.out.print("\u001B[34m lengthMinutes:\u001B[0m ");
        int lengthInMinutes;
                while(true){
                    try {
                         lengthInMinutes= scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException  Exception ) {
                        System.out.println("\u001B[31msvar med tal\u001B[0m ");
                        scanner.nextLine();
                    }
                }


        System.out.print("\u001B[34mgenre:\u001B[0m ");
        String genre;
        while (true)
            try {
                genre= scanner.nextLine();
                if (genre.isEmpty() || genre.matches("[+-]?\\d+")){
                    throw new IllegalArgumentException("\u001B[31mindput skal ikke være tom eller et tal\u001B[0m ");
                }
                break;
            }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }


        boolean exist = false;
        for (Movie existing : controller.getMovies()) {
            if (existing.getTitle().equalsIgnoreCase(title)
                    && existing.getDirector().equalsIgnoreCase(director)
                    && existing.getGenre().equalsIgnoreCase(genre)
                    && existing.getYearCreated() == yearCreated
                    && existing.getIsInColor() == inColor
                    && existing.getLengthInMinutes() == lengthInMinutes)
                exist = true;
            break;
        }
        if (exist) {

            System.out.println(" \u001B[31mdu har alerede operertet den film\u001B[0m");

        } else {
            controller.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
            printText("\u001B[31m Din film er nu oprettet\u001B[0m");
        }
    }


    public void read() {
        System.out.println("\u001B[31m hvad vil du søge? ");
        System.out.println("> \u001B[0m");
        String searchText = scanner.nextLine();
        ArrayList<Movie> list = controller.searchMovies(searchText);
        if (list.isEmpty()) {
            System.out.println("\u001B[31mkan ikke finde denne søgning\u001B[0m");
        }
        for (Movie movie : list) {


            System.out.println(movie.getTitle() + "\u001B[31m er skrevet af\u001B[0m " + movie.getDirector() + " \u001B[31m i \u001B[0m " + movie.getYearCreated());


        }
        printText("\u001B[31m Slut på liste.\u001B[0m");
    }

    public void update() {
        System.out.print("\u001B[31mskriv title på filmen du vil opdatere: ");
        System.out.println("> \u001B[0m");
        String serchText = scanner.nextLine();
        ArrayList<Movie> list = controller.searchMovies(serchText);
        if (list.isEmpty()) {
            System.out.println("\u001B[31m der findes ikke filmen med dette title\u001B[0m");
        }else if (list.size()==1){
            edit(list.get(0));
        }else {
            for (int i = 0; i < list.size(); i++) {
                Movie m = list.get(i);
                System.out.println((i + 1) + " : " + m.getTitle() + " som er fra " + m.getYearCreated());
            }
            System.out.println("\u001B[31m hvilket af dem skal opdateres");
            System.out.println("> \u001B[0m");

            int nummer;
            while (true) {
                try {
                    nummer = scanner.nextInt();
                    if (nummer >= 1 && nummer <= list.size()) {
                        scanner.nextLine();
                        edit(list.get(nummer - 1));
                        break;
                    }
                 else {

                        System.out.println("vælge et nummer mellem 1 og "+ list.size());
                    }

                } catch (InputMismatchException e) {
                    System.out.println("vælge et tal");
                    scanner.nextLine();
                }
            }

        }

    }

    private void edit(Movie movie) {
        String title = movie.getTitle();
        String director = movie.getDirector();
        int yearCreated = movie.getYearCreated();
        boolean inColor = movie.getIsInColor();
        int lengthInMinutes = movie.getLengthInMinutes();
        String genre = movie.getGenre();

        System.out.println("\u001B[31m vil du ændre title (yes or no): \u001B[0m");
        String INPUT = scanner.nextLine();
        if (INPUT.equalsIgnoreCase("yes")) {
            System.out.println("\u001B[31mhvad skal title ændres til \u001B[0m");
            title = scanner.nextLine();
        }
        if (INPUT.equalsIgnoreCase("no")) {
            System.out.println("\u001B[31mtitle er uændret \u001B[0m");
        }
        System.out.println("\u001B[31m vil du ændre dirctor (yes or no): ");
        String INPUT1 = scanner.nextLine();
        if (INPUT1.equalsIgnoreCase("yes")) {
            System.out.println("\u001B[31m hvad skal dirctor ændres til\u001B[0m");
            director = scanner.nextLine();
        }
        if (INPUT1.equalsIgnoreCase("no")) {
            System.out.println("\u001B[31m dirctor er uændret\u001B[0m");
        }
        System.out.println("\u001B[31m vil du ændre yearCreated (yes or no): \u001B[0m");
        String INPUT2 = scanner.nextLine();
        if (INPUT2.equalsIgnoreCase("yes")) {
            System.out.println("\u001B[31mhvad skal yearCreated ændres til\u001B[0m");
            yearCreated = scanner.nextInt();
            scanner.nextLine();
        }
        if (INPUT2.equalsIgnoreCase("no")) {
            System.out.println("\u001B[31myearCreated er uændret\u001B[0m");
        }

        do {

            System.out.println("\u001B[31m vil du ændre inColor (yes or no):\u001B[0m ");
            String INPUt = scanner.nextLine();
            if (INPUt.equalsIgnoreCase("yes")) {
                System.out.println("\u001B[31m hvad skal inColor ændres til\u001B[0m");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("yes")) {
                    inColor = true;
                    break;
                } else if (input.equalsIgnoreCase("no")) {
                    inColor = false;
                    break;
                }


            } else if (INPUt.equalsIgnoreCase("no")) {
                System.out.println("\u001B[31minColor er uændtet\u001B[0m");
                break;
            }
        } while (true);


        System.out.println("\u001B[31m vil du ændre lengthMinutes (yes or no):\u001B[0m ");
        String INPUT3 = scanner.nextLine();
        if (INPUT3.equalsIgnoreCase("yes")) {
            System.out.println("\u001B[31mhvad skal lengthMinutes ændres til\u001B[0m");
            lengthInMinutes = scanner.nextInt();
            scanner.nextLine();
        }
        if (INPUT3.equalsIgnoreCase("no")) {
            System.out.println("\u001B[31mlengthMinutes er uændret\u001B[0m");
        }


        System.out.println("\u001B[31m vil du ændre genre (yes or no): \u001B[0m");
        String INPUT4 = scanner.nextLine();
        if (INPUT4.equalsIgnoreCase("yes")) {
            System.out.println("\u001B[31mhvad skal genre ændres til\u001B[0m");
            genre = scanner.nextLine();
        }
        if (INPUT4.equalsIgnoreCase("no")) {
            System.out.println("\u001B[31m genre er uændret\u001B[0m");
        }
        controller.editMovie(movie, title, director, yearCreated, inColor, lengthInMinutes, genre);
        System.out.println("\u001B[31mfilmen er ændret til :\u001B[0m ");
        System.out.println("\u001B[34mtitle:\u001B[0m " + movie.getTitle() + " \u001B[34m director:\u001B[0m " + movie.getDirector() + " \u001B[34m year created:\u001B[0m " + movie.getYearCreated() + " \u001B[34m genre:\u001B[0m " + movie.getGenre() + " \u001B[34m length(minutes):\u001B[0m " + movie.getLengthInMinutes() + " \u001B[34m inColor er:\u001B[0m " + movie.getIsInColor());


    }

    public void delete() {
        System.out.println("\u001B[31mhvad vil du delete? ");
        System.out.print(" skriv title> \u001B[0m");
        String title = scanner.nextLine();
        ArrayList<Movie> list = controller.searchMovies(title);
        ArrayList<Movie> matchTitle = new ArrayList<>();
        for (Movie movie : list) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchTitle.add(movie);
            }
        }
        if (matchTitle.isEmpty()) {
            System.out.println("\u001B[31mthis movie not exist\u001B[0m");
        } else if (matchTitle.size() > 1) {
            int count = 0;
            for (int i = 0; i < matchTitle.size(); i++) {
                Movie m = matchTitle.get(i);
                System.out.println((i + 1) + "\u001B[31m :\u001B[0m " + m.getTitle() + "\u001B[31m som er fra\u001B[0m " + m.getYearCreated());
            }
            System.out.println("\u001B[31mhvilket af dem skal slettes\u001B[0m");
            System.out.println("> ");
            int nummer = scanner.nextInt();
            if (nummer >= 1 && nummer <= matchTitle.size()) {
                scanner.nextLine();
                // controller.deleteMovie(list.get(nummer - 1));
                //count++;
                System.out.println("\u001B[31mvil du slette\u001B[0m " + matchTitle.get(nummer - 1).getTitle());
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    controller.deleteMovie(matchTitle.get(nummer - 1));
                    count++;
                    System.out.println(matchTitle.get(nummer - 1).getTitle() + "\u001B[31m er blevet slettet \u001B[0m" + count + "\u001B[31m film.\u001B[0m");
                    System.out.println("\u001B[31mder blevet slettet\u001B[0m " + count + " \u001B[31mfilm\u001B[0m");
                } else if (answer.equalsIgnoreCase("no")) {
                    System.out.println(matchTitle.get(nummer - 1).getTitle() + "\u001B[31m blev ikke slettet\u001B[0m");
                }
            }
        } else {
            int count = 0;
            //controller.deleteMovie(list.get(0));
            System.out.println("\u001B[31mvil du slette \u001B[0m" + matchTitle.get(0).getTitle());
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                controller.deleteMovie(matchTitle.get(0));
                count++;
                System.out.println(matchTitle.get(0).getTitle() + "\u001B[31m er blevet slettet\u001B[0m");
                System.out.println("\u001B[31mder blevet slettet " + count + " film\u001B[0m");
            } else if (answer.equalsIgnoreCase("no")) {
                System.out.println(matchTitle.get(0).getTitle() + " \u001B[31mblev ikke slettet\u001B[0m");
            }


            // System.out.println(list.get(0).getTitle() + " \u001B[31mer slettet\u001B[0m");
            //System.out.println("\u001B[31mdet blevet slettet\u001B[0m " + count + "\u001B[31m film.\u001B[0m");


        }
    }

    public void markAsSeen() {
        System.out.println("\u001B[31mhvad har du set? ");
        System.out.print(" skriv title> \u001B[0m");
        String title = scanner.nextLine();
        ArrayList<Movie> list = controller.getMovies();
        if (list.isEmpty()) {
            System.out.println("\u001B[31mthe movie not exist\u001B[0m");
        }
        int count = 0;
        for (Movie movie : list) {
            if (title.equalsIgnoreCase(movie.getTitle())) {
                ++count;
                controller.markAsWatched(movie);
            }

            System.out.println(movie.getTitle() + "\u001B[31m fra\u001B[0m " + movie.getYearCreated() + " \u001B[31maf\u001B[0m " + movie.getDirector() + "\u001B[31m er markeret som set\u001B[0m");
        }
        System.out.println("\u001B[31mdet blevet markeret \u001B[0m" + count + " \u001B[31mfilm.\u001B[0m");

    }

    public void lastWatched() {
        System.out.println("\u001B[31m her kan du se listen over de film du ikke har set siden...");
        System.out.println("hvor mange day  siden skal du undersøge?\u001B[0m");
        int day = scanner.nextInt();

        scanner.nextLine();
        java.util.ArrayList<Movie> list = controller.getMoviesNotWatchedForDays(day);
        if (list.isEmpty()) {
            System.out.println("\u001B[31mder blev fundet ingen film som du ikke har set\u001B[0m " + day + "\u001B[31m siden\u001B[0m");
        } else {
            for (Movie movie : list) {
                System.out.println("\u001B[31m du har ikke set \u001B[0m" + movie.getTitle() + " \u001B[31mfor \u001B[0m" + day + " \u001B[31msiden.\u001B[0m");
            }
        }

    }


    public void showMovies() {
        ArrayList<Movie> movies = controller.getMovies();
        if (movies.isEmpty()) {
            System.out.println("\u001B[31mder er ingen film");
        }
        System.out.println("listen over alle film er: \u001B[0m");
        for (Movie movie : movies) {

            System.out.println("\u001B[34mtitle:\u001B[0m " + movie.getTitle() + " \u001B[34m director:\u001B[0m " + movie.getDirector() + " \u001B[34m year created:\u001B[0m " + movie.getYearCreated() + " \u001B[34m genre:\u001B[0m " + movie.getGenre() + " \u001B[34m length(minutes):\u001B[0m " + movie.getLengthInMinutes() + " \u001B[34m inColor er:\u001B[0m " + movie.getIsInColor());


        }
    }


    public void startProgram() {
        System.out.println("----- MENU-----");

        menu();
        printText("Farvel og tak!");
    }
}