package tool.utils;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;
public class ImageUtil
{
public static void saveImage(BufferedImage image, String pathFolder, String name) {
try {
/* 18 */       File folder = new File(pathFolder);
/* 19 */       if (!folder.exists()) {
/* 20 */         folder.mkdirs();
}
/* 22 */       File outputfile = new File(pathFolder + "/" + name + ".png");
/* 23 */       ImageIO.write(image, "png", outputfile);
/* 24 */     } catch (Exception exception) {}
}
public static void saveImage(byte[] data, String pathFolder, String name) {
try {
/* 30 */       ByteArrayInputStream bis = new ByteArrayInputStream(data);
/* 31 */       BufferedImage image = ImageIO.read(bis);
/* 32 */       File folder = new File(pathFolder);
/* 33 */       if (!folder.exists()) {
/* 34 */         folder.mkdirs();
}
/* 36 */       File outputfile = new File(pathFolder + "/" + name + ".png");
/* 37 */       ImageIO.write(image, "png", outputfile);
/* 38 */     } catch (Exception exception) {}
}
public static BufferedImage trimImage(BufferedImage image) {
/* 43 */     WritableRaster raster = image.getAlphaRaster();
/* 44 */     int width = raster.getWidth();
/* 45 */     int height = raster.getHeight();
/* 46 */     int left = 0;
/* 47 */     int top = 0;
/* 48 */     int right = width - 1;
/* 49 */     int bottom = height - 1;
/* 50 */     int minRight = width - 1;
/* 51 */     int minBottom = height - 1;
/* 54 */     label53: for (; top <= bottom; top++) {
/* 55 */       for (int x = 0; x < width; x++) {
/* 56 */         if (raster.getSample(x, top, 0) != 0) {
/* 57 */           minRight = x;
/* 58 */           minBottom = top;
break label53;
}
}
}
/* 64 */     label54: for (; left < minRight; left++) {
/* 65 */       for (int y = height - 1; y > top; y--) {
/* 66 */         if (raster.getSample(left, y, 0) != 0) {
/* 67 */           minBottom = y;
break label54;
}
}
}
/* 74 */     label55: for (; bottom > minBottom; bottom--) {
/* 75 */       for (int x = width - 1; x >= left; x--) {
/* 76 */         if (raster.getSample(x, bottom, 0) != 0) {
/* 77 */           minRight = x;
break label55;
}
}
}
/* 84 */     label52: for (; right > minRight; right--) {
/* 85 */       for (int y = bottom; y >= top; y--) {
/* 86 */         if (raster.getSample(right, y, 0) != 0) {
break label52;
}
}
}
try {
/* 92 */       return image.getSubimage(left, top, right - left + 1, bottom - top + 1);
/* 93 */     } catch (Exception e) {
/* 94 */       return image;
}
}
}



