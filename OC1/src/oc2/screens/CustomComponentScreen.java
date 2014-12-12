package oc2.screens;

import com.codename1.ui.layouts.BorderLayout;
import oc1.screen.*;

public final class CustomComponentScreen
    extends Screen
{
    
    public static final ScreenFactory FACTORY = new GlobScreenFactory("Custom") {
        public Screen doCreate(ScreenLink link) {
            return new CustomComponentScreen();
        }     
    };

    CustomComponentScreen() {
        super("Custom");
    }
    
    @Override
    public void layoutForPortrait() {
        form.setLayout(new BorderLayout());
        form.addComponent(BorderLayout.CENTER,new CustomComponent());
    }
    
    @Override
    protected void refresh() {
        super.refresh();
    }

}