package c1.app;

import c1.services.ILocationManager;
import com.codename1.io.Storage;
import x.Registry;
import x.app.CurrentState;
import fake.FakeLocationManager;
import fake.FakeStorage;
import x.event.Change;
import c1.services.C1Locations;
import x.services.ServiceProviders;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CurrentStateTest {
    
    FakeListener listener = new FakeListener(); 
    FakeListener listener2 = new FakeListener(); 
    CurrentState testObject;
    
    static class FakeListener implements Change.Listener {
        boolean called;
        public void onChange() {
            called = true;
        }
    }
    
    @Before
    public void setUp() {
        Registry.put(Storage.class, new FakeStorage());
        Registry.put(ILocationManager.class, new FakeLocationManager());
        Registry.put(C1Locations.class, new C1Locations());
        Registry.put(ServiceProviders.class, new ServiceProviders());
        testObject = new CurrentState();
    }
    
    @Test
    public void is_not_null() {
        assertNotNull(testObject);
    }

    @Test
    public void notifies_listener_when_there_is_a_change() {
        testObject.addListener(listener);
        testObject.broadcastChange();
        
        assertTrue(listener.called);
    }

    @Test
    public void notifies_listeners_when_there_is_a_change() {
        testObject.addListener(listener);
        testObject.addListener(listener2);
        
        testObject.broadcastChange();
        
        assertTrue(listener.called);
        assertTrue(listener2.called);
    }
    
}
