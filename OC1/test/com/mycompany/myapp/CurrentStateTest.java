package com.mycompany.myapp;

import com.codename1.io.Storage;
import com.codename1.location.LocationManager;
import com.mycompany.fake.FakeLocationManager;
import com.mycompany.fake.FakeStorage;
import com.mycompany.myapp.event.Change;
import com.mycompany.myapp.services.Locations;
import com.mycompany.myapp.stores.ServiceProviders;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class CurrentStateTest {
    
    FakeListener listener = new FakeListener(); 
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
        Registry.put(LocationManager.class, new FakeLocationManager());
        Registry.put(Locations.class, new Locations());
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
    
}
