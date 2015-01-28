package cheesy.ultra.mundane.trophies.swipy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(RobolectricGradleTestRunner.class)
//@Config(emulateSdk = 18, reportSdk = 18)
public class MainActivityTest {

    @Test
    public void testWillShowTrophyForTheFirstTime(){
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();

        ShadowActivity shadowHome = Robolectric.shadowOf(activity);

        assertThat(shadowHome.peekNextStartedActivityForResult().intent.getComponent(),
                equalTo(new ComponentName(activity, TrophyActivity.class)));
    }

    @Test
    public void willShowQuestionsForTheSubsequentTime(){
        //Given second+ Time (no pref saved)
        //When MainActivity Create
        //Then Question starts

        Activity activity = new MainActivity();

        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences(activity.getString(R.string.preference_file), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(activity.getString(R.string.first_trophy), true).commit();

        Activity activityInstance = Robolectric.buildActivity(MainActivity.class).create().get();
        ShadowActivity shadowHome = Robolectric.shadowOf(activityInstance);

        assertThat(shadowHome.peekNextStartedActivityForResult(), is(nullValue()));

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
