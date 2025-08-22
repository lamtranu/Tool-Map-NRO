package tool.scrollbar;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;
public class ModernScrollBarUI
extends BasicScrollBarUI
{
/* 22 */   private final int THUMB_SIZE = 80;
protected Dimension getMaximumThumbSize() {
/* 26 */     if (this.scrollbar.getOrientation() == 1) {
/* 27 */       return new Dimension(0, 80);
}
/* 29 */     return new Dimension(80, 0);
}
protected Dimension getMinimumThumbSize() {
/* 35 */     if (this.scrollbar.getOrientation() == 1) {
/* 36 */       return new Dimension(0, 80);
}
/* 38 */     return new Dimension(80, 0);
}
protected JButton createIncreaseButton(int i) {
/* 44 */     return new ScrollBarButton();
}
protected JButton createDecreaseButton(int i) {
/* 49 */     return new ScrollBarButton();
}
protected void paintTrack(Graphics grphcs, JComponent jc, Rectangle rctngl) {}
protected void paintThumb(Graphics grphcs, JComponent jc, Rectangle rctngl) {
/* 59 */     Graphics2D g2 = (Graphics2D)grphcs;
/* 60 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 61 */     int x = rctngl.x;
/* 62 */     int y = rctngl.y;
/* 63 */     int width = rctngl.width;
/* 64 */     int height = rctngl.height;
/* 65 */     if (this.scrollbar.getOrientation() == 1) {
/* 66 */       y += 8;
/* 67 */       height -= 16;
} else {
/* 69 */       x += 8;
/* 70 */       width -= 16;
}
/* 72 */     g2.setColor(this.scrollbar.getForeground());
/* 73 */     g2.fillRoundRect(x, y, width, height, 1, 1);
}
private class ScrollBarButton
extends JButton {
public ScrollBarButton() {
/* 79 */       setBorder(BorderFactory.createEmptyBorder());
}
public void paint(Graphics grphcs) {}
}
}



