package cheesy.ultra.mundane.trophies.swipy.questions;

/**
 * Created by lfendy on 26/11/14.
 */
public class Node {

    public enum NodeType {
        Question
    }

    private String id;
    private String text;
    private NodeType type;

    public Node(String[] rawData) {
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

    public NodeType getType() {
        return type;
    }

    private NodeType getTypeFrom(String t){
        if("q".equalsIgnoreCase(t)) {
            return NodeType.Question;
        }
        return null;
    }
}
