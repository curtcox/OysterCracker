package oc1.screen;

import hash.NamedExpression;
import oc1.util.Check;
import oc1.util.Mirror;
import oc1.util.Mirrors;

public final class ScreenController
    implements ScreenContext.Provider
{

    public interface Lookup {
        ScreenController lookup(ScreenLink link);
    }

    private final Object target;
    
    public ScreenController(Object target) {
        this.target = Check.notNull(target);
    }
    
    public ScreenContext getContext() {
        ScreenContext context = new ScreenContext();
        addSpecifics(context,Mirrors.of(target));
        return context;
    }
    
    protected void addSpecifics(ScreenContext context, final Mirror mirror) {
        for (final String method : mirror.getMethods()) {
            context.put(method, new Getter() { public NamedExpression get() {
                return new NamedExpression(method) {
                    public Object invoke(Object[] values) {
                        return mirror.invoke(method, values);
                    }
                };    
            }});
        }
    }
}
