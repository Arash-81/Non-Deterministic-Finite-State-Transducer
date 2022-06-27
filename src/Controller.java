import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    ArrayList<State> states;
    HashMap<String, Integer> map;
    int count = 0;

    public Controller() {
        states = new ArrayList<>();
        map =  new HashMap<>();
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

    public void addSetTransition(String inStateName, char []inputSet, String outStateName){
        State inState = states.get(map.get(inStateName));
        State outState = states.get(map.get(outStateName));
        inState.addTransition(outState, inputSet, inputSet);
    }

    public String[] parseInput(String inputString){
        return null;
    }
}
