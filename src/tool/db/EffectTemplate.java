package tool.db;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Arrays;
import tool.utils.Util;
public class EffectTemplate {
private int id;
private int[][] axisSubImage;
private int[][][] axisFrame;
private BufferedImage imageOri;
private BufferedImage[] frame;
public void setId(int id) {
this.id = id;
}
public void setAxisSubImage(int[][] axisSubImage) {
this.axisSubImage = axisSubImage;
}
public void setAxisFrame(int[][][] axisFrame) {
this.axisFrame = axisFrame;
}
public void setImageOri(BufferedImage imageOri) {
this.imageOri = imageOri;
}
public void setFrame(BufferedImage[] frame) {
this.frame = frame;
}
public boolean equals(Object o) {
if (o == this)
return true;
if (!(o instanceof EffectTemplate))
return false;
EffectTemplate other = (EffectTemplate)o;
if (!other.canEqual(this))
return false;
if (getId() != other.getId())
return false;
if (!Arrays.deepEquals((Object[])getAxisSubImage(), (Object[])other.getAxisSubImage()))
return false;
if (!Arrays.deepEquals((Object[])getAxisFrame(), (Object[])other.getAxisFrame()))
return false;
Object this$imageOri = getImageOri(), other$imageOri = other.getImageOri();
return ((this$imageOri == null) ? (other$imageOri != null) : !this$imageOri.equals(other$imageOri)) ? false : (!!Arrays.deepEquals((Object[])getFrame(), (Object[])other.getFrame()));
}
protected boolean canEqual(Object other) {
return other instanceof EffectTemplate;
}
public int hashCode() {
int PRIME = 59;
int result = 1;
result = result * 59 + getId();
result = result * 59 + Arrays.deepHashCode((Object[])getAxisSubImage());
result = result * 59 + Arrays.deepHashCode((Object[])getAxisFrame());
Object $imageOri = getImageOri();
result = result * 59 + (($imageOri == null) ? 43 : $imageOri.hashCode());
return result * 59 + Arrays.deepHashCode((Object[])getFrame());
}
public String toString() {
return "EffectTemplate(id=" + getId() + ", axisSubImage=" + Arrays.deepToString((Object[])getAxisSubImage()) + ", axisFrame=" + Arrays.deepToString((Object[])getAxisFrame()) + ", imageOri=" + getImageOri() + ", frame=" + Arrays.deepToString((Object[])getFrame()) + ")";
}
public int getId() {
return this.id;
}
public int[][] getAxisSubImage() {
return this.axisSubImage;
}
public int[][][] getAxisFrame() {
return this.axisFrame;
}
public BufferedImage getImageOri() {
return this.imageOri;
}
public BufferedImage[] getFrame() {
return this.frame;
}
public int getSizeFrame() {
return this.axisFrame.length;
}
public BufferedImage getFrame(int f) {
if (this.frame == null)
this.frame = new BufferedImage[this.axisFrame.length];
if (this.frame[f] == null) {
BufferedImage image = new BufferedImage(1000, 1000, 2);
Graphics2D g = image.createGraphics();
for (int i = 0; i < (this.axisFrame[f]).length; i++) {
try {
int imgInfo = this.axisFrame[f][i][2];
BufferedImage subImage = this.imageOri.getSubimage(this.axisSubImage[imgInfo][1], this.axisSubImage[imgInfo][2], this.axisSubImage[imgInfo][3], this.axisSubImage[imgInfo][4]);
g.drawImage(subImage, 500 + this.axisFrame[f][i][0], 500 + this.axisFrame[f][i][1], (ImageObserver)null);
} catch (Exception e) {
e.printStackTrace();
}
}
g.dispose();
this.frame[f] = Util.trimImage(image);
}
return this.frame[f];
}
}
