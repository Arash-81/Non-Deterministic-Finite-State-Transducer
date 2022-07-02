import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private final ArrayList<State> states;
    private final HashMap<String, Integer> map;
    private int ALIndex;
    private boolean isAccept;

    public Controller() {
        states = new ArrayList<>();
        map =  new HashMap<>();
        ALIndex = 0;
    }

    public void addState(String stateName, boolean isFinal){
        map.put(stateName, ALIndex);
        states.add(ALIndex, new State(isFinal));
        ALIndex++;
    }

    public void addTransition(String inStateName, char input, char output, String outStateName){
        State inState = states.get(map.get(inStateName));
        State outState = states.get(map.get(outStateName));
        inState.addTransition(outState, input, output);
    }

    public void addSetTransition(String inStateName, String inputSet, String outStateName){
        State inState = states.get(map.get(inStateName));
        State outState = states.get(map.get(outStateName));
        inState.addTransitions(outState, inputSet.toCharArray(), inputSet.toCharArray());
    }

    public void parseInput(String inputString){
         isAccept = false;
        searchForOutput(states.get(0), inputString.toCharArray(), 0, "");
        if (!isAccept)
            System.out.println("FAIL");
    }

    private void searchForOutput(State current, char[] input, int inputIndex, String output){
        if (inputIndex == input.length) {
            lambdaTransitions(current, output);
            return;
        }

        StateInfo[] statesInfo = current.getNextStatesInfo(input[inputIndex]);
        for (StateInfo stateInfo : statesInfo){
            char ch = stateInfo.input();
            if (ch == '\u0000')
                searchForOutput(stateInfo.state(), input, inputIndex, output + stateInfo.output());
            else {
                if (stateInfo.output() != '\u0000')
                    searchForOutput(stateInfo.state(), input, inputIndex + 1, output + stateInfo.output());
                else
                    searchForOutput(stateInfo.state(), input, inputIndex + 1, output);
            }
        }
    }

    private void lambdaTransitions(State current, String output){
        if (current.isFinal()){
            System.out.println(output);
             isAccept = true;
        }
        StateInfo[] statesInfo = current.lambdaTransitions();
        for (StateInfo stateInfo : statesInfo) {
            char ch = stateInfo.output();
            if (ch != '\u0000')
                lambdaTransitions(stateInfo.state(), output + ch);
            else
                lambdaTransitions(stateInfo.state(), output);
        }
    }
}
