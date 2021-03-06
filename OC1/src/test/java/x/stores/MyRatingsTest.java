package x.stores;

import config.ShouldRun;
import fake.FakeStorage;
import fake.FakeXRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.app.Registry;
import x.domain.ID;
import x.domain.Rating;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

public class MyRatingsTest {
    
    MyRatings ratings1;
    
    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        FakeXRegistryLoader.load();
        ratings1 = new MyRatings();
    }

    @Test
    public void test_getFor_returns_value_previously_put_in_storage() {
        ID id = new ID("" + hashCode());
        Rating rating = new Rating(toString());
        ratings1.put(id, rating);
        FakeStorage storage = (FakeStorage) Registry.get(XStorage.class);
        storage.inputStream = new ByteArrayInputStream(storage.outputStream.toByteArray());
        
        MyRatings ratings2 = new MyRatings();
        Rating actual = ratings2.getFor(id);
        
        assertEquals(rating,actual);
    }
    
}
