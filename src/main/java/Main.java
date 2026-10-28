import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Velkommen til min filmsamling!");
        Controller controller = new Controller();
        UserInterface userInterface = new UserInterface(controller);
        userInterface.startProgram();
    }

}
