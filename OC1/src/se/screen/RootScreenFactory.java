package se.screen;

import common.Registry;
import common.screen.CompositeScreenFactory;
import common.screen.ScreenFactory;
import common.screen.dynamic.DynamicScreenFactory;
import common.screen.dynamic.LazyScreenFactory;
import common.screen.dynamic.StringMapStringSource;
import c1.app.Version;
import common.util.StringMap;
import c1.screenfactories.*;
import c1.screens.CustomComponentScreen;
import c1.screens.Home;
import c1.screens.ProviderDetailsScreen;

import java.util.Arrays;
import java.util.List;

/**
 * The top-level ScreenFactory.
 * @author Curt
 */
public final class RootScreenFactory {

    public static List<String> index = Arrays.asList(
            "Device_Info",
            "LocationSelection", "ProviderDetails",
            "Filter", "Search", "Custom"
    );

    public static ScreenFactory of() {
        return of(Registry.get(StringMap.class));
    }

    public static ScreenFactory of(StringMap layouts) {
        return new CompositeScreenFactory(
                new DeviceInfoScreenFactory(),
                LocationSelectionScreenFactory.FACTORY,
                ProviderDetailsScreen.FACTORY,
                FilterScreenFactory.FACTORY,
                SearchScreenFactory.FACTORY,
                CustomComponentScreen.FACTORY,
                dynamicScreens(layouts),
                new IndexScreenFactory(),
                new LazyScreenFactory(layouts)
        );
    }

    private static ScreenFactory dynamicScreens(StringMap layouts) {
        return DynamicScreenFactory.builder()
                .map(""     , Version.VERSION, new Home(), new StringMapStringSource(layouts,"Home"))
                .build();
    }
}