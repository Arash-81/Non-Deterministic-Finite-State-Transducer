import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private final ArrayList<State> states;
    private final HashMap<String, Integer> map;
    private int count;

    public Controller() {
        states = new ArrayList<>();
        map =  new HashMap<>();
        count = 0;
    }

    public void addState(String stateName, boolean isFinal){
        map.put(stateName, count);
        states.add(count, new State(isFinal));
        count++;
    }

    public void addTransition(String inStateName, char input, char output, String outStateName){
        State inState = states.get(map.get(inStateName));
        State outState = states.get(map.get(outStateName));
        inState.addTransition(outState, input, output);
    }

    public void addSetTransition(String inStateName, String inputSet, String outStateName){
        State inState = states.get(map.get(inStateName));
        State outState = states.get(map.get(outStateName));
        inState.addTransition(outState, inputSet.toCharArray(), inputSet.toCharArray());
    }

    public void parseInput(String inputString){
        if (!searchForOutput(states.get(0), inputString.toCharArray(), 0, ""))
            System.out.println("FAIL");
    }

    private boolean searchForOutput(State current, char[] input, int inputIndex, String output){
        if (inputIndex == input.length)
            return lambdaTransitions(current, output);

        StateInfo[] next = current.getNextStates(input[inputIndex]);
        for (StateInfo state : next){
            char ch = state.input();
            if (ch == '\u0000')
                searchForOutput(state.state(), input, inputIndex, output);
            else {
                if (state.output() != '\u0000')
                    searchForOutput(state.state(), input, inputIndex + 1, output + state.output());
                else
                    searchForOutput(state.state(), input, inputIndex + 1, output);
            }
        }
        return false;
    }

    private boolean lambdaTransitions(State current, String output){
        if (current.isFinal()){
            System.out.println(output);
            return true;
        }
        State[] states = current.lambdaTransitions();
        for (State state : states)
            lambdaTransitions(state, output);
        return false;
    }
}
