package org.robovm.apple.uikit;

import org.robovm.apple.foundation.*;

public class UIViewController
        extends UIResponder
        implements NSCoding, UIAppearanceContainer, UITraitEnvironment, UIStateRestoring, NSExtensionRequestHandling
{
    private UIView view = new UIView();
    private final NSArray<UIViewController> viewControllers = new NSArray<UIViewController>();

    public UIView getView() {
        return view;
    }

    public void setView(UIView view) {
        this.view = view;
    }

    @Override
    public void encode(NSCoder nsCoder) {

    }

    @Override
    public void beginRequest(NSExtensionContext nsExtensionContext) {

    }

    @Override
    public UIStateRestoring getRestorationParent() {
        return null;
    }

    @Override
    public Class<?> getObjectRestorationClass() {
        return null;
    }

    @Override
    public void encodeRestorableState(NSCoder nsCoder) {

    }

    @Override
    public void decodeRestorableState(NSCoder nsCoder) {

    }

    @Override
    public void applicationFinishedRestoringState() {

    }

    @Override
    public UITraitCollection getTraitCollection() {
        return null;
    }

    @Override
    public void traitCollectionDidChange(UITraitCollection uiTraitCollection) {

    }

    public NSArray<UIViewController> getChildViewControllers() {
        return viewControllers;
    }

    public  void addChildViewController(UIViewController var1) {

    }
}
