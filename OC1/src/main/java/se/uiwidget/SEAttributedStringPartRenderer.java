package se.uiwidget;

import x.ui.AttributedString;
import x.ui.Color;
import x.ui.Dimension;
import x.ui.Point;

import java.awt.*;

final class SEAttributedStringPartRenderer
    implements AttributedString.PartRenderer
{
    private final Graphics2D g;

    public SEAttributedStringPartRenderer(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void renderPartAt(AttributedString.Part part, Point point) {
        Color color = part.color;
        if (color!=null) {
            g.setColor(awtColor(part.color));
        }
        g.drawString(part.text,point.x,point.y + height());
    }

    @Override
    public Dimension size(AttributedString.Part part) {
        String text = part.text;
        int w = fontMetrics().stringWidth(text);
        return new Dimension(w,height());
    }

    int height() {
        return fontMetrics().getHeight();
    }

    FontMetrics fontMetrics() {
        return g.getFontMetrics();
    }

    private java.awt.Color awtColor(Color color) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
