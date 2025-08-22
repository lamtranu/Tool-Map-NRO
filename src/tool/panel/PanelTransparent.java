package tool.panel;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
public class PanelTransparent
extends JComponent
{
private float alpha;
public float getAlpha() {
/* 18 */     return this.alpha;
}
public void setAlpha(float alpha) {
/* 22 */     this.alpha = alpha;
/* 23 */     repaint();
}
public void paint(Graphics grphcs) {
/* 30 */     Graphics2D g2 = (Graphics2D)grphcs.create();
/* 31 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 32 */     g2.setColor(getBackground());
/* 33 */     g2.setComposite(AlphaComposite.getInstance(3, this.alpha));
/* 34 */     int[] x = { 0, getWidth(), getWidth() - 100, 0 };
/* 35 */     int[] y = { 0, 0, getHeight(), getHeight() };
/* 36 */     g2.fillPolygon(x, y, x.length);
/* 37 */     g2.dispose();
/* 38 */     super.paint(grphcs);
}
}



