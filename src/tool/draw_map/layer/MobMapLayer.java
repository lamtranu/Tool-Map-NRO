package tool.draw_map.layer;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.List;
import tool.draw_map.DrawMapScr;
import tool.draw_map.template.MobMap;
import tool.draw_map.load.MobTemplate;

public class MobMapLayer implements Layer {

    private BufferedImage image;
    private DrawMapScr drawMapScr;
    private List<MobMap> mobs;
    private int iFlag;
    private boolean show;

    public void clear() {
        this.mobs.clear();
    }

    public MobMapLayer(DrawMapScr drawMapScr, List<MobMap> mobs, int w, int h) {
        this.show = true;
        this.mobs = mobs;
        this.drawMapScr = drawMapScr;
        this.image = new BufferedImage(w * 24, h * 24, 2);
    }

    public void setSizeImage(int w, int h, int offset, int dir) {
        this.image = new BufferedImage(w * 24, h * 24, 2);
        switch (dir) {
            case 0:
                for (MobMap mob : this.mobs) {
                    mob.setY(mob.getY() + offset * 24);
                }
                break;
            case 1:
                for (MobMap mob : this.mobs) {
                    mob.setX(mob.getX() + offset * 24);
                }
                break;
        }
    }

    public void put(MobTemplate temp, int x, int y) {
        if (x < 0 || y < 0 || x > this.image.getWidth() || y > this.image.getHeight()) {
            return;
        }
        if (temp.getId() != 4) {
            y = y / 24 * 24;
        }
        int hp = 752002;
        int level = 1;
        this.mobs.add(new MobMap(temp, x, y, level, hp));
    }

    public void drawMobChoose(MobTemplate temp, int x, int y) {
        if (temp == null) {
            return;
        }
        if (temp.getId() != 4) {
            y = y / 24 * 24;
        }
        Graphics2D g = this.image.createGraphics();
        try {
            int minX, minY, maxX, maxY;
            if (temp.getType() == 0) {
                minX = x;
                maxX = x;
                minY = y;
                maxY = y;
            } else if (temp.getType() == 1) {
                minX = x - temp.getRangeMove() / 4 * 3;
                maxX = x + temp.getRangeMove() / 4 * 3;
                minY = y;
                maxY = y;
            } else {
                minX = x - temp.getRangeMove() / 4 * 3;
                maxX = x + temp.getRangeMove() / 4 * 3;
                minY = y - temp.getRangeMove() / 4 * 2;
                maxY = y + temp.getRangeMove() / 4 * 2;
            }
            g.drawImage(temp.getImages()[0], x - temp.getImages()[0].getWidth() / 2, y - temp.getImages()[0].getHeight() / 4 * 3, (ImageObserver) null);
            g.setStroke(new BasicStroke(2.0F));
            this.iFlag++;
            if (this.iFlag >= 10) {
                this.iFlag = 0;
            }
            if (this.iFlag > 5) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.white);
            }
            g.drawLine(minX - 2, y - 2, minX + 2, y + 2);
            g.drawLine(minX + 2, y - 2, minX - 2, y + 2);
            g.drawLine(maxX - 2, y - 2, maxX + 2, y + 2);
            g.drawLine(maxX + 2, y - 2, maxX - 2, y + 2);
            if (temp.getType() == 4) {
                g.drawLine(x - 2, minY - 2, x + 2, minY + 2);
                g.drawLine(x + 2, minY - 2, x - 2, minY + 2);
                g.drawLine(x - 2, maxY - 2, x + 2, maxY + 2);
                g.drawLine(x + 2, maxY - 2, x - 2, maxY + 2);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        g.dispose();
    }

    public void draw() {
        clearImage();
        if (!isShow()) {
            return;
        }
        Graphics2D g = this.image.createGraphics();
        for (MobMap mob : this.mobs) {
            mob.paint(g, mob.equals(this.drawMapScr.mobMapChose));
        }
        g.dispose();
    }

    public BufferedImage getBufferedImage() {
        return this.image;
    }

    public void clearImage() {
        Graphics2D g = this.image.createGraphics();
        g.setComposite(AlphaComposite.Clear);
        int x = -this.drawMapScr.camera.camX;
        if (x < 0) {
            x = 0;
        }
        int y = -this.drawMapScr.camera.camY;
        if (y < 0) {
            y = 0;
        }
        int w = this.drawMapScr.camera.width;
        if (w + x > this.image.getWidth()) {
            w = this.image.getWidth() - x;
        }
        int h = this.drawMapScr.camera.height;
        if (h + y > this.image.getHeight()) {
            h = this.image.getHeight() - y;
        }
        g.fillRect(x, y, w, h);
        g.dispose();
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return this.show;
    }
}
