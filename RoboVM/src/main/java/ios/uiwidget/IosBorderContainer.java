package ios.uiwidget;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.uikit.UIView;

final class IosBorderContainer
        extends UIView
{
    private UIView center;
    private UIView north;
    private UIView east;
    private UIView west;

    private IosBorderContainer() {}

    static IosBorderContainer of(UIView center) {
        IosBorderContainer borderView = new IosBorderContainer();
        borderView.center = center;
        borderView.addSubview(center);
        return borderView;
    }

    public IosBorderContainer north(UIView north) {
        this.north = north;
        addSubview(north);
        return this;
    }

    public IosBorderContainer east(UIView east) {
        this.east = east;
        addSubview(east);
        return this;
    }

    public IosBorderContainer west(UIView west) {
        this.west = west;
        this.addSubview(west);
        return this;
    }

    @Override
    public void layoutSubviews() {
        if (north!=null) {
            layoutTopRow();
        }
        layoutBottomRow();
    }

    void layoutTopRow() {
        north.setFrame(new CGRect(0, 0, width(), height(north)));
    }

    void layoutBottomRow() {
        if (west!=null) {
            layoutWest();
        }
        if (east!=null) {
            layoutEast();
        }
        layoutCenter();
    }

    void layoutCenter() {
        center.setFrame(new CGRect(width(west), height(north), centerWidth(), bottomHeight()));
    }

    void layoutWest() {
        west.setFrame(new CGRect(0, height(north), width(west), bottomHeight()));
    }

    void layoutEast() {
        east.setFrame(new CGRect(width() - width(east), height(north), width(east), bottomHeight()));
    }

    double centerWidth() {
        return width() - width(west) - width(east);
    }

    double bottomHeight() {
        return height() - height(north);
    }

    double width() {
        return getFrame().getWidth();
    }

    double height() {
        return getFrame().getHeight();
    }

    double width(UIView view) {
        return view==null ? 0 : size(view).getWidth();
    }

    double height(UIView view) {
        return view==null ? 0 : size(view).getHeight();
    }

    CGSize size(UIView view) {
        return view.getSizeThatFits(getFrame().getSize());
    }

    @Override
    public CGSize getSizeThatFits(CGSize size) {
        return new CGSize(widthThatFits(),heightThatFits());
    }

    private double widthThatFits() {
        return max(width(north), width(east) + width(west) + width(center));
    }

    private double heightThatFits() {
        return height(north) + max(height(east),height(west),height(center));
    }

    private static double max(double a, double b, double c) {
        return max(max(a, b), c);
    }

    private static double max(double a, double b) {
        return Math.max(a,b);
    }

}
