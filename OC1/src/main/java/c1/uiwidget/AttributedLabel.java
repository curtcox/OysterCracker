package c1.uiwidget;

import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import x.ui.Color;

import static com.codename1.ui.plaf.Style.*;

final class AttributedLabel
    extends Component
{

    @Override
    public void paint(Graphics g) {
        drawBackground(g);
        drawText(g);
    }

    void drawText(Graphics g) {
        g.setColor(Color.BLACK.toInt());
        g.drawString("text", 0, 0);
        g.drawString("TEXT_DECORATION_NONE", 0, 20, TEXT_DECORATION_NONE);
        g.drawString("TEXT_DECORATION_UNDERLINE", 0, 40, TEXT_DECORATION_UNDERLINE);
        g.drawString("TEXT_DECORATION_STRIKETHRU", 0, 60, TEXT_DECORATION_STRIKETHRU);
        g.drawString("TEXT_DECORATION_OVERLINE", 0, 80, TEXT_DECORATION_OVERLINE);
        g.drawString("TEXT_DECORATION_3D_SHADOW_NORTH", 0, 100, TEXT_DECORATION_3D_SHADOW_NORTH);
        g.drawString("TEXT_DECORATION_3D", 0, 120, TEXT_DECORATION_3D);
        g.drawString("TEXT_DECORATION_3D_LOWERED", 0, 140, TEXT_DECORATION_3D_LOWERED);
    }
    
    void drawBackground(Graphics g) {
        g.setColor(Color.WHITE.toInt());
        g.fillRect(0,0, getWidth(), getHeight());
    }
}
