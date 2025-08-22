package tool.utils;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class NotifyUtil
{
public static final void showMessageDialog(JFrame parent, String message) {
try {
/* 19 */       JOptionPane.showMessageDialog(parent, message, "Twilight75 - Hệ thống thông báo", 0, new ImageIcon(Util.getImageById(8410, 2)));
/* 20 */     } catch (Exception exception) {}
}
public static final void showMessageDialog(JFrame parent, String message, Image icon) {
/* 25 */     JOptionPane.showMessageDialog(parent, message, "Twilight75 - Hệ thống thông báo", 0, new ImageIcon(icon));
}
public static final String showInputDialog(JFrame parent, String message) {
try {
/* 30 */       String input = null;
/* 31 */       input = String.valueOf(JOptionPane.showInputDialog(parent, message, "Twilight75 - Hệ thống thông báo", 0, new ImageIcon(Util.getImageById(8410, 2)), null, null));
/* 32 */       return input;
/* 33 */     } catch (Exception ex) {
/* 34 */       ex.printStackTrace();
/* 35 */       return "";
}
}
public static final String showInputDialog(JFrame parent, String message, String defaultText) {
/* 40 */     String input = null;
/* 41 */     input = String.valueOf(JOptionPane.showInputDialog(parent, message, "Twilight75 - Hệ thống thông báo", -1, null, null, defaultText));
/* 42 */     return input;
}
public static final int showConfirmDialog(JFrame parent, String message) {
try {
/* 47 */       return JOptionPane.showConfirmDialog(parent, message, "Twilight75 - Hệ thống thông báo", 0, 0, new ImageIcon(Util.getImageById(8410, 2)));
/* 48 */     } catch (Exception ex) {
/* 49 */       ex.printStackTrace();
/* 50 */       return -1;
}
}
}



