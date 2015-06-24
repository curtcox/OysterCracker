package c1.app;

import c1.net.C1RawNetwork;
import c1.services.ILocationManager;
import com.codename1.io.Storage;
import x.Registry;
import x.device.IDeviceInfo;
import x.log.ILog;
import x.log.ILogManager;
import x.net.Network;
import x.ui.IDisplay;
import fake.FakeDeviceInfo;
import fake.FakeDisplay;
import fake.FakeLocationManager;
import fake.FakeStorage;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class C1RegistryLoaderTest {

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
        Registry.put(Network.class, new C1RawNetwork());
        Registry.put(IDisplay.class, new FakeDisplay());
        Registry.put(ILocationManager.class, new FakeLocationManager());
        Registry.put(IDeviceInfo.class, new FakeDeviceInfo());
        C1RegistryLoader.loadPlatform();
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1RegistryLoaderTest.class);
    }

}