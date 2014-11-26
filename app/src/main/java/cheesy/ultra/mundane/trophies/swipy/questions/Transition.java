package cheesy.ultra.mundane.trophies.swipy.questions;

/**
 * Created by lfendy on 26/11/14.
 */
public class Transition {
    private String fromState;
    private String toState;
    private Type type;

    public enum Type {
        YES,
        NO
    }

    public Transition(String[] rawData) {
        fromState = rawData[0];
        toState = rawData[1];
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

    public String getFromState() {
        return fromState;
    }

    public String getToState() {
        return toState;
    }

    public Type getType() {
        return type;
    }
}
