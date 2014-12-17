package cheesy.ultra.mundane.trophies.swipy.questions;

import java.util.ArrayList;

/**
 * Created by lfendy on 26/11/14.
 */
public class FiniteStateMachine {
    private final ArrayList<State> states = new ArrayList<State>();
    private final ArrayList<Transition> transitions = new ArrayList<Transition>();

    public FiniteStateMachine(String[][] states, String[][] transitions) {

        for(String[] state : states)
        {
            State s = new State(state);
            this.states.add(s);
        }

        for(String[] transition : transitions)
        {
            Transition t = new Transition(transition);
            this.transitions.add(t);
        }

    }

    public State getNextState(State.Id from, Transition.Type answer) {

        for(Transition t:this.transitions)
        {
            if(from.equals(t.getFromState()) && answer==t.getType()) {
                return this.getState(t.getToState());
            }
        }
        return null;
    }

    public State getState(State.Id stateId) {
        for(State s:this.states)
        {
            if(s.getId().equals(stateId))
                return s;
        }
        return null;
    }

    public State getFirstQuestionState() {
        for(State state : states){
            if(state.getType() == State.Type.Question){
                return state;
            }
        }
        return null;
    }
}
