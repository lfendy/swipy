package cheesy.ultra.mundane.trophies.swipy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import cheesy.ultra.mundane.trophies.swipy.fragment.NavigationDrawerFragment;
import cheesy.ultra.mundane.trophies.swipy.fragment.QuestionFragment;
import cheesy.ultra.mundane.trophies.swipy.questions.HardcodedQs;
import cheesy.ultra.mundane.trophies.swipy.questions.State;
import cheesy.ultra.mundane.trophies.swipy.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    public static final int REQ_CODE_TROPHY = 100;

    NavigationDrawerFragment mNavigationDrawerFragment;

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

        if (!isCanHazWonFirstTrophy()) {
            startTrophyActivity();
        } else {
            startQuestionFragment(HardcodedQs.getFirstQuestion().getId());
        }
    }

    private void startQuestionFragment(State.Id current) {

        Bundle bundle = new Bundle();
        bundle.putString(QuestionFragment.CURRENT_QUESTION, current.getInnerId());

        QuestionFragment frag = new QuestionFragment();
        frag.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commit();
    }

    public void startTrophyActivity() {
        Intent intent = new Intent(this, TrophyActivity.class);
        intent.putExtra(TrophyActivity.CURRENT_STATE, "0");
        startActivity(intent);
    }

    private boolean isCanHazWonFirstTrophy() {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_file), MODE_PRIVATE);
        return prefs.getBoolean(getString(R.string.first_trophy), false);
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
            startQuestionFragment(HardcodedQs.getFirstQuestion().getId());
        }
    }
}
