package tool.draw_map.load;
import tool.utils.Util;
import java.awt.image.BufferedImage;
import java.util.Arrays;
public class MobTemplate {
private int id;
private int type;
private String name;
private BufferedImage[] images;
private int rangeMove;
private int speed;
public void setId(int id) {
this.id = id;
}
public void setType(int type) {
this.type = type;
}
public void setName(String name) {
this.name = name;
}
public void setImages(BufferedImage[] images) {
this.images = images;
}
public void setRangeMove(int rangeMove) {
this.rangeMove = rangeMove;
}
public void setSpeed(int speed) {
this.speed = speed;
}
public boolean equals(Object o) {
if (o == this)
return true;
if (!(o instanceof MobTemplate))
return false;
MobTemplate other = (MobTemplate)o;
if (!other.canEqual(this))
return false;
if (getId() != other.getId())
return false;
if (getType() != other.getType())
return false;
if (getRangeMove() != other.getRangeMove())
return false;
if (getSpeed() != other.getSpeed())
return false;
Object this$name = getName(), other$name = other.getName();
return ((this$name == null) ? (other$name != null) : !this$name.equals(other$name)) ? false : (!!Arrays.deepEquals((Object[])getImages(), (Object[])other.getImages()));
}
protected boolean canEqual(Object other) {
return other instanceof MobTemplate;
}
public int hashCode() {
int PRIME = 59;
int result = 1;
result = result * 59 + getId();
result = result * 59 + getType();
result = result * 59 + getRangeMove();
result = result * 59 + getSpeed();
Object $name = getName();
result = result * 59 + (($name == null) ? 43 : $name.hashCode());
return result * 59 + Arrays.deepHashCode((Object[])getImages());
}
public String toString() {
return "MobTemplate(id=" + getId() + ", type=" + getType() + ", name=" + getName() + ", images=" + Arrays.deepToString((Object[])getImages()) + ", rangeMove=" + getRangeMove() + ", speed=" + getSpeed() + ")";
}
public int getId() {
return this.id;
}
public int getType() {
return this.type;
}
public String getName() {
return this.name;
}
public int getRangeMove() {
return this.rangeMove;
}
public int getSpeed() {
return this.speed;
}
public MobTemplate(int id, int type, String name, int rangeMove, int speed) {
this.id = id;
this.type = type;
this.name = name;
this.rangeMove = rangeMove;
this.speed = speed;
}
public BufferedImage[] getImages() {
if (this.images == null)
try {
this.images = new BufferedImage[3];
this.images[0] = Util.getImageMobById(this.id, 1);
this.images[1] = Util.getImageMobById(this.id, 2);
this.images[2] = Util.getImageMobById(this.id, 3);
} catch (Exception exception) {}
return this.images;
}
}
