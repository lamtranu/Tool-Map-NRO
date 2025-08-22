package tool.utils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import tool.db.DatabaseManagers;
import tool.db.DatabaseResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
public class Util {
public static Random rd = new Random();
public static int range(int from, int to) {
return from + rd.nextInt(to - from + 1);
}
public static BufferedImage flipImageX(BufferedImage image) {
BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), 2);
Graphics2D g = newImage.createGraphics();
g.drawImage(image, image.getWidth(), 0, -image.getWidth(), image.getHeight(), null);
return newImage;
}
public static BufferedImage changeRed(BufferedImage img) {
BufferedImage i = new BufferedImage(img.getWidth(), img.getHeight(), 2);
Graphics2D gi = (Graphics2D)i.getGraphics();
gi.drawImage(img, 0, 0, (ImageObserver)null);
WritableRaster raster = i.getRaster();
for (int xx = 0; xx < i.getWidth(); xx++) {
for (int yy = 0; yy < i.getHeight(); yy++) {
int[] pixels = raster.getPixel(xx, yy, (int[])null);
pixels[0] = 255;
pixels[1] = 0;
pixels[2] = 0;
raster.setPixel(xx, yy, pixels);
}
}
return i;
}
public static BufferedImage resizeImage(BufferedImage ori, int w, int h) {
BufferedImage tThumbImage = new BufferedImage(w, h, 1);
Graphics2D tGraphics2D = tThumbImage.createGraphics();
tGraphics2D.setBackground(Color.WHITE);
tGraphics2D.setPaint(Color.WHITE);
tGraphics2D.fillRect(0, 0, w, h);
tGraphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
tGraphics2D.drawImage(ori, 0, 0, w, h, null);
return tThumbImage;
}
public static boolean canDoLastTime(long lastTime, int timeDo) {
return (System.currentTimeMillis() - lastTime >= timeDo);
}
public static BufferedImage getImageById(int id, int zoomSize) throws Exception {
BufferedImage image = ImageIO.read(new File("data/icon/x" + zoomSize + "/" + id + ".png"));
return image;
}
public static BufferedImage getBgImageById(int id, int zoomSize) throws Exception {
BufferedImage image = ImageIO.read(new File("data/item_bg_temp/x" + zoomSize + "/" + id + ".png"));
return image;
}
public static BufferedImage getImageMobById(int id, int f) throws Exception {
BufferedImage image = ImageIO.read(new File("data/mob/" + id + "/" + id + "_" + f + ".png"));
return image;
}
public static BufferedImage getImageEffectById(int id) throws Exception {
BufferedImage image = ImageIO.read(new File("data/effect/" + id + "/" + Character.MIN_VALUE + ".png"));
return image;
}
public static BufferedImage getImageByPath(String path) throws Exception {
BufferedImage image = ImageIO.read(new File(path));
return image;
}
public static BufferedImage getImageBEffectByType(int typeEff) throws Exception {
BufferedImage img = null;
if (typeEff == 1) {
img = getImageByPath("data/bg/lacay.png");
} else if (typeEff == 2) {
img = getImageByPath("data/bg/lacay2.png");
} else if (typeEff == 5) {
img = getImageByPath("data/bg/lacay3.png");
} else if (typeEff == 6) {
img = getImageByPath("data/bg/lacay4.png");
} else if (typeEff == 7) {
img = getImageByPath("data/bg/lacay5.png");
} else if (typeEff == 11) {
img = getImageByPath("data/bg/tuyet.png");
} else if (typeEff == 4) {
img = getImageByPath("data/bg/sao.png");
} else if (typeEff == 12) {
img = getImageByPath("data/bg/mua1.png");
}
return img;
}
public static void main(String[] args) {
try {
DatabaseResultSet rs = DatabaseManagers.executeQuery("select npcs from map_template");
JSONValue jv = new JSONValue();
while (rs.next()) {
JSONArray dataArray = (JSONArray)JSONValue.parse(rs.getString("npcs").replaceAll("\\\"", ""));
if (dataArray.size() != 0)
for (int i = 0; i < dataArray.size(); i++) {
JSONArray npc = (JSONArray)JSONValue.parse(String.valueOf(dataArray.get(i)));
int id = Integer.parseInt(String.valueOf(npc.get(0)));
int avatar = Integer.parseInt(String.valueOf(npc.get(3)));
DatabaseManagers.executeUpdate("update npc_template set avatar = ? where id = ?", new Object[] { Integer.valueOf(avatar), Integer.valueOf(id) });
}
}
} catch (Exception exception) {}
}
public static BufferedImage trimImage(BufferedImage image) {
WritableRaster raster = image.getAlphaRaster();
int width = raster.getWidth();
int height = raster.getHeight();
int left = 0;
int top = 0;
int right = width - 1;
int bottom = height - 1;
int minRight = width - 1;
int minBottom = height - 1;
label53: for (; top <= bottom; top++) {
for (int x = 0; x < width; x++) {
if (raster.getSample(x, top, 0) != 0) {
minRight = x;
minBottom = top;
break label53;
}
}
}
label54: for (; left < minRight; left++) {
for (int y = height - 1; y > top; y--) {
if (raster.getSample(left, y, 0) != 0) {
minBottom = y;
break label54;
}
}
}
label55: for (; bottom > minBottom; bottom--) {
for (int x = width - 1; x >= left; x--) {
if (raster.getSample(x, bottom, 0) != 0) {
minRight = x;
break label55;
}
}
}
label52: for (; right > minRight; right--) {
for (int y = bottom; y >= top; y--) {
if (raster.getSample(right, y, 0) != 0)
break label52;
}
}
try {
return image.getSubimage(left, top, right - left + 1, bottom - top + 1);
} catch (Exception e) {
return image;
}
}
}
