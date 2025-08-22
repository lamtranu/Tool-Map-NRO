package tool.draw_map.load;

import tool.part.Part;
import tool.utils.Util;
import java.awt.image.BufferedImage;

public class NpcTemplate {

    private int id;
    private String name;
    private int head;
    private int body;
    private int leg;
    private int avatar;

    private Part pHead;
    private Part pBody;
    private Part pLeg;

    private BufferedImage iHead;
    private BufferedImage iBody;
    private BufferedImage iLeg;

    private boolean eH;
    private boolean eB;
    private boolean eL;

    // ===== Constructor =====
    public NpcTemplate() {}

    public NpcTemplate(int id, String name, int head, int body, int leg, int avatar) {
        this.id = id;
        this.name = name;
        this.head = head;
        this.body = body;
        this.leg = leg;
        this.avatar = avatar;
    }

    // ===== Setters =====
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setHead(int head) { this.head = head; }
    public void setBody(int body) { this.body = body; }
    public void setLeg(int leg) { this.leg = leg; }
    public void setAvatar(int avatar) { this.avatar = avatar; }

    public void setPHead(Part pHead) { this.pHead = pHead; }
    public void setPBody(Part pBody) { this.pBody = pBody; }
    public void setPLeg(Part pLeg) { this.pLeg = pLeg; }

    public void setIHead(BufferedImage iHead) { this.iHead = iHead; }
    public void setIBody(BufferedImage iBody) { this.iBody = iBody; }
    public void setILeg(BufferedImage iLeg) { this.iLeg = iLeg; }

    public void setEH(boolean eH) { this.eH = eH; }
    public void setEB(boolean eB) { this.eB = eB; }
    public void setEL(boolean eL) { this.eL = eL; }

    // ===== Getters =====
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public int getHead() { return this.head; }
    public int getBody() { return this.body; }
    public int getLeg() { return this.leg; }
    public int getAvatar() { return this.avatar; }

    public Part getPHead() { return this.pHead; }
    public Part getPBody() { return this.pBody; }
    public Part getPLeg() { return this.pLeg; }

    public boolean isEH() { return this.eH; }
    public boolean isEB() { return this.eB; }
    public boolean isEL() { return this.eL; }

    // ===== Safe image getters =====
    public BufferedImage getIHead() {
        if (iHead == null && !eH) {
            try {
                if (head <= 0) { // id không hợp lệ
                    eH = true;
                    iHead = getPlaceholderImage();
                } else {
                    pHead = Part.getPart(head);
                    if (pHead != null && pHead.getPi().length > 0) {
                        iHead = Util.getImageById(pHead.getPi()[0].getIconId(), 1);
                        if (iHead == null) iHead = getPlaceholderImage();
                    } else {
                        eH = true;
                        iHead = getPlaceholderImage();
                    }
                }
            } catch (Exception ex) {
                eH = true;
                iHead = getPlaceholderImage();
            }
        }
        return iHead;
    }

    public BufferedImage getIBody() {
        if (iBody == null && !eB) {
            try {
                if (body <= 0) { eB = true; iBody = getPlaceholderImage(); }
                else {
                    pBody = Part.getPart(body);
                    if (pBody != null && pBody.getPi().length > 1) {
                        iBody = Util.getImageById(pBody.getPi()[1].getIconId(), 1);
                        if (iBody == null) iBody = getPlaceholderImage();
                    } else {
                        eB = true;
                        iBody = getPlaceholderImage();
                    }
                }
            } catch (Exception ex) {
                eB = true;
                iBody = getPlaceholderImage();
            }
        }
        return iBody;
    }

    public BufferedImage getILeg() {
        if (iLeg == null && !eL) {
            try {
                if (leg <= 0) { eL = true; iLeg = getPlaceholderImage(); }
                else {
                    pLeg = Part.getPart(leg);
                    if (pLeg != null && pLeg.getPi().length > 1) {
                        iLeg = Util.getImageById(pLeg.getPi()[1].getIconId(), 1);
                        if (iLeg == null) iLeg = getPlaceholderImage();
                    } else {
                        eL = true;
                        iLeg = getPlaceholderImage();
                    }
                }
            } catch (Exception ex) {
                eL = true;
                iLeg = getPlaceholderImage();
            }
        }
        return iLeg;
    }

    // ===== Placeholder image (đơn giản, tránh null) =====
    private BufferedImage getPlaceholderImage() {
        BufferedImage img = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
        // có thể vẽ màu mặc định hoặc để trong suốt
        return img;
    }

    // ===== equals / hashCode / toString =====
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof NpcTemplate)) return false;
        NpcTemplate other = (NpcTemplate) o;
        if (!other.canEqual(this)) return false;
        if (id != other.id || head != other.head || body != other.body || leg != other.leg || avatar != other.avatar) return false;
        if (eH != other.eH || eB != other.eB || eL != other.eL) return false;
        if (name != null ? !name.equals(other.name) : other.name != null) return false;
        if (pHead != null ? !pHead.equals(other.pHead) : other.pHead != null) return false;
        if (pBody != null ? !pBody.equals(other.pBody) : other.pBody != null) return false;
        if (pLeg != null ? !pLeg.equals(other.pLeg) : other.pLeg != null) return false;
        if (iHead != null ? !iHead.equals(other.iHead) : other.iHead != null) return false;
        if (iBody != null ? !iBody.equals(other.iBody) : other.iBody != null) return false;
        return iLeg != null ? iLeg.equals(other.iLeg) : other.iLeg == null;
    }

    protected boolean canEqual(Object other) {
        return other instanceof NpcTemplate;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 59 * result + id;
        result = 59 * result + head;
        result = 59 * result + body;
        result = 59 * result + leg;
        result = 59 * result + avatar;
        result = 59 * result + (eH ? 79 : 97);
        result = 59 * result + (eB ? 79 : 97);
        result = 59 * result + (eL ? 79 : 97);
        result = 59 * result + (name != null ? name.hashCode() : 43);
        result = 59 * result + (pHead != null ? pHead.hashCode() : 43);
        result = 59 * result + (pBody != null ? pBody.hashCode() : 43);
        result = 59 * result + (pLeg != null ? pLeg.hashCode() : 43);
        result = 59 * result + (iHead != null ? iHead.hashCode() : 43);
        result = 59 * result + (iBody != null ? iBody.hashCode() : 43);
        result = 59 * result + (iLeg != null ? iLeg.hashCode() : 43);
        return result;
    }

    @Override
    public String toString() {
        return "NpcTemplate(id=" + id + ", name=" + name + ", head=" + head + ", body=" + body + ", leg=" + leg
                + ", avatar=" + avatar + ", pHead=" + pHead + ", pBody=" + pBody + ", pLeg=" + pLeg
                + ", iHead=" + iHead + ", iBody=" + iBody + ", iLeg=" + iLeg
                + ", eH=" + eH + ", eB=" + eB + ", eL=" + eL + ")";
    }
}
