package cheesy.ultra.mundane.trophies.swipy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;


import cheesy.ultra.mundane.trophies.swipy.fragment.QuestionFragment;
import cheesy.ultra.mundane.trophies.swipy.questions.FiniteStateMachine;
import cheesy.ultra.mundane.trophies.swipy.questions.HardcodedQs;
import cheesy.ultra.mundane.trophies.swipy.questions.State;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

    private void assertNextActivity(Activity activity, Class nextActivityClass){
        ShadowActivity shadowHome = Robolectric.shadowOf(activity);
        assertThat(shadowHome.peekNextStartedActivityForResult().intent.getComponent(),
                equalTo(new ComponentName(activity, nextActivityClass)));
    }

    @Test
    public void testWillShowTrophyForTheFirstTime(){
        FragmentActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();

        assertNextActivity(activity, TrophyActivity.class);

        assertThat(activity.getSupportFragmentManager().findFragmentById(R.id.container), is(nullValue()));
    }

    private MainActivity createMainActivity() {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);

        FragmentActivity activity = controller.get();

        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences(activity.getString(R.string.preference_file), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(activity.getString(R.string.first_trophy), true).commit();

        activity = controller.create().get();
        return (MainActivity)activity;
    }

    @Test
    public void willShowQuestionsForTheSubsequentTime() {

        String[][] questionStates = {{"0", "Question 1", "q"}};

        String[][] questionTransitions = {};

        HardcodedQs.mFsm = new FiniteStateMachine(questionStates, questionTransitions);

        FragmentActivity activity = createMainActivity();

        ShadowActivity shadowHome = Robolectric.shadowOf(activity);
        assertThat(shadowHome.peekNextStartedActivityForResult(), is(nullValue()));

        Fragment questionFragment = activity.getSupportFragmentManager().findFragmentById(R.id.container);
        assertThat(questionFragment, is(notNullValue()));
        assertThat(questionFragment, isA(Fragment.class));
        assertThat(questionFragment.getArguments().getString(QuestionFragment.CURRENT_QUESTION_ID), equalTo("0"));
        assertThat(questionFragment.getArguments().getString(QuestionFragment.CURRENT_QUESTION_TEXT), equalTo("Question 1"));
    }

    @Test
    public void willShowNextQuestionOnYesAnswer(){
        String[][] questionStates = {
                {"0", "Question 1", "q"},
                {"1", "Question Yes", "q"},
                {"2", "Question No", "q"}};

        String[][] questionTransitions = {
                {"0", "1", "Y"},
                {"0", "2", "N"}};

        HardcodedQs.mFsm = new FiniteStateMachine(questionStates, questionTransitions);

        MainActivity activity = createMainActivity();

        activity.handleYes(HardcodedQs.getStateFromId(new State.Id("0")).getId());

        Fragment questionFragment = activity.getSupportFragmentManager().findFragmentById(R.id.container);
        assertThat(questionFragment, is(notNullValue()));
        assertThat(questionFragment, isA(Fragment.class));
        assertThat(questionFragment.getArguments().getString(QuestionFragment.CURRENT_QUESTION_ID), equalTo("1"));
        assertThat(questionFragment.getArguments().getString(QuestionFragment.CURRENT_QUESTION_TEXT), equalTo("Question Yes"));
    }

    @Test
    public void willShowNextQuestionOnNoAnswer(){
        String[][] questionStates = {
                {"0", "Question 1", "q"},
                {"1", "Question Yes", "q"},
                {"2", "Question No", "q"}};

        String[][] questionTransitions = {
                {"0", "1", "Y"},
                {"0", "2", "N"}};

        HardcodedQs.mFsm = new FiniteStateMachine(questionStates, questionTransitions);

        MainActivity activity = createMainActivity();

        activity.handleNo(HardcodedQs.getStateFromId(new State.Id("0")).getId());

        Fragment questionFragment = activity.getSupportFragmentManager().findFragmentById(R.id.container);
        assertThat(questionFragment, is(notNullValue()));
        assertThat(questionFragment, isA(Fragment.class));
        assertThat(questionFragment.getArguments().getString(QuestionFragment.CURRENT_QUESTION_ID), equalTo("2"));
        assertThat(questionFragment.getArguments().getString(QuestionFragment.CURRENT_QUESTION_TEXT), equalTo("Question No"));
    }

    @Test
    public void willShowTrophyForCorrectYesAnswers(){
        String[][] questionStates = {
                {"0", "Question 1", "q"},
                {"1", "Trophy Yes", "t"}};

        String[][] questionTransitions = {{"0", "1", "Y"}};

        HardcodedQs.mFsm = new FiniteStateMachine(questionStates, questionTransitions);

        MainActivity activity = createMainActivity();

        activity.handleYes(HardcodedQs.getStateFromId(new State.Id("0")).getId());

        assertNextActivity(activity, TrophyActivity.class);
    }

    @Test
    public void willShowTrophyForCorrectNoAnswers(){
        String[][] questionStates = {
                {"0", "Question 1", "q"},
                {"1", "Trophy No", "t"}};

        String[][] questionTransitions = {{"0", "1", "N"}};

        HardcodedQs.mFsm = new FiniteStateMachine(questionStates, questionTransitions);

        MainActivity activity = createMainActivity();

        activity.handleNo(HardcodedQs.getStateFromId(new State.Id("0")).getId());

        assertNextActivity(activity, TrophyActivity.class);
    }

    @Test
    public void willShowFailForAnyIncorrectYesAnswers(){
        String[][] questionStates = {
                {"0", "Question 1", "q"},
                {"1", "Fail Yes", "f"}};

        String[][] questionTransitions = {{"0", "1", "Y"}};

        HardcodedQs.mFsm = new FiniteStateMachine(questionStates, questionTransitions);

        MainActivity activity = createMainActivity();

        activity.handleYes(HardcodedQs.getStateFromId(new State.Id("0")).getId());

        assertNextActivity(activity, EndActivity.class);
    }

    @Test
    public void willShowFailForAnyIncorrectNoAnswers(){
        String[][] questionStates = {
                {"0", "Question 1", "q"},
                {"1", "Fail No", "f"}};

        String[][] questionTransitions = {{"0", "1", "N"}};

        HardcodedQs.mFsm = new FiniteStateMachine(questionStates, questionTransitions);

        MainActivity activity = createMainActivity();

        activity.handleNo(HardcodedQs.getStateFromId(new State.Id("0")).getId());

        assertNextActivity(activity, EndActivity.class);
    }



}
