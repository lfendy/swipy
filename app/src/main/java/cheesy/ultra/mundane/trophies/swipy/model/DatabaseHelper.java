package cheesy.ultra.mundane.trophies.swipy.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "SwipyData", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, ObtainedTrophy.class);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, ObtainedTrophy.class, true);
            TableUtils.createTable(connectionSource, ObtainedTrophy.class);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
