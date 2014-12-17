package cheesy.ultra.mundane.trophies.swipy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.SharedPreferences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void willShowTrophyForTheFirstTime(){

        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();

        ShadowActivity shadowHome = Robolectric.shadowOf(activity);

        assertThat(shadowHome.peekNextStartedActivityForResult().intent.getComponent(), equalTo(new ComponentName(activity, TrophyActivity.class)));
    }

    @Test
    public void willShowQuestionsForTheSubsequentTime(){
        //Given second+ Time (no pref saved)
        //When MainActivity Create
        //Then Question starts
    }

}
