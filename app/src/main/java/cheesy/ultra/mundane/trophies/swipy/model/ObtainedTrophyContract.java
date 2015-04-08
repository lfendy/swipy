package cheesy.ultra.mundane.trophies.swipy.model;

import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class ObtainedTrophyContract
        implements BaseColumns {
    public static final String TABLE_NAME = "obtained_trophy";

    public static final String CONTENT_URI_PATH = "obtainedTrophy";
    public static final String AUTHORITY = "cheesy.ultra.mundane.trophies.swipy.model";

    public static final String MIMETYPE_TYPE = "obtainedTrophies";
    public static final String MIMETYPE_NAME = "cheesy.ultra.mundane.trophies.swipy.model";

    public static final int CONTENT_URI_PATTERN_MANY = 1;
    public static final int CONTENT_URI_PATTERN_ONE = 2;

    public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

    private ObtainedTrophyContract() {
    }

    public static final String NAME = "name";
}