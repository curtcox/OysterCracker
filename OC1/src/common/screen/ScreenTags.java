package common.screen;

/**
 * Immutable set of screen tags.
 */
public final class ScreenTags {

    final String tags;

    private ScreenTags(String tags) {
        this.tags = tags;
    }

    public static ScreenTags of(String tags) {
        return new ScreenTags(tags.toLowerCase());
    }

    @Override
    public String toString() {
        return tags;
    }
}