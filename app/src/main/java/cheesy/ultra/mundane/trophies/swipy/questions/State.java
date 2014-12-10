package cheesy.ultra.mundane.trophies.swipy.questions;

/**
 * Created by lfendy on 26/11/14.
 */
public class State {

    public static class Id {
        private String mId;
        public Id(String id){
            mId = id;
        }

        public String getId(){
          return mId;
        }

        @Override
        public boolean equals(Object id1) {
            return mId.equals(((Id) id1).getId());
        }
    }

    public enum Type {
        Question
    }

    private Id id;
    private String text;
    private Type type;

    public State(String[] rawData) {
        id = new Id(rawData[0]);
        text = rawData[1];
        type = getTypeFrom(rawData[2]);
    }

    public Id getId() {
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
