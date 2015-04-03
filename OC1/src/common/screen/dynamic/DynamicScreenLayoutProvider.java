package common.screen.dynamic;

import common.*;
import common.log.ILog;
import common.log.ILogManager;
import common.screen.ScreenLink;
import common.uiwidget.UIColumn;
import common.uiwidget.UIComponent;
import common.uiwidget.UIGrid;
import common.uiwidget.UILabel;
import hash.*;
import common.event.StringSource;

/**
 * Provides a ScreenLayout that is dynamically generated at runtime.
 * @author Curt
 */
public final class DynamicScreenLayoutProvider
    implements ScreenLayoutProvider
{
    final StringSource source;
    static final String layout = "layout";
    
    DynamicScreenLayoutProvider(StringSource source) {
        this.source = source;    
    }

    public UIComponent getLayout(ScreenLink link, ScreenContext context) {
        String sourceCode = source.getString();
        if (!isValidHash(sourceCode)) {
            return messageScreen("Source is not valid Hash");
        }
        return screenForResult(getLayoutFromHash(sourceCode,context));
    }

    private boolean isValidHash(String sourceCode) {
        return sourceCode!=null && sourceCode.length()>0;
    }

    private UIComponent screenForResult(Object result) {
        if (result==null)                   { return messageScreen("(null)");            }
        if (result instanceof UIComponent)  { return (UIComponent) result;               }
        if (result instanceof String)       { return messageScreen((String) result);      }
        if (result instanceof SyntaxError)  { return errorScreen((SyntaxError) result);  }
        throw new IllegalArgumentException("result="+result);
    }
    
    private UIComponent messageScreen(String message) {
        return label(message);
    }

    private UIComponent errorScreen(SyntaxError error) {
        return new UIColumn(
                label(error.type.toString()),
                label(error.errorSource),
                label(error.methodSource)
        );
    }

    private UIComponent exception(Exception e) {
        return new UIGrid(2,1,
                label(e.getClass().toString()),
                label(e.getMessage()));
    }

    private Object getLayoutFromHash(String sourceCode,ScreenContext screenContext) {
        try {
            return Run
                .source(sourceCode)
                .method(layout)
                .namedValues(asNamedValues(screenContext))
                .args();
        } catch (Exception e) {
            log(e);
            return exception(e);
        }
    }

    private UILabel label(String text) {
        return new UILabel(text);
    }
    
    private NamedValues asNamedValues(ScreenContext screenContext) {
        return new ScreenContextAsNamedValues(screenContext);
    }
    
    private void log(Exception e) {
        getLog().log(e);    
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(DynamicScreenLayoutProvider.class);
    }

}
