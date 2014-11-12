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
        watching_nsfw,
        end
    }

    private static Map<Question, String> mQuestions;
    private static Map<Question, Question> mNextQuestions;

    static {
        mQuestions = new HashMap<Question, String>();
        mQuestions.put(Question.are_you_at_work, "R U @ werk ?");
        mQuestions.put(Question.in_front_of_desk, "front of desk?");
        mQuestions.put(Question.watching_nsfw, "watching NSFW?");

        mNextQuestions = new HashMap<Question, Question>();
        mNextQuestions.put(Question.are_you_at_work, Question.in_front_of_desk);
        mNextQuestions.put(Question.in_front_of_desk, Question.watching_nsfw);
        mNextQuestions.put(Question.watching_nsfw, Question.end);
    }

    public static String getQuestion(Question q){
       return mQuestions.get(q);
    }

    public static Question getNextQuestion(Question q){
        return mNextQuestions.get(q);
    }
}
