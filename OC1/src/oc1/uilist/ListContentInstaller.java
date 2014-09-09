package oc1.uilist;

import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;
import oc1.event.SwappableList;

/**
 *
 * @author Curt
 */
public final class ListContentInstaller {
    
    public static void install(final SearchableList list, final SwappableList items, final StringToList stringToList) {
        final TextField search = list.searchTerm;
        search.addDataChangeListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                items.become(stringToList.listFor(search.getText()));
            }
        });        
    }

}