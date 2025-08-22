package tool.draw_map.layer;
import tool.db.EffectTemplate;
import tool.draw_map.DrawMapScr;
import tool.draw_map.template.EffectMap;
import tool.draw_map.template.SubEffectMap;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.List;
public class EffectMapLayer
implements Layer
{
private BufferedImage[] image;
private int indexImage;
private DrawMapScr drawMapScr;
private List<EffectMap> effects;
private List<SubEffectMap> subEffectMaps;
private long lastTimeNextF;
private int timeNextF;
private int f;
private boolean show;
public void clear() {
/*  28 */     this.effects.clear();
/*  29 */     this.subEffectMaps.clear();
}
public void setSizeImage(int w, int h, int offset, int dir) {
/*  34 */     this.image = new BufferedImage[5];
/*  35 */     this.image[0] = new BufferedImage(w * 24, h * 24, 2);
/*  36 */     this.image[1] = new BufferedImage(w * 24, h * 24, 2);
/*  37 */     this.image[2] = new BufferedImage(w * 24, h * 24, 2);
/*  38 */     this.image[3] = new BufferedImage(w * 24, h * 24, 2);
/*  39 */     this.image[4] = new BufferedImage(w * 24, h * 24, 2);
/*  40 */     switch (dir) {
case 0:
/*  42 */         for (EffectMap eff : this.effects) {
/*  43 */           eff.setY(eff.getY() + offset * 24);
}
break;
case 1:
/*  47 */         for (EffectMap eff : this.effects) {
/*  48 */           eff.setX(eff.getX() + offset * 24);
}
break;
}
}
public void put(EffectTemplate temp, int x, int y) {
int layer = this.drawMapScr.layerEff + 1;
if (x < 0 || y < 0 || x > this.image[layer].getWidth() || y > this.image[layer].getHeight()) {
return;
}
if (this.drawMapScr.layerEff < 1) {
y -= 240;
}
this.effects.add(new EffectMap(temp, x, y, this.drawMapScr.layerEff, -1, 1));
}
public void drawWpChose(EffectTemplate temp, int x, int y) {
if (temp == null)
return;
if (System.currentTimeMillis() - this.lastTimeNextF >= this.timeNextF) {
this.f++;
this.lastTimeNextF = System.currentTimeMillis();
}
if (this.f > temp.getSizeFrame() - 1)
this.f = 0;
Graphics2D g = this.image[this.drawMapScr.layerEff + 1].createGraphics();
g.drawImage(temp.getFrame(this.f), x - temp.getFrame(this.f).getWidth() / 2, y - temp.getFrame(this.f).getHeight(), (ImageObserver)null);
g.dispose();
}
public EffectMapLayer(DrawMapScr drawMapScr, List<EffectMap> effects, List<SubEffectMap> subEffectMaps, int w, int h) {
/*  80 */     this.timeNextF = 50;
/* 164 */     this.show = true; this.subEffectMaps = subEffectMaps; this.effects = effects; this.drawMapScr = drawMapScr; this.image = new BufferedImage[5]; this.image[0] = new BufferedImage(w * 24, h * 24, 2);
this.image[1] = new BufferedImage(w * 24, h * 24, 2);
this.image[2] = new BufferedImage(w * 24, h * 24, 2);
this.image[3] = new BufferedImage(w * 24, h * 24, 2);
/* 168 */     this.image[4] = new BufferedImage(w * 24, h * 24, 2); } public void setShow(boolean show) { this.show = show; }
public boolean isShow() {
/* 173 */     return this.show;
}
public void draw() {
try {
clearImage();
if (!isShow())
return;
for (int i = 0; i < 5; i++) {
Graphics2D graphics2D = this.image[i].createGraphics();
for (EffectMap eff : this.effects) {
if (eff.getLayer() + 1 == i)
eff.paint(graphics2D, eff.equals(this.drawMapScr.effChose));
}
graphics2D.dispose();
}
Graphics2D g = this.image[4].createGraphics();
for (SubEffectMap eff : this.subEffectMaps) {
if (eff.getLayer() + 1 == 4)
eff.paint(g, eff.equals(this.drawMapScr.effChose));
}
g.dispose();
} catch (Exception exception) {}
}
public BufferedImage getBufferedImage() {
BufferedImage image = this.image[this.indexImage];
this.indexImage++;
if (this.indexImage > 4)
this.indexImage = 0;
return image;
}
public void clearImage() {
for (int i = 0; i < 5; i++) {
Graphics2D g = this.image[i].createGraphics();
g.setComposite(AlphaComposite.Clear);
int x = -this.drawMapScr.camera.camX;
if (x < 0)
x = 0;
int y = -this.drawMapScr.camera.camY;
if (y < 0)
y = 0;
int w = this.drawMapScr.camera.width;
if (w + x > this.image[i].getWidth())
w = this.image[i].getWidth() - x;
int h = this.drawMapScr.camera.height;
if (h + y > this.image[i].getHeight())
h = this.image[i].getHeight() - y;
g.fillRect(x, y, w, h);
g.dispose();
}
}
}




