package oc1.screen;

import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import hash.NamedValueProvider;
import hash.Run;
import oc1.event.StringSource;
import oc1.log.Log;
import oc1.log.LogManager;

/**
 *
 * @author Curt
 */
public class DynamicScreenLayout
    implements ScreenLayout.Provider
{
    final StringSource source;
    
    DynamicScreenLayout(StringSource source) {
        this.source = source;    
    }

    public ScreenLayout getLayout(ScreenContext context) {
        String hashSourceCode = source.getString();
        if (!isValidHash(hashSourceCode)) {
            return messageScreen("Source is not valid Hash");
        }
        return screenForResult(getLayoutFromHash(hashSourceCode,context));
    }

    private boolean isValidHash(String sourceCode) {
        return sourceCode!=null && sourceCode.length()>0;
    }

    private ScreenLayout screenForResult(Object result) {
        if (result instanceof ScreenLayout) {
            return (ScreenLayout) result;
        }
        if (result==null) {
            return messageScreen("(null)");
        }
        if (result instanceof String) {
            return messageScreen((String)result);
        }
        throw new IllegalArgumentException("result="+result);
    }
    
    private ScreenLayout messageScreen(String message) {
        return new ScreenLayout(new GridLayout(1,1),new Label(message));
    }

    private Object getLayoutFromHash(String sourceCode,ScreenContext context) {
        try {
            return Run
                .source(sourceCode)
                .method("layout")
                .context(context(context))
                .args();
        } catch (Exception e) {
            log(e);
            return e.toString();
        }
    }

    private NamedValueProvider context(ScreenContext context) {
        return new ScreenContextAsNamedValueProvider(context);
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private Log getLog() {
        return LogManager.of().getLog(DynamicScreenLayout.class);    
    }

}
