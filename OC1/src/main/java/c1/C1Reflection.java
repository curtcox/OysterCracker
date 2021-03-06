package c1;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;

public final class C1Reflection {

    public static void set(Object object, String field, Object value) {
        log("object " + object + " field " + field + " value " + value);
        Button button = (Button) object;
        button.setTextPosition(Label.BOTTOM);
    }

    private static void log(String message) {
        getLog().log(message);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1Reflection.class);
    }

}
