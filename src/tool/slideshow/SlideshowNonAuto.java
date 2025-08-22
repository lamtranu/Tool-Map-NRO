package tool.slideshow;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class SlideshowNonAuto extends JLayeredPane {
private final JPanel panel;
private Component componentShow;
private Component componentOut;
private int currentIndex;
private boolean next;
private Timer timer;
private int animationDuration = 500; // ms
private int animationInterval = 10;  // ms
private int animationStep;
private int animationMaxSteps;
public SlideshowNonAuto() {
setOpaque(false);
panel = new JPanel(null); // Dùng null layout để tự quản lý vị trí
panel.setOpaque(false);
add(panel, Integer.valueOf(0));
animationMaxSteps = animationDuration / animationInterval;
timer = new Timer(animationInterval, new ActionListener() {
public void actionPerformed(ActionEvent e) {
animateStep();
}
});
}
public void initSlideshow(Component... coms) {
if (coms.length >= 2) {
for (Component com : coms) {
com.setVisible(false);
panel.add(com);
}
componentShow = panel.getComponent(0);
componentShow.setVisible(true);
componentShow.setBounds(0, 0, getWidth(), getHeight());
currentIndex = 0;
}
}
public void next() {
if (!timer.isRunning()) {
next = true;
int nextIndex = (currentIndex + 1) % panel.getComponentCount();
startAnimation(nextIndex);
}
}
public void back() {
if (!timer.isRunning()) {
next = false;
int prevIndex = (currentIndex - 1 + panel.getComponentCount()) % panel.getComponentCount();
startAnimation(prevIndex);
}
}
public void slideTo(int index) {
if (!timer.isRunning() && index >= 0 && index < panel.getComponentCount() && index != currentIndex) {
next = (index > currentIndex);
startAnimation(index);
}
}
private void startAnimation(int targetIndex) {
componentOut = componentShow;
componentShow = panel.getComponent(targetIndex);
componentShow.setVisible(true);
animationStep = 0;
componentShow.setBounds(next ? getWidth() : -getWidth(), 0, getWidth(), getHeight());
timer.start();
}
private void animateStep() {
animationStep++;
double fraction = (double) animationStep / animationMaxSteps;
int width = getWidth();
int offset = (int) (width * fraction);
if (next) {
componentShow.setBounds(width - offset, 0, width, getHeight());
componentOut.setBounds(-offset, 0, width, getHeight());
} else {
componentShow.setBounds(-width + offset, 0, width, getHeight());
componentOut.setBounds(offset, 0, width, getHeight());
}
panel.repaint();
if (animationStep >= animationMaxSteps) {
timer.stop();
componentOut.setVisible(false);
componentShow.setBounds(0, 0, getWidth(), getHeight());
currentIndex = panel.getComponentZOrder(componentShow);
}
}
@Override
public void doLayout() {
super.doLayout();
if (componentShow != null) {
componentShow.setBounds(componentShow.getX(), 0, getWidth(), getHeight());
}
if (componentOut != null && componentOut.isVisible()) {
componentOut.setBounds(componentOut.getX(), 0, getWidth(), getHeight());
}
panel.setBounds(0, 0, getWidth(), getHeight());
}
public int getCurrentSlide() {
return currentIndex;
}
}
