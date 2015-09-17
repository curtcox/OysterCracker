package x.pagefactories;

import x.Registry;
import x.log.XLogWriter;
import x.page.PageFactory;
import x.page.PageTags;

public final class LogEntryPageFactory {

    public static PageFactory of() {
        return itemListScreenFactoryFactory().newFactory(
                PageTags.of("Log"),
                logEntries().asNamedValues(),
                new NamedValueToPageLink());
    }

    private static ItemListPageFactoryFactory itemListScreenFactoryFactory() {
        return Registry.get(ItemListPageFactoryFactory.class);
    }

    private static NamedValueListSource logEntries() {
        return Registry.get(XLogWriter.class);
    }

}
