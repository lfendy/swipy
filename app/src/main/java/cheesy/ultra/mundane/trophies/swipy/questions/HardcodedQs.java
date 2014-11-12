package cheesy.ultra.mundane.trophies.swipy.questions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lfendy on 12/11/14.
 */
public final class HardcodedQs {

    public static enum Question{
        are_you_at_work,
        in_front_of_desk,
        watching_nsfw
    }

    private static Map<Question, String> mQuestions;

    static {
        mQuestions = new HashMap<Question, String>();
        mQuestions.put(Question.are_you_at_work, "R U @ werk ?");
        mQuestions.put(Question.in_front_of_desk, "front of desk?");
        mQuestions.put(Question.watching_nsfw, "watching NSFW?");
    }

    public static String getQuestion(Question q){
       return mQuestions.get(q);
    }

    public static Question getNextQuestion(Question q){
        return Question.in_front_of_desk;
    }
}
