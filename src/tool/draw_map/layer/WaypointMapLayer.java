package tool.draw_map.layer;
import tool.draw_map.DrawMapScr;
import tool.draw_map.template.Waypoint;
import tool.utils.Util;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
public class WaypointMapLayer
implements Layer
{
private BufferedImage image;
private DrawMapScr drawMapScr;
private List<Waypoint> waypoints;
private long lastTimeChangeColor;
private int timeChangeColor;
private boolean changeColor;
private boolean show;
public void clear() {
/*  29 */     this.waypoints.clear();
}
public void setSizeImage(int w, int h, int offset, int dir) {
/*  34 */     this.image = new BufferedImage(w * 24, h * 24, 2);
/*  35 */     switch (dir) {
case 0:
/*  37 */         for (Waypoint wp : this.waypoints) {
/*  38 */           wp.setY(wp.getY() + offset * 24);
}
break;
case 1:
/*  42 */         for (Waypoint wp : this.waypoints) {
/*  43 */           wp.setX(wp.getX() + offset * 24);
}
break;
}
}
public void put(int x, int y) {
x = x / 24 * 24;
y = y / 24 * 24;
if (x < 0 || y < 0 || x >= this.image.getWidth() || y >= this.image.getHeight()) {
return;
}
for (Waypoint wp : this.waypoints) {
if (wp.getX() == x && wp.getY() == y) {
return;
}
if (wp.getX() == x) {
if (wp.getY() + wp.getH() == y) {
wp.setH(wp.getH() + 24);
return;
}
if (wp.getY() - 24 == y) {
wp.setY(y);
wp.setH(wp.getH() + 24);
return;
}
continue;
}
if (wp.getY() == y) {
if (wp.getX() + wp.getW() == x) {
wp.setW(wp.getW() + 24);
return;
}
if (wp.getX() - 24 == x) {
wp.setX(x);
wp.setW(wp.getW() + 24);
return;
}
}
}
this.waypoints.add(new Waypoint("null", x, y, 24, 24, false, false, 1, 1, 1));
}
public WaypointMapLayer(DrawMapScr drawMapScr, List<Waypoint> waypoints, int w, int h) {
/* 117 */     this.timeChangeColor = 300;
/* 200 */     this.show = true;
this.waypoints = waypoints;
this.drawMapScr = drawMapScr;
this.image = new BufferedImage(w * 24, h * 24, 2);
/* 204 */   } public void setShow(boolean show) { this.show = show; }
public boolean isShow() {
/* 209 */     return this.show;
}
public void drawWpChose(int x, int y) {
Graphics2D g = this.image.createGraphics();
x = x / 24 * 24;
y = y / 24 * 24;
try {
g.setColor(Color.red);
g.drawRect(x, y, 24, 24);
} catch (Exception ex) {
ex.printStackTrace();
}
g.dispose();
}
public void draw() {
clearImage();
if (!isShow())
return;
if (Util.canDoLastTime(this.lastTimeChangeColor, this.timeChangeColor)) {
this.lastTimeChangeColor = System.currentTimeMillis();
this.changeColor = !this.changeColor;
}
Graphics2D g = this.image.createGraphics();
g.setStroke(new BasicStroke(2.0F));
for (Waypoint wp : this.waypoints) {
if (wp.equals(this.drawMapScr.wpChose)) {
if (this.changeColor) {
g.setColor(Color.red);
} else {
g.setColor(Color.WHITE);
}
g.drawRect(wp.getX(), wp.getY(), wp.getW(), wp.getH());
g.drawLine(wp.getX(), wp.getY(), wp.getX() + wp.getW(), wp.getY() + wp.getH());
g.drawLine(wp.getX(), wp.getY() + wp.getH(), wp.getX() + wp.getW(), wp.getY());
} else {
if (this.changeColor) {
g.setColor(Color.blue);
} else {
g.setColor(Color.WHITE);
}
g.drawRect(wp.getX(), wp.getY(), wp.getW(), wp.getH());
g.drawLine(wp.getX(), wp.getY() + wp.getH() / 4, wp.getX() + wp.getW(), wp.getY() + wp.getH() / 4);
g.drawLine(wp.getX(), wp.getY() + wp.getH() / 2, wp.getX() + wp.getW(), wp.getY() + wp.getH() / 2);
g.drawLine(wp.getX(), wp.getY() + wp.getH() / 4 * 3, wp.getX() + wp.getW(), wp.getY() + wp.getH() / 4 * 3);
}
if (this.changeColor) {
g.setColor(Color.blue);
} else {
g.setColor(Color.WHITE);
}
String mapGo = wp.getMapGo() + "";
String go = wp.getGoX() + "-" + wp.getGoY();
g.drawString(mapGo + "(" + go + ")", (wp.getX() == this.drawMapScr.mapWidth - 24) ? (wp.getX() - (mapGo + "(" + go + ")").length() / 4 * 24) : wp.getX(), (wp.getY() == 0) ? (wp.getY() + wp.getH() + 10) : (wp.getY() - 5));
g.drawString(wp.getName(), (wp.getX() == this.drawMapScr.mapWidth - 24) ? (wp.getX() - wp.getName().length() / 4 * 24) : wp.getX(), (wp.getY() == 0) ? (wp.getY() + wp.getH() + 25) : (wp.getY() + wp.getH() + 15));
}
g.dispose();
}
public BufferedImage getBufferedImage() {
return this.image;
}
public void clearImage() {
Graphics2D g = this.image.createGraphics();
g.setComposite(AlphaComposite.Clear);
int x = -this.drawMapScr.camera.camX;
if (x < 0)
x = 0;
int y = -this.drawMapScr.camera.camY;
if (y < 0)
y = 0;
int w = this.drawMapScr.camera.width;
if (w + x > this.image.getWidth())
w = this.image.getWidth() - x;
int h = this.drawMapScr.camera.height;
if (h + y > this.image.getHeight())
h = this.image.getHeight() - y;
g.fillRect(x, y, w, h);
g.dispose();
}
}



