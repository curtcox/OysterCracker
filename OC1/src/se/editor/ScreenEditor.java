package se.editor;

import common.Registry;
import common.event.StringChange;
import common.screen.Screen;
import se.events.Events;
import se.ui.EditTaggedValueEvent;
import se.uiwidget.StringEditor;
import se.util.TaggedValue;

import javax.swing.*;

public final class ScreenEditor {

    TaggedValue editing;
    final JFrame frame = new JFrame();
    final StringEditor editor = new StringEditor(textListener(),null);

    private static ScreenEditor screenEditor;

    /**
     * Only use this for testing.
     */
    ScreenEditor() {}

    public static ScreenEditor of() {
        if (screenEditor == null) {
            screenEditor = new ScreenEditor();
            screenEditor.register();
            screenEditor.init();
        }
        return screenEditor;
    }

    void init() {
        frame.getContentPane().add(editor);
        frame.setSize(500,500);
    }

    void register() {
        events().registerListenerFor(editCommandListener(), EditTaggedValueEvent.class);
    }

    Events.Listener editCommandListener() {
        return new Events.Listener() {
            @Override
            public void onEvent(Events.Event event) {
                EditTaggedValueEvent editEvent = (EditTaggedValueEvent) event;
                edit(editEvent.taggedValue);
            }
        };
    }

    private StringChange.Listener textListener() {
        return new StringChange.Listener() {
            @Override
            public void onChange(StringChange.Event event) {
                editing.setContents(editor.getText());
                refreshScreen();
            }
        };
    }

    private void refreshScreen() {
        Screen screen = Screen.getShowing();
        if (screen!=null) {
            screen.refresh();
        }
    }

    void edit(TaggedValue value) {
        editing = value;
        editor.setText(value.getContents());
        frame.setVisible(true);
    }

    static Events events() {
        return Registry.get(Events.class);
    }

}
