package cheesy.ultra.mundane.trophies.swipy.questions;


/**
 * Created by lfendy on 26/11/14.
 */
public class Transition {
    private State.Id fromState;
    private State.Id toState;
    private Type type;

    public enum Type {
        YES,
        NO
    }

    public Transition(String[] rawData) {
        fromState = new State.Id(rawData[0]);
        toState = new State.Id(rawData[1]);
        type = getTypeFrom(rawData[2]);
    }

    private Type getTypeFrom(String t) {
        if("y".equalsIgnoreCase(t)) {
            return Type.YES;
        } else if("n".equalsIgnoreCase(t)){
            return Type.NO;
        }
        return null;
    }

    public State.Id getFromState() {
        return fromState;
    }

    public State.Id getToState() {
        return toState;
    }

    public Type getType() {
        return type;
    }
}
