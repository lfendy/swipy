package cheesy.ultra.mundane.trophies.swipy.model;

import android.provider.BaseColumns;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation.*;

//@Contract()
@DatabaseTable(tableName="obtained_trophy")
@DefaultContentUri(authority = "cheesy.ultra.mundane.trophies.swipy.model", path = "obtainedTrophy")
@DefaultContentMimeTypeVnd(name = "cheesy.ultra.mundane.trophies.swipy.model", type = "obtainedTrophies")
public class ObtainedTrophy {

    @DatabaseField(columnName = BaseColumns._ID, generatedId = true)
    @DefaultSortOrder
    private int id;

    @DatabaseField
    private String name;

    public ObtainedTrophy() {
    }

    public ObtainedTrophy(String name) {
        this.id = 0;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
