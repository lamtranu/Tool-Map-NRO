package tool.liquidprogress;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JProgressBar;
public class LiquidProgress
extends JProgressBar
{
private final LiquidProgressUI UI;
public int getBorderSize() {
/* 17 */     return this.borderSize;
}
public void setBorderSize(int borderSize) {
/* 21 */     this.borderSize = borderSize;
}
public int getSpaceSize() {
/* 25 */     return this.spaceSize;
}
public void setSpaceSize(int spaceSize) {
/* 29 */     this.spaceSize = spaceSize;
}
public Color getAnimateColor() {
/* 33 */     return this.animateColor;
}
public void setAnimateColor(Color animateColor) {
/* 37 */     this.animateColor = animateColor;
}
public Color getBorderColor() {
/* 41 */     return this.borderColor;
}
public void setBorderColor(Color borderColor) {
/* 45 */     this.borderColor = borderColor;
}
/* 49 */   private int borderSize = 5;
/* 50 */   private int spaceSize = 5;
/* 51 */   private Color animateColor = new Color(125, 216, 255);
/* 52 */   private Color borderColor = new Color(0, 178, 255);
public LiquidProgress() {
/* 55 */     this.UI = new LiquidProgressUI(this);
/* 56 */     setOpaque(false);
/* 57 */     setFont(new Font(getFont().getFamily(), 1, 20));
/* 58 */     setPreferredSize(new Dimension(100, 100));
/* 59 */     setBackground(Color.WHITE);
/* 60 */     setForeground(new Color(0, 178, 255));
/* 61 */     setUI(this.UI);
/* 62 */     setStringPainted(true);
}
public void startAnimation() {
/* 66 */     this.UI.start();
}
public void stopAnimation() {
/* 70 */     this.UI.stop();
}
}



