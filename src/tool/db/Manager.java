package tool.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tool.db.EffectTemplate;
import tool.draw_map.load.BgItemTemplate;
import tool.draw_map.load.HeadAvatar;
import tool.draw_map.load.MapTemplate;
import tool.draw_map.load.MobTemplate;
import tool.draw_map.load.NpcTemplate;
import tool.draw_map.load.TilesetType;
import tool.utils.Logger;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import tool.db.DatabaseManagers;
import tool.db.DatabaseResultSet;

public class Manager {

    private static Manager I;

    public static Manager gI() {
        if (I == null) {
            I = new Manager();
        }
        return I;
    }
    private List<TilesetType> tile_set_type = new ArrayList<>();
    private List<NpcTemplate> npcTemplates;
    private List<HeadAvatar> headAvatars;
    private List<BgItemTemplate> bgItemTemplates;
    private List<MobTemplate> mobTemplates;
    private List<EffectTemplate> effectTemplates;
    private List<MapTemplate> mapTemplates;

    public List<TilesetType> getTile_set_type() {
        return this.tile_set_type;
    }

    public List<NpcTemplate> getNpcTemplates() {
        return this.npcTemplates;
    }

    public List<HeadAvatar> getHeadAvatars() {
        return this.headAvatars;
    }

    public List<BgItemTemplate> getBgItemTemplates() {
        return this.bgItemTemplates;
    }

    public List<MobTemplate> getMobTemplates() {
        return this.mobTemplates;
    }

    public List<EffectTemplate> getEffectTemplates() {
        return this.effectTemplates;
    }

    public List<MapTemplate> getMapTemplates() {
        return this.mapTemplates;
    }

    private Manager() {
        (new Thread(() -> {
            load();
            Logger.warning("Load hot\n");
        })).start();
    }

    private void load() {
        loadMapTemplate();
        loadNpcTemplate();
        loadHeadAvatar();
        loadBgItemTemplate();
        loadMobTemplate();
        loadTileType();
        loadEffectTemplate();
    }

    private void loadMapTemplate() {
        this.mapTemplates = new ArrayList<>();
        try {
            DatabaseResultSet rs = DatabaseManagers.executeQuery("select * from map_template");
            while (rs.next()) {
                this.mapTemplates.add(new MapTemplate(rs.getInt("id"), rs.getString("name")));
            }
            Logger.success("Load map template(" + this.mapTemplates.size() + ")\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEffectTemplate() {
        this.effectTemplates = new ArrayList<>();
        try {
            for (int i = 0; i < 750; i++) {
                EffectTemplate eff = readEff(i);
                if (eff != null && eff.getSizeFrame() > 0) {
                    this.effectTemplates.add(eff);
                }
            }
            Logger.success("Load effect template(" + this.effectTemplates.size() + ")\n");
        } catch (Exception exception) {
        }
    }

    public EffectTemplate readEff(int id) {
        EffectTemplate eff = null;
        try {
            //DataInputStream dis = new DataInputStream(new FileInputStream("data/effdata/x1/" + id));
            DataInputStream dis = new DataInputStream(new FileInputStream("data/effect" + id));
            dis.readShort();
            byte[] data = new byte[dis.readInt()];
            dis.read(data);
            byte[] dataImage = new byte[dis.readInt()];
            dis.read(dataImage);
            ByteArrayInputStream bis = new ByteArrayInputStream(dataImage);
            BufferedImage oriImage = ImageIO.read(bis);
            eff = new EffectTemplate();
            eff.setId(id);
            eff.setImageOri(oriImage);
            readDataEffect(data, 1, oriImage, eff, id);
        } catch (Exception exception) {
        }
        return eff;
    }

    private void readDataEffect(byte[] data, int zoom, BufferedImage oriImage, EffectTemplate eff, int idEff) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            DataInputStream dis = new DataInputStream(bis);
            int nImageInfo = dis.readByte();
            BufferedImage[] imageInfo = new BufferedImage[nImageInfo];
            eff.setAxisSubImage(new int[nImageInfo][]);
            for (int i = 0; i < nImageInfo; i++) {
                eff.getAxisSubImage()[i] = new int[5];
                int id = dis.readByte();
                int x = dis.readUnsignedByte();
                int y = dis.readUnsignedByte();
                int w = dis.readUnsignedByte();
                int h = dis.readUnsignedByte();
                try {
                    if (y + h > oriImage.getHeight()) {
                        h = oriImage.getHeight() - y;
                    }
                    if (x + w > oriImage.getWidth()) {
                        w = oriImage.getWidth() - x;
                    }
                    x = Math.abs(x);
                    y = Math.abs(y);
                    h = Math.abs(h);
                    w = Math.abs(w);
                    eff.getAxisSubImage()[i][0] = id;
                    eff.getAxisSubImage()[i][1] = x;
                    eff.getAxisSubImage()[i][2] = y;
                    eff.getAxisSubImage()[i][3] = w;
                    eff.getAxisSubImage()[i][4] = h;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(oriImage.getWidth() + " : " + x + " : " + w + " -");
                    JOptionPane.showMessageDialog(null, null, null, 1, new ImageIcon(oriImage));
                }
            }
            int nFrame = dis.readShort();
            eff.setAxisFrame(new int[nFrame][][]);
            for (int j = 0; j < nFrame; j++) {
                int nF = dis.readByte();
                eff.getAxisFrame()[j] = new int[nF][];
                for (int m = 0; m < nF; m++) {
                    eff.getAxisFrame()[j][m] = new int[3];
                    int dx = dis.readShort() * zoom;
                    int dy = dis.readShort() * zoom;
                    int idImage = dis.readByte();
                    eff.getAxisFrame()[j][m][0] = dx;
                    eff.getAxisFrame()[j][m][1] = dy;
                    eff.getAxisFrame()[j][m][2] = idImage;
                }
            }
            int arrF = dis.readShort();
            for (int k = 0; k < arrF; k++);
        } catch (Exception exception) {
        }
    }

    private void loadNpcTemplate() {
        this.npcTemplates = new ArrayList<>();
        try {
            DatabaseResultSet rs = DatabaseManagers.executeQuery("select * from npc_template");
            while (rs.next()) {
                NpcTemplate npcTemplate = new NpcTemplate(rs.getInt("id"), rs.getString("name"), rs.getInt("head"), rs.getInt("body"), rs.getInt("leg"), rs.getInt("avatar"));
                this.npcTemplates.add(npcTemplate);
            }
            Logger.success("Load npc template(" + this.npcTemplates.size() + ")\n");
        } catch (Exception exception) {
        }
    }

    private void loadHeadAvatar() {
        this.headAvatars = new ArrayList<>();
        try {
            DatabaseResultSet rs = DatabaseManagers.executeQuery("select * from head_avatar");
            while (rs.next()) {
                HeadAvatar headAvatar = new HeadAvatar(rs.getInt("head_id"), rs.getInt("avatar_id"));
                this.headAvatars.add(headAvatar);
            }
            Logger.success("Load head avatar(" + this.headAvatars.size() + ")\n");
        } catch (Exception exception) {
        }
    }

    private void loadBgItemTemplate() {
        this.bgItemTemplates = new ArrayList<>();
        try {
            DatabaseResultSet rs = DatabaseManagers.executeQuery("select * from bg_item_template");
            while (rs.next()) {
                BgItemTemplate bgItemTemplate = new BgItemTemplate(rs.getInt("id"), rs.getInt("image_id"), rs.getInt("layer"), rs.getInt("dx"), rs.getInt("dy"));
                this.bgItemTemplates.add(bgItemTemplate);
            }
            Logger.success("Load background item template(" + this.bgItemTemplates.size() + ")\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMobTemplate() {
        this.mobTemplates = new ArrayList<>();
        try {
            DatabaseResultSet rs = DatabaseManagers.executeQuery("select * from mob_template");
            while (rs.next()) {
                MobTemplate mobTemplate = new MobTemplate(rs.getInt("id"), rs.getInt("type"), rs.getString("name"), rs.getInt("range_move"), rs.getInt("speed"));
                this.mobTemplates.add(mobTemplate);
            }
            Logger.success("Load mob template(" + this.mobTemplates.size() + ")\n");
        } catch (Exception exception) {
        }
    }

    private void loadTileType() {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("data/girlkun/map/tile_set_info"));
            int nTileset = dis.readByte();
            for (int i = 0; i < nTileset; i++) {
                TilesetType t = new TilesetType();
                t.id = i + 1;
                int n = dis.readByte();
                for (int j = 0; j < n; j++) {
                    int tileType = dis.readInt();
                    int nTile = dis.readByte();
                    for (int k = 0; k < nTile; k++) {
                        int tileIndex = dis.readByte();
                        List<Integer> list = t.tileType.computeIfAbsent(tileIndex, v -> new ArrayList<>());
                        list.add(tileType);
                    }
                }
                this.tile_set_type.add(t);
            }
            Logger.success("Load tile set type (" + this.tile_set_type.size() + ")\n");
            // --- Serialize ra JSON ---
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter("data/girlkun/map/tile_set_info.json")) {
                gson.toJson(this.tile_set_type, writer);
            }
        Logger.success("Đã xuất tile_set_type ra JSON: tile_set_info.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ____________________________________________________________() {
    }

    public NpcTemplate getNpcTemplateById(int npcId) throws Exception {
        for (NpcTemplate npc : this.npcTemplates) {
            if (npc.getId() == npcId) {
                return npc;
            }
        }
        throw new Exception("npc " + npcId);
    }

    public HeadAvatar getHeadAvatarByHeadId(int headId) throws Exception {
        for (HeadAvatar ha : this.headAvatars) {
            if (ha.getHead() == headId) {
                return ha;
            }
        }
        throw new Exception("head avatar " + headId);
    }

    public EffectTemplate getEffectTemplateById(int id) throws Exception {
        for (EffectTemplate eff : this.effectTemplates) {
            if (eff.getId() == id) {
                return eff;
            }
        }
        throw new Exception("effect " + id);
    }
}
