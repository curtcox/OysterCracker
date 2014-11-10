package oc1.screen;

import com.codename1.ui.Component;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.table.TableLayout;
import hash.NamedExpression;
import java.util.HashMap;

/**
 * A map of layout methods for adding to a ScreenContext.
 * @author Curt
 */
final class DynamicScreenLayoutMethods
    extends HashMap
{
    DynamicScreenLayoutMethods() {
        put("grid",grid());        
        put("table",table());        
    }   
    
    private NamedExpression grid() {
        return new NamedExpression("grid") {
            @Override
            public Object invoke(Object[] values) {
                int rows    = integer(values[0]);
                int columns = integer(values[1]);
                return grid(rows,columns,components(values));
            }
        };
    }

    private NamedExpression table() {
        return new NamedExpression("table") {
            @Override
            public Object invoke(Object[] values) {
                int rows    = integer(values[0]);
                int columns = integer(values[1]);
                return table(rows,columns,components(values));
            }
        };
    }

    private Component[] components(Object[] values) {
        Component[] components = new Component[values.length-2];
        for (int i=0; i<components.length; i++) {
            components[i] = component(values[i+2]);
        }
        return components;
    }

    private Component component(Object o) {
        if (o instanceof Component) {
            return (Component) o;
        }    
        if (o instanceof String) {
            return new Label(o.toString());
        }
        if (o instanceof ScreenLayout) {
            return ((ScreenLayout)o).toComponent();
        }
        String message = "Argument " + o;
        throw new IllegalArgumentException(message);
    }
    
    private int integer(Object o) {
        if (o instanceof Long) {
            long value = (Long) o;
            return (int) value;
        }
        if (o instanceof Integer) {
            return (Integer)o;
        }
        return 0;
    }
    
    private ScreenLayout grid(int rows,int columns,Component[] components) {
        return screen(new GridLayout(rows,columns),components);
    }

    private ScreenLayout table(int rows,int columns,Component[] components) {
        return screen(new TableLayout(rows,columns),components);
    }

    private ScreenLayout screen(Layout layout, Component... components) {
        return new ScreenLayout(layout,components);
    }
}