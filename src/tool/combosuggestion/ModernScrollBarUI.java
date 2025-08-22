package tool.combosuggestion;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

@SuppressWarnings("serial")
public class ModernScrollBarUI extends BasicScrollBarUI {

    private static final int THUMB_SIZE = 60;

    @Override
    protected Dimension getMinimumThumbSize() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(0, THUMB_SIZE);
        } else {
            return new Dimension(THUMB_SIZE, 0);
        }
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new ScrollBarButton();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new ScrollBarButton();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // Không vẽ track
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = thumbBounds.x;
        int y = thumbBounds.y;
        int width = thumbBounds.width - 4;
        int height = thumbBounds.height;

        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            y += 8;
            height -= 16;
        } else {
            x += 8;
            width -= 16;
        }

        g2.setColor(scrollbar.getForeground());
        g2.fillRoundRect(x + 2, y, width, height, 10, 10);
        g2.dispose();
    }

    // ================= Scroll bar button vô hình ================= //
    private static class ScrollBarButton extends JButton {
        public ScrollBarButton() {
            setBorder(BorderFactory.createEmptyBorder());
            setOpaque(false);
            setContentAreaFilled(false);
        }

        @Override
        public void paint(Graphics g) {
            // Không vẽ button
        }
    }
}
