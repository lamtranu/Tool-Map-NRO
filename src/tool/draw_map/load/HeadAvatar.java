package tool.draw_map.load;
public class HeadAvatar {
private int head;
private int avatar;
public void setHead(int head) {
this.head = head;
}
public void setAvatar(int avatar) {
this.avatar = avatar;
}
public boolean equals(Object o) {
if (o == this)
return true;
if (!(o instanceof HeadAvatar))
return false;
HeadAvatar other = (HeadAvatar)o;
return !other.canEqual(this) ? false : ((getHead() != other.getHead()) ? false : (!(getAvatar() != other.getAvatar())));
}
protected boolean canEqual(Object other) {
return other instanceof HeadAvatar;
}
public int hashCode() {
int PRIME = 59;
int result = 1;
result = result * 59 + getHead();
return result * 59 + getAvatar();
}
public String toString() {
return "HeadAvatar(head=" + getHead() + ", avatar=" + getAvatar() + ")";
}
public HeadAvatar(int head, int avatar) {
this.head = head;
this.avatar = avatar;
}
public int getHead() {
return this.head;
}
public int getAvatar() {
return this.avatar;
}
}
