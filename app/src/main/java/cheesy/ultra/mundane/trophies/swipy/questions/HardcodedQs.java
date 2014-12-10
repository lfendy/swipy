package cheesy.ultra.mundane.trophies.swipy.questions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lfendy on 12/11/14.
 */
public final class HardcodedQs {

    //write questions in terms states and transitions
    //



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


}
