package cheesy.ultra.mundane.trophies.swipy;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import cheesy.ultra.mundane.trophies.swipy.R;
import cheesy.ultra.mundane.trophies.swipy.model.ObtainedTrophyContract;

public class TrophyListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_list);


        ListView listView = (ListView) findViewById(R.id.myTrophyListView);

        Cursor cursor = getContentResolver().query(ObtainedTrophyContract.CONTENT_URI, new String[]{ObtainedTrophyContract._ID, ObtainedTrophyContract.NAME}, null, new String[0], null);

        listView.setAdapter(new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.trophy_item,
                cursor,
                new String[]{ObtainedTrophyContract.NAME},
                new int[]{R.id.trophyText},
                0));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trophy_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
