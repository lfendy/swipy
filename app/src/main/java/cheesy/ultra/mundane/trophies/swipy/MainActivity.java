package cheesy.ultra.mundane.trophies.swipy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import cheesy.ultra.mundane.trophies.swipy.fragment.NavigationDrawerFragment;
import cheesy.ultra.mundane.trophies.swipy.fragment.QuestionFragment;
import cheesy.ultra.mundane.trophies.swipy.questions.HardcodedQs;
import cheesy.ultra.mundane.trophies.swipy.questions.State;

import cheesy.ultra.mundane.trophies.swipy.model.ObtainedTrophyContract;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see cheesy.ultra.mundane.trophies.swipy.util.SystemUiHider
 */
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, QuestionFragment.QuestionEventHandler {

    public static final int REQ_CODE_TROPHY = 100;

    NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    public void handleYes(State.Id currentId) {
        startNextQuestionActivity(HardcodedQs.getAfterYes(currentId));
    }
    @Override
    public void handleNo(State.Id currentId) {
        startNextQuestionActivity(HardcodedQs.getAfterNo(currentId));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        if (!hasWonFirstTrophy()) {
            startTrophyActivity();
            
        } else {
            startQuestionFragment(HardcodedQs.getFirstQuestion());
        }

        
    }

    private void startNextQuestionActivity(State next) {
        if(next.getType() == State.Type.Fail){
            Intent intent = new Intent(this, EndActivity.class);
            startActivityForResult(intent, REQ_CODE_TROPHY);
        } else if (next.getType() == State.Type.Trophy) {
            Intent intent = new Intent(this, TrophyActivity.class);
            intent.putExtra(TrophyActivity.CURRENT_STATE, next.getId().getInnerId());
            startActivityForResult(intent, REQ_CODE_TROPHY);
        } else {
            startQuestionFragment(next);
        }
    }

    public void startQuestionFragment(State current) {
        Bundle bundle = new Bundle();
        bundle.putString(QuestionFragment.CURRENT_QUESTION_TEXT, current.getText());
        bundle.putString(QuestionFragment.CURRENT_QUESTION_ID, current.getId().getInnerId());

        QuestionFragment frag = new QuestionFragment();
        frag.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commit();
    }

    public void startTrophyActivity() {
        Intent intent = new Intent(this, TrophyActivity.class);
        intent.putExtra(TrophyActivity.CURRENT_STATE, HardcodedQs.FIRST_TROPHY_ID);
        startActivity(intent);
    }

    private boolean hasWonFirstTrophy() {

        Cursor cursor = getContentResolver().query(ObtainedTrophyContract.CONTENT_URI,
                new String[]{ObtainedTrophyContract.NAME},
                ObtainedTrophyContract.NAME + " = ?",
                new String[]{HardcodedQs.FIRST_TROPHY_ID},
                null);

        return cursor.moveToFirst();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_TROPHY) {
            startQuestionFragment(HardcodedQs.getFirstQuestion());
        }
    }
}
