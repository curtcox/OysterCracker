package ios.app;

import com.codename1.io.Storage;
import config.ShouldRun;
import fake.FakeDeviceInfo;
import fake.FakeDisplay;
import fake.FakeStorage;
import org.junit.Before;
import org.junit.Test;
import x.app.Registry;
import x.event.NamedValueListSource;
import x.log.ILog;
import x.log.ILogManager;
import x.ui.IDisplay;

import static org.junit.Assume.assumeTrue;

public class IosRegistryLoaderTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
    }

    @Test
    public void load_with_fake_storage_and_raw_network() {
        loadPlatform();
    }

    @Test
    public void can_log_after_loading() {
        loadPlatform();
        log("Doesn't throw an exception. So we got that going for us.");
    }

    void loadPlatform() {
        Registry.put(Storage.class, new FakeStorage());
        Registry.put(IDisplay.class, new FakeDisplay());
        Registry.put(NamedValueListSource.class, new FakeDeviceInfo());
        IosRegistryLoader.loadPlatform();
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(this);
    }

}