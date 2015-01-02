package c1.log;

import common.log.ILog;
import common.Registry;
import common.ui.IDisplay;
import common.ui.IForm;

public final class Log
    implements ILog
{

    final Class clazz;
    final String prefix;
    
    Log(Class clazz) {
        this.clazz = clazz;
        prefix = ":" + clazz.getCanonicalName() + ":";
    }

    public void log(Throwable e) {
        log("Caught exception");
        log("class=" + e.getClass());
        log("message=" + e.getMessage());
        e.printStackTrace();
    }

    public void log(String message) {
        getLogWriter().log(now() + ":" + thread() + ":" + screen() + prefix + message);
    }
    
    String now() {
        return hex(System.currentTimeMillis());
    }

    String thread() {
        return Thread.currentThread().getName();
    }

    private String hex(long value) {
        return "" + value;
    }
    
    private LogWriter getLogWriter() {
        return LogWriter.of();
    }

    private String screen() {
        IForm form = form();
        return (form==null) ? "null" : form.getTitle();
    }
    
    private IForm form() {
        return display().getCurrent();
    }

    private IDisplay display() {
        return Registry.get(IDisplay.class);
    }

}