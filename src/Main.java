/****
 * Arash Gholamdokht 9922762286
 ****/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Controller controller = new Controller();

        //1'st rule
        controller.addState("q0", false);
        controller.addState("q1", false);
        controller.addState("q2", false);
        controller.addState("q3", true);
        controller.addState("q4", false);
        controller.addSetTransition("q0", "abcdefghijklmnopqrstuvwxyz", "q0");
        controller.addTransition("q0", 's', 's', "q1");
        controller.addTransition("q0", 'x', 'x', "q1");
        controller.addTransition("q0", 'z', 'z', "q1");
        controller.addTransition("q0", 'o', 'o', "q1");
        controller.addTransition("q1", '\u0000', 'e', "q2");
        controller.addTransition("q2", '\u0000', 's', "q3");
        controller.addTransition("q0", 's', 's', "q4");
        controller.addTransition("q0", 'c', 'c', "q4");
        controller.addTransition("q4", 'h', 'h', "q1");

        //2'nd rule
        controller.addState("q5", false);
        controller.addSetTransition("q0", "bcdfghjklmnpqrstvwxyz", "q5");
        controller.addTransition("q5", 'y', 'i', "q1");

        //3'rd rule
        controller.addState("q6", false);
        controller.addSetTransition("q0", "uaeio", "q6");
        controller.addTransition("q6", 'y', 'y', "q2");

        //4'th rule
        controller.addState("q7", false);
        controller.addTransition("q0", 'f', 'v', "q1");
        controller.addTransition("q0", 'f', 'v', "q7");
        controller.addTransition("q7", 'e', 'e', "q2");

        //regular nouns rule
        controller.addSetTransition("q0", "abcdgijklmnpqrtuvw", "q2");

        controller.addState("q8", false);
        controller.addSetTransition("q0", "abdefghijklmnopqrtuvwxyz", "q8");
        controller.addTransition("q8", 'h', 'h', "q2");

        controller.addState("q9", false);
        controller.addSetTransition("q0", "abcdeghijklmnopqrstuvwxyz", "q9");
        controller.addTransition("q9", 'e', 'e', "q2");

        File file = new File("test.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String input = scanner.next();
            System.out.print(input + " -> ");
            controller.parseInput(input);
        }
    }
}
