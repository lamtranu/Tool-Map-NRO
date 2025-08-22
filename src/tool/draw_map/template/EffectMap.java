package tool.draw_map.template;
import tool.db.EffectTemplate;
import tool.utils.Util;
import java.awt.Graphics2D;
public class EffectMap {
private EffectTemplate temp;
private int x;
private int y;
private int layer;
private int loop;
private int loopCount;
private int type;
private int indexFrom;
private int indexTo;
private long lastTimeNextFrame;
private int timeNextFrame;
private int f;
private int iLoopCount;
public void setTemp(EffectTemplate temp) {
this.temp = temp;
}
public void setX(int x) {
this.x = x;
}
public void setY(int y) {
this.y = y;
}
public void setLayer(int layer) {
this.layer = layer;
}
public void setLoop(int loop) {
this.loop = loop;
}
public void setLoopCount(int loopCount) {
this.loopCount = loopCount;
}
public void setType(int type) {
this.type = type;
}
public void setIndexFrom(int indexFrom) {
this.indexFrom = indexFrom;
}
public void setIndexTo(int indexTo) {
this.indexTo = indexTo;
}
public void setLastTimeNextFrame(long lastTimeNextFrame) {
this.lastTimeNextFrame = lastTimeNextFrame;
}
public void setTimeNextFrame(int timeNextFrame) {
this.timeNextFrame = timeNextFrame;
}
public void setF(int f) {
this.f = f;
}
public void setILoopCount(int iLoopCount) {
this.iLoopCount = iLoopCount;
}
public boolean equals(Object o) {
if (o == this)
return true;
if (!(o instanceof EffectMap))
return false;
EffectMap other = (EffectMap)o;
if (!other.canEqual(this))
return false;
if (getX() != other.getX())
return false;
if (getY() != other.getY())
return false;
if (getLayer() != other.getLayer())
return false;
if (getLoop() != other.getLoop())
return false;
if (getLoopCount() != other.getLoopCount())
return false;
if (getType() != other.getType())
return false;
if (getIndexFrom() != other.getIndexFrom())
return false;
if (getIndexTo() != other.getIndexTo())
return false;
if (getLastTimeNextFrame() != other.getLastTimeNextFrame())
return false;
if (getTimeNextFrame() != other.getTimeNextFrame())
return false;
if (getF() != other.getF())
return false;
if (getILoopCount() != other.getILoopCount())
return false;
Object this$temp = getTemp(), other$temp = other.getTemp();
return !((this$temp == null) ? (other$temp != null) : !this$temp.equals(other$temp));
}
protected boolean canEqual(Object other) {
return other instanceof EffectMap;
}
public int hashCode() {
int PRIME = 59;
int result = 1;
result = result * 59 + getX();
result = result * 59 + getY();
result = result * 59 + getLayer();
result = result * 59 + getLoop();
result = result * 59 + getLoopCount();
result = result * 59 + getType();
result = result * 59 + getIndexFrom();
result = result * 59 + getIndexTo();
long $lastTimeNextFrame = getLastTimeNextFrame();
result = result * 59 + (int)($lastTimeNextFrame >>> 32L ^ $lastTimeNextFrame);
result = result * 59 + getTimeNextFrame();
result = result * 59 + getF();
result = result * 59 + getILoopCount();
Object $temp = getTemp();
return result * 59 + (($temp == null) ? 43 : $temp.hashCode());
}
public String toString() {
return "EffectMap(temp=" + getTemp() + ", x=" + getX() + ", y=" + getY() + ", layer=" + getLayer() + ", loop=" + getLoop() + ", loopCount=" + getLoopCount() + ", type=" + getType() + ", indexFrom=" + getIndexFrom() + ", indexTo=" + getIndexTo() + ", lastTimeNextFrame=" + getLastTimeNextFrame() + ", timeNextFrame=" + getTimeNextFrame() + ", f=" + getF() + ", iLoopCount=" + getILoopCount() + ")";
}
public EffectMap(EffectTemplate temp, int x, int y, int layer, int loop, int loopCount) {
this.timeNextFrame = 50;
this.temp = temp;
this.x = x;
this.y = y;
this.layer = layer;
this.loop = loop;
this.loopCount = loopCount;
}
public EffectTemplate getTemp() {
return this.temp;
}
public int getX() {
return this.x;
}
public int getY() {
return this.y;
}
public int getLayer() {
return this.layer;
}
public int getLoop() {
return this.loop;
}
public int getLoopCount() {
return this.loopCount;
}
public int getType() {
return this.type;
}
public int getIndexFrom() {
return this.indexFrom;
}
public int getIndexTo() {
return this.indexTo;
}
public long getLastTimeNextFrame() {
return this.lastTimeNextFrame;
}
public int getTimeNextFrame() {
return this.timeNextFrame;
}
public int getF() {
return this.f;
}
public int getILoopCount() {
return this.iLoopCount;
}
public void paint(Graphics2D g, boolean changeColor) {
try {
if (System.currentTimeMillis() - this.lastTimeNextFrame > this.timeNextFrame) {
this.f++;
if (this.f >= this.temp.getSizeFrame())
if (this.loopCount == -1) {
this.f = 0;
} else {
this.iLoopCount++;
if (this.iLoopCount >= this.loopCount) {
this.iLoopCount = 0;
this.f = 0;
} else {
this.f = this.temp.getSizeFrame() - 1;
}
}
this.lastTimeNextFrame = System.currentTimeMillis();
}
int cy = this.y - this.temp.getFrame(this.f).getHeight();
if (this.layer < 1)
cy += 240;
g.drawImage(changeColor ? Util.changeRed(this.temp.getFrame()[this.f]) : this.temp.getFrame(this.f), this.x - this.temp
.getFrame(this.f).getWidth() / 2, cy, null);
} catch (Exception e) {
e.printStackTrace();
}
}
}
