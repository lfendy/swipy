package cheesy.ultra.mundane.trophies.swipy.model;

import com.tojc.ormlite.android.OrmLiteSimpleContentProvider;
import com.tojc.ormlite.android.framework.MatcherController;
import com.tojc.ormlite.android.framework.MimeTypeVnd;

public class ObtainedTrophyProvider extends OrmLiteSimpleContentProvider<DatabaseHelper>
{
    @Override
    protected Class<DatabaseHelper> getHelperClass()
    {
        return DatabaseHelper.class;
    }

    @Override
    public boolean onCreate()
    {
        setMatcherController(new MatcherController()
                        .add(ObtainedTrophy.class, MimeTypeVnd.SubType.DIRECTORY, "", ObtainedTrophyContract.CONTENT_URI_PATTERN_MANY)
                        .add(ObtainedTrophy.class, MimeTypeVnd.SubType.ITEM, "#", ObtainedTrophyContract.CONTENT_URI_PATTERN_ONE)
        );
        return true;
    }
}
