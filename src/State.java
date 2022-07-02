import java.util.LinkedList;

public class State {

    private final LinkedList<Transition> transitions;
    private final boolean isFinal;

    private static class Transition {
        private final State next;
        private final char [] input;
        private final char [] output;

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

        public StateInfo getNextStateInfo(char in, State state){
            //add lambda transitions
            if (input[0] == '\u0000')
                return new StateInfo('\u0000', output[0], state);
            for (int i = 0; i < input.length; i++)
                if (in == input[i])
                    return new StateInfo(in, output[i], state);
            return null;
        }
    }

    public State(boolean isFinal) {
        transitions = new LinkedList<>();
        this.isFinal = isFinal;
    }

    public void addTransition(State out, char input, char output){
        transitions.add(new Transition(out, input, output));
    }

    public void addTransitions(State out, char[] input, char[] output){
        transitions.add(new Transition(out, input, output));
    }

    public StateInfo[] getNextStatesInfo(char input) {
        LinkedList<StateInfo> statesInfo = new LinkedList<>();
        for (Transition transition : transitions) {
            StateInfo stateInfo = transition.getNextStateInfo(input, transition.next);
            if (stateInfo != null)
                statesInfo.add(stateInfo);
        }
        return statesInfo.toArray(new StateInfo[0]);
    }

    public boolean isFinal(){
        return isFinal;
    }

    public StateInfo[] lambdaTransitions(){
        LinkedList<StateInfo> statesInfo = new LinkedList<>();
        for (Transition transition : transitions)
            if (transition.input[0] == '\u0000')
                statesInfo.add(new StateInfo('\u0000', transition.output[0], transition.next));
        return statesInfo.toArray(new StateInfo[0]);
    }
}
