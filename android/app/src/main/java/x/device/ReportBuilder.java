package x.device;

import x.event.LiveList;
import x.event.XLiveList;

import java.util.List;

/**
 * For building a plain-text report.
 */
public final class ReportBuilder {

    final LiveList list = new XLiveList();
    final StringBuilder out = new StringBuilder();

    public void value(String key, Object value) {
        list.add(new DeviceKeyValuePair(key,value));
        out.append("\t" + key + "=" + value);
        out.append("\r\n");
    }

    public void section(String string, List<DeviceKeyValuePair> pairs) {
        out.append(string);
        out.append("\r\n");
        for (DeviceKeyValuePair pair : pairs) {
            value(pair.key,pair.value);
        }
    }

    @Override
    public String toString() {
        return out.toString();
    }

    public LiveList<DeviceKeyValuePair> toKeyValuePairs() {
        return list;
    }
}
