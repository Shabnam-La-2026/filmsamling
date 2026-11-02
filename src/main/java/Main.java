import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Velkommen til min filmsamling!");
        Controller controller = new Controller();
        UserInterface userInterface = new UserInterface(controller);

        userInterface.startProgram();
    }

}
