package fake;

import va.pagefactories.VaItemListPageFactoryFactory;
import va.ui.VaFormFactory;
import x.app.Registry;
import x.app.CurrentState;
import x.domain.ConsumerServiceProvider;
import x.log.ILogManager;
import x.log.XLogManager;
import x.log.XLogWriter;
import x.pagefactories.ItemListPageFactoryFactory;
import x.services.XConsumerServiceProviders;
import x.ui.IDisplay;
import x.ui.IFormFactory;

public class FakeVaRegistryLoader {
    
    public static void load() {
        put(ILogManager.class,                new XLogManager());
        put(XLogWriter.class,                 new XLogWriter());
        put(ConsumerServiceProvider.class,            ConsumerServiceProvider.NULL);
        put(XConsumerServiceProviders.class,           new XConsumerServiceProviders());
        put(CurrentState.class, new CurrentState());
        put(IFormFactory.class,               new VaFormFactory());
        put(IDisplay.class,                   new FakeDisplay());
        put(ItemListPageFactoryFactory.class, new VaItemListPageFactoryFactory());
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz,object);
    }

}
