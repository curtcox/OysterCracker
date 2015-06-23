package se.util;

import common.event.StringSource;
import common.page.ScreenTags;

import java.util.ArrayList;
import java.util.List;

public final class SimpleTaggedValueStringMap
    implements TaggedValueStringMap
{

    List<TaggedValue> taggedValues = new ArrayList();

    @Override
    public String get(String key) {
        for (TaggedValue value : getValuesFor(ScreenTags.of(key))) {
            return value.getContents();
        }
        return null;
    }

    public TaggedValue newValue() {
        TaggedValue value = new SimpleTaggedValue();
        taggedValues.add(value);
        return value;
    }

    public TaggedValue[] getValuesFor(ScreenTags tags) {
        for (TaggedValue value : taggedValues) {
            if (value.getTags().equals(tags)) {
                return new TaggedValue[] {value};
            }
        }
        return new TaggedValue[0];
    }

    @Override
    public StringSource[] get(ScreenTags tags) {
        for (final TaggedValue value : getValuesFor(tags)) {
            return new StringSource[] {asStringSource(value)};
        }
        return new StringSource[0];
    }

    private StringSource asStringSource(final TaggedValue value) {
        return new StringSource() {
            @Override
            public String getString() {
                return value.getContents();
            }
        };
    }
}
