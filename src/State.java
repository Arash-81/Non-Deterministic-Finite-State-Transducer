import java.util.LinkedList;

public class State {

    private static class Transition {
        State next;
        char [] input;
        char [] output;

        public Transition(State next, char[] input, char[] output) {
            this.next = next;
            this.input = input;
            this.output = output;
        }

        public Transition(State next, char input, char output) {
            this.next = next;
            this.input = new char[1];
            this.input[0] = input;
            this.output = new char[1];
            this.output[0] = output;
        }
    }
    LinkedList<Transition> transitions;
    String name;
    boolean isFinal;

    public State(String name, boolean isFinal) {
        transitions = new LinkedList<>();
        this.name = name;
        this.isFinal = isFinal;
    }

    public void addTransition(State out, char input, char output){
        transitions.add(new Transition(out, input, output));
    }

    public void addTransition(State out, char[] input, char[] output){
        transitions.add(new Transition(out, input, output));
    }
}
