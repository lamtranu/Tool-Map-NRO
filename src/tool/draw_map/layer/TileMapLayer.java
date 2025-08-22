package tool.draw_map.layer;

import tool.draw_map.load.TilesetType;
import tool.draw_map.DrawMapScr;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TileMapLayer implements Layer {

    private TilesetType tileSetType;
    private BufferedImage[] tileSet;
    private int[][] tileMap;
    private BufferedImage image;
    private DrawMapScr drawMapScr;

    public void setTileSetType(TilesetType tileSetType) {
        this.tileSetType = tileSetType;
    }

    public void setTileSet(BufferedImage[] tileSet) {
        this.tileSet = tileSet;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setDrawMapScr(DrawMapScr drawMapScr) {
        this.drawMapScr = drawMapScr;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TileMapLayer)) {
            return false;
        }
        TileMapLayer other = (TileMapLayer) o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (isShow() != other.isShow()) {
            return false;
        }
        Object this$tileSetType = getTileSetType(), other$tileSetType = other.getTileSetType();
        if ((this$tileSetType == null) ? (other$tileSetType != null) : !this$tileSetType.equals(other$tileSetType)) {
            return false;
        }
        if (!Arrays.deepEquals((Object[]) getTileSet(), (Object[]) other.getTileSet())) {
            return false;
        }
        if (!Arrays.deepEquals((Object[]) getTileMap(), (Object[]) other.getTileMap())) {
            return false;
        }
        Object this$image = getImage(), other$image = other.getImage();
        if ((this$image == null) ? (other$image != null) : !this$image.equals(other$image)) {
            return false;
        }
        Object this$drawMapScr = getDrawMapScr(), other$drawMapScr = other.getDrawMapScr();
        return !((this$drawMapScr == null) ? (other$drawMapScr != null) : !this$drawMapScr.equals(other$drawMapScr));
    }

    protected boolean canEqual(Object other) {
        return other instanceof TileMapLayer;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (isShow() ? 79 : 97);
        Object $tileSetType = getTileSetType();
        result = result * 59 + (($tileSetType == null) ? 43 : $tileSetType.hashCode());
        result = result * 59 + Arrays.deepHashCode((Object[]) getTileSet());
        result = result * 59 + Arrays.deepHashCode((Object[]) getTileMap());
        Object $image = getImage();
        result = result * 59 + (($image == null) ? 43 : $image.hashCode());
        Object $drawMapScr = getDrawMapScr();
        return result * 59 + (($drawMapScr == null) ? 43 : $drawMapScr.hashCode());
    }

    public String toString() {
        return "TileMapLayer(tileSetType=" + getTileSetType() + ", tileSet=" + Arrays.deepToString((Object[]) getTileSet()) + ", tileMap=" + Arrays.deepToString((Object[]) getTileMap()) + ", image=" + getImage() + ", drawMapScr=" + getDrawMapScr() + ", show=" + isShow() + ")";
    }

    public TilesetType getTileSetType() {
        return this.tileSetType;
    }

    public BufferedImage[] getTileSet() {
        return this.tileSet;
    }

    public int[][] getTileMap() {
        return this.tileMap;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public DrawMapScr getDrawMapScr() {
        return this.drawMapScr;
    }
    public static boolean drawTileType = false;
    public static boolean drawGrid = false;
    private boolean show;

    public void clear() {
    }

    public TileMapLayer(DrawMapScr drawMapScr, int w, int h) {
        this.show = true;
        this.drawMapScr = drawMapScr;
        this.image = new BufferedImage(w * 24, h * 24, 2);
        this.tileMap = new int[h][w];
        clearMap();
    }

    public void clearMap() {
        for (int i = 0; i < this.tileMap.length; i++) {
            for (int j = 0; j < (this.tileMap[i]).length; j++) {
                this.tileMap[i][j] = -1;
            }
        }
    }

    public void draw() {
        clearImage();
        if (!isShow()) {
            return;
        }
        drawTileMap();
    }

    public void setSizeImage(int w, int h, int offset, int dir) {
        this.image = new BufferedImage(w * 24, h * 24, 2);
    }

    private boolean drawTileMap(Graphics2D g) {
        int ys = -this.drawMapScr.camera.camY / 24 - 1;
        if (ys < 0) {
            ys = 0;
        }
        int ye = (-this.drawMapScr.camera.camY + this.drawMapScr.camera.height) / 24 + 1;
        if (ye >= this.tileMap.length) {
            ye = this.tileMap.length - 1;
        }
        int xs = -this.drawMapScr.camera.camX / 24 - 1;
        if (xs < 0) {
            xs = 0;
        }
        int xe = (-this.drawMapScr.camera.camX + this.drawMapScr.camera.width) / 24 + 1;
        if (xe >= (this.tileMap[0]).length) {
            xe = (this.tileMap[0]).length - 1;
        }
        for (int y = ys; y <= ye; y++) {
            for (int x = xs; x <= xe; x++) {
                if (this.tileSet != null && this.tileMap[y][x] != -1) {
                    try {
                        g.drawImage(this.tileSet[this.tileMap[y][x]], x * 24, y * 24, (ImageObserver) null);
                    } catch (Exception exception) {
                    }
                }
                if (drawTileType) {
                    if (this.tileSetType == null) {
                        this.tileSetType = this.drawMapScr.tileSetType;
                    }
                    if (this.tileSetType != null) {
                        List<Integer> tileType = (List<Integer>) this.tileSetType.tileType.get(Integer.valueOf(this.tileMap[y][x] + 1));
                        if (tileType != null) {
                            g.setColor(Color.red);
                            g.setStroke(new BasicStroke(2.0F));
                            for (Iterator<Integer> iterator = tileType.iterator(); iterator.hasNext();) {
                                int tile = ((Integer) iterator.next()).intValue();
                                if (tile == 2) {
                                    g.drawLine(x * 24, y * 24 + 5, x * 24 + 24, y * 24 + 5);
                                    continue;
                                }
                                if (tile == 4) {
                                    g.drawLine(x * 24 + 5, y * 24, x * 24 + 5, y * 24 + 24);
                                    continue;
                                }
                                if (tile == 8) {
                                    g.drawLine(x * 24 + 19, y * 24, x * 24 + 19, y * 24 + 24);
                                    continue;
                                }
                                if (tile == 8192) {
                                    g.drawLine(x * 24, y * 24 + 19, x * 24 + 24, y * 24 + 19);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (drawGrid) {
            int x = -this.drawMapScr.camera.camX;
            if (x < 0) {
                x = 0;
            }
            int i = -this.drawMapScr.camera.camY;
            if (i < 0) {
                i = 0;
            }
            int w = this.drawMapScr.camera.width;
            if (w + x > this.image.getWidth()) {
                w = this.image.getWidth() - x;
            }
            int h = this.drawMapScr.camera.height;
            if (h + i > this.image.getHeight()) {
                h = this.image.getHeight() - i;
            }
            g.setStroke(new BasicStroke(1.0F));
            g.setColor(Color.white);
            for (int yy = ys; yy <= ye; yy++) {
                g.drawLine(x, yy * 24, x + w, yy * 24);
                for (int xx = xs; xx <= xe; xx++) {
                    g.drawLine(xx * 24, i, xx * 24, i + h);
                }
            }
        }
        return true;
    }

    private void drawTileMap() {
        Graphics2D g = this.image.createGraphics();
        if (drawTileMap(g)) {
            return;
        }
        for (int y = 0; y < this.tileMap.length; y++) {
            if (y * 24 + this.drawMapScr.camera.camY >= 0) {
                if (y * 24 + this.drawMapScr.camera.camY > this.drawMapScr.camera.height) {
                    break;
                }
                if (drawGrid) {
                    g.drawLine(0, y * 24, this.drawMapScr.camera.width - this.drawMapScr.camera.camX, y * 24);
                }
                for (int x = 0; x < (this.tileMap[y]).length; x++) {
                    if (x * 24 + this.drawMapScr.camera.camX >= 0) {
                        if (x * 24 + this.drawMapScr.camera.camX > this.drawMapScr.camera.width) {
                            break;
                        }
                        if (drawGrid) {
                            g.drawLine(x * 24, 0, x * 24, this.drawMapScr.camera.height - this.drawMapScr.camera.camY);
                        }
                        if (this.tileSet != null && this.tileMap[y][x] != -1) {
                            try {
                                g.drawImage(this.tileSet[this.tileMap[y][x]], x * 24, y * 24, (ImageObserver) null);
                            } catch (Exception exception) {
                            }
                        }
                        if (drawTileType) {
                            if (this.tileSetType == null) {
                                this.tileSetType = this.drawMapScr.tileSetType;
                            }
                            if (this.tileSetType != null) {
                                List<Integer> tileType = (List<Integer>) this.tileSetType.tileType.get(Integer.valueOf(this.tileMap[y][x] + 1));
                                if (tileType != null) {
                                    g.setColor(Color.BLACK);
                                    g.setStroke(new BasicStroke(2.0F));
                                    for (Iterator<Integer> iterator = tileType.iterator(); iterator.hasNext();) {
                                        int tile = ((Integer) iterator.next()).intValue();
                                        if (tile == 2) {
                                            g.drawLine(x * 24, y * 24 + 5, x * 24 + 24, y * 24 + 5);
                                            continue;
                                        }
                                        if (tile == 4) {
                                            g.drawLine(x * 24 + 5, y * 24, x * 24 + 5, y * 24 + 24);
                                            continue;
                                        }
                                        if (tile == 8) {
                                            g.drawLine(x * 24 + 19, y * 24, x * 24 + 19, y * 24 + 24);
                                            continue;
                                        }
                                        if (tile == 8192) {
                                            g.drawLine(x * 24, y * 24 + 19, x * 24 + 24, y * 24 + 19);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void putTileMap(BufferedImage[] tileSet, int[][] indexTile, int x, int y) {
        if (this.tileSet == null) {
            this.tileSet = tileSet;
        }
        x /= 24;
        y /= 24;
        try {
            for (int yy = 0; yy < indexTile.length; yy++) {
                for (int xx = 0; xx < (indexTile[yy]).length; xx++) {
                    try {
                        if ((indexTile.length <= 1 && (indexTile[yy]).length <= 1) || indexTile[yy][xx] != -1) {
                            this.tileMap[yy + y][xx + x] = indexTile[yy][xx];
                        }
                    } catch (Exception exception) {
                    }
                }
            }
        } catch (Exception exception) {
        }
    }

    public void setTileMap(int[][] tileMap) {
        this.tileMap = tileMap;
        if (tileMap != null && tileMap.length != 0) {
            this.image = new BufferedImage((tileMap[0]).length * 24, tileMap.length * 24, 2);
        }
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
    }

    public void drawItemChose(BufferedImage[][] tileChose, int x, int y) {
        if (tileChose != null) {
            x -= x % 24;
            y -= y % 24;
            Graphics2D g = this.image.createGraphics();
            for (int yy = 0; yy < tileChose.length; yy++) {
                for (int xx = 0; xx < (tileChose[yy]).length; xx++) {
                    if (tileChose[yy][xx] != null) {
                        g.drawImage(tileChose[yy][xx], x + xx * 24, y + yy * 24, (ImageObserver) null);
                        g.setColor(Color.green);
                        if (yy == 0 || tileChose[yy - 1][xx] == null) {
                            g.drawLine(x + xx * 24, y + yy * 24, x + xx * 24 + 24, y + yy * 24);
                        }
                        if (yy == tileChose.length - 1 || tileChose[yy + 1][xx] == null) {
                            g.drawLine(x + xx * 24, y + yy * 24 + 24, x + xx * 24 + 24, y + yy * 24 + 24);
                        }
                        if (xx == 0 || tileChose[yy][xx - 1] == null) {
                            g.drawLine(x + xx * 24, y + yy * 24, x + xx * 24, y + yy * 24 + 24);
                        }
                        if (xx == (tileChose[yy]).length - 1 || tileChose[yy][xx + 1] == null) {
                            g.drawLine(x + xx * 24 + 24, y + yy * 24, x + xx * 24 + 24, y + yy * 24 + 24);
                        }
                    }
                }
            }
        }
    }

    public BufferedImage getBufferedImage() {
        return this.image;
    }

    public void copyTile(int x1, int y1, int x2, int y2) {
        x1 -= this.drawMapScr.camera.camX;
        x2 -= this.drawMapScr.camera.camX;
        y1 -= this.drawMapScr.camera.camY;
        y2 -= this.drawMapScr.camera.camY;
        int x = (x1 < x2) ? x1 : x2;
        int y = (y1 < y2) ? y1 : y2;
        x -= x % 24;
        y -= y % 24;
        x /= 24;
        y /= 24;
        int w = Math.abs(x1 - x2);
        int h = Math.abs(y1 - y2);
        w += 24 - w % 24;
        h += 24 - h % 24;
        w /= 24;
        h /= 24;
        int[][] indexTilesChose = new int[h][w];
        for (int i = 0; i < indexTilesChose.length; i++) {
            for (int k = 0; k < (indexTilesChose[i]).length; k++) {
                try {
                    indexTilesChose[i][k] = this.tileMap[i + y][k + x];
                } catch (Exception e) {
                    indexTilesChose[i][k] = -1;
                }
            }
        }
        indexTilesChose = borderTileChose(indexTilesChose);
        BufferedImage[][] tilesChose = new BufferedImage[indexTilesChose.length][(indexTilesChose[0]).length];
        for (int j = 0; j < indexTilesChose.length; j++) {
            for (int k = 0; k < (indexTilesChose[j]).length; k++) {
                try {
                    if (indexTilesChose[j][k] != -1) {
                        tilesChose[j][k] = this.tileSet[indexTilesChose[j][k]];
                    }
                } catch (Exception e) {
                    indexTilesChose[j][k] = -1;
                }
            }
        }
        this.drawMapScr.indexTilesChose = indexTilesChose;
        this.drawMapScr.tilesChose = tilesChose;
        if (this.drawMapScr.tilesChose.length == 1 && (this.drawMapScr.tilesChose[0]).length == 1) {
            this.drawMapScr.tileChose = this.drawMapScr.tilesChose[0][0];
        }
    }

    private int[][] borderTileChose(int[][] tileFocus) {
        if (tileFocus.length == 1 && (tileFocus[0]).length == 1) {
            return tileFocus;
        }
        boolean left = false;
        boolean right = false;
        boolean top = false;
        boolean bottom = false;
        int i;
        for (i = 0; i < tileFocus.length; i++) {
            if (tileFocus[i][0] != -1) {
                left = true;
                break;
            }
        }
        if (!left && (tileFocus[0]).length > 1) {
            int[][] temp = new int[tileFocus.length][(tileFocus[0]).length - 1];
            for (int ih = 0; ih < tileFocus.length; ih++) {
                for (int iw = 1; iw < (tileFocus[ih]).length; iw++) {
                    temp[ih][iw - 1] = tileFocus[ih][iw];
                }
            }
            tileFocus = temp;
            return borderTileChose(tileFocus);
        }
        for (i = 0; i < tileFocus.length; i++) {
            if (tileFocus[i][(tileFocus[i]).length - 1] != -1) {
                right = true;
                break;
            }
        }
        if (!right && (tileFocus[0]).length > 1) {
            int[][] temp = new int[tileFocus.length][(tileFocus[0]).length - 1];
            for (int ih = 0; ih < tileFocus.length; ih++) {
                for (int iw = 0; iw < (tileFocus[ih]).length - 1; iw++) {
                    temp[ih][iw] = tileFocus[ih][iw];
                }
            }
            tileFocus = temp;
            return borderTileChose(tileFocus);
        }
        for (i = 0; i < (tileFocus[0]).length; i++) {
            if (tileFocus[0][i] != -1) {
                top = true;
                break;
            }
        }
        if (!top && tileFocus.length > 1) {
            int[][] temp = new int[tileFocus.length - 1][(tileFocus[0]).length];
            for (int ih = 1; ih < tileFocus.length; ih++) {
                for (int iw = 0; iw < (tileFocus[ih]).length; iw++) {
                    temp[ih - 1][iw] = tileFocus[ih][iw];
                }
            }
            tileFocus = temp;
            return borderTileChose(tileFocus);
        }
        for (i = 0; i < (tileFocus[0]).length; i++) {
            if (tileFocus[tileFocus.length - 1][i] != -1) {
                bottom = true;
                break;
            }
        }
        if (!bottom && tileFocus.length > 1) {
            int[][] temp = new int[tileFocus.length - 1][(tileFocus[0]).length];
            for (int ih = 0; ih < tileFocus.length - 1; ih++) {
                for (int iw = 0; iw < (tileFocus[ih]).length; iw++) {
                    temp[ih][iw] = tileFocus[ih][iw];
                }
            }
            tileFocus = temp;
            return borderTileChose(tileFocus);
        }
        return tileFocus;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return this.show;
    }
}
