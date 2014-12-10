package cheesy.ultra.mundane.trophies.swipy.questions;

import java.util.HashMap;
import java.util.Map;

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


    public static enum Question{
        are_you_at_work,
        in_front_of_desk,
        watching_nsfw,
        fail,
        win
    }

    private static class Option {
        private Question mYes;
        private Question mNo;
        public Option(Question yes, Question no){
           mYes = yes;
           mNo = no;
        }

        public Question getmYes() {
            return mYes;
        }

        public Question getmNo() {
            return mNo;
        }
    }

    private static Map<Question, String> mQuestions;
    //private static Map<Question, Question> mNextQuestions;
    private static Map<Question, Option> mNextQuestions;

    private static FiniteStateMachine mFsm;


    static {
        mQuestions = new HashMap<Question, String>();
        mQuestions.put(Question.are_you_at_work, "R U @ werk ?");
        mQuestions.put(Question.in_front_of_desk, "front of desk?");
        mQuestions.put(Question.watching_nsfw, "watching NSFW?");

        mNextQuestions = new HashMap<Question, Option>();
        mNextQuestions.put(Question.are_you_at_work,
                new Option(Question.in_front_of_desk, Question.fail));
        mNextQuestions.put(Question.in_front_of_desk,
                new Option(Question.watching_nsfw,Question.fail));
        mNextQuestions.put(Question.watching_nsfw,
                new Option(Question.win, Question.fail));

        mFsm = new FiniteStateMachine(rawStates, rawTransitions);
    }

    public static String getQuestion(Question q){
       return mQuestions.get(q);
    }

    public static Question getYesOption(Question q) {
        return mNextQuestions.get(q).getmYes();
    }

    public static Question getNoOption(Question q) {
        return mNextQuestions.get(q).getmNo();
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

    public static State getQuestionFromState(State.Id id){
        return mFsm.getState(id);
    }


}
