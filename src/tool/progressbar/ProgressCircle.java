package tool.progressbar;
import javax.swing.JProgressBar;
public class ProgressCircle
extends JProgressBar
{
private final ProgressCircleUI ui;
public ProgressCircle() {
/* 16 */     setOpaque(false);
/* 17 */     setStringPainted(true);
/* 18 */     this.ui = new ProgressCircleUI();
/* 19 */     setUI(this.ui);
}
public String getString() {
/* 24 */     return (int)(getValue() * this.ui.getAnimate()) + "%";
}
public void start() {
/* 28 */     this.ui.start();
}
}



