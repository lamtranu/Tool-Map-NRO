package tool.progressbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ProgressBarCustom
        extends JProgressBar {

    public Color getColorString() {
        /* 19 */ return this.colorString;
    }

    public void setColorString(Color colorString) {
        /* 23 */ this.colorString = colorString;
    }
    /* 26 */    private Color colorString = new Color(200, 200, 200);

    public ProgressBarCustom() {
        /* 29 */ setStringPainted(true);
        /* 30 */ setPreferredSize(new Dimension(100, 15));
        /* 31 */ setBackground(new Color(255, 255, 255));
        /* 32 */ setForeground(new Color(69, 124, 235));
        /* 33 */ setUI(new BasicProgressBarUI() {
            protected void paintString(Graphics grphcs, int i, int i1, int i2, int i3, int i4, Insets insets) {
                /* 36 */ grphcs.setColor(ProgressBarCustom.this.getColorString());
                /* 37 */ super.paintString(grphcs, i, i1, i2, i3, i4, insets);
            }
        });
    }
}
