package tool.textfield;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
public class PasswordField
extends JPasswordField {
public String getHelperText() {
/*  24 */     return this.helperText;
}
private final Animator animator;
public void setHelperText(String helperText) {
/*  28 */     this.helperText = helperText;
/*  29 */     repaint();
}
public String getLabelText() {
/*  33 */     return this.labelText;
}
public void setLabelText(String labelText) {
/*  37 */     this.labelText = labelText;
}
public Color getLineColor() {
/*  41 */     return this.lineColor;
}
public void setLineColor(Color lineColor) {
/*  45 */     this.lineColor = lineColor;
}
private boolean animateHinText = true;
private float location;
private boolean show;
private boolean mouseOver = false;
/*  53 */   private String labelText = "KUN";
/*  54 */   private String helperText = "";
/*  55 */   private int spaceHelperText = 15;
/*  56 */   private Color lineColor = new Color(3, 155, 216);
public PasswordField() {
/*  59 */     setBorder(new EmptyBorder(20, 3, 23, 3));
/*  60 */     setSelectionColor(new Color(76, 204, 255));
/*  61 */     addMouseListener(new MouseAdapter()
{
public void mouseEntered(MouseEvent me) {
/*  64 */             PasswordField.this.mouseOver = true;
/*  65 */             PasswordField.this.repaint();
}
public void mouseExited(MouseEvent me) {
/*  70 */             PasswordField.this.mouseOver = false;
/*  71 */             PasswordField.this.repaint();
}
});
/*  74 */     addFocusListener(new FocusAdapter()
{
public void focusGained(FocusEvent fe) {
/*  77 */             PasswordField.this.showing(false);
}
public void focusLost(FocusEvent fe) {
/*  82 */             PasswordField.this.showing(true);
}
});
/*  85 */     TimingTargetAdapter timingTargetAdapter = new TimingTargetAdapter()
{
public void begin() {
/*  88 */           PasswordField.this.animateHinText = PasswordField.this.getText().equals("");
}
public void timingEvent(float fraction) {
/*  93 */           PasswordField.this.location = fraction;
/*  94 */           PasswordField.this.repaint();
}
};
/*  98 */     this.animator = new Animator(300, (TimingTarget)timingTargetAdapter);
/*  99 */     this.animator.setResolution(0);
/* 100 */     this.animator.setAcceleration(0.5F);
/* 101 */     this.animator.setDeceleration(0.5F);
}
private void showing(boolean action) {
/* 105 */     if (this.animator.isRunning()) {
/* 106 */       this.animator.stop();
} else {
/* 108 */       this.location = 1.0F;
}
/* 110 */     this.animator.setStartFraction(1.0F - this.location);
/* 111 */     this.show = action;
/* 112 */     this.location = 1.0F - this.location;
/* 113 */     this.animator.start();
}
public void paint(Graphics grphcs) {
/* 118 */     super.paint(grphcs);
/* 119 */     Graphics2D g2 = (Graphics2D)grphcs;
/* 120 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 121 */     g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
/* 122 */     int width = getWidth();
/* 123 */     int height = getHeight();
/* 124 */     if (this.mouseOver) {
/* 125 */       g2.setColor(this.lineColor);
} else {
/* 127 */       g2.setColor(new Color(150, 150, 150));
}
/* 129 */     g2.fillRect(2, height - this.spaceHelperText - 1, width - 4, 1);
/* 130 */     createHintText(g2);
/* 131 */     createLineStyle(g2);
/* 132 */     createHelperText(g2);
/* 133 */     g2.dispose();
}
private void createHintText(Graphics2D g2) {
double size;
/* 137 */     Insets in = getInsets();
/* 138 */     g2.setColor(new Color(150, 150, 150));
/* 139 */     FontMetrics ft = g2.getFontMetrics();
/* 140 */     Rectangle2D r2 = ft.getStringBounds(this.labelText, g2);
/* 141 */     double height = (getHeight() - in.top - in.bottom);
/* 142 */     double textY = (height - r2.getHeight()) / 2.0D;
/* 144 */     if (this.animateHinText) {
/* 145 */       if (this.show) {
/* 146 */         size = (18.0F * (1.0F - this.location));
} else {
/* 148 */         size = (18.0F * this.location);
}
} else {
/* 151 */       size = 18.0D;
}
/* 153 */     g2.drawString(this.labelText, in.right, (int)(in.top + textY + ft.getAscent() - size));
}
private void createLineStyle(Graphics2D g2) {
/* 157 */     if (isFocusOwner()) {
/* 158 */       double size, width = (getWidth() - 4);
/* 159 */       int height = getHeight() - this.spaceHelperText;
/* 160 */       g2.setColor(this.lineColor);
/* 162 */       if (this.show) {
/* 163 */         size = width * (1.0F - this.location);
} else {
/* 165 */         size = width * this.location;
}
/* 167 */       double x = (width - size) / 2.0D;
/* 168 */       g2.fillRect((int)(x + 2.0D), height - 2, (int)size, 2);
}
}
private void createHelperText(Graphics2D g2) {
/* 173 */     if (this.helperText != null && !this.helperText.equals("")) {
/* 174 */       Insets in = getInsets();
/* 175 */       int height = getHeight() - 15;
/* 176 */       g2.setColor(new Color(255, 76, 76));
/* 177 */       Font font = getFont();
/* 178 */       g2.setFont(font.deriveFont(0, (font.getSize() - 1)));
/* 179 */       FontMetrics ft = g2.getFontMetrics();
/* 180 */       Rectangle2D r2 = ft.getStringBounds(this.labelText, g2);
/* 181 */       double textY = (15.0D - r2.getHeight()) / 2.0D;
/* 182 */       g2.drawString(this.helperText, in.right, (int)((height + ft.getAscent()) - textY));
}
}
public void setText(String string) {
/* 188 */     if (!getText().equals(string)) {
/* 189 */       showing(string.equals(""));
}
/* 191 */     super.setText(string);
}
}



