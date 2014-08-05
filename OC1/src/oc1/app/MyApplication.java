package oc1.app;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import oc1.log.LogManager;
import oc1.screen.Screen;
import oc1.screen.ScreenFactory;
import oc1.screens.HomeScreen;

public class MyApplication {

    private Form current;

    public void init(Object context) {
        try {
            loadTheme();
            RegistryLoader.load();
            ExceptionLogger.of().install();
        } catch(IOException e){
            log(e);
        }
    }

    private void log(Exception e) {
        LogManager.of().getLog(MyApplication.class).log(e);    
    }
    
    private void loadTheme() throws IOException {
        Resources theme = Resources.openLayered("/theme");
        setThemeProps(theme);
        Registry.put(Resources.class, theme);
    }

    private UIManager getUIManager() {
       return UIManager.getInstance();
    }
    
    private Display getDisplay() {
       return Display.getInstance();
    }
    
    private void setThemeProps(Resources theme) throws IOException {
        getUIManager().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
    }

    public void start() {
        if (current != null){
            current.show();
            return;
        }
        HeartbeatMonitor.install();
        show();
    }

    private void show() {
        ScreenFactory factory = Registry.get(ScreenFactory.class);
        factory.create().show();
    }
    
    public void stop() {
        current = getDisplay().getCurrent();
    }
    
    public void destroy() {}

}
