import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.addState("q0", false);
        controller.addState("q1", false);
        controller.addState("q2", true);
        controller.addSetTransition("q0", "abcdefghijklmnopqrstuvwxyz", "q0");
        controller.addSetTransition("q0", "abcdefghijklmnopqrtuvwxyz", "q2");
        controller.addTransition("q0", 's', 's', "q1");
        controller.addTransition("q1", '\u0000', '\u0000', "q2");
        controller.addTransition("q1", 's', '\u0000', "q2");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        controller.parseInput(input);

    }
}
