package x.page;

import x.Registry;
import x.ui.IDisplay;
import x.uiwidget.XComponent;

/**
 * The entire UI, as presented to the user, at a specific time.
 * Implementers will need to override at least one layout method to create the UI.
 * See Screen.
 */
public abstract class Page {

    /**
     * The link used to get to this page.
     */
    public final PageLink link;
    public final String title;

    public Page(PageLink link) {
        this.link = link;
        this.title = link.title();
    }

    protected Page(String title) {
        this.title = title;
        link = PageLink.of(this, title);
    }

    public static final Page withFixedLayout(final String title, final XComponent layout) {
        return new Page(title) {
            @Override
            public XComponent layoutForPortrait() {
                return layout;
            }
        };
    }

    /**
     * This is called whenever the screen is shown.
     * Override it in order to update any screen state that might have
     * changed since the last showing.
     */
    public void refresh() {}

    public abstract XComponent layoutForPortrait();

    public XComponent layoutForLandscape() {
        return layoutForPortrait();
    }

    public final boolean isPortrait() {
        return Registry.get(IDisplay.class).isPortrait();
    }


}
