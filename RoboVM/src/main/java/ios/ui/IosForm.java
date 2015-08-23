package ios.ui;

import ios.uiwidget.IosBorderViewController;
import ios.uiwidget.IosButtonViewController;
import ios.uiwidget.IosLabelViewController;
import org.robovm.apple.uikit.*;
import x.Registry;
import x.command.Command;
import x.log.ILog;
import x.log.ILogManager;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XButton;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;

public final class IosForm
        extends UIViewController
        implements IForm
{
    private final PageLink link;
    private Command back;
    private IosButtonViewController backButton;
    private UIViewController rendered;

    IosForm(PageLink link) {
        this.link = link;
        this.backButton = backButton();
    }

    @Override
    public void layout(XComponent layout) {
        rendered = renderedForm(layout);
        addChildViewController(rendered);
        getView().addSubview(rendered.getView());
        show();
    }

    private UIViewController renderedForm(XComponent layout) {
        return center(render(layout))
                .north(navigationPanel());
    }

    private UIViewController render(XComponent layout) {
        return IosUIRenderer.render(layout);
    }

    private UIViewController navigationPanel() {
        backButton = backButton();
        return center(address())
                .west(backButton);
    }

    private IosBorderViewController center(UIViewController center) {
        return IosBorderViewController.of(center);
    }

    private IosLabelViewController address() {
        return IosLabelViewController.of(new XLabel(link.title()));
    }

    private IosButtonViewController backButton() {
        return IosButtonViewController.of(new XButton("<") {
            @Override
            public void onTap() {
                back.go();
            }
        });
    }

    @Override
    public void show() {
        log("show" + this);
        rendered.getView().setFrame(getView().getFrame());
        getView().setNeedsLayout();
        getView().setNeedsDisplay();
        display().show(this);
    }

    @Override
    public void setBackCommand(Command back) {
        this.back = back;
        backButton.setEnabled(back != null);
    }

    @Override
    public void showBack() {
        show();
    }

    @Override
    public String getTitle() {
        return link.title();
    }

    @Override
    public PageLink getScreenLink() {
        return link;
    }

    @Override
    public String toString() {
        return super.toString() + " link = " + link.toString();
    }

    private IosDisplay display() {
        return IosDisplay.of();
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosForm.class);
    }
}
