package c1.screen;

import com.codename1.capture.Capture;
import com.codename1.components.SpanButton;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import hash.NamedExpression;

import java.io.IOException;
import java.util.HashMap;

/**
 * A map of component methods for adding to a ScreenContext.
 */
final class DynamicScreenMediaCaptureMethods
    extends HashMap
{
    DynamicScreenMediaCaptureMethods() {
        put(capture_image());        
    }   
    
    private void put(NamedExpression expression) {
        put(expression.name,expression);
    }
    
    private NamedExpression capture_image() {
        return new NamedExpression("capture_image") {
            
            @Override
            public Object invoke(Object[] values) {
                final SpanButton button = new SpanButton("Capture Image");
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String photo = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
                        if (photo!=null) {
                            try {
                                button.setIcon(Image.createImage(photo));
                                button.getComponentForm().revalidate();
                            } catch (IOException e) {
                                throw new RuntimeException(e.getMessage());
                            }
                        }
                    }
                });
                return button;
            }

        };
    }

}
