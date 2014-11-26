package cheesy.ultra.mundane.trophies.swipy.questions;

/**
 * Created by lfendy on 26/11/14.
 */
public class State {

    public enum Type {
        Question
    }

    private String id;
    private String text;
    private Type type;

    public State(String[] rawData) {
        id = rawData[0];
        text = rawData[1];
        type = getTypeFrom(rawData[2]);
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Type getType() {
        return type;
    }

    private Type getTypeFrom(String t){
        if("q".equalsIgnoreCase(t)) {
            return Type.Question;
        }
        return null;
    }
}
