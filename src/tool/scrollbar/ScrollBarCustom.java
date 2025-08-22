package tool.scrollbar;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;
public class ScrollBarCustom
extends JScrollBar
{
public ScrollBarCustom() {
/* 16 */     setUI(new ModernScrollBarUI());
/* 17 */     setPreferredSize(new Dimension(5, 5));
/* 18 */     setForeground(new Color(94, 139, 231));
/* 19 */     setUnitIncrement(20);
/* 20 */     setOpaque(false);
}
}



