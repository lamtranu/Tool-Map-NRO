package tool.avatar;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Avatar extends JComponent {

    private Icon icon;
    private int borderSize;

    public Icon getIcon() {
        return this.icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
        repaint();
    }

    public int getBorderSize() {
        return this.borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (icon == null) {
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height) - borderSize * 2;
        int x = (width - diameter) / 2;
        int y = (height - diameter) / 2;

        // Lấy ảnh vừa với vòng tròn
        Rectangle size = getAutoSize(icon, diameter);
        BufferedImage img = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2_img = img.createGraphics();

        g2_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // tạo mask tròn
        g2_img.fillOval(0, 0, diameter, diameter);
        Composite old = g2_img.getComposite();
        g2_img.setComposite(AlphaComposite.SrcIn);
        g2_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2_img.drawImage(toImage(icon), size.x, size.y, size.width, size.height, null);
        g2_img.setComposite(old);
        g2_img.dispose();

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ border
        if (borderSize > 0) {
            g2.setColor(getForeground());
            g2.fillOval(x, y, diameter + borderSize * 2, diameter + borderSize * 2);
        }

        // Vẽ background nếu opaque
        if (isOpaque()) {
            g2.setColor(getBackground());
            g2.fillOval(x + borderSize, y + borderSize, diameter, diameter);
        }

        // Vẽ ảnh
        g2.drawImage(img, x + borderSize, y + borderSize, null);
    }

    private Rectangle getAutoSize(Icon icon, int size) {
        int iw = icon.getIconWidth();
        int ih = icon.getIconHeight();

        double scale = (double) size / Math.max(iw, ih);
        int w = (int) (iw * scale);
        int h = (int) (ih * scale);

        int x = (size - w) / 2;
        int y = (size - h) / 2;

        return new Rectangle(new Point(x, y), new Dimension(w, h));
    }

    private Image toImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            // convert icon sang image
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }
}
