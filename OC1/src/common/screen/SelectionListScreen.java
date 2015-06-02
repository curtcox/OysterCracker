package common.screen;

import common.event.Action;
import common.log.ILog;
import common.log.ILogManager;
import common.Registry;
import common.uiwidget.UIComponent;
import common.uiwidget.ISearchableList;
/**
 * A screen for selecting an item from a list of items.
 * Using this class for a specific list type typically involves:
 * - implementing use selected item for navigation to the selection
 * - providing the list of choices in the constructor
 * - providing a way to render the items in the list constructor
 * @param <T> the type of things in this list
 */
public abstract class SelectionListScreen<T>
    extends Screen
{
    private final ISearchableList<T> searchList;

    public SelectionListScreen(ScreenLink link, ISearchableList<T> searchList) {
        super(link);
        this.searchList = searchList;
        addSelectionListener();
    }

    @Override
    public UIComponent layoutForPortrait() {
        return null;
        //return new ScreenLayout(searchList.component);
    }

    private void addSelectionListener() {
        searchList.onSelected(new Action.Listener() {
            public void actionPerformed(Action event) {
                log("selected " + getSelected());
                followLink(useSelectedItem(getSelected()));
            }
        });
    }

    private void followLink(ScreenLink link) {
        Screen.show(link,screenFactory());
    }

    private T getSelected() {
        return searchList.getSelected();
    }

    /**
     * Provides a link to the screen for the selected item.
     * @param item the selected item
     * @return link for the next screen
     */
    protected abstract ScreenLink useSelectedItem(T item);
    
    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(SelectionListScreen.class);
    }

    private static ScreenFactory screenFactory() {
        return Registry.get(ScreenFactory.class);
    }

}