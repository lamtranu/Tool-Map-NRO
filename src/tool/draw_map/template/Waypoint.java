package tool.draw_map.template;
public class Waypoint {
private String name;
private int x;
private int y;
private int w;
private int h;
private boolean enter;
private boolean offline;
private int mapGo;
private int goX;
private int goY;
public void setName(String name) {
this.name = name;
}
public void setX(int x) {
this.x = x;
}
public void setY(int y) {
this.y = y;
}
public void setW(int w) {
this.w = w;
}
public void setH(int h) {
this.h = h;
}
public void setEnter(boolean enter) {
this.enter = enter;
}
public void setOffline(boolean offline) {
this.offline = offline;
}
public void setMapGo(int mapGo) {
this.mapGo = mapGo;
}
public void setGoX(int goX) {
this.goX = goX;
}
public void setGoY(int goY) {
this.goY = goY;
}
public boolean equals(Object o) {
if (o == this)
return true;
if (!(o instanceof Waypoint))
return false;
Waypoint other = (Waypoint)o;
if (!other.canEqual(this))
return false;
if (getX() != other.getX())
return false;
if (getY() != other.getY())
return false;
if (getW() != other.getW())
return false;
if (getH() != other.getH())
return false;
if (isEnter() != other.isEnter())
return false;
if (isOffline() != other.isOffline())
return false;
if (getMapGo() != other.getMapGo())
return false;
if (getGoX() != other.getGoX())
return false;
if (getGoY() != other.getGoY())
return false;
Object this$name = getName(), other$name = other.getName();
return !((this$name == null) ? (other$name != null) : !this$name.equals(other$name));
}
protected boolean canEqual(Object other) {
return other instanceof Waypoint;
}
public int hashCode() {
int PRIME = 59;
int result = 1;
result = result * 59 + getX();
result = result * 59 + getY();
result = result * 59 + getW();
result = result * 59 + getH();
result = result * 59 + (isEnter() ? 79 : 97);
result = result * 59 + (isOffline() ? 79 : 97);
result = result * 59 + getMapGo();
result = result * 59 + getGoX();
result = result * 59 + getGoY();
Object $name = getName();
return result * 59 + (($name == null) ? 43 : $name.hashCode());
}
public String toString() {
return "Waypoint(name=" + getName() + ", x=" + getX() + ", y=" + getY() + ", w=" + getW() + ", h=" + getH() + ", enter=" + isEnter() + ", offline=" + isOffline() + ", mapGo=" + getMapGo() + ", goX=" + getGoX() + ", goY=" + getGoY() + ")";
}
public Waypoint(String name, int x, int y, int w, int h, boolean enter, boolean offline, int mapGo, int goX, int goY) {
this.name = name;
this.x = x;
this.y = y;
this.w = w;
this.h = h;
this.enter = enter;
this.offline = offline;
this.mapGo = mapGo;
this.goX = goX;
this.goY = goY;
}
public String getName() {
return this.name;
}
public int getX() {
return this.x;
}
public int getY() {
return this.y;
}
public int getW() {
return this.w;
}
public int getH() {
return this.h;
}
public boolean isEnter() {
return this.enter;
}
public boolean isOffline() {
return this.offline;
}
public int getMapGo() {
return this.mapGo;
}
public int getGoX() {
return this.goX;
}
public int getGoY() {
return this.goY;
}
}
