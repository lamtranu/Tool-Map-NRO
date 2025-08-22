package tool.liquidprogress;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicProgressBarUI;
public class LiquidProgressUI
extends BasicProgressBarUI
{
private final LiquidProgress pro;
private Thread thread;
private boolean start;
/*  25 */   private float location1 = -1.0F;
private float location2;
public LiquidProgressUI(LiquidProgress pro) {
/*  29 */     this.pro = pro;
/*  30 */     init();
}
private void init() {
/*  34 */     start();
}
public void start() {
/*  38 */     if (!this.start) {
/*  39 */       this.start = true;
/*  40 */       this.thread = new Thread(new Runnable()
{
public void run() {
/*  43 */               while (LiquidProgressUI.this.start) {
/*  44 */                 LiquidProgressUI.this.location1 = LiquidProgressUI.this.location1 + 0.01F;
/*  45 */                 LiquidProgressUI.this.location2 = LiquidProgressUI.this.location2 + 0.01F;
/*  46 */                 if (LiquidProgressUI.this.location1 > 1.0F) {
/*  47 */                   LiquidProgressUI.this.location1 = -1.0F;
}
/*  49 */                 if (LiquidProgressUI.this.location2 > 1.0F) {
/*  50 */                   LiquidProgressUI.this.location2 = -1.0F;
}
/*  52 */                 LiquidProgressUI.this.pro.repaint();
/*  53 */                 LiquidProgressUI.this.sleep();
}
}
});
/*  57 */       this.thread.start();
}
}
public void stop() {
/*  62 */     this.start = false;
}
private void sleep() {
try {
/*  67 */       Thread.sleep(5L);
/*  68 */     } catch (InterruptedException e) {
/*  69 */       System.err.println(e);
}
}
public void paint(Graphics grphcs, JComponent jc) {
/*  75 */     Graphics2D g2 = (Graphics2D)grphcs;
/*  76 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  77 */     int width = jc.getWidth();
/*  78 */     int height = jc.getHeight();
/*  79 */     int size = Math.min(width, height);
/*  80 */     int x = (width - size) / 2;
/*  81 */     int y = (height - size) / 2;
/*  82 */     g2.setColor(this.pro.getBorderColor());
/*  83 */     g2.fillOval(x, y, size, size);
/*  84 */     int borderSize = this.pro.getBorderSize();
/*  85 */     size -= borderSize * 2;
/*  86 */     g2.setColor(this.pro.getBackground());
/*  87 */     g2.fillOval(x + borderSize, y + borderSize, size, size);
/*  88 */     int spaceSize = this.pro.getSpaceSize();
/*  89 */     borderSize += spaceSize;
/*  90 */     size -= spaceSize * 2;
/*  91 */     createAnimation(g2, x + borderSize, y + borderSize, size);
/*  92 */     if (this.progressBar.isStringPainted()) {
/*  93 */       paintString(grphcs);
}
/*  95 */     g2.dispose();
}
private void createAnimation(Graphics2D grphcs, int x, int y, int size) {
/*  99 */     BufferedImage img = new BufferedImage(size, size, 2);
/* 100 */     Graphics2D g2 = img.createGraphics();
/* 101 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 102 */     Ellipse2D circle = new Ellipse2D.Double(0.0D, 0.0D, size, size);
/* 103 */     g2.setColor(this.progressBar.getBackground());
/* 104 */     g2.fill(circle);
/* 105 */     g2.setComposite(AlphaComposite.SrcIn);
/* 106 */     int value = (int)(size * this.progressBar.getPercentComplete());
/* 107 */     int waterStyleHeight = (int)(size * 0.07F);
/* 108 */     g2.setColor(this.pro.getAnimateColor());
/* 109 */     g2.fillRect(0, size - value, size, size);
/* 110 */     g2.fill((new ModelLiquid(new Rectangle((int)(this.location1 * size), size - value - waterStyleHeight, size, waterStyleHeight))).createWaterStyle());
/* 111 */     g2.fill((new ModelLiquid(new Rectangle((int)(this.location2 * size), size - value - waterStyleHeight, size, waterStyleHeight))).createWaterStyle());
/* 112 */     g2.dispose();
/* 113 */     grphcs.drawImage(img, x, y, (ImageObserver)null);
}
private void paintString(Graphics g) {
/* 117 */     Insets b = this.progressBar.getInsets();
/* 118 */     int barRectWidth = this.progressBar.getWidth() - b.right - b.left;
/* 119 */     int barRectHeight = this.progressBar.getHeight() - b.top - b.bottom;
/* 120 */     g.setColor(this.progressBar.getForeground());
/* 121 */     paintString(g, b.left, b.top, barRectWidth, barRectHeight, 0, b);
}
}



