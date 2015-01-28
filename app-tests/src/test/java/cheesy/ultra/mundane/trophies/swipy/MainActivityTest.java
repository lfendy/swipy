package cheesy.ultra.mundane.trophies.swipy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
//@Config(emulateSdk = 18, reportSdk = 18)
public class MainActivityTest {

    @Test
    public void testWillShowTrophyForTheFirstTime(){
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();

        ShadowActivity shadowHome = Robolectric.shadowOf(activity);

        assertThat(shadowHome.getNextStartedActivity().getComponent(),
                equalTo(new ComponentName(activity, TrophyActivity.class)));
    }


    @Test
    public void willShowQuestionsForTheSubsequentTime(){
        //Given second+ Time (no pref saved)
        //When MainActivity Create
        //Then Question starts

        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);

        Activity activity = controller.get();

        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences(activity.getString(R.string.preference_file), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(activity.getString(R.string.first_trophy), true).commit();

        controller.create();

        ShadowActivity shadowHome = Robolectric.shadowOf(activity);
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
