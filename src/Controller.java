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
        states.add(count, new State(stateName, isFinal));
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
        search(states.get(0), inputString.toCharArray(), 0, "");
    }

    private void search(State current, char[] input, int iIn, String output){
        if (current.isFinal() && iIn == input.length) {
            System.out.println(output);
            return;
        }
        if (iIn == input.length)
            //check for lambda transitions
            return;

        State[] next = current.getNextStates(input[iIn]);
        for (State state : next){
            char ch = current.computeOutput(state, input[iIn]);
            if (ch == '\u0000')
                search(state, input, iIn, output);
            else
                search(state, input, iIn + 1, output + ch);
        }
    }
}
