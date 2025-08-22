package tool.combosuggestion;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

@SuppressWarnings("serial")
public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        // Set UI custom
        setUI(new ModernScrollBarUI());

        // Kích thước scrollbar (rộng/thanh ngang)
        setPreferredSize(new Dimension(8, 8));

        // Màu thanh cuộn
        setForeground(new Color(180, 180, 180));

        // Màu nền track (không vẽ track, nhưng để mặc định trắng)
        setBackground(Color.WHITE);

        // Tốc độ scroll
        setUnitIncrement(20);

        // Tắt các border mặc định
        setOpaque(false);
        setBorder(null);
    }
}
