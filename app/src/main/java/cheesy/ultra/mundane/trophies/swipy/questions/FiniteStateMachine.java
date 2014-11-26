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

    public State getNextState(String from, Transition.Type answer) {

        for(Transition t:this.transitions)
        {
            if(from==t.getFromState() && answer==t.getType()) {
                return this.getState(t.getToState());
            }
        }
        return null;
    }

    private State getState(String toState) {
        for(State s:this.states)
        {
            if(s.getId()==toState)
                return s;
        }
        return null;
    }
}
