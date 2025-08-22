package tool.part;
import java.awt.image.BufferedImage;
import tool.utils.Util;
public class PartInfo {
private BufferedImage imageIcon;
private int iconId;
private int dx;
private int dy;
public void setImageIcon(BufferedImage imageIcon) {
this.imageIcon = imageIcon;
}
public void setDx(int dx) {
this.dx = dx;
}
public void setDy(int dy) {
this.dy = dy;
}
public boolean equals(Object o) {
if (o == this)
return true;
if (!(o instanceof PartInfo))
return false;
PartInfo other = (PartInfo)o;
if (!other.canEqual(this))
return false;
if (getIconId() != other.getIconId())
return false;
if (getDx() != other.getDx())
return false;
if (getDy() != other.getDy())
return false;
Object this$imageIcon = getImageIcon(), other$imageIcon = other.getImageIcon();
return !((this$imageIcon == null) ? (other$imageIcon != null) : !this$imageIcon.equals(other$imageIcon));
}
protected boolean canEqual(Object other) {
return other instanceof PartInfo;
}
public int hashCode() {
int PRIME = 59;
int result = 1;
result = result * 59 + getIconId();
result = result * 59 + getDx();
result = result * 59 + getDy();
Object $imageIcon = getImageIcon();
return result * 59 + (($imageIcon == null) ? 43 : $imageIcon.hashCode());
}
public String toString() {
return "PartInfo(imageIcon=" + getImageIcon() + ", iconId=" + getIconId() + ", dx=" + getDx() + ", dy=" + getDy() + ")";
}
public BufferedImage getImageIcon() {
return this.imageIcon;
}
public int getIconId() {
return this.iconId;
}
public int getDx() {
return this.dx;
}
public int getDy() {
return this.dy;
}
public PartInfo(int iconId, int dx, int dy) {
try {
this.imageIcon = Util.getImageById(iconId, 2);
} catch (Exception exception) {}
this.iconId = iconId;
this.dx = dx;
this.dy = dy;
}
public void setIconId(int iconId) {
try {
this.iconId = iconId;
this.imageIcon = Util.getImageById(iconId, 2);
} catch (Exception exception) {}
}
}
