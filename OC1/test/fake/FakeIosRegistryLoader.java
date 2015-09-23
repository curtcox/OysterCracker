package fake;

import ios.pagefactories.IosItemListPageFactoryFactory;
import ios.ui.IosFormFactory;
import x.app.Registry;
import x.app.CurrentState;
import x.domain.ServiceProvider;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.pagefactories.ItemListPageFactoryFactory;
import x.services.XServiceProviders;
import x.ui.IDisplay;
import x.ui.IFormFactory;

public class FakeIosRegistryLoader {
    
    public static void load() {
        put(ILogManager.class,                new XLogManager());
        put(XLogWriter.class,                 new XLogWriter());
        put(ServiceProvider.class,            ServiceProvider.NULL);
        put(XServiceProviders.class,           new XServiceProviders());
        put(CurrentState.class, new CurrentState());
        put(IFormFactory.class,               new IosFormFactory());
        put(IDisplay.class,                   new FakeDisplay());
        put(ItemListPageFactoryFactory.class, new IosItemListPageFactoryFactory());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
