package oc1.screen;

import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import oc1.app.Registry;
import oc1.command.LoggedCommand;
import oc1.log.LogManager;
import oc1.ui.FormFactory;

/**
 * The entire UI, as presented to the user, at a specific time.
 * Implementors will need to override at least one layout method to create the UI.
 * @author Curt
 */
public abstract class Screen {

    public final Form form;
    private Screen previous; // set once
    private Command back;    // set once

    /**
     * Override this constructor to create a new screen.
     * @param name name of the Screen and title of the underlying Form
     */
    public Screen(String name) {
        this(FormFactory.of().newForm(name));
    }
    
    /**
     * This constructor is exposed mostly for testing.
     * @param form 
     */
    public Screen(Form form) {
        this.form = form;
        refreshOnOrientationChanage();
        log("created " + form.getTitle());
    }

    private void setPrevious(Screen previous) {
        verifyPreviousNotSet();
        this.previous = previous;
        back = new LoggedCommand("Back") {
            @Override protected void go() {
                back();
            }
        };
    }

    private void verifyPreviousNotSet() {
        if (previous!=null) {
            throw new RuntimeException();
        }
    }
    
    public void show() {
        log("show " + form.getTitle());
        refresh();
        layoutForm();
        form.show();
        form.setBackCommand(back);
    }
   
    public void back() {
        log("back " + form.getTitle());
        if (previous!=null) {
            goBack();
        }
    }
    
    private void goBack() {
        previous.refresh();
        previous.layoutForm();
        previous.form.showBack();
    }

    /**
     * This is called whenever the screen is shown.
     * Override it in order to update any screen state that might have
     * changed since the last showing.
     */
    protected void refresh() {}
    
    public boolean isPortrait() {
        return Registry.get(Display.class).isPortrait();
    }

    private void refreshOnOrientationChanage() {
        form.addOrientationListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                refresh();
            }
        });
    }
    
    final public void layoutForm() {
        form.removeAll();
        if (isPortrait()) {
            layoutForPortrait();
        } else {
            layoutForLandscape();
        }
    }

    protected abstract void layoutForPortrait();

    protected void layoutForLandscape() {
        layoutForPortrait();
    }

    private void log(String message) {
        LogManager.of().getLog(Screen.class).log(message);    
    }
}
