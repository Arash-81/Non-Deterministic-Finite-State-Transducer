import java.util.LinkedList;

public class State {

    private static class Transition {
        private final State state;
        private final char [] input;
        private final char [] output;

        public Transition(State next, char[] input, char[] output) {
            this.state = next;
            this.input = input;
            this.output = output;
        }

        public Transition(State next, char input, char output) {
            this.state = next;
            this.input = new char[1];
            this.input[0] = input;
            this.output = new char[1];
            this.output[0] = output;
        }

        public boolean validInput(char in){
            for (char c : input)
                if (c == in)
                    return true;
            return false;
        }

        public char getOutput(char in){
            for (int i = 0; i < input.length; i++)
                if (in == input[i])
                    return output[i];
            return '\u0000';
        }
    }

    private final LinkedList<Transition> transitions;
    String name;
    private final boolean isFinal;

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

    public State[] getNextStates(char input) {
        LinkedList<State> next = new LinkedList<>();
        for (Transition transition : transitions){
            if (transition.validInput(input))
                next.add(transition.state);
        }
        return next.toArray(new State[0]);
    }

    public char computeOutput(State next, char input){
        for (Transition transition: transitions){
            if (transition.state == next)
                return transition.getOutput(input);
        }
        return ' ';
    }

    public boolean isFinal(){
        return isFinal;
    }
}
