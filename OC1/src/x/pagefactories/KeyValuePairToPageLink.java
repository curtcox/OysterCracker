package x.pagefactories;

import x.page.Page;
import x.page.PageLink;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;

import java.util.List;

/**
 * Translates from a KeyValuePair to a link to a page for viewing the pair.
 */
public final class KeyValuePairToPageLink
    implements ItemToPageLink
{
    @Override
    public PageLink pageLink(Object item) {
        KeyValuePair pair = (KeyValuePair) item;
        return Page.withFixedLayout(title(pair), layout(pair)).link;
    }

    private String title(KeyValuePair pair) {
        return pair.key;
    }

    private XComponent layout(KeyValuePair pair) {
        Object value = pair.value;
        if (value instanceof List) {
            return layout(pair.key,(List) pair.value);
        }
        return new XLabel("=" +pair.value);
    }

    private XComponent layout(String key, List list) {
        return new XLabel(key + "=" + list);
    }

}
