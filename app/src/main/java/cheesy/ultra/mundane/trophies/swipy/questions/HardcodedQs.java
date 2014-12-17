package cheesy.ultra.mundane.trophies.swipy.questions;

/**
 * Created by lfendy on 12/11/14.
 */
public final class HardcodedQs {

    //write questions in terms states and transitions
    // [[]]
    //

    private static String[][] rawStates = {
            {"1", "yo, mofo, R u @ werk?", "q"},
            {"2", "front of desk?", "q"},
            {"3", "watching NSFW?", "q"},
            {"4", "Risk taker trophy!", "t"},
            {"5", "U suck.", "f"}
    };

    private static String[][] rawTransitions = {
            {"1", "2", "Y"},
            {"1", "5", "N"},
            {"2", "3", "Y"},
            {"2", "5", "N"},
            {"3", "4", "Y"},
            {"3", "5", "N"}
    };

    private static FiniteStateMachine mFsm;

    static {

        mFsm = new FiniteStateMachine(rawStates, rawTransitions);
    }

    public static State getFirstQuestion(){
        return mFsm.getFirstState();
    }

    public static State getAfterYes(State.Id s){
        return mFsm.getNextState(s, Transition.Type.YES);
    }

    public static State getAfterNo(State.Id s){
        return mFsm.getNextState(s, Transition.Type.NO);
    }

    public static State getStateFromId(State.Id id){
        return mFsm.getState(id);
    }


}
