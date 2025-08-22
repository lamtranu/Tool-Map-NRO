package tool.draw_map.load;

import tool.utils.Util;
import java.awt.image.BufferedImage;

public class BgItemTemplate {

    private BufferedImage image;
    private int id;
    private int imageId;
    private int layer;
    private int dx;
    private int dy;

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BgItemTemplate)) {
            return false;
        }
        BgItemTemplate other = (BgItemTemplate) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (getId() != other.getId()) {
            return false;
        }
        if (getImageId() != other.getImageId()) {
            return false;
        }
        if (getLayer() != other.getLayer()) {
            return false;
        }
        if (getDx() != other.getDx()) {
            return false;
        }
        if (getDy() != other.getDy()) {
            return false;
        }
        Object this$image = getImage(), other$image = other.getImage();
        return !((this$image == null) ? (other$image != null) : !this$image.equals(other$image));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BgItemTemplate;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + getId();
        result = result * 59 + getImageId();
        result = result * 59 + getLayer();
        result = result * 59 + getDx();
        result = result * 59 + getDy();
        Object $image = getImage();
        return result * 59 + (($image == null) ? 43 : $image.hashCode());
    }

    public String toString() {
        return "BgItemTemplate(image=" + getImage() + ", id=" + getId() + ", imageId=" + getImageId() + ", layer=" + getLayer() + ", dx=" + getDx() + ", dy=" + getDy() + ")";
    }

    public int getId() {
        return this.id;
    }

    public int getImageId() {
        return this.imageId;
    }

    public int getLayer() {
        return this.layer;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public BufferedImage getImage() {
        if (this.image == null) {
            try {
                this.image = Util.getBgImageById(this.imageId, 1);
            } catch (Exception exception) {
            }
        }
        return this.image;
    }

    public BgItemTemplate(int id, int imageId, int layer, int dx, int dy) {
        this.id = id;
        this.imageId = imageId;
        this.layer = layer;
        this.dx = dx;
        this.dy = dy;
    }
}
