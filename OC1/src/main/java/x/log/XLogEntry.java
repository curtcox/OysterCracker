package x.log;

import x.event.LiveList;
import x.event.XLiveList;
import x.util.NamedValue;
import x.event.NamedValueListSource;
import x.screen.Screen;

import java.util.Arrays;

public final class XLogEntry
    implements NamedValueListSource
{
    final Object target;
    final Class clazz;
    final String message;
    final Object[] details;
    final Thread thread;
    final Screen screen;
    final long time;

    XLogEntry(Object target, Class clazz, String message, Object... details) {
        this.target = target;
        this.clazz = clazz;
        this.screen = Screen.getShowing();
        this.message = message;
        this.details = details;
        this.thread = Thread.currentThread();
        this.time = System.currentTimeMillis();
    }

    public static XLogEntry of(Object target, Class clazz,String message,Object...details) {
        return new XLogEntry(target,clazz,message,details);
    }

    String time() {
        return hex(System.currentTimeMillis());
    }

    private String hex(long value) {
        return "" + value;
    }

    String thread() {
        return thread.getName();
    }

    private String screen() {
        return "" + screen;
    }

    private String prefix() {
        return clazz.getCanonicalName();
    }

    public String toString() {
        return time() + ":" + thread() + ":" + screen() + ":" + prefix() + ":" + message;
    }

    @Override
    public LiveList<NamedValue> asNamedValues() {
        return XLiveList.of(Arrays.asList(
            new NamedValue("target",target),
            new NamedValue("clazz",clazz),
            new NamedValue("message",message),
            new NamedValue("thread",thread),
            new NamedValue("screen",screen),
            new NamedValue("time",time),
            new NamedValue("details",XLiveList.of(Arrays.asList(details)))
        ));
    }
}
