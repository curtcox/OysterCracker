package va.uilist;

import com.vaadin.event.FieldEvents;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uilist.XSearchFilterInstaller;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

public final class VaSearchFilterInstaller
    implements XSearchFilterInstaller
{
    
    public static void vaSpecificInstall(final VaSearchableList list, final StringToListFilter stringToListFilter) {
        list.searchTerm.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                setFilterText(list, stringToListFilter, textChangeEvent.getText());
            }
        });
    }

    static void setFilterText(VaSearchableList list, StringToListFilter stringToListFilter, String text) {
        log("filtering  " + list + " with " + text);
        list.setFilter(stringToListFilter.listFilterFor(text));
    }

    @Override
    public void install(XSearchableList list, StringToListFilter stringToListFilter) {
        vaSpecificInstall((VaSearchableList) list, stringToListFilter);
    }

    private static void log(String message) {
        getLog().log(message);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(VaSearchFilterInstaller.class);
    }

}
