package tool.button;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class ButtonBadges extends JButton {

    private Animator animator;
    private int targetSize;
    private float animatSize;
    private Point pressedPoint;
    private float alpha;
    private Color effectColor = new Color(173, 173, 173);
    private int badges;

    public ButtonBadges() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                animatSize = 0f;
                pressedPoint = me.getPoint();
                alpha = 0.5f;
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }
        });

        animator = new Animator(400, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1.0f - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        });
        animator.setResolution(0);
    }

    public int getBadges() {
        return badges;
    }

    public void setBadges(int badges) {
        this.badges = badges;
        repaint();
    }

    public Color getEffectColor() {
        return effectColor;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ background hình tròn
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height) - 8;
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        g2.setColor(getBackground());
        g2.fillOval(x, y, size, size);

        // Vẽ ripple effect khi nhấn
        if (pressedPoint != null) {
            g2.setColor(effectColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.fillOval(
                    pressedPoint.x - (int)(animatSize / 2f),
                    pressedPoint.y - (int)(animatSize / 2f),
                    (int) animatSize,
                    (int) animatSize
            );
        }

        g2.dispose();
        super.paintComponent(g); // vẽ text/icon
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (badges > 0) {
            String value = (badges > 9) ? "+9" : Integer.toString(badges);
            int width = getWidth();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            FontMetrics fm = g2.getFontMetrics();
            Rectangle2D r2 = fm.getStringBounds(value, g2);
            int fw = (int) r2.getWidth();
            int fh = (int) r2.getHeight();

            int size = Math.max(fw, fh) + 4;
            g2.setColor(getForeground());
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            g2.fillOval(width - size, 0, size, size);

            int x = (size - fw) / 2;
            g2.setColor(Color.WHITE);
            g2.setComposite(AlphaComposite.SrcOver);
            g2.drawString(value, width - size + x, fm.getAscent() + 1);
        }
    }
}
