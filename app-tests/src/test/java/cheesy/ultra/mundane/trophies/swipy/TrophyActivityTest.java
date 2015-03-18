package cheesy.ultra.mundane.trophies.swipy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
public class TrophyActivityTest {

    @Test
    public void willSetSharedPrefToTrueForFirstTrophy() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra(TrophyActivity.CURRENT_STATE, "0");
        Activity activity = Robolectric.buildActivity(TrophyActivity.class).withIntent(intent).create().get();

        SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences(activity.getString(R.string.preference_file), Context.MODE_PRIVATE);

        assertThat(sharedPreferences.getBoolean(activity.getString(R.string.first_trophy), false), equalTo(true));
    }
}
