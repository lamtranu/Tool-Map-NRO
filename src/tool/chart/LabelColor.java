package tool.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class LabelColor extends JLabel {

    private Color borderColor = null; // Màu viền, có thể set nếu muốn
    private int borderWidth = 1;       // Độ dày viền

    public LabelColor() {
        setOpaque(false);
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
    }

    public void setBorderWidth(int width) {
        this.borderWidth = width;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int size = Math.min(width, height) - borderWidth * 2;

            int x = (width - size) / 2;
            int y = (height - size) / 2;

            // Vẽ background
            g2.setColor(getBackground());
            g2.fillOval(x, y, size, size);

            // Vẽ viền nếu có
            if (borderColor != null) {
                g2.setColor(borderColor);
                g2.setStroke(new java.awt.BasicStroke(borderWidth));
                g2.drawOval(x, y, size, size);
            }
        } finally {
            g2.dispose();
        }
        super.paintComponent(g);
    }
}
