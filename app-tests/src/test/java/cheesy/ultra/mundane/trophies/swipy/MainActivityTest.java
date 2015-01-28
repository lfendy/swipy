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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
//@Config(emulateSdk = 18, reportSdk = 18)
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


    @Test
    public void willShowQuestionsForTheSubsequentTime(){
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);

        FragmentActivity activity = controller.get();

        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences(activity.getString(R.string.preference_file), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(activity.getString(R.string.first_trophy), true).commit();

        activity = controller.create().get();

        ShadowActivity shadowHome = Robolectric.shadowOf(activity);
        assertThat(shadowHome.peekNextStartedActivityForResult(), is(nullValue()));

        assertThat(activity.getSupportFragmentManager().findFragmentById(R.id.container), is(notNullValue()));
        assertThat(activity.getSupportFragmentManager().findFragmentById(R.id.container),
                isA(Fragment.class));
    }

    @Test
    public void willShowNextQuestion(){

    }

    @Test
    public void willShowTrophyForCorrectAnswers(){

    }

    @Test
    public void willShowFailForAnyIncorrectAnswer(){

    }



}
