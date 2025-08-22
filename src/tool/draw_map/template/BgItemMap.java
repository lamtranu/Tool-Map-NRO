package tool.draw_map.template;
import tool.draw_map.load.BgItemTemplate;
import tool.draw_map.DrawMapScr;
import tool.utils.Util;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class BgItemMap {
private BgItemTemplate temp;
private int x;
private int y;
public void setTemp(BgItemTemplate temp) {
this.temp = temp;
}
public void setX(int x) {
this.x = x;
}
public void setY(int y) {
this.y = y;
}
public boolean equals(Object o) {
if (o == this)
return true;
if (!(o instanceof BgItemMap))
return false;
BgItemMap other = (BgItemMap)o;
if (!other.canEqual(this))
return false;
if (getX() != other.getX())
return false;
if (getY() != other.getY())
return false;
Object this$temp = getTemp(), other$temp = other.getTemp();
return !((this$temp == null) ? (other$temp != null) : !this$temp.equals(other$temp));
}
protected boolean canEqual(Object other) {
return other instanceof BgItemMap;
}
public int hashCode() {
int PRIME = 59;
int result = 1;
result = result * 59 + getX();
result = result * 59 + getY();
Object $temp = getTemp();
return result * 59 + (($temp == null) ? 43 : $temp.hashCode());
}
public String toString() {
return "BgItemMap(temp=" + getTemp() + ", x=" + getX() + ", y=" + getY() + ")";
}
public BgItemTemplate getTemp() {
return this.temp;
}
public int getX() {
return this.x;
}
public int getY() {
return this.y;
}
public BgItemMap(BgItemTemplate temp, int x, int y) {
this.temp = temp;
this.x = x;
this.y = y;
}
public void draw(Graphics2D g, DrawMapScr drawMapScr) {
if (this.temp != null)
if (this.temp.getLayer() == 4 && drawMapScr.is3D) {
g.drawImage(this.temp.getImage(), this.x + this.temp.getDx() - drawMapScr.camera.camX / 10, this.y + this.temp.getDy(), null);
} else {
g.drawImage(this.temp.getImage(), this.x + this.temp.getDx(), this.y + this.temp.getDy(), null);
if (this.temp.getLayer() == 1 && drawMapScr.isMapDouble) {
BufferedImage image = this.temp.getImage();
g.drawImage(Util.flipImageX(image), drawMapScr.mapWidth - this.x + this.temp.getDx() - image.getWidth(), this.y + this.temp.getDy(), null);
}
}
}
}
