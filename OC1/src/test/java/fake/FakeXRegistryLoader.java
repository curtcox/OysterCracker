package fake;

import x.app.Registry;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.log.ILogManager;
import x.stores.XStorage;
import x.ui.IDisplay;

public class FakeXRegistryLoader {
    
    public static void load() {
        put(ILogManager.class, new XLogManager());
        put(XLogWriter.class,  new XLogWriter());
        put(IDisplay.class,    new FakeDisplay());
        put(XStorage.class,    new FakeStorage());
    }
    
    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
