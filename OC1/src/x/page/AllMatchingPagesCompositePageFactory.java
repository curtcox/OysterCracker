package x.page;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * A PageFactory that returns all of the matching pages from the factories it contains.
 */
final class AllMatchingPagesCompositePageFactory
    implements PageFactory
{
    private final PageFactory[] factories;

    AllMatchingPagesCompositePageFactory(PageFactory... factories) {
        this.factories = factories;
    }

    public Page[] create(PageLink link) {
        List<Page> all = new ArrayList();
        for (PageFactory factory : factories) {
            Page[] pages = factory.create(link);
            all.addAll(Arrays.asList(pages));
        }
        return all.toArray(new Page[0]);
    }
}
