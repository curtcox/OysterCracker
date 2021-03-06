package x.util;

import x.page.PageTags;

/**
 * A glob in the file system filename sense.
 * @author Curt
 */
public final class Glob {

    private final String glob;
    
    public static Glob of(String glob) {
        return new Glob(glob);    
    }
    
    private Glob(String glob) {
        this.glob = glob.toLowerCase();
    }

    public boolean matches(PageTags tags) {
        return matches(tags.toString());
    }

    public boolean matches(String string) {
        return glob.equals("*") || glob.equals(string.toLowerCase());
    }

}
