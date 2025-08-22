package tool.draw_map.template;
import tool.draw_map.load.MobTemplate;
import tool.utils.Util;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
public class MobMap {
private MobTemplate temp;
private int x;
private int y;
private int level;
private int hp;
private long lastTimeNextF;
private int f;
public void setTemp(MobTemplate temp) {
this.temp = temp;
}
public void setLevel(int level) {
this.level = level;
}
public void setHp(int hp) {
this.hp = hp;
}
public void setLastTimeNextF(long lastTimeNextF) {
this.lastTimeNextF = lastTimeNextF;
}
public void setF(int f) {
this.f = f;
}
public void setTimeNextF(int timeNextF) {
this.timeNextF = timeNextF;
}
public void setMinY(int minY) {
this.minY = minY;
}
public void setMaxY(int maxY) {
this.maxY = maxY;
}
public void setCY(int cY) {
this.cY = cY;
}
public void setDirY(int dirY) {
this.dirY = dirY;
}
public void setMinX(int minX) {
this.minX = minX;
}
public void setMaxX(int maxX) {
this.maxX = maxX;
}
public void setCX(int cX) {
this.cX = cX;
}
public void setDirX(int dirX) {
this.dirX = dirX;
}
public boolean equals(Object o) {
if (o == this)
return true;
if (!(o instanceof MobMap))
return false;
MobMap other = (MobMap)o;
if (!other.canEqual(this))
return false;
if (getX() != other.getX())
return false;
if (getY() != other.getY())
return false;
if (getLevel() != other.getLevel())
return false;
if (getHp() != other.getHp())
return false;
if (getLastTimeNextF() != other.getLastTimeNextF())
return false;
if (getF() != other.getF())
return false;
if (getTimeNextF() != other.getTimeNextF())
return false;
if (getMinY() != other.getMinY())
return false;
if (getMaxY() != other.getMaxY())
return false;
if (getCY() != other.getCY())
return false;
if (getDirY() != other.getDirY())
return false;
if (getMinX() != other.getMinX())
return false;
if (getMaxX() != other.getMaxX())
return false;
if (getCX() != other.getCX())
return false;
if (getDirX() != other.getDirX())
return false;
Object this$temp = getTemp(), other$temp = other.getTemp();
return !((this$temp == null) ? (other$temp != null) : !this$temp.equals(other$temp));
}
protected boolean canEqual(Object other) {
return other instanceof MobMap;
}
public int hashCode() {
int PRIME = 59;
int result = 1;
result = result * 59 + getX();
result = result * 59 + getY();
result = result * 59 + getLevel();
result = result * 59 + getHp();
long $lastTimeNextF = getLastTimeNextF();
result = result * 59 + (int)($lastTimeNextF >>> 32L ^ $lastTimeNextF);
result = result * 59 + getF();
result = result * 59 + getTimeNextF();
result = result * 59 + getMinY();
result = result * 59 + getMaxY();
result = result * 59 + getCY();
result = result * 59 + getDirY();
result = result * 59 + getMinX();
result = result * 59 + getMaxX();
result = result * 59 + getCX();
result = result * 59 + getDirX();
Object $temp = getTemp();
return result * 59 + (($temp == null) ? 43 : $temp.hashCode());
}
public String toString() {
return "MobMap(temp=" + getTemp() + ", x=" + getX() + ", y=" + getY() + ", level=" + getLevel() + ", hp=" + getHp() + ", lastTimeNextF=" + getLastTimeNextF() + ", f=" + getF() + ", timeNextF=" + getTimeNextF() + ", minY=" + getMinY() + ", maxY=" + getMaxY() + ", cY=" + getCY() + ", dirY=" + getDirY() + ", minX=" + getMinX() + ", maxX=" + getMaxX() + ", cX=" + getCX() + ", dirX=" + getDirX() + ")";
}
public MobTemplate getTemp() {
return this.temp;
}
public int getX() {
return this.x;
}
public int getY() {
return this.y;
}
public int getLevel() {
return this.level;
}
public int getHp() {
return this.hp;
}
public long getLastTimeNextF() {
return this.lastTimeNextF;
}
public int getF() {
return this.f;
}
private int timeNextF = 50;
private int minY;
private int maxY;
private int cY;
public int getTimeNextF() {
return this.timeNextF;
}
public int getMinY() {
return this.minY;
}
public int getMaxY() {
return this.maxY;
}
public int getCY() {
return this.cY;
}
private int dirY = 1;
private int minX;
private int maxX;
private int cX;
public int getDirY() {
return this.dirY;
}
public int getMinX() {
return this.minX;
}
public int getMaxX() {
return this.maxX;
}
public int getCX() {
return this.cX;
}
private int dirX = 1;
public int getDirX() {
return this.dirX;
}
public void setX(int x) {
this.x = x;
this.cX = x;
this.cY = this.y;
if (this.temp.getType() == 0) {
this.minX = this.cX;
this.maxX = this.cX;
this.minY = this.cY;
this.maxY = this.cY;
} else if (this.temp.getType() == 1) {
this.minX = this.cX - this.temp.getRangeMove() / 4 * 3;
this.maxX = this.cX + this.temp.getRangeMove() / 4 * 3;
this.minY = this.cY;
this.maxY = this.cY;
} else {
this.minX = this.cX - this.temp.getRangeMove() / 4 * 3;
this.maxX = this.cX + this.temp.getRangeMove() / 4 * 3;
this.minY = this.cY - this.temp.getRangeMove() / 4 * 2;
this.maxY = this.cY + this.temp.getRangeMove() / 4 * 2;
}
}
public void setY(int y) {
this.y = y;
this.cX = this.x;
this.cY = y;
if (this.temp.getType() == 0) {
this.minX = this.cX;
this.maxX = this.cX;
this.minY = this.cY;
this.maxY = this.cY;
} else if (this.temp.getType() == 1) {
this.minX = this.cX - this.temp.getRangeMove() / 4 * 3;
this.maxX = this.cX + this.temp.getRangeMove() / 4 * 3;
this.minY = this.cY;
this.maxY = this.cY;
} else {
this.minX = this.cX - this.temp.getRangeMove() / 4 * 3;
this.maxX = this.cX + this.temp.getRangeMove() / 4 * 3;
this.minY = this.cY - this.temp.getRangeMove() / 4 * 2;
this.maxY = this.cY + this.temp.getRangeMove() / 4 * 2;
}
}
public MobMap(MobTemplate temp, int x, int y, int level, int hp) {
this.temp = temp;
this.x = x;
this.y = y;
this.level = level;
this.hp = hp;
this.cX = x;
this.cY = y;
if (this.temp.getType() == 0) {
this.minX = this.cX;
this.maxX = this.cX;
this.minY = this.cY;
this.maxY = this.cY;
} else if (this.temp.getType() == 1) {
this.minX = this.cX - this.temp.getRangeMove() / 4 * 3;
this.maxX = this.cX + this.temp.getRangeMove() / 4 * 3;
this.minY = this.cY;
this.maxY = this.cY;
} else {
this.minX = this.cX - this.temp.getRangeMove() / 4 * 3;
this.maxX = this.cX + this.temp.getRangeMove() / 4 * 3;
this.minY = this.cY - this.temp.getRangeMove() / 4 * 2;
this.maxY = this.cY + this.temp.getRangeMove() / 4 * 2;
}
}
public void paint(Graphics2D g, boolean changeColor) {
if (Util.canDoLastTime(this.lastTimeNextF, this.timeNextF)) {
this.lastTimeNextF = System.currentTimeMillis();
this.f++;
if (this.f > 2)
this.f = 0;
if (this.temp.getType() != 0)
this.cX += this.dirX * this.temp.getSpeed();
if (this.temp.getType() == 4)
this.cY += this.dirY * this.temp.getSpeed();
if (this.cX < this.minX || this.cX > this.maxX)
this.dirX *= -1;
if (this.cY < this.minY || this.cY > this.maxY)
this.dirY *= -1;
}
try {
if (this.dirX > 0) {
g.drawImage(changeColor ? Util.changeRed(this.temp.getImages()[this.f]) : this.temp.getImages()[this.f], this.cX - this.temp
.getImages()[this.f].getWidth() / 2, this.cY - this.temp.getImages()[this.f].getHeight() / 4 * 3, (ImageObserver)null);
} else {
int w = this.temp.getImages()[this.f].getWidth();
int h = this.temp.getImages()[this.f].getHeight();
int aX = this.cX - w / 2;
int aY = this.cY - h / 4 * 3;
g.drawImage(changeColor ? Util.changeRed(this.temp.getImages()[this.f]) : this.temp.getImages()[this.f], aX + w, aY, -w, h, null);
}
} catch (Exception exception) {}
}
}
