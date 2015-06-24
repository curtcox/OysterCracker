package se.ui;

import x.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;

final class SEUIRenderer {

    static JComponent render(UIComponent layout) {
        if (layout instanceof UIPeeredComponent)  { return peer(layout);   }
        if (layout instanceof UIButton)           { return button(layout); }
        if (layout instanceof UILabel)            { return label(layout);  }
        if (layout instanceof UIFlow)             { return flow(layout);   }
        if (layout instanceof UIColumn)           { return column(layout); }
        if (layout instanceof UIRow)              { return row(layout);    }
        String message = layout == null ? "null" : layout.getClass().getName();
        IllegalArgumentException e = new IllegalArgumentException(message);
        log(e);
        throw e;
    }

    private static JComponent peer(UIComponent layout) {
        UIPeeredComponent peered = (UIPeeredComponent) layout;
        return (JComponent) peered.peer;
    }

    static JPanel column(UIComponent layout) {
        return box(layout,Y_AXIS);
    }

    static JPanel row(UIComponent layout) {
        return box(layout,X_AXIS);
    }

    static JPanel box(UIComponent layout,int axis) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,axis));
        for (UIComponent component : ((UIContainer) layout).components) {
            panel.add(render(component));
        }
        return panel;
    }

    static JPanel flow(UIComponent layout) {
        JPanel panel = new JPanel();
        for (UIComponent component : ((UIContainer) layout).components) {
            panel.add(render(component));
        }
        return panel;
    }

    static JButton button(UIComponent layout) {
        final UIButton button = (UIButton) layout;
        JButton jButton = new JButton(button.text);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                button.onTap();
            }
        });
        return jButton;
    }

    static JLabel label(UIComponent layout) {
        UILabel label = (UILabel) layout;
        JLabel jlabel = new JLabel(label.text);
        jlabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return jlabel;
    }

    private static void log(Throwable t) {
        getLog().log(t);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(SEUIRenderer.class);
    }

}
