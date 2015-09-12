package x.page;

import x.Registry;
import x.event.Action;
import x.log.ILog;
import x.log.ILogManager;
import x.screen.Screen;
import x.uiwidget.XPeeredComponent;
import x.uiwidget.XSearchableList;

/**
 * A screen for selecting an item from a list of items.
 * Using this class for a specific list type typically involves:
 * - implementing use selected item for navigation to the selection
 * - providing the list of choices in the constructor
 * - providing a way to render the items in the list constructor
 * @param <T> the type of things in this list
 */
public abstract class SelectionListPage<T>
    extends Page
{
    private final XSearchableList<T> searchList;

    public SelectionListPage(PageLink link, XSearchableList<T> searchList) {
        super(link);
        this.searchList = searchList;
        addSelectionListener();
    }

    @Override
    public XPeeredComponent layoutForPortrait() {
        return new XPeeredComponent(searchList.getComponent());
    }

    private void addSelectionListener() {
        searchList.onSelected(new Action.Listener() {
            public void actionPerformed(Action event) {
                followLink(useSelectedItem(getSelected()));
            }
        });
    }

    private void followLink(PageLink link) {
        Screen.show(link);
    }

    private T getSelected() {
        return searchList.getSelected();
    }

    /**
     * Provides a link to the screen for the selected item.
     * @param item the selected item
     * @return link for the next screen
     */
    protected abstract PageLink useSelectedItem(T item);

    @Override
    public String toString() {
        return link.title();
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(SelectionListPage.class);
    }
}
