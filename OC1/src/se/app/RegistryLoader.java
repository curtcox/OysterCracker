package se.app;

import common.log.ILogManager;
import common.Registry;
import common.net.Network;
import se.editor.ScreenEditor;
import se.events.Events;
import se.log.*;
import common.screen.ScreenFactory;
import common.ui.IDisplay;
import common.ui.IFormFactory;
import common.util.StringMap;
import common.net.RootStringMap;
import se.net.SERawNetwork;
import se.screen.RootScreenFactory;
import se.ui.SEDisplay;
import se.ui.SEFormFactory;
import se.util.TaggedValueStringMap;

/**
 * Loads the registry with all of the instances needed for startup.
 * @author Curt
 */
final class RegistryLoader {

    static void load() {
        put(ILogManager.class,      new LogManager());
        put(LogWriter.class,        new LogWriter());
        put(Events.class,           new Events());
        put(IFormFactory.class,     new SEFormFactory());
        put(IDisplay.class,         SEDisplay.of());
        put(Network.class,          new SERawNetwork());
        putTaggedValueStringMap();
        put(ScreenFactory.class,    RootScreenFactory.of());
        put(ScreenEditor.class,     ScreenEditor.of());
    }

    private static void putTaggedValueStringMap() {
        TaggedValueStringMap stringMap = new TaggedValueStringMap();
        put(StringMap.class, stringMap);
        put(TaggedValueStringMap.class, stringMap);
    }

    static void put(Class clazz, Object object) {
        Registry.put(clazz, object);
    }
}
