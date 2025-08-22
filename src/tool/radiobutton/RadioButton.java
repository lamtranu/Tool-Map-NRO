package tool.radiobutton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JRadioButton;
public class RadioButton
extends JRadioButton
{
public RadioButton() {
/* 19 */     setOpaque(false);
/* 20 */     setCursor(new Cursor(12));
/* 21 */     setBackground(new Color(69, 124, 235));
}
public void paint(Graphics grphcs) {
/* 26 */     super.paint(grphcs);
/* 27 */     Graphics2D g2 = (Graphics2D)grphcs;
/* 28 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 29 */     int ly = (getHeight() - 16) / 2;
/* 30 */     if (isSelected()) {
/* 31 */       if (isEnabled()) {
/* 32 */         g2.setColor(getBackground());
} else {
/* 34 */         g2.setColor(Color.GRAY);
}
/* 36 */       g2.fillOval(1, ly, 16, 16);
/* 37 */       g2.setColor(Color.WHITE);
/* 38 */       g2.fillOval(2, ly + 1, 14, 14);
/* 39 */       if (isEnabled()) {
/* 40 */         g2.setColor(getBackground());
} else {
/* 42 */         g2.setColor(Color.GRAY);
}
/* 44 */       g2.fillOval(5, ly + 4, 8, 8);
} else {
/* 46 */       if (isFocusOwner()) {
/* 47 */         g2.setColor(getBackground());
} else {
/* 49 */         g2.setColor(Color.GRAY);
}
/* 51 */       g2.fillOval(1, ly, 16, 16);
/* 52 */       g2.setColor(Color.WHITE);
/* 53 */       g2.fillOval(2, ly + 1, 14, 14);
}
/* 55 */     g2.dispose();
}
}



