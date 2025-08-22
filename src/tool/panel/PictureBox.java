package tool.panel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
public class PictureBox
extends JLayeredPane
{
private Icon image;
public Icon getImage() {
/* 23 */     return this.image;
}
public void setImage(Icon image) {
/* 27 */     this.image = image;
}
protected void paintComponent(Graphics grphcs) {
/* 34 */     if (this.image != null) {
/* 35 */       Graphics2D g2 = (Graphics2D)grphcs;
/* 36 */       Rectangle size = getAutoSize(this.image);
/* 37 */       g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 38 */       g2.drawImage(toImage(this.image), (size.getLocation()).x, (size.getLocation()).y, (size.getSize()).width, (size.getSize()).height, null);
}
/* 40 */     super.paintComponent(grphcs);
}
private Rectangle getAutoSize(Icon image) {
/* 44 */     int w = getWidth();
/* 45 */     int h = getHeight();
/* 46 */     if (w > image.getIconWidth()) {
/* 47 */       w = image.getIconWidth();
}
/* 49 */     if (h > image.getIconHeight()) {
/* 50 */       h = image.getIconHeight();
}
/* 52 */     int iw = image.getIconWidth();
/* 53 */     int ih = image.getIconHeight();
/* 54 */     double xScale = w / iw;
/* 55 */     double yScale = h / ih;
/* 56 */     double scale = Math.max(xScale, yScale);
/* 57 */     int width = (int)(scale * iw);
/* 58 */     int height = (int)(scale * ih);
/* 59 */     int x = getWidth() / 2 - width / 2;
/* 60 */     int y = getHeight() / 2 - height / 2;
/* 61 */     return new Rectangle(new Point(x, y), new Dimension(width, height));
}
private Image toImage(Icon icon) {
/* 65 */     return ((ImageIcon)icon).getImage();
}
}



