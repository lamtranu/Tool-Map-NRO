package tool.progressbar;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
public class ProgressCircleUI
extends BasicProgressBarUI
{
private Animator animator;
private float animate;
public float getAnimate() {
/* 29 */     return this.animate;
}
public void setAnimate(float animate) {
/* 33 */     this.animate = animate;
}
public void start() {
/* 40 */     if (!this.animator.isRunning()) {
/* 41 */       this.animator.start();
}
}
public void installUI(JComponent jc) {
/* 47 */     super.installUI(jc);
/* 48 */     TimingTargetAdapter timingTargetAdapter = new TimingTargetAdapter()
{
public void timingEvent(float fraction) {
/* 51 */           ProgressCircleUI.this.setAnimate(fraction);
/* 52 */           ProgressCircleUI.this.progressBar.repaint();
}
};
/* 55 */     this.animator = new Animator(800, (TimingTarget)timingTargetAdapter);
/* 56 */     this.animator.setResolution(0);
/* 57 */     this.animator.setAcceleration(0.5F);
/* 58 */     this.animator.setDeceleration(0.5F);
}
public void paint(Graphics g, JComponent c) {
/* 63 */     Graphics2D g2 = (Graphics2D)g.create();
/* 64 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 65 */     Area area = new Area(createCircle(c, 0, 0, 360.0D));
/* 66 */     area.subtract(new Area(createCircle(c, 15, 0, 360.0D)));
/* 67 */     g2.setColor(new Color(216, 216, 216, 50));
/* 68 */     g2.fill(area);
/* 69 */     g2.setComposite(AlphaComposite.SrcIn);
/* 70 */     int r = (int)(this.progressBar.getPercentComplete() * 360.0D);
/* 71 */     g2.setPaint(new GradientPaint(0.0F, 0.0F, this.progressBar.getBackground(), 0.0F, c.getHeight(), this.progressBar.getForeground()));
/* 72 */     Area area1 = new Area(createCircle(c, 0, 90, (-r * this.animate)));
/* 73 */     area1.subtract(new Area(createCircle(c, 15, 0, 360.0D)));
/* 74 */     g2.fill(area1);
/* 75 */     if (this.progressBar.isStringPainted()) {
/* 76 */       paintString(g);
}
/* 78 */     g2.dispose();
}
private Shape createCircle(Component c, int s, int start, double angle) {
/* 82 */     int width = c.getWidth();
/* 83 */     int height = c.getHeight();
/* 84 */     int size = Math.min(width, height) - s;
/* 85 */     int x = (width - size) / 2;
/* 86 */     int y = (height - size) / 2;
/* 87 */     return new Arc2D.Double(x, y, size, size, start, angle, 2);
}
private void paintString(Graphics g) {
/* 91 */     Insets b = this.progressBar.getInsets();
/* 92 */     int barRectWidth = this.progressBar.getWidth() - b.right - b.left;
/* 93 */     int barRectHeight = this.progressBar.getHeight() - b.top - b.bottom;
/* 94 */     g.setColor(new Color(212, 212, 212));
/* 95 */     paintString(g, b.left, b.top, barRectWidth, barRectHeight, 0, b);
}
}



