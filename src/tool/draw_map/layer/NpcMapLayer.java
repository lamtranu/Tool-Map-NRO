package tool.draw_map.layer;

import tool.draw_map.load.NpcTemplate;
import tool.draw_map.DrawMapScr;
import tool.draw_map.template.NpcMap;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Lớp quản lý layer chứa NPC trong bản đồ
 */
public class NpcMapLayer implements Layer {

    private BufferedImage image;
    private final DrawMapScr drawMapScr;
    private final List<NpcMap> npcs;
    private final NpcMap npcChose;
    private boolean show;

    public NpcMapLayer(DrawMapScr drawMapScr, List<NpcMap> npcs, int w, int h) {
        this.drawMapScr = drawMapScr;
        this.npcs = npcs;
        this.npcChose = new NpcMap(null, 0, 0);
        this.show = true;
        this.image = new BufferedImage(w * 24, h * 24, BufferedImage.TYPE_INT_ARGB);
    }

    /** Xóa toàn bộ NPC trong layer */
    public void clear() {
        if (npcs != null) {
            npcs.clear();
        }
    }

    /** Thay đổi kích thước buffer image và dịch chuyển NPC theo offset */
    public void setSizeImage(int w, int h, int offset, int dir) {
        this.image = new BufferedImage(w * 24, h * 24, BufferedImage.TYPE_INT_ARGB);
        if (npcs == null) return;

        switch (dir) {
            case 0: // Dịch theo Y
                for (NpcMap npc : npcs) {
                    npc.setY(npc.getY() + offset * 24);
                }
                break;
            case 1: // Dịch theo X
                for (NpcMap npc : npcs) {
                    npc.setX(npc.getX() + offset * 24);
                }
                break;
        }
    }

    /** Thêm NPC mới vào layer */
    public void put(NpcTemplate temp, int x, int y) {
        if (temp == null || npcs == null || image == null) return;

        y = y / 24 * 24; // snap theo grid 24px
        if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) return;

        npcs.add(new NpcMap(temp, x, y));
    }

    /** Vẽ NPC đang được chọn (preview) */
    public void drawNpcChoose(NpcTemplate temp, int x, int y) {
        if (npcChose == null || image == null) return;

        y = y / 24 * 24; // snap grid
        Graphics2D g = image.createGraphics();
        try {
            npcChose.setTemp(temp);
            npcChose.setX(x);
            npcChose.setY(y);
            npcChose.paint(g, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            g.dispose();
        }
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return this.show;
    }

    /** Vẽ tất cả NPC trong layer */
    public void draw() {
        clearImage();
        if (!isShow() || image == null || npcs == null) return;

        Graphics2D g = image.createGraphics();
        for (NpcMap npc : npcs) {
            npc.paint(g, npc.equals(drawMapScr.npcMapChose));
        }
        g.dispose();
    }

    public BufferedImage getBufferedImage() {
        return this.image;
    }

    /** Xóa vùng image theo camera để chuẩn bị vẽ lại */
    public void clearImage() {
        if (image == null || drawMapScr == null || drawMapScr.camera == null) return;

        Graphics2D g = image.createGraphics();
        g.setComposite(AlphaComposite.Clear);

        int x = -drawMapScr.camera.camX;
        if (x < 0) x = 0;

        int y = -drawMapScr.camera.camY;
        if (y < 0) y = 0;

        int w = drawMapScr.camera.width;
        if (w + x > image.getWidth()) w = image.getWidth() - x;

        int h = drawMapScr.camera.height;
        if (h + y > image.getHeight()) h = image.getHeight() - y;

        g.fillRect(x, y, w, h);
        g.dispose();
    }
}
