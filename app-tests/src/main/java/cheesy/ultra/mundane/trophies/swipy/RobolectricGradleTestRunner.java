package cheesy.ultra.mundane.trophies.swipy;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.res.Fs;

public class RobolectricGradleTestRunner extends RobolectricTestRunner {
    private static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 18;

    public RobolectricGradleTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        String myAppPath = RobolectricGradleTestRunner.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        String classpath = "/app-tests/build/classes/main";
        myAppPath = myAppPath.replace(classpath, "");
        String path = myAppPath + "app/src/main/";

        String manifestPath = path + "AndroidManifest.xml";
        String resPath = path + "res";
        String assetPath = path + "assets";
        return createAppManifest(Fs.fileFromPath(manifestPath), Fs.fileFromPath(resPath), Fs.fileFromPath(assetPath));
    }
}
