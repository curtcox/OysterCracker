package x.page.dynamic;

import hash.NamedExpression;
import x.pageparts.XScreenButton;
import x.uiwidget.XButton;
import x.uiwidget.XLabel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * A map of component methods for adding to a ScreenContext.
 */
final class DynamicScreenComponentMethods
    extends HashMap
{
    DynamicScreenComponentMethods() {
        put(image());        
        put(button());        
        put(link());        
    }   
    
    private void put(NamedExpression expression) {
        put(expression.name,expression);
    }
    
    private NamedExpression button() {
        return new NamedExpression("button") {
            
            @Override
            public Object invoke(Object[] values) {
                String text = string(values[0]);
                String image = string(values[1]);
                String leadingTo = string(values[2]);
                return button(text,image,leadingTo);
            }

        };
    }

    private NamedExpression image() {
        return new NamedExpression("image") {
            
            @Override
            public Object invoke(Object[] values) {
                URI uri = uri(values[0]);
                XLabel label = new XLabel();
                label.icon = uri;
                return label;
            }

        };
    }

    private NamedExpression link() {
        return new NamedExpression("link") {
            
            @Override
            public Object invoke(Object[] values) {
                String text = string(values[0]);
                return link(text);
            }

        };
    }

    private URI uri(Object value) {
        try {
            return new URI(string(value));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String string(Object value) {
        return (value==null) ? "" : value.toString();
    }

    private XButton button(String text, String image, String leadingTo) {
        return XScreenButton.builder().text(text).image(image).leadingTo(leadingTo).build();
    }

    private XButton link(String text) {
        return XScreenButton.builder().text(text).leadingTo(text).build();
    }
    
}
