package cheesy.ultra.mundane.trophies.swipy;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cheesy.ultra.mundane.trophies.swipy.model.ObtainedTrophyContract;
import cheesy.ultra.mundane.trophies.swipy.questions.HardcodedQs;
import cheesy.ultra.mundane.trophies.swipy.questions.State;
import cheesy.ultra.mundane.trophies.swipy.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class TrophyActivity extends Activity {


    public static String CURRENT_STATE = "current state";
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;



    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_trophy);

        final View contentView = findViewById(R.id.fullscreen_content);

        final State.Id currentStateId = new State.Id((String) getIntent().getSerializableExtra(CURRENT_STATE));
        String trophyText = HardcodedQs.getStateFromId(currentStateId).getText();
        TextView tv = (TextView) contentView;
        tv.setText(trophyText);

        setHazWonTrophy(currentStateId.getInnerId());
    }



    private void setHazWonTrophy(String trophyId){
        if(!trophyObtained(trophyId)) addTrophy(trophyId);
    }

    private void addTrophy(String trophyId) {
        ContentValues trophyValues = new ContentValues();
        trophyValues.put(ObtainedTrophyContract.NAME, trophyId);

        getContentResolver().insert(ObtainedTrophyContract.CONTENT_URI, trophyValues);
    }

    private boolean trophyObtained(String trophyId) {
        Cursor cursor = getContentResolver().query(ObtainedTrophyContract.CONTENT_URI,
                new String[]{ObtainedTrophyContract.NAME},
                ObtainedTrophyContract.NAME + " = ?",
                new String[]{trophyId},
                null);

        return cursor.moveToFirst();
    }
}
