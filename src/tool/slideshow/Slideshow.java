package tool.slideshow;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.Timer;
/**
* Slideshow thuần Java Swing, không cần thư viện ngoài.
*/
public class Slideshow extends JLayeredPane {
private final JPanel panel;        // panel chứa slide
private final Pagination pagination;
private Timer timer;               // timer auto next
private Component componentShow;
private Component componentOut;
private int currentIndex;
private boolean next;
private int animationStep = 0;
private final int animationMax = 20; // số bước animation
private Timer animationTimer;
public Slideshow() {
setOpaque(true);
setBackground(new Color(200, 200, 200));
panel = new JPanel(null); // null layout, tự set bounds
pagination = new Pagination();
pagination.setEventPagination((pageClick) -> {
if (pageClick != currentIndex && (animationTimer == null || !animationTimer.isRunning())) {
next = currentIndex < pageClick;
startAnimationTo(pageClick);
}
});
setLayout(null); // tự set bounds
add(panel, JLayeredPane.DEFAULT_LAYER);
add(pagination, JLayeredPane.POPUP_LAYER);
timer = new Timer(3000, (e) -> next());
}
public void initSlideshow(Component... slides) {
if (slides.length < 1) return;
for (Component c : slides) {
c.setVisible(false);
panel.add(c);
}
componentShow = slides[0];
componentShow.setVisible(true);
currentIndex = 0;
pagination.setTotalPage(slides.length);
pagination.setCurrentIndex(0);
// set size bounds khi Slideshow được add vào JFrame
addComponentListener(new java.awt.event.ComponentAdapter() {
@Override
public void componentResized(java.awt.event.ComponentEvent e) {
layoutComponents();
}
});
timer.start();
}
private void layoutComponents() {
panel.setBounds(0, 0, getWidth(), getHeight() - 30);
pagination.setBounds(0, getHeight() - 30, getWidth(), 30);
for (Component c : panel.getComponents()) {
c.setBounds(0, 0, panel.getWidth(), panel.getHeight());
}
}
private void startAnimationTo(int targetIndex) {
if (componentShow == null) return;
componentOut = componentShow;
componentShow = panel.getComponent(targetIndex);
componentShow.setVisible(true);
animationStep = 0;
currentIndex = targetIndex;
if (animationTimer != null && animationTimer.isRunning()) animationTimer.stop();
animationTimer = new Timer(15, null);
animationTimer.addActionListener((ActionEvent e) -> animateStep());
animationTimer.start();
}
private void animateStep() {
animationStep++;
float fraction = (float) animationStep / animationMax;
int w = panel.getWidth();
int xShow = (int) ((next ? 1 - fraction : fraction - 1) * w);
int xOut = (int) ((next ? -fraction : fraction) * w);
componentShow.setLocation(xShow, 0);
componentOut.setLocation(xOut, 0);;
if (animationStep >= animationMax) {
animationTimer.stop();
componentOut.setVisible(false);
componentShow.setLocation(0, 0);
}
}
public void next() {
int nextIndex = (currentIndex + 1) % panel.getComponentCount();
next = true;
startAnimationTo(nextIndex);
}
public void back() {
int backIndex = (currentIndex - 1 + panel.getComponentCount()) % panel.getComponentCount();
next = false;
startAnimationTo(backIndex);
}
}
