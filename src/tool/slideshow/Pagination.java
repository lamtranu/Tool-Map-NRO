package tool.slideshow;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
* Pagination thuần Java, không cần MigLayout hay Trident
*/
public class Pagination extends JPanel {
private int index;
private int currentIndex;
private EventPagination event;
private int dotSize = 12;       // kích thước dot
private int spacing = 10;       // khoảng cách giữa dot
public Pagination() {
setLayout(new FlowLayout(FlowLayout.CENTER, spacing, 0));
}
public void setEventPagination(EventPagination event) {
this.event = event;
}
public void setTotalPage(int totalPage) {
removeAll();
for (int i = 0; i < totalPage; i++) {
add(new Item(i));
}
repaint();
revalidate();
}
public void setIndex(int index) {
this.index = index;
}
public void setCurrentIndex(int currentIndex) {
this.currentIndex = currentIndex;
if (getComponentCount() > currentIndex) {
((Item) getComponent(currentIndex)).setAlpha(1.0f);
}
}
/**
* Animation đơn giản (alpha 0->1) dùng Timer, không thư viện ngoài
*/
public void animateToIndex(int newIndex) {
if (newIndex < 0 || newIndex >= getComponentCount()) return;
Item itemIn = (Item) getComponent(newIndex);
Item itemOut = (Item) getComponent(currentIndex);
Timer timer = new Timer(15, null);
final float[] alpha = {0.0f};
timer.addActionListener((ActionEvent e) -> {
alpha[0] += 0.1f;
if (alpha[0] >= 1.0f) {
alpha[0] = 1.0f;
currentIndex = newIndex;
timer.stop();
}
itemIn.setAlpha(alpha[0]);
itemOut.setAlpha(1.0f - alpha[0]);
});
timer.start();
}
private class Item extends JButton {
private float alpha = 0.0f;
public Item(final int idx) {
setContentAreaFilled(false);
setBorder(new EmptyBorder(5, 5, 5, 5));
setBackground(Color.WHITE);
setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
setPreferredSize(new Dimension(dotSize, dotSize));
addActionListener((ActionEvent e) -> {
if (event != null) event.onClick(idx);
});
}
public void setAlpha(float alpha) {
this.alpha = alpha;
repaint();
}
@Override
public void paintComponent(Graphics g) {
super.paintComponent(g);
Graphics2D g2 = (Graphics2D) g.create();
g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
// background mờ
g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
g2.setColor(getBackground());
g2.fillOval(0, 0, dotSize, dotSize);
// dot chính
g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
g2.setColor(new Color(11, 124, 173));
g2.fillOval(0, 0, dotSize, dotSize);
g2.dispose();
}
}
}
