package tool.utils;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class DrawUtil
{
public static void drawImage(Graphics g, BufferedImage image, int x, int y) {
/* 15 */     Graphics2D g2 = (Graphics2D)g;
/* 16 */     g2.drawImage(image, x, y, null);
}
public static void drawImage(Graphics2D g2, BufferedImage image, int x, int y) {
/* 20 */     g2.drawImage(image, x, y, null);
}
public static void drawImageCenter(Graphics g, BufferedImage image, int x, int y) {
/* 24 */     Graphics2D g2 = (Graphics2D)g;
/* 25 */     g2.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, null);
}
public static void drawImageCenter(Graphics2D g2, BufferedImage image, int x, int y) {
/* 29 */     g2.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, null);
}
}



