package tool.checkbox;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JCheckBox;

public class JCheckBoxCustom extends JCheckBox {

    private final int border = 4;
    private final int size = 16;
    private final int arc = 4;

    public JCheckBoxCustom() {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setBackground(new Color(69, 124, 235));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ly = (getHeight() - size) / 2;

        // Vẽ checkbox background
        if (isSelected()) {
            g2.setColor(isEnabled() ? getBackground() : Color.GRAY);
            g2.fillRoundRect(1, ly, size, size, arc, arc);

            // Vẽ tick
            int[] px = {4, 8, 14, 12, 8, 6};
            int[] py = {ly + 8, ly + 14, ly + 5, ly + 3, ly + 10, ly + 6};
            g2.setColor(Color.WHITE);
            g2.fillPolygon(px, py, px.length);

        } else {
            // Checkbox chưa chọn
            g2.setColor(Color.GRAY);
            g2.fillRoundRect(1, ly, size, size, arc, arc);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(2, ly + 1, size - 2, size - 2, arc, arc);
        }

        g2.dispose();
    }
}
