package cheesy.ultra.mundane.trophies.swipy;

import android.app.Activity;
import android.content.ComponentName;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
