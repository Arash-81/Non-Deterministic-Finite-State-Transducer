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

        public StateInfo getNextStateInfo(char in, State state){
            for (int i = 0; i < input.length; i++) {
                if (in == input[i])
                    return new StateInfo(in, output[i], state);
                if (in == '\u0000')
                    //add lambda transitions
                    return new StateInfo('\u0000', output[i], state);
            }
            return null;
        }
    }

    private final LinkedList<Transition> transitions;
    private final boolean isFinal;

    public State(boolean isFinal) {
        transitions = new LinkedList<>();
        this.isFinal = isFinal;
    }

    public void addTransition(State out, char input, char output){
        transitions.add(new Transition(out, input, output));
    }

    public void addTransition(State out, char[] input, char[] output){
        transitions.add(new Transition(out, input, output));
    }

    public StateInfo[] getNextStates(char input) {
        LinkedList<StateInfo> states = new LinkedList<>();
        for (Transition transition : transitions) {
            StateInfo state = transition.getNextStateInfo(input, transition.state);
            if (state != null)
                states.add(state);
        }

        return states.toArray(new StateInfo[0]);
    }

    public boolean isFinal(){
        return isFinal;
    }

    public State[] lambdaTransitions(){
        LinkedList<State> states = new LinkedList<>();
        for (Transition transition : transitions)
            if (transition.input[0] == '\u0000')
                states.add(transition.state);
        return states.toArray(new State[0]);
    }
}
