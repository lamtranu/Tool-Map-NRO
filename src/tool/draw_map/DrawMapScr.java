package tool.draw_map;

import tool.button.Button;
import tool.db.EffectTemplate;
import tool.draw_map.load.BgItemTemplate;
import tool.draw_map.load.MobTemplate;
import tool.draw_map.load.NpcTemplate;
import tool.draw_map.load.TilesetType;
import tool.tool.Taidz;
import tool.db.Manager;
import tool.draw_map.layer.BgItemLayer;
import tool.draw_map.layer.EffectMapLayer;
import tool.draw_map.layer.Layer;
import tool.draw_map.layer.MobMapLayer;
import tool.draw_map.layer.NpcMapLayer;
import tool.draw_map.layer.TileMapLayer;
import tool.draw_map.layer.WaypointMapLayer;
import tool.draw_map.template.BgItemMap;
import tool.draw_map.template.EffectMap;
import tool.draw_map.template.MobMap;
import tool.draw_map.template.NpcMap;
import tool.draw_map.template.SubEffectMap;
import tool.draw_map.template.Waypoint;
import tool.utils.NotifyUtil;
import tool.utils.Util;
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import tool.db.DatabaseManagers;
import tool.db.DatabaseResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public final class DrawMapScr extends JInternalFrame {

    private Thread tDrawMap;

    private Thread tDrawTile;

    private BufferedImage layerPaintScreen;

    private BufferedImage tamDem;

    private boolean draw;

    private long lastTimeMove;

    private long st;

    private int fps;

    private long lastTimeCalFPS;

    private int timeCalFPS;

    private BufferedImage[] tile;

    public BufferedImage tileChose;

    private int indexTileChose;

    private BufferedImage layerMask;

    private TilesetType tilesetTypeData;

    private int tileId;

    private Camera camTileSet;

    private BGItemTable tableBGITEM;

    private BGItemList bGItemList;

    private NpcTable npcTable;

    private NpcList npcList;

    private MobTable mobTable;

    private MobList mobList;

    private WaypointList waypointList;

    private EffectTable effectTable;

    private EffectList effectList;

    public boolean is3D;

    private boolean isDebugMouse;

    private boolean lockCamera;

    private int xTile;

    private int yTile;

    private int[] anchorCamera;

    private boolean moveCamera;

    private int[] anchorCopy;

    private boolean copyTilesChose;

    private int[] mouseAxis;

    private boolean holdLeftMouse;

    private boolean holdCtrl;

    private boolean holdKeyAlt;

    private boolean holdSpace;

    private KeyEventDispatcher ked;

    private BufferedImage[][] tileChoseBefore;

    private int[][] indexTileChoseBefore;

    private boolean holdE;

    private boolean holdR;

    public List<Layer> layers;

    private DefaultTableModel model;

    public int indexLayer;

    public final Camera camera;

    public BufferedImage[][] tilesChose;

    public int[][] indexTilesChose;

    public BgItemTemplate bgItemChose1;

    public BgItemTemplate bgItemChose2;

    public BgItemTemplate bgItemChose3;

    public BgItemTemplate bgItemChose4;

    public BgItemMap bgChose;

    public EffectMap effChose;

    public int layerEff;

    public EffectTemplate effTempChose;

    public MobTemplate mobChose;

    public NpcTemplate npcChoose;

    public NpcMap npcMapChose;

    public MobMap mobMapChose;

    public Waypoint wpChose;

    private BufferedImage[] tileSet;

    public TilesetType tileSetType;

    private String mapName;

    public int mapId;

    public int mapWidth;

    public int mapHeight;

    private int scrW;

    private int scrH;

    public List<MobMap> mobs;

    public List<NpcMap> npcs;

    public List<Waypoint> waypoints;

    public List<EffectMap> effectMaps;

    public List<SubEffectMap> subEffectMaps;

    public List<BgItemMap> bgItemL1;

    public List<BgItemMap> bgItemL2;

    public List<BgItemMap> bgItemL3;

    public List<BgItemMap> bgItemL4;

    private Button btnTile;

    private Button button1;

    private Button button10;

    private Button button11;

    private Button button12;

    private Button button13;

    private Button button14;

    private Button button15;

    private Button button16;

    private Button button17;

    private Button button18;

    private Button button2;

    private Button button20;

    private Button button21;

    private Button button22;

    private Button button23;

    private Button button24;

    private Button button25;

    private Button button26;

    private Button button3;

    private Button button4;

    private Button button5;

    private Button button6;

    private Button buttonImportTilePNG;

    private Button button7;

    private Button button8;

    private Button button9;

    private JCheckBox jCheckBox1;

    private JCheckBox jCheckBox2;

    private JCheckBox jCheckBox3;

    private JCheckBox jCheckBox4;

    private JCheckBox jCheckBox5;

    private JCheckBox jCheckBox6;

    private JLabel jLabel1;

    private JPanel jPanel1;

    private JPanel jPanel2;

    private JPanel jPanel3;

    private JPanel jPanel4;

    private JScrollPane jScrollPane1;

    private JScrollPane jScrollPane2;

    private JToolBar.Separator jSeparator1;

    private JToolBar.Separator jSeparator3;

    private JToolBar.Separator jSeparator4;

    private JToolBar.Separator jSeparator5;

    private JToolBar.Separator jSeparator6;

    private JToolBar.Separator jSeparator7;

    private JToolBar.Separator jSeparator8;

    private JToolBar.Separator jSeparator9;

    private JToolBar.Separator jSeparatorButtonAddTilePNG;

    private JToolBar jToolBar1;

    private JLabel lblLayerName;

    private JPanel panelScreen;

    private JPanel panelTileMap;

    private JTable tblListLayer;

    public boolean isMapDouble;

    private byte[] texttaidz;

    private Color colortaidz;

    private int xtaidz;

    private int ytaidz;

    private int vXtaidz;

    private int vYtaidz;

    private long lastTimetaidz;

    private static Robot robot;

    public DrawMapScr() {
        this.timeCalFPS = 100;
        this.indexTileChose = -1;
        this.camTileSet = new Camera();
        this.isDebugMouse = true;
        this.lockCamera = false;
        this.xTile = -1;
        this.yTile = -1;
        this.anchorCamera = new int[2];
        this.anchorCopy = new int[2];
        this.mouseAxis = new int[2];
        this.layers = new ArrayList<>();
        this.indexLayer = -1;
        this.camera = new Camera();
        this.indexTilesChose = new int[][]{{-1}};
        this.layerEff = 2;
        this.mobs = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.waypoints = new ArrayList<>();
        this.effectMaps = new ArrayList<>();
        this.subEffectMaps = new ArrayList<>();
        this.bgItemL1 = new ArrayList<>();
        this.bgItemL2 = new ArrayList<>();
        this.bgItemL3 = new ArrayList<>();
        this.bgItemL4 = new ArrayList<>();
        this.texttaidz = new byte[]{
            -30, -99, -92, 32, 71, 105, 114, 108, 107, 117,
            110, 32, -30, -99, -92};
        this.colortaidz = Color.WHITE;
        this.xtaidz = 1111;
        this.ytaidz = -15;
        this.vXtaidz = 0;
        this.vYtaidz = 1;
        initComponents();
        setup();
        initThread();
        start();
        addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(DrawMapScr.this.ked);
            }
        });
        if (this.tile != null) {
            File file = new File("data/tile/1");
            if (file.exists()) {
                readTileSet(file);
            }
        }
        createNew(45, 30);
        addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
                DrawMapScr.this.lastTimeMove = System.currentTimeMillis();
            }
        });
    }

    private void start() {
        this.tDrawMap.start();
        this.tDrawTile.start();
    }

    private void initThread() {
        this.tDrawMap = new Thread(() -> {
            while (!this.isClosed) {
                try {
                    if (this.isSelected && Util.canDoLastTime(this.lastTimeMove, 500)) {
                        this.st = System.currentTimeMillis();
                        draw();
                    }
                    Thread.sleep(1L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.tDrawTile = new Thread(() -> {
            while (true) {
                try {
                    if (this.isSelected && Util.canDoLastTime(this.lastTimeMove, 500)) {
                        drawTile();
                    }
                    Thread.sleep(10L);
                } catch (Exception exception) {
                }
            }
        });
    }

    private void clearImage(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.layerMask.getWidth(), this.layerMask.getHeight());
    }

    private void drawTileSet(Graphics2D g) {
        int x = 0;
        int y = 0;
        y += this.camTileSet.camY;
        for (int i = 0; i < this.tile.length; i++) {
            g.drawImage(this.tile[i], x, y, (ImageObserver) null);
            List<Integer> tileType = (List<Integer>) this.tilesetTypeData.tileType.get(Integer.valueOf(i + 1));
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(2.0F));
            for (Iterator<Integer> iterator = tileType.iterator(); iterator.hasNext();) {
                int tile = ((Integer) iterator.next()).intValue();
                if (tile == 2) {
                    g.drawLine(x, y + 5, x + 24, y + 5);
                    continue;
                }
                if (tile == 4) {
                    g.drawLine(x + 5, y, x + 5, y + 24);
                    continue;
                }
                if (tile == 8) {
                    g.drawLine(x + 19, y, x + 19, y + 24);
                    continue;
                }
                if (tile == 8192) {
                    g.drawLine(x, y + 19, x + 24, y + 19);
                }
            }
            x += 24;
            if (x >= 264) {
                y += 24;
                x = 0;
            }
        }
        g.setColor(Color.green);
        g.drawRect(0, 0, this.layerMask.getWidth() - 1, this.layerMask.getHeight() - 1);
    }

    private void drawTileChose(Graphics2D g) {
        g.setColor(Color.GREEN);
        if (this.xTile > -1 && this.yTile > -1) {
            g.drawRect(this.xTile * 24, this.yTile * 24 + this.camTileSet.camY, 24, 24);
        }
        g.setColor(Color.red);
        g.setStroke(new BasicStroke(2.0F));
        if (this.tileChose == null) {
            return;
        }
        int pX = 0;
        int pY = this.camTileSet.camY;
        for (int i = 0; i < this.tile.length; i++) {
            if (this.tileChose.equals(this.tile[i])) {
                g.drawRect(pX, pY, 24, 24);
                break;
            }
            pX += 24;
            if (pX >= 264) {
                pX = 0;
                pY += 24;
            }
        }
    }

    private void drawTile() {
        if (this.tile == null) {
            return;
        }
        if (this.layerMask == null) {
            this.layerMask = new BufferedImage(this.panelTileMap.getWidth(), this.panelTileMap.getHeight(), 2);
        }
        Graphics grph = this.layerMask.getGraphics();
        Graphics2D g = (Graphics2D) grph;
        clearImage(g);
        drawTileSet(g);
        drawTileChose(g);
        drawTileToScreen();
    }

    private void drawTileToScreen() {
        Graphics grph = this.panelTileMap.getGraphics();
        Graphics2D g = (Graphics2D) grph;
        g.drawImage(this.layerMask, 0, 0, (ImageObserver) null);
    }

//    public void readTileSet(File file) {
//        try {
//            this.tileId = Integer.parseInt(file.getName().replaceAll(".png", ""));
//            this.tilesetTypeData = Manager.gI().getTile_set_type().get(this.tileId - 1);
//            BufferedImage img = ImageIO.read(file);
//            int h = img.getHeight();
//            int nTile = h / 24;
//            this.tileChose = null;
//            this.tile = new BufferedImage[nTile + 1];
//            for (int i = 0; i < nTile; i++) {
//                this.tile[i] = img.getSubimage(0, i * 24, 24, 24);
//                List<Integer> tileType = (List<Integer>) this.tilesetTypeData.tileType.get(Integer.valueOf(i + 1));
//                if (tileType != null) {
//                    List<Integer> list = new ArrayList<>();
//                    for (Iterator<Integer> iterator = tileType.iterator(); iterator.hasNext();) {
//                        int tile = ((Integer) iterator.next()).intValue();
//                        if ((tile == 2 || tile == 4 || tile == 8 || tile == 8192) && !list.contains(Integer.valueOf(tile))) {
//                            list.add(Integer.valueOf(tile));
//                        }
//                    }
//                    if (list.size() == 4) {
//                        Graphics graphics = this.tile[i].getGraphics();
//                        graphics.setColor(Color.pink);
//                        graphics.fillRect(0, 0, 24, 24);
//                        graphics.dispose();
//                    }
//                }
//            }
//            BufferedImage eraser = new BufferedImage(24, 24, 2);
//            Graphics2D g = eraser.createGraphics();
//            try {
//                BufferedImage e = Util.getImageByPath("data/eraser.png");
//                g.drawImage(e, 0, 0, (ImageObserver) null);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            this.tile[this.tile.length - 1] = eraser;
//            g.dispose();
//            setTileSet(this.tile, this.tilesetTypeData);
//            this.btnTile.setText(this.tileId + "");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    public void readTileSet(File file) {
        try {
            this.tileId = Integer.parseInt(file.getName().replaceAll(".png", ""));
            System.out.println("🔍 Đang load tileId = " + this.tileId + " từ file: " + file.getName());

            this.tilesetTypeData = Manager.gI().getTile_set_type().get(this.tileId - 1);
            System.out.println("📦 TilesetType ID = " + this.tilesetTypeData.id + ", Số tileType = " + this.tilesetTypeData.tileType.size());

            BufferedImage img = ImageIO.read(file);
            int h = img.getHeight();
            int nTile = h / 24;

            this.tileChose = null;
            this.tile = new BufferedImage[nTile + 1];

            for (int i = 0; i < nTile; i++) {
                this.tile[i] = img.getSubimage(0, i * 24, 24, 24);

                List<Integer> tileType = this.tilesetTypeData.tileType.get(i + 1);
                if (tileType != null) {
                    System.out.println("🧱 Tile " + (i + 1) + " có type: " + tileType);

                    List<Integer> list = new ArrayList<>();
                    for (int type : tileType) {
                        if ((type == 2 || type == 4 || type == 8 || type == 8192) && !list.contains(type)) {
                            list.add(type);
                        }
                    }
                    if (list.size() == 4) {
                        System.out.println("🎨 Tile " + (i + 1) + " được tô màu HỒNG (đủ 4 type đặc biệt)");
                        Graphics graphics = this.tile[i].getGraphics();
                        graphics.setColor(Color.pink);
                        graphics.fillRect(0, 0, 24, 24);
                        graphics.dispose();
                    }
                } else {
                    System.out.println("❗ Tile " + (i + 1) + " không có type.");
                }
            }

            BufferedImage eraser = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = eraser.createGraphics();
            try {
                BufferedImage e = Util.getImageByPath("data/eraser.png");
                g.drawImage(e, 0, 0, null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.tile[this.tile.length - 1] = eraser;
            g.dispose();

            setTileSet(this.tile, this.tilesetTypeData);
            this.btnTile.setText(this.tileId + "");
            System.out.println("✅ Load thành công tileId = " + this.tileId + " với " + nTile + " tile.");
        } catch (IOException | IndexOutOfBoundsException | NumberFormatException ex) {
            System.err.println("❌ Lỗi khi đọc tileSet từ file: " + file.getName());
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        this.panelScreen = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.tblListLayer = new JTable();
        this.jToolBar1 = new JToolBar();
        this.button3 = new Button();
        this.jSeparator5 = new JToolBar.Separator();
        this.button4 = new Button();
        this.jSeparator4 = new JToolBar.Separator();
        this.jSeparator1 = new JToolBar.Separator();
        this.button1 = new Button();

        this.jSeparatorButtonAddTilePNG = new JToolBar.Separator();

        this.jSeparator7 = new JToolBar.Separator();
        this.jSeparator6 = new JToolBar.Separator();

        this.button6 = new Button();
        this.jSeparator9 = new JToolBar.Separator();

        this.buttonImportTilePNG = new Button();

        this.button24 = new Button();
        this.jSeparator3 = new JToolBar.Separator();
        this.button14 = new Button();
        this.jSeparator8 = new JToolBar.Separator();
        this.button23 = new Button();
        this.lblLayerName = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.jPanel2 = new JPanel();
        this.button5 = new Button();
        this.button7 = new Button();
        this.button9 = new Button();
        this.button10 = new Button();
        this.button11 = new Button();
        this.button12 = new Button();
        this.button13 = new Button();
        this.button20 = new Button();
        this.button21 = new Button();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.button15 = new Button();
        this.button16 = new Button();
        this.button17 = new Button();
        this.button18 = new Button();
        this.jPanel3 = new JPanel();
        this.button2 = new Button();
        this.button8 = new Button();
        this.button22 = new Button();
        this.jCheckBox1 = new JCheckBox();
        this.jCheckBox2 = new JCheckBox();
        this.jCheckBox4 = new JCheckBox();
        this.jCheckBox3 = new JCheckBox();
        this.jCheckBox5 = new JCheckBox();
        this.jCheckBox6 = new JCheckBox();
        this.panelTileMap = new JPanel();
        this.jPanel4 = new JPanel();
        this.btnTile = new Button();
        this.button25 = new Button();
        this.button26 = new Button();
        setClosable(true);
        setIconifiable(true);
        GroupLayout panelScreenLayout = new GroupLayout(this.panelScreen);
        this.panelScreen.setLayout(panelScreenLayout);
        panelScreenLayout.setHorizontalGroup(panelScreenLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 1112, 32767));
        panelScreenLayout.setVerticalGroup(panelScreenLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 755, 32767));
        this.tblListLayer.setFont(new Font("SansSerif", 1, 12));
        this.tblListLayer.setModel(new DefaultTableModel(new Object[0][], (Object[]) new String[]{"Layer", "Show"}) {
            Class[] types = new Class[]{Object.class, Boolean.class};

            public Class getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }
        });
        this.tblListLayer.setSelectionMode(0);
        this.tblListLayer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                DrawMapScr.this.tblListLayerMouseClicked(evt);
            }

            public void mousePressed(MouseEvent evt) {
                DrawMapScr.this.tblListLayerMousePressed(evt);
            }

            public void mouseReleased(MouseEvent evt) {
                DrawMapScr.this.tblListLayerMouseReleased(evt);
            }
        });
        this.jToolBar1.add((Component) this.button4);
        this.jToolBar1.add(this.jSeparator4);
        this.jToolBar1.add(this.jSeparator1);
        this.button1.setBackground(new Color(204, 0, 204));
        this.button1.setForeground(new Color(255, 255, 255));
        this.button1.setText("       Import map     ");
        this.button1.setFocusable(false);
        this.button1.setFont(new Font("SansSerif", 1, 14));
        this.button1.setHorizontalTextPosition(0);
        this.button1.setVerticalTextPosition(3);
        this.button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button1ActionPerformed(evt);
            }
        });
        this.jToolBar1.add((Component) this.button1);

        this.jToolBar1.add(this.jSeparator7);
        this.jToolBar1.add(this.jSeparator6);
        this.button6.setBackground(new Color(204, 0, 204));
        this.button6.setForeground(new Color(255, 255, 255));
        this.button6.setText("Import bg item");
        this.button6.setFocusable(false);
        this.button6.setFont(new Font("SansSerif", 1, 14));
        this.button6.setHorizontalTextPosition(0);
        this.button6.setVerticalTextPosition(3);
        this.button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button6ActionPerformed(evt);
            }
        });
        //buttonImportTilePNG
        this.buttonImportTilePNG.setBackground(new Color(204, 0, 204));
        this.buttonImportTilePNG.setForeground(new Color(255, 255, 255));
        this.buttonImportTilePNG.setText("Import tile png");
        this.buttonImportTilePNG.setFocusable(false);
        this.buttonImportTilePNG.setFont(new Font("SansSerif", 1, 14));
        this.buttonImportTilePNG.setHorizontalTextPosition(0);
        this.buttonImportTilePNG.setVerticalTextPosition(3);
        this.buttonImportTilePNG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.buttonAddTilePngActionPerformed(evt);
            }
        });
        this.jToolBar1.add((Component) this.buttonImportTilePNG);
        this.jToolBar1.add(this.jSeparatorButtonAddTilePNG);
        this.jToolBar1.add((Component) this.button6);
        this.jToolBar1.add(this.jSeparator9);
        this.button24.setBackground(new Color(204, 0, 204));
        this.button24.setForeground(new Color(255, 255, 255));
        this.button24.setText("Import effect");
        this.button24.setFocusable(false);
        this.button24.setFont(new Font("SansSerif", 1, 14));
        this.button24.setHorizontalTextPosition(0);
        this.button24.setVerticalTextPosition(3);
        this.button24.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button24ActionPerformed(evt);
            }
        });
        this.jToolBar1.add((Component) this.button24);
        this.jToolBar1.add(this.jSeparator3);
        this.button14.setBackground(new Color(204, 0, 204));
        this.button14.setForeground(new Color(255, 255, 255));
        this.button14.setText("Load data map");
        this.button14.setFocusable(false);
        this.button14.setFont(new Font("SansSerif", 1, 14));
        this.button14.setHorizontalTextPosition(0);
        this.button14.setVerticalTextPosition(3);
        this.button14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button14ActionPerformed(evt);
            }
        });
        this.jToolBar1.add((Component) this.button23);
        this.lblLayerName.setFont(new Font("SansSerif", 1, 14));
        this.lblLayerName.setHorizontalAlignment(0);
        this.jScrollPane2.setHorizontalScrollBarPolicy(31);
        this.button5.setBackground(new Color(0, 204, 0));
        this.button5.setForeground(new Color(255, 255, 255));
        this.button5.setText("Bg template");
        this.button5.setFocusable(false);
        this.button5.setFont(new Font("SansSerif", 1, 14));
        this.button5.setHorizontalTextPosition(0);
        this.button5.setVerticalTextPosition(3);
        this.button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button5ActionPerformed(evt);
            }
        });
        this.button7.setBackground(new Color(204, 0, 204));
        this.button7.setForeground(new Color(255, 255, 255));
        this.button7.setText("Bg item list");
        this.button7.setFocusable(false);
        this.button7.setFont(new Font("SansSerif", 1, 14));
        this.button7.setHorizontalTextPosition(0);
        this.button7.setVerticalTextPosition(3);
        this.button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button7ActionPerformed(evt);
            }
        });
        this.button9.setBackground(new Color(0, 204, 0));
        this.button9.setForeground(new Color(255, 255, 255));
        this.button9.setText("Npc template");
        this.button9.setFocusable(false);
        this.button9.setFont(new Font("SansSerif", 1, 14));
        this.button9.setHorizontalTextPosition(0);
        this.button9.setVerticalTextPosition(3);
        this.button9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button9ActionPerformed(evt);
            }
        });
        this.button10.setBackground(new Color(204, 0, 204));
        this.button10.setForeground(new Color(255, 255, 255));
        this.button10.setText("Npc list");
        this.button10.setFocusable(false);
        this.button10.setFont(new Font("SansSerif", 1, 14));
        this.button10.setHorizontalTextPosition(0);
        this.button10.setVerticalTextPosition(3);
        this.button10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button10ActionPerformed(evt);
            }
        });
        this.button11.setBackground(new Color(0, 204, 0));
        this.button11.setForeground(new Color(255, 255, 255));
        this.button11.setText("Mob template");
        this.button11.setFocusable(false);
        this.button11.setFont(new Font("SansSerif", 1, 14));
        this.button11.setHorizontalTextPosition(0);
        this.button11.setVerticalTextPosition(3);
        this.button11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button11ActionPerformed(evt);
            }
        });
        this.button12.setBackground(new Color(204, 0, 204));
        this.button12.setForeground(new Color(255, 255, 255));
        this.button12.setText("Mob list");
        this.button12.setFocusable(false);
        this.button12.setFont(new Font("SansSerif", 1, 14));
        this.button12.setHorizontalTextPosition(0);
        this.button12.setVerticalTextPosition(3);
        this.button12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button12ActionPerformed(evt);
            }
        });
        this.button13.setBackground(new Color(204, 0, 204));
        this.button13.setForeground(new Color(255, 255, 255));
        this.button13.setText("Waypoint list");
        this.button13.setFocusable(false);
        this.button13.setFont(new Font("SansSerif", 1, 14));
        this.button13.setHorizontalTextPosition(0);
        this.button13.setVerticalTextPosition(3);
        this.button13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button13ActionPerformed(evt);
            }
        });
        this.button20.setBackground(new Color(0, 204, 0));
        this.button20.setForeground(new Color(255, 255, 255));
        this.button20.setText("Effect template");
        this.button20.setFocusable(false);
        this.button20.setFont(new Font("SansSerif", 1, 14));
        this.button20.setHorizontalTextPosition(0);
        this.button20.setVerticalTextPosition(3);
        this.button20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button20ActionPerformed(evt);
            }
        });
        this.button21.setBackground(new Color(204, 0, 204));
        this.button21.setForeground(new Color(255, 255, 255));
        this.button21.setText("Effect list");
        this.button21.setFocusable(false);
        this.button21.setFont(new Font("SansSerif", 1, 14));
        this.button21.setHorizontalTextPosition(0);
        this.button21.setVerticalTextPosition(3);
        this.button21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button21ActionPerformed(evt);
            }
        });
        this.jPanel1.setLayout(new BorderLayout(10, 10));
        this.jLabel1.setFont(new Font("SansSerif", 1, 14));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("Expand");
        this.jPanel1.add(this.jLabel1, "Center");
        this.button15.setBackground(new Color(0, 204, 0));
        this.button15.setForeground(new Color(255, 255, 255));
        this.button15.setText("Top");
        this.button15.setFont(new Font("SansSerif", 1, 12));
        this.button15.setPreferredSize(new Dimension(21, 30));
        this.button15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button15ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component) this.button15, "First");
        this.button16.setBackground(new Color(0, 204, 0));
        this.button16.setForeground(new Color(255, 255, 255));
        this.button16.setText("Left");
        this.button16.setFont(new Font("SansSerif", 1, 12));
        this.button16.setPreferredSize(new Dimension(55, 55));
        this.button16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button16ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component) this.button16, "Before");
        this.button17.setBackground(new Color(0, 204, 0));
        this.button17.setForeground(new Color(255, 255, 255));
        this.button17.setText("Bottom");
        this.button17.setFont(new Font("SansSerif", 1, 12));
        this.button17.setPreferredSize(new Dimension(19, 30));
        this.button17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button17ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component) this.button17, "Last");
        this.button18.setBackground(new Color(0, 204, 0));
        this.button18.setForeground(new Color(255, 255, 255));
        this.button18.setText("Right");
        this.button18.setFont(new Font("SansSerif", 1, 12));
        this.button18.setPreferredSize(new Dimension(55, 27));
        this.button18.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button18ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component) this.button18, "After");
        this.button2.setBackground(new Color(204, 0, 204));
        this.button2.setForeground(new Color(255, 255, 255));
        this.button2.setText("Save map");
        this.button2.setFocusable(false);
        this.button2.setFont(new Font("SansSerif", 1, 14));
        this.button2.setHorizontalTextPosition(0);
        this.button2.setVerticalTextPosition(3);
        this.button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button2ActionPerformed(evt);
            }
        });
        this.button8.setBackground(new Color(204, 0, 204));
        this.button8.setForeground(new Color(255, 255, 255));
        this.button8.setText("Save bgitem");
        this.button8.setFocusable(false);
        this.button8.setFont(new Font("SansSerif", 1, 14));
        this.button8.setHorizontalTextPosition(0);
        this.button8.setVerticalTextPosition(3);
        this.button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button8ActionPerformed(evt);
            }
        });
        this.button22.setBackground(new Color(204, 0, 204));
        this.button22.setForeground(new Color(255, 255, 255));
        this.button22.setText("Save effect");
        this.button22.setFocusable(false);
        this.button22.setFont(new Font("SansSerif", 1, 14));
        this.button22.setHorizontalTextPosition(0);
        this.button22.setVerticalTextPosition(3);
        this.button22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button22ActionPerformed(evt);
            }
        });
        this.jCheckBox1.setFont(new Font("SansSerif", 1, 12));
        this.jCheckBox1.setText("Grid");
        this.jCheckBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.jCheckBox1ActionPerformed(evt);
            }
        });
        this.jCheckBox2.setFont(new Font("SansSerif", 1, 12));
        this.jCheckBox2.setText("Type tile");
        this.jCheckBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.jCheckBox2ActionPerformed(evt);
            }
        });
        this.jCheckBox4.setFont(new Font("SansSerif", 1, 12));
        this.jCheckBox4.setSelected(true);
        this.jCheckBox4.setText("Debug");
        this.jCheckBox4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.jCheckBox4ActionPerformed(evt);
            }
        });
        this.jCheckBox3.setFont(new Font("SansSerif", 1, 12));
        this.jCheckBox3.setText("3D");
        this.jCheckBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.jCheckBox3ActionPerformed(evt);
            }
        });
        this.jCheckBox5.setFont(new Font("SansSerif", 1, 12));
        this.jCheckBox5.setText("Double");
        this.jCheckBox5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.jCheckBox5ActionPerformed(evt);
            }
        });
        this.jCheckBox6.setFont(new Font("SansSerif", 1, 12));
        this.jCheckBox6.setText("Lock camera");
        this.jCheckBox6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.jCheckBox6ActionPerformed(evt);
            }
        });
        GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jCheckBox3, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent(this.jCheckBox2, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent(this.jCheckBox4, -1, -1, 32767).addComponent((Component) this.button2, -1, -1, 32767).addComponent((Component) this.button8, -1, -1, 32767).addComponent((Component) this.button22, -1, -1, 32767).addComponent(this.jCheckBox1, -1, -1, 32767).addComponent(this.jCheckBox5, -1, -1, 32767).addComponent(this.jCheckBox6, -1, -1, 32767)).addGap(0, 0, 0)));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent((Component) this.button2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent((Component) this.button8, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent((Component) this.button22, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCheckBox1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jCheckBox2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCheckBox3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCheckBox5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCheckBox4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCheckBox6).addGap(0, 0, 32767)));
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent((Component) this.button11, -1, 100, 32767).addComponent((Component) this.button9, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent((Component) this.button5, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent((Component) this.button20, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent((Component) this.button13, -2, 80, -2).addComponent((Component) this.button12, -1, 80, 32767).addComponent((Component) this.button7, -1, 111, 32767).addComponent((Component) this.button10, -1, -1, 32767).addComponent((Component) this.button21, -1, -1, 32767))).addComponent(this.jPanel1, -1, -1, 32767).addComponent(this.jPanel3, -1, -1, 32767)).addGap(0, 0, 0)));
        jPanel2Layout.linkSize(0, new Component[]{(Component) this.button10, (Component) this.button11, (Component) this.button12, (Component) this.button13, (Component) this.button5, (Component) this.button7, (Component) this.button9});
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent((Component) this.button5, -2, -1, -2).addComponent((Component) this.button7, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent((Component) this.button9, -2, -1, -2).addComponent((Component) this.button10, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent((Component) this.button11, -2, -1, -2).addComponent((Component) this.button12, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent((Component) this.button13, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent((Component) this.button20, -2, -1, -2).addComponent((Component) this.button21, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel1, -2, 128, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel3, -1, -1, 32767).addGap(71, 71, 71)));
        this.jScrollPane2.setViewportView(this.jPanel2);
        this.panelTileMap.setBackground(new Color(204, 153, 255));
        this.panelTileMap.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                DrawMapScr.this.panelTileMapMouseClicked(evt);
            }

            public void mouseEntered(MouseEvent evt) {
                DrawMapScr.this.panelTileMapMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                DrawMapScr.this.panelTileMapMouseExited(evt);
            }

            public void mousePressed(MouseEvent evt) {
                DrawMapScr.this.panelTileMapMousePressed(evt);
            }
        });
        GroupLayout panelTileMapLayout = new GroupLayout(this.panelTileMap);
        this.panelTileMap.setLayout(panelTileMapLayout);
        panelTileMapLayout.setHorizontalGroup(panelTileMapLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
        panelTileMapLayout.setVerticalGroup(panelTileMapLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 179, 32767));
        this.jPanel4.setLayout(new BorderLayout(20, 0));
        this.btnTile.setBackground(new Color(0, 153, 0));
        this.btnTile.setForeground(new Color(255, 255, 255));
        this.btnTile.setFont(new Font("SansSerif", 1, 14));
        this.btnTile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.btnTileActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component) this.btnTile, "Center");
        this.button25.setBackground(new Color(255, 0, 255));
        this.button25.setForeground(new Color(255, 255, 255));
        this.button25.setText("<");
        this.button25.setFont(new Font("SansSerif", 1, 14));
        this.button25.setPreferredSize(new Dimension(60, 29));
        this.button25.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button25ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component) this.button25, "Before");
        this.button26.setBackground(new Color(255, 0, 255));
        this.button26.setForeground(new Color(255, 255, 255));
        this.button26.setText(">");
        this.button26.setFont(new Font("SansSerif", 1, 14));
        this.button26.setPreferredSize(new Dimension(60, 29));
        this.button26.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DrawMapScr.this.button26ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component) this.button26, "After");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jToolBar1, -1, -1, 32767).addGroup(layout.createSequentialGroup().addComponent(this.panelScreen, -2, -1, -2).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(9, 9, 9).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.lblLayerName, -1, -1, 32767).addComponent(this.jScrollPane2, -1, 264, 32767).addComponent(this.jScrollPane1, -2, 0, 32767).addComponent(this.panelTileMap, -1, -1, 32767))).addGroup(layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jPanel4, -1, -1, 32767)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jToolBar1, -2, 40, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.panelScreen, -2, -1, -2).addGroup(layout.createSequentialGroup().addComponent(this.lblLayerName, -2, 26, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, 223, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane2, -2, 263, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.panelTileMap, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel4, -1, -1, 32767))).addGap(0, 0, 0)));
        pack();
    }

    private void choseLayer() {
        try {
            this.indexLayer = this.tblListLayer.getSelectedRow();
            if (this.indexLayer != -1) {
                boolean chk = false;
                try {
                    chk = ((Boolean) this.tblListLayer.getValueAt(this.indexLayer, 1)).booleanValue();
                } catch (Exception e) {
                    chk = ((Boolean) this.tblListLayer.getValueAt(this.indexLayer, 0)).booleanValue();
                }
                ((Layer) this.layers.get(this.indexLayer)).setShow(chk);
                switch (this.indexLayer) {
                    case 0:
                        this.lblLayerName.setText("Item background 1");
                        break;
                    case 2:
                        this.lblLayerName.setText("Item background 2");
                        break;
                    case 6:
                        this.lblLayerName.setText("Item background 3");
                        break;
                    case 7:
                        this.lblLayerName.setText("Item background 4");
                        break;
                    case 1:
                        this.lblLayerName.setText("Tile map");
                        break;
                    case 3:
                        this.lblLayerName.setText("Waypoint");
                        break;
                    case 4:
                        this.lblLayerName.setText("Npc");
                        break;
                    case 5:
                        this.lblLayerName.setText("Mob");
                        break;
                    case 8:
                        this.lblLayerName.setText("Effect");
                        break;
                }
            }
        } catch (Exception exception) {
        }
    }

    private void tblListLayerMouseClicked(MouseEvent evt) {
        choseLayer();
    }

    private void button1ActionPerformed(ActionEvent evt) {
        (new Thread(() -> {
            JFileChooser fileChooser = new JFileChooser("data/map/tile_map_data");
            if (fileChooser.showOpenDialog((Component) Taidz.I) == 0) {
                try {
                    File file = fileChooser.getSelectedFile();
                    readMapdata(file);
                    NotifyUtil.showMessageDialog((JFrame) Taidz.I, "map");
                } catch (Exception exception) {
                }
            }
        })).start();
    }

    private void readMapdata(File file) throws Exception {
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        int w = dis.readByte();
        int h = dis.readByte();
        int[][] tileMap = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                tileMap[i][j] = dis.readByte() - 1;
            }
        }
        dis.close();
        createNew(w, h);
        Layer layer = this.layers.get(1);
        ((TileMapLayer) layer).setTileMap(tileMap);
        ((TileMapLayer) layer).setTileSet(this.tileSet);
    }

    private void button2ActionPerformed(ActionEvent evt) {
        (new Thread(() -> {
            JFileChooser fileChooser = new JFileChooser("data\\map\\tile_map_data");
            if (fileChooser.showSaveDialog((Component) Taidz.I) == 0) {
                try {
                    Layer layer = this.layers.get(1);
                    if (layer == null) {
                        return;
                    }
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
                    int[][] tileMap = ((TileMapLayer) layer).getTileMap();
                    dos.writeByte((tileMap[0]).length);
                    dos.writeByte(tileMap.length);
                    for (int i = 0; i < tileMap.length; i++) {
                        for (int j = 0; j < (tileMap[i]).length; j++) {
                            dos.writeByte(tileMap[i][j] + 1);
                        }
                    }
                    dos.flush();
                    dos.close();
                    NotifyUtil.showMessageDialog((JFrame) Taidz.I, "ok");
                } catch (Exception exception) {
                }
            }
        })).start();
    }

    private void button5ActionPerformed(ActionEvent evt) {
        if (this.tableBGITEM == null) {
            this.tableBGITEM = new BGItemTable(this);
        }
        this.tableBGITEM.setVisible(true);
    }

    private void button6ActionPerformed(ActionEvent evt) {
        (new Thread(() -> {
            JFileChooser fileChooser = new JFileChooser("data/map/item_bg_map_data");
            if (fileChooser.showOpenDialog((Component) Taidz.I) == 0) {
                try {
                    readDataBgItem(fileChooser.getSelectedFile());
                    NotifyUtil.showMessageDialog((JFrame) Taidz.I, "background item");
                } catch (Exception exception) {
                }
            }
        })).start();
    }

    private void buttonAddTilePngActionPerformed(ActionEvent evt) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final JFileChooser fileChooser = new JFileChooser("data/map/item_bg_map_data");
                if (fileChooser.showOpenDialog((Component) Taidz.I) == JFileChooser.APPROVE_OPTION) {
                    final File pngFile = fileChooser.getSelectedFile();

                    // Chạy trên EDT vì là UI
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // Tạo TileTypeEditor
                                new TileTypeEditor(pngFile, new TileTypeEditor.OnConfirmListener() {
                                    @Override
                                    public void onConfirm(int[][] tileTypes) {
                                        // Chọn nơi lưu tile mới
                                        JFileChooser saveChooser = new JFileChooser("data/tile/");
                                        saveChooser.setDialogTitle("Lưu tile mới vào...");
                                        saveChooser.setSelectedFile(new File("data/tile/new_tile"));

                                        if (saveChooser.showSaveDialog((Component) Taidz.I) == JFileChooser.APPROVE_OPTION) {
                                            File saveFile = saveChooser.getSelectedFile();
                                            try {
                                                // Lưu ảnh PNG
                                                BufferedImage originalImg = ImageIO.read(pngFile);
                                                String fileName = saveFile.getName();
                                                if (fileName.endsWith(".png")) {
                                                    fileName = fileName.substring(0, fileName.length() - 4);
                                                    saveFile = new File(saveFile.getParentFile(), fileName);
                                                }
                                                ImageIO.write(originalImg, "png", saveFile);

                                                // Tạo TilesetType
                                                int tileId = Integer.parseInt(fileName);
                                                TilesetType newTileSet = new TilesetType();
                                                newTileSet.id = tileId;

                                                int tileIndex = 1;
                                                for (int y = 0; y < tileTypes.length; y++) {
                                                    for (int x = 0; x < tileTypes[0].length; x++) {
                                                        int mask = tileTypes[y][x]; // bitmask thực sự, ví dụ 2|4|8
                                                        List<Integer> types = new ArrayList<>();
                                                        for (int bit = 0; bit < 32; bit++) {
                                                            int value = 1 << bit;
                                                            if ((mask & value) != 0) {
                                                                types.add(value);
                                                            }
                                                        }
                                                        // Nếu mask = 0 thì types vẫn rỗng []
                                                        newTileSet.tileType.put(tileIndex, types);

                                                        // ---- LOG để kiểm tra ----
                                                        System.out.println("Tile " + tileIndex + " type: " + types);

                                                        tileIndex++;
                                                    }
                                                }

                                                // Gán vào Manager
                                                List<TilesetType> tileSetList = Manager.gI().getTile_set_type();
                                                while (tileSetList.size() < tileId) {
                                                    tileSetList.add(null);
                                                }
                                                tileSetList.set(tileId - 1, newTileSet);

                                                // Lưu lại file tile_set_info
                                                saveTileSetInfo();

                                                NotifyUtil.showMessageDialog((JFrame) Taidz.I,
                                                        "✅ Đã lưu tile và tileType thành công!");
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                                NotifyUtil.showMessageDialog((JFrame) Taidz.I,
                                                        "❌ Lỗi khi lưu file tile!");
                                            }
                                        }
                                    }
                                });
                            } catch (IOException ex) {
                                // Bắt IOException từ constructor TileTypeEditor
                                ex.printStackTrace();
                                NotifyUtil.showMessageDialog((JFrame) Taidz.I,
                                        "❌ Lỗi khi mở TileTypeEditor!");
                            }
                        }
                    });
                }
            }
        }).start();
    }

//    private void saveTileSetInfo() {
//        try {
//            File file = new File("data/girlkun/map/tile_set_info");
//            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
//
//            List<TilesetType> tileSetList = Manager.gI().getTile_set_type();
//            int nTileset = tileSetList.size();
//            if (nTileset > 255) {
//                throw new IllegalStateException("Số lượng tileset vượt quá giới hạn (255).");
//            }
//
//            dos.writeByte(nTileset); // ✅ Ghi số lượng tileset
//
//            for (TilesetType t : tileSetList) {
//                Map<Integer, List<Integer>> tileTypeMap = t.tileType;
//
//                dos.writeByte(tileTypeMap.size()); // số tileIndex, nên = TOTAL_TILE, kể cả rỗng
//
//                for (int tileIndex = 1; tileIndex <= tileTypeMap.size(); tileIndex++) {
//                    List<Integer> types = tileTypeMap.get(tileIndex);
//                    if (types == null) {
//                        types = new ArrayList<>(); // đảm bảo tileIndex rỗng cũng lưu
//                    }
//                    dos.writeByte(types.size()); // số loại type trong tile
//
//                    for (int type : types) {
//                        dos.writeInt(type);
//                    }
//                }
//            }
//
//            dos.flush();
//            dos.close();
//            System.out.println("✅ Đã lưu tile_set_info với " + nTileset + " tileset.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("❌ Lỗi khi lưu tile_set_info!");
//        }
//    }
    private void saveTileSetInfo() {
    try {
        File file = new File("data/girlkun/map/tile_set_info");
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));

        List<TilesetType> tileSetList = Manager.gI().getTile_set_type();
        int nTileset = tileSetList.size();
        if (nTileset > 255) {
            throw new IllegalStateException("Số lượng tileset vượt quá giới hạn (255).");
        }

        // Ghi số lượng tileset
        dos.writeByte(nTileset);

        for (int i = 0; i < nTileset; i++) {
            TilesetType t = tileSetList.get(i);
            if (t == null) {
                t = new TilesetType();
                t.id = i + 1;
                t.tileType = new HashMap<>();
            }

            // Chuyển map tileIndex -> list tileType thành map tileType -> list tileIndex
            Map<Integer, List<Integer>> typeToIndices = new HashMap<>();
            for (Map.Entry<Integer, List<Integer>> entry : t.tileType.entrySet()) {
                int tileIndex = entry.getKey();
                List<Integer> types = entry.getValue();
                if (types != null) {
                    for (int type : types) {
                        typeToIndices.computeIfAbsent(type, k -> new ArrayList<>()).add(tileIndex);
                    }
                }
            }

            // Số nhóm tileType
            dos.writeByte(typeToIndices.size());

            // Ghi từng nhóm tileType
            for (Map.Entry<Integer, List<Integer>> e : typeToIndices.entrySet()) {
                int tileType = e.getKey();
                List<Integer> indices = e.getValue();
                dos.writeInt(tileType);           // tileType
                dos.writeByte(indices.size());    // số tileIndex
                for (int idx : indices) {
                    dos.writeByte(idx);           // tileIndex
                }
            }
        }

        dos.flush();
        dos.close();
        System.out.println("✅ Đã lưu tile_set_info với " + nTileset + " tileset (merge + đè ID trùng).");

    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("❌ Lỗi khi lưu tile_set_info!");
    }
}


    private void readDataBgItem(File file) throws Exception {
        this.bgItemL1.clear();
        this.bgItemL2.clear();
        this.bgItemL3.clear();
        this.bgItemL4.clear();
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        int n = dis.readShort();
        for (int i = 0; i < n; i++) {
            int id = dis.readShort();
            int x = dis.readShort();
            int y = dis.readShort();
            BgItemTemplate temp = Manager.gI().getBgItemTemplates().get(id);
            switch (temp.getLayer()) {
                case 1:
                    this.bgItemL1.add(new BgItemMap(temp, x * 24, y * 24));
                    break;
                case 2:
                    this.bgItemL2.add(new BgItemMap(temp, x * 24, y * 24));
                    break;
                case 3:
                    this.bgItemL3.add(new BgItemMap(temp, x * 24, y * 24));
                    break;
                case 4:
                    this.bgItemL4.add(new BgItemMap(temp, x * 24, y * 24));
                    break;
            }
        }
        if (this.bGItemList != null) {
            this.bGItemList.fillToTable();
        }
    }

    private void button7ActionPerformed(ActionEvent evt) {
        if (this.bGItemList == null) {
            this.bGItemList = new BGItemList(this);
        }
        this.bGItemList.fillToTable();
        this.bGItemList.setVisible(true);
    }

    private void jCheckBox1ActionPerformed(ActionEvent evt) {
        TileMapLayer.drawGrid = this.jCheckBox1.isSelected();
    }

    private void jCheckBox2ActionPerformed(ActionEvent evt) {
        TileMapLayer.drawTileType = this.jCheckBox2.isSelected();
    }

    private void button8ActionPerformed(ActionEvent evt) {
        (new Thread(() -> {
            JFileChooser fileChooser = new JFileChooser("data\\map\\item_bg_map_data");
            if (fileChooser.showSaveDialog((Component) Taidz.I) == 0) {
                try {
                    if (this.bgItemL1 == null || this.bgItemL2 == null || this.bgItemL3 == null || this.bgItemL4 == null) {
                        return;
                    }
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
                    int n = this.bgItemL1.size() + this.bgItemL2.size() + this.bgItemL3.size() + this.bgItemL4.size();
                    dos.writeShort(n);
                    for (BgItemMap bg : this.bgItemL1) {
                        dos.writeShort(bg.getTemp().getId());
                        dos.writeShort(bg.getX() / 24);
                        dos.writeShort(bg.getY() / 24);
                    }
                    for (BgItemMap bg : this.bgItemL2) {
                        dos.writeShort(bg.getTemp().getId());
                        dos.writeShort(bg.getX() / 24);
                        dos.writeShort(bg.getY() / 24);
                    }
                    for (BgItemMap bg : this.bgItemL3) {
                        dos.writeShort(bg.getTemp().getId());
                        dos.writeShort(bg.getX() / 24);
                        dos.writeShort(bg.getY() / 24);
                    }
                    for (BgItemMap bg : this.bgItemL4) {
                        dos.writeShort(bg.getTemp().getId());
                        dos.writeShort(bg.getX() / 24);
                        dos.writeShort(bg.getY() / 24);
                    }
                    NotifyUtil.showMessageDialog((JFrame) Taidz.I, "Lbackground item");
                } catch (Exception exception) {
                }
            }
        })).start();
    }

    private void button9ActionPerformed(ActionEvent evt) {
        if (this.npcTable == null) {
            this.npcTable = new NpcTable(this);
        }
        this.npcTable.setVisible(true);
    }

    private void button10ActionPerformed(ActionEvent evt) {
        if (this.npcList == null) {
            this.npcList = new NpcList(this);
        }
        this.npcList.fillToTable();
        this.npcList.setVisible(true);
    }

    private void button11ActionPerformed(ActionEvent evt) {
        if (this.mobTable == null) {
            this.mobTable = new MobTable(this);
        }
        this.mobTable.setVisible(true);
    }

    private void button12ActionPerformed(ActionEvent evt) {
        if (this.mobList == null) {
            this.mobList = new MobList(this);
        }
        this.mobList.fillToTable();
        this.mobList.setVisible(true);
    }

    private void button13ActionPerformed(ActionEvent evt) {
        if (this.waypointList == null) {
            this.waypointList = new WaypointList(this);
        }
        this.waypointList.fillToTable();
        this.waypointList.setVisible(true);
    }

    private void button14ActionPerformed(ActionEvent evt) {
        try {
            int mapId = Integer.parseInt(NotifyUtil.showInputDialog((JFrame) Taidz.I, "Map id"));
            if (this.waypointList == null) {
                this.waypointList = new WaypointList(this);
            }
            if (this.mobList == null) {
                this.mobList = new MobList(this);
            }
            if (this.npcList == null) {
                this.npcList = new NpcList(this);
            }
            if (this.effectList == null) {
                this.effectList = new EffectList(this);
            }
            this.effectMaps.clear();
            this.bgItemL1.clear();
            this.bgItemL2.clear();
            this.bgItemL3.clear();
            this.bgItemL4.clear();
            this.npcs.clear();
            this.mobs.clear();
            this.waypoints.clear();
            DatabaseResultSet rs = DatabaseManagers.executeQuery("select * from map_template where id = ?", new Object[]{Integer.valueOf(mapId)});
            if (rs.first()) {
                JSONValue jv = new JSONValue();
                JSONArray dataArray = (JSONArray) JSONValue.parse(rs.getString("data"));
                int tileMap = Integer.parseInt(String.valueOf(dataArray.get(3)));
                readTileSet(new File("data/tile/" + tileMap));
                readMapdata(new File("data/map/tile_map_data/" + mapId));
                readDataBgItem(new File("data/map/item_bg_map_data/" + mapId));
                this.mapName = rs.getString("name");
                this.mapId = mapId;
                this.waypointList.readTextWaypoint(rs.getString("waypoints"));
                this.mobList.readTextMob(rs.getString("mobs"));
                this.npcList.readDataNpc(rs.getString("npcs"));
                DataInputStream dis = new DataInputStream(new FileInputStream("data/map/eff_map/" + mapId));
                int n = dis.readShort();
                for (int i = 0; i < n; i++) {
                    String key = dis.readUTF();
                    String value = dis.readUTF();
                    System.out.println(key + "- " + value);
                    readKeyValuesEff(key, value, mapId);
                }
                this.effectList.fillToTable();
            }
            NotifyUtil.showMessageDialog((JFrame) Taidz.I, "Loadmap " + this.mapName);
        } catch (Exception e) {
            System.out.println("Clkhi full map");
            e.printStackTrace();
        }
    }

    private void readKeyValuesEff(String key, String value, int mapId) {
        if (key.equals("eff")) {
            int loop, loopCount;
            String[] array = value.split("\\.");
            int id = Integer.parseInt(array[0]);
            int layer = Integer.parseInt(array[1]);
            int x = Integer.parseInt(array[2]);
            int y = Integer.parseInt(array[3]);
            if (array.length <= 4) {
                loop = -1;
                loopCount = 1;
            } else {
                loop = Integer.parseInt(array[4]);
                loopCount = Integer.parseInt(array[5]);
            }
            try {
                EffectMap eff = new EffectMap(Manager.gI().getEffectTemplateById(id), x, y, layer, loop, loopCount);
                if (array.length > 6) {
                    System.out.println(mapId + " - " + key + " - " + value);
                    eff.setType(Integer.parseInt(array[6]));
                    if (array.length > 7) {
                        eff.setIndexFrom(Integer.parseInt(array[7]));
                        eff.setIndexTo(Integer.parseInt(array[8]));
                    }
                }
                this.effectMaps.add(eff);
            } catch (Exception exception) {
            }
        } else if (key.equals("beff")) {

        }
    }

    private void button15ActionPerformed(ActionEvent evt) {
        if (this.layers.get(1) != null) {
            try {
                int n = Integer.parseInt(NotifyUtil.showInputDialog((JFrame) Taidz.I, "Row"));
                int[][] tileMap = ((TileMapLayer) this.layers.get(1)).getTileMap();
                int w = (tileMap[0]).length;
                int h = tileMap.length + n;
                int[][] newTileMap = new int[h][w];
                int i;
                for (i = 0; i < n; i++) {
                    Arrays.fill(newTileMap[i], -1);
                }
                for (i = n; i < newTileMap.length; i++) {
                    for (int j = 0; j < (newTileMap[i]).length; j++) {
                        newTileMap[i][j] = tileMap[i - n][j];
                    }
                }
                for (Layer layer : this.layers) {
                    if (layer != null) {
                        layer.setSizeImage(w, h, n, 0);
                    }
                }
                this.mapWidth = w * 24;
                this.mapHeight = h * 24;
                this.layerPaintScreen = new BufferedImage(w * 24, h * 24, 2);
                ((TileMapLayer) this.layers.get(1)).setTileMap(newTileMap);
                this.camera.camY -= 24 * n;
                if (this.npcList != null) {
                    this.npcList.fillToTable();
                }
                if (this.mobList != null) {
                    this.mobList.fillToTable();
                }
                if (this.waypointList != null) {
                    this.waypointList.fillToTable();
                }
                if (this.effectList != null) {
                    this.effectList.fillToTable();
                }
                if (this.bGItemList != null) {
                    this.bGItemList.fillToTable();
                }
            } catch (Exception exception) {
            }
        }
    }

    private void button17ActionPerformed(ActionEvent evt) {
        if (this.layers.get(1) != null) {
            try {
                int n = Integer.parseInt(NotifyUtil.showInputDialog((JFrame) Taidz.I, "Row"));
                int[][] tileMap = ((TileMapLayer) this.layers.get(1)).getTileMap();
                int w = (tileMap[0]).length;
                int h = tileMap.length + n;
                int[][] newTileMap = new int[h][w];
                int i;
                for (i = newTileMap.length - n; i < newTileMap.length; i++) {
                    Arrays.fill(newTileMap[i], -1);
                }
                for (i = 0; i < tileMap.length; i++) {
                    for (int j = 0; j < (newTileMap[i]).length; j++) {
                        newTileMap[i][j] = tileMap[i][j];
                    }
                }
                for (Layer layer : this.layers) {
                    if (layer != null) {
                        layer.setSizeImage(w, h, n, -1);
                    }
                }
                this.mapWidth = w * 24;
                this.mapHeight = h * 24;
                this.layerPaintScreen = new BufferedImage(w * 24, h * 24, 2);
                ((TileMapLayer) this.layers.get(1)).setTileMap(newTileMap);
                if (this.npcList != null) {
                    this.npcList.fillToTable();
                }
                if (this.mobList != null) {
                    this.mobList.fillToTable();
                }
                if (this.waypointList != null) {
                    this.waypointList.fillToTable();
                }
                if (this.effectList != null) {
                    this.effectList.fillToTable();
                }
                if (this.bGItemList != null) {
                    this.bGItemList.fillToTable();
                }
            } catch (Exception exception) {
            }
        }
    }

    private void button16ActionPerformed(ActionEvent evt) {
        if (this.layers.get(1) != null) {
            try {
                int n = Integer.parseInt(NotifyUtil.showInputDialog((JFrame) Taidz.I, "Row"));
                int[][] tileMap = ((TileMapLayer) this.layers.get(1)).getTileMap();
                int w = (tileMap[0]).length + n;
                int h = tileMap.length;
                int[][] newTileMap = new int[h][w];
                int i;
                for (i = 0; i < newTileMap.length; i++) {
                    for (int j = 0; j < n; j++) {
                        newTileMap[i][j] = -1;
                    }
                }
                for (i = 0; i < newTileMap.length; i++) {
                    for (int j = n; j < (newTileMap[i]).length; j++) {
                        newTileMap[i][j] = tileMap[i][j - n];
                    }
                }
                for (Layer layer : this.layers) {
                    if (layer != null) {
                        layer.setSizeImage(w, h, n, 1);
                    }
                }
                this.mapWidth = w * 24;
                this.mapHeight = h * 24;
                this.layerPaintScreen = new BufferedImage(w * 24, h * 24, 2);
                ((TileMapLayer) this.layers.get(1)).setTileMap(newTileMap);
                this.camera.camX -= 24 * n;
                if (this.npcList != null) {
                    this.npcList.fillToTable();
                }
                if (this.mobList != null) {
                    this.mobList.fillToTable();
                }
                if (this.waypointList != null) {
                    this.waypointList.fillToTable();
                }
                if (this.effectList != null) {
                    this.effectList.fillToTable();
                }
                if (this.bGItemList != null) {
                    this.bGItemList.fillToTable();
                }
            } catch (Exception exception) {
            }
        }
    }

    private void button18ActionPerformed(ActionEvent evt) {
        if (this.layers.get(1) != null) {
            try {
                int n = Integer.parseInt(NotifyUtil.showInputDialog((JFrame) Taidz.I, "Row"));
                int[][] tileMap = ((TileMapLayer) this.layers.get(1)).getTileMap();
                int w = (tileMap[0]).length + n;
                int h = tileMap.length;
                int[][] newTileMap = new int[h][w];
                int i;
                for (i = 0; i < newTileMap.length; i++) {
                    for (int j = w - n; j < w; j++) {
                        newTileMap[i][j] = -1;
                    }
                }
                for (i = 0; i < newTileMap.length; i++) {
                    for (int j = 0; j < w - n; j++) {
                        newTileMap[i][j] = tileMap[i][j];
                    }
                }
                for (Layer layer : this.layers) {
                    if (layer != null) {
                        layer.setSizeImage(w, h, n, -1);
                    }
                }
                this.mapWidth = w * 24;
                this.mapHeight = h * 24;
                this.layerPaintScreen = new BufferedImage(w * 24, h * 24, 2);
                ((TileMapLayer) this.layers.get(1)).setTileMap(newTileMap);
                if (this.npcList != null) {
                    this.npcList.fillToTable();
                }
                if (this.mobList != null) {
                    this.mobList.fillToTable();
                }
                if (this.waypointList != null) {
                    this.waypointList.fillToTable();
                }
                if (this.effectList != null) {
                    this.effectList.fillToTable();
                }
                if (this.bGItemList != null) {
                    this.bGItemList.fillToTable();
                }
            } catch (Exception exception) {
            }
        }
    }

    private void button20ActionPerformed(ActionEvent evt) {
        if (this.effectTable == null) {
            this.effectTable = new EffectTable(this);
        }
        this.effectTable.setVisible(true);
    }

    private void button21ActionPerformed(ActionEvent evt) {
        if (this.effectList == null) {
            this.effectList = new EffectList(this);
        }
        this.effectList.fillToTable();
        this.effectList.setVisible(true);
    }

    private void button22ActionPerformed(ActionEvent evt) {
        (new Thread(() -> {
            JFileChooser fileChooser = new JFileChooser("data\\map\\eff_map");
            if (fileChooser.showSaveDialog((Component) Taidz.I) == 0) {
                try {
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
                    dos.writeShort(this.effectMaps.size() + this.subEffectMaps.size());
                    for (EffectMap eff : this.effectMaps) {
                        dos.writeUTF("eff");
                        String data = eff.getTemp().getId() + "." + eff.getLayer() + "." + eff.getX() + "." + eff.getY() + "." + eff.getLoop() + "." + eff.getLoopCount();
                        dos.writeUTF(data);
                    }
                    for (SubEffectMap eff : this.subEffectMaps) {
                        dos.writeUTF("beff");
                        dos.writeUTF(eff.getTypeEff() + "");
                    }
                    dos.close();
                    NotifyUtil.showMessageDialog((JFrame) Taidz.I, "Leffect map");
                } catch (Exception exception) {
                }
            }
        })).start();
    }

    private void button23ActionPerformed(ActionEvent evt) {
        for (int mapId = 0; mapId < 166; mapId++) {
            try {
                if (this.waypointList == null) {
                    this.waypointList = new WaypointList(this);
                }
                if (this.mobList == null) {
                    this.mobList = new MobList(this);
                }
                if (this.npcList == null) {
                    this.npcList = new NpcList(this);
                }
                if (this.effectList == null) {
                    this.effectList = new EffectList(this);
                }
                this.effectMaps.clear();
                this.bgItemL1.clear();
                this.bgItemL2.clear();
                this.bgItemL3.clear();
                this.bgItemL4.clear();
                this.npcs.clear();
                this.mobs.clear();
                this.waypoints.clear();
                DatabaseResultSet rs = DatabaseManagers.executeQuery("select * from map_template where id = ?", new Object[]{Integer.valueOf(mapId)});
                if (rs.first()) {
                    JSONValue jv = new JSONValue();
                    JSONArray dataArray = (JSONArray) JSONValue.parse(rs.getString("data"));
                    int tileMap = Integer.parseInt(String.valueOf(dataArray.get(3)));
                    readMapdata(new File("data/map/tile_map_data/" + mapId));
                    readDataBgItem(new File("data/map/item_bg_map_data/" + mapId));
                    this.waypointList.readTextWaypoint(rs.getString("waypoints"));
                    this.mobList.readTextMob(rs.getString("mobs"));
                    this.npcList.readDataNpc(rs.getString("npcs"));
                    DataInputStream dis = new DataInputStream(new FileInputStream("data/map/eff_map/" + mapId));
                    int n = dis.readShort();
                    for (int i = 0; i < n; i++) {
                        String key = dis.readUTF();
                        String value = dis.readUTF();
                        readKeyValuesEff(key, value, mapId);
                    }
                    this.effectList.fillToTable();
                }
                this.mapName = rs.getString("name");
                this.mapId = mapId;
                Thread.sleep(10L);
            } catch (Exception exception) {
            }
        }
    }

    private void button24ActionPerformed(ActionEvent evt) {
        (new Thread(() -> {
            JFileChooser fileChooser = new JFileChooser("data/map/eff_map");
            if (fileChooser.showOpenDialog((Component) Taidz.I) == 0) {
                try {
                    DataInputStream dis = new DataInputStream(new FileInputStream(fileChooser.getSelectedFile()));
                    this.effectMaps.clear();
                    this.subEffectMaps.clear();
                    int n = dis.readShort();
                    for (int i = 0; i < n; i++) {
                        String key = dis.readUTF();
                        String value = dis.readUTF();
                        System.out.println(key + "   " + value);
                        readKeyValuesEff(key, value, this.mapId);
                    }
                    NotifyUtil.showMessageDialog((JFrame) Taidz.I, "effect map");
                } catch (Exception exception) {
                }
            }
        })).start();
    }

    private void jCheckBox3ActionPerformed(ActionEvent evt) {
        this.is3D = this.jCheckBox3.isSelected();
    }

    private void jCheckBox4ActionPerformed(ActionEvent evt) {
        this.isDebugMouse = this.jCheckBox4.isSelected();
    }

    private void jCheckBox5ActionPerformed(ActionEvent evt) {
        this.isMapDouble = this.jCheckBox5.isSelected();
    }

    private void panelTileMapMousePressed(MouseEvent evt) {
        if (this.tile != null && this.tile.length != 0) {
            int x = evt.getX() / 24;
            int y = (evt.getY() - this.camTileSet.camY) / 24;
            int chose = x + y * 11;
            if (chose < this.tile.length) {
                this.tileChose = this.tile[chose];
                if (chose != this.tile.length - 1) {
                    this.indexTileChose = chose;
                } else {
                    this.indexTileChose = -1;
                }
            } else {
                this.tileChose = null;
                this.indexTileChose = -1;
            }
            setTileChose(new BufferedImage[][]{{this.tileChose},}, new int[][]{{this.indexTileChose}});
        }
    }

    private void panelTileMapMouseEntered(MouseEvent evt) {
        setCursor(new Cursor(12));
    }

    private void panelTileMapMouseClicked(MouseEvent evt) {
        if (this.tile != null && this.tile.length != 0) {
            int x = evt.getX() / 24;
            int y = (evt.getY() - this.camTileSet.camY) / 24;
            int chose = x + y * 11;
            if (chose < this.tile.length) {
                this.tileChose = this.tile[chose];
                if (chose != this.tile.length - 1) {
                    this.indexTileChose = chose;
                } else {
                    this.indexTileChose = -1;
                }
            } else {
                this.tileChose = null;
                this.indexTileChose = -1;
            }
            setTileChose(new BufferedImage[][]{{this.tileChose},}, new int[][]{{this.indexTileChose}});
        }
    }

    private void panelTileMapMouseExited(MouseEvent evt) {
        if (!this.moveCamera) {
            defaultCursor();
        } else {
            setCursor(new Cursor(13));
        }
        this.xTile = -1;
        this.yTile = -1;
    }

    private void tblListLayerMousePressed(MouseEvent evt) {
        choseLayer();
    }

    private void tblListLayerMouseReleased(MouseEvent evt) {
        choseLayer();
    }

    private void button25ActionPerformed(ActionEvent evt) {
        int tileId = this.tileId - 1;
        if (tileId < 1) {
            tileId = Manager.gI().getTile_set_type().size();
        }
        File file = new File("data/tile/" + tileId);
        if (file.exists()) {
            readTileSet(file);
        }
    }

//    private void button26ActionPerformed(ActionEvent evt) {
//        int tileId = this.tileId + 1;
//        if (tileId > 31) {
//            tileId = 1;
//        }
//        
//        File file = new File("data/tile/" + tileId);
//        if (file.exists()) {
//            readTileSet(file);
//        }
//    }
    private void button26ActionPerformed(ActionEvent evt) {
        int tileId = this.tileId + 1;

        // ✅ DEBUG: Log tileId hiện tại và tileId sẽ thử load
        System.out.println("➡️ Next tileId: " + tileId);

        if (tileId > Manager.gI().getTile_set_type().size()) {
            tileId = 1;
            System.out.println("🔁 Quay lại tileId = 1 do vượt quá giới hạn.");
        }

        File file = new File("data/tile/" + tileId);
        System.out.println("📂 Kiểm tra file tồn tại: " + file.getAbsolutePath() + " | exists = " + file.exists());

        if (file.exists()) {
            readTileSet(file);
        } else {
            System.out.println("❌ File không tồn tại, không thể load tileId = " + tileId);
        }
    }

    private void btnTileActionPerformed(ActionEvent evt) {
        (new Thread(() -> {
            JFileChooser fileChooser = new JFileChooser("data/tile");
            if (fileChooser.showOpenDialog(this) == 0) {
                File fileChose = fileChooser.getSelectedFile();
                readTileSet(fileChose);
            }
        })).start();
    }

    private void jCheckBox6ActionPerformed(ActionEvent evt) {
        this.lockCamera = this.jCheckBox6.isSelected();
        lockCamera();
    }

    private void lockCamera() {
        if (!this.lockCamera) {
            return;
        }
        if (this.camera.camX + this.mapWidth < this.camera.width - 15) {
            this.camera.camX = this.camera.width - 15 - this.mapWidth;
        }
        if (this.camera.camY + this.mapHeight < this.camera.height - 17) {
            this.camera.camY = this.camera.height - 17 - this.mapHeight;
        }
        if (this.camera.camX > 15) {
            this.camera.camX = 15;
        }
        if (this.camera.camY > 17) {
            this.camera.camY = 17;
        }
    }

    private void moveCamera(MouseEvent e) {
        if (this.moveCamera) {
            int dx = this.mouseAxis[0] - this.anchorCamera[0];
            int dy = this.mouseAxis[1] - this.anchorCamera[1];
            this.camera.camX += dx;
            this.camera.camY += dy;
            this.anchorCamera[0] = this.anchorCamera[0] + dx;
            this.anchorCamera[1] = this.anchorCamera[1] + dy;
            lockCamera();
            if (this.tamDem != null) {
                if (e.getX() < 0) {
                    moveMouse(new Point((MouseInfo.getPointerInfo().getLocation()).x + this.panelScreen.getWidth(), (MouseInfo.getPointerInfo().getLocation()).y));
                    this.anchorCamera[0] = this.anchorCamera[0] + this.panelScreen.getWidth();
                }
                if (e.getX() > this.tamDem.getWidth()) {
                    moveMouse(new Point((MouseInfo.getPointerInfo().getLocation()).x - this.panelScreen.getWidth(), (MouseInfo.getPointerInfo().getLocation()).y));
                    this.anchorCamera[0] = this.anchorCamera[0] - this.panelScreen.getWidth();
                }
                if (e.getY() < 0) {
                    moveMouse(new Point((MouseInfo.getPointerInfo().getLocation()).x, (MouseInfo.getPointerInfo().getLocation()).y + this.panelScreen.getHeight()));
                    this.anchorCamera[1] = this.anchorCamera[1] + this.panelScreen.getHeight();
                }
                if (e.getY() > this.tamDem.getHeight()) {
                    moveMouse(new Point((MouseInfo.getPointerInfo().getLocation()).x, (MouseInfo.getPointerInfo().getLocation()).y - this.panelScreen.getHeight()));
                    this.anchorCamera[1] = this.anchorCamera[1] - this.panelScreen.getHeight();
                }
            }
        }
    }

    private void putItem(int x, int y) {
        if (this.indexLayer != -1) {
            Layer layer = this.layers.get(this.indexLayer);
            switch (this.indexLayer) {
                case 5:
                    ((MobMapLayer) layer).put(this.mobChose, x, y);
                    if (this.mobList != null) {
                        this.mobList.fillToTable();
                    }
                    break;
                case 3:
                    ((WaypointMapLayer) layer).put(x, y);
                    if (this.waypointList != null) {
                        this.waypointList.fillToTable();
                    }
                    break;
                case 4:
                    ((NpcMapLayer) layer).put(this.npcChoose, x, y);
                    if (this.npcList != null) {
                        this.npcList.fillToTable();
                    }
                    break;
                case 0:
                    ((BgItemLayer) layer).putBgItem(this.bgItemChose1, x, y);
                    break;
                case 2:
                    ((BgItemLayer) layer).putBgItem(this.bgItemChose2, x, y);
                    break;
                case 6:
                    ((BgItemLayer) layer).putBgItem(this.bgItemChose3, x, y);
                    break;
                case 7:
                    ((BgItemLayer) layer).putBgItem(this.bgItemChose4, x, y);
                    break;
                case 1:
                    ((TileMapLayer) layer).putTileMap(this.tileSet, this.indexTilesChose, x, y);
                    break;
                case 8:
                    ((EffectMapLayer) layer).put(this.effTempChose, x, y);
                    if (this.effectList != null) {
                        this.effectList.fillToTable();
                    }
                    break;
            }
            switch (this.indexLayer) {
                case 0:
                case 2:
                case 6:
                case 7:
                    if (this.bGItemList != null) {
                        this.bGItemList.fillToTable();
                    }
                    break;
            }
        }
    }

    private void mousePress(MouseEvent e) {
        if (e.getButton() == 1) {
            this.holdLeftMouse = true;
            putItem(e.getX() - this.camera.camX, e.getY() - this.camera.camY);
        } else if (e.getButton() == 2) {
            this.anchorCopy[0] = e.getX();
            this.anchorCopy[1] = e.getY();
            this.copyTilesChose = true;
        } else if (e.getButton() == 3) {
            this.anchorCamera[0] = e.getX();
            this.anchorCamera[1] = e.getY();
            this.moveCamera = true;
            setCursor(new Cursor(13));
        }
    }

    private void mouseRelease(MouseEvent e) {
        if (e.getButton() == 1) {
            this.holdLeftMouse = false;
        } else if (e.getButton() == 2) {
            this.copyTilesChose = false;
            if (!this.holdR) {
                try {
                    Layer layer = this.layers.get(1);
                    if (layer != null) {
                        ((TileMapLayer) layer).copyTile(this.anchorCopy[0], this.anchorCopy[1], this.mouseAxis[0], this.mouseAxis[1]);
                    }
                } catch (Exception exception) {
                }
                this.indexLayer = 1;
                this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
            }
        } else if (e.getButton() == 3) {
            this.moveCamera = false;
            setCursor(new Cursor(0));
        }
    }

    private void setupKeyMouseAdapter() {
        this.panelScreen.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                DrawMapScr.this.mousePress(e);
            }

            public void mouseReleased(MouseEvent e) {
                DrawMapScr.this.mouseRelease(e);
            }
        });

        this.panelTileMap.addMouseWheelListener(e -> {
            this.camTileSet.camY -= e.getWheelRotation() * 24;
            if (this.camTileSet.camY > 0) {
                this.camTileSet.camY = 0;
            }
        });
        this.panelScreen.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                DrawMapScr.this.mouseAxis[0] = e.getX();
                DrawMapScr.this.mouseAxis[1] = e.getY();
                DrawMapScr.this.moveCamera(e);
                if (DrawMapScr.this.holdLeftMouse && DrawMapScr.this.indexLayer == 1) {
                    DrawMapScr.this.putItem(e.getX() - DrawMapScr.this.camera.camX, e.getY() - DrawMapScr.this.camera.camY);
                }
            }

            public void mouseMoved(MouseEvent e) {
                DrawMapScr.this.mouseAxis[0] = e.getX();
                DrawMapScr.this.mouseAxis[1] = e.getY();
                DrawMapScr.this.moveCamera(e);
            }
        });
        this.panelScreen.addMouseWheelListener(e -> {
            if (this.holdKeyAlt) {
                this.camera.camX -= e.getWheelRotation() * 24;
            } else {
                this.camera.camY -= e.getWheelRotation() * 24;
            }
            lockCamera();
        });
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this.ked = new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent ke) {
                if (DrawMapScr.this.isSelected) {
                    switch (ke.getID()) {
                        case 401:
                            DrawMapScr.this.keyPress(ke);
                            break;
                        case 402:
                            DrawMapScr.this.keyRelease(ke);
                            break;
                    }
                }
                return false;
            }
        });
    }

    private void keyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 18:
                setCursor(new Cursor(13));
                this.holdKeyAlt = true;
                break;
            case 17:
                this.holdCtrl = true;
                break;
            case 66:
                this.camera.camX = 15;
                this.camera.camY = 17;
                break;
            case 32:
                if (!this.holdSpace) {
                    this.holdSpace = true;
                    this.anchorCamera[0] = this.mouseAxis[0];
                    this.anchorCamera[1] = this.mouseAxis[1];
                    this.moveCamera = true;
                    setCursor(new Cursor(13));
                }
                break;
            case 69:
                if (!this.holdE) {
                    this.holdE = true;
                    this.tileChoseBefore = this.tilesChose;
                    this.indexTileChoseBefore = this.indexTilesChose;
                    this.indexTileChose = -1;
                    this.tileChose = this.tileSet[this.tileSet.length - 1];
                    setTileChose(new BufferedImage[][]{{this.tileChose},}, new int[][]{{this.indexTileChose}});
                }
                break;
            case 82:
                if (!this.holdR) {
                    this.holdR = true;
                    this.anchorCopy[0] = this.mouseAxis[0];
                    this.anchorCopy[1] = this.mouseAxis[1];
                }
                break;
        }
    }

    private void keyRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 18:
                defaultCursor();
                this.holdKeyAlt = false;
                break;
            case 17:
                this.holdCtrl = false;
                break;
            case 32:
                this.holdSpace = false;
                this.moveCamera = false;
                setCursor(new Cursor(0));
                break;
            case 69:
                if (this.holdE) {
                    this.holdE = false;
                    setTileChose(this.tileChoseBefore, this.indexTileChoseBefore);
                }
                break;
            case 82:
                this.holdR = false;
                break;
        }
    }

    private void setCursor(String path, int x, int y) {
        try {
            Image image = ImageIO.read(new File(path));
            Toolkit t = Toolkit.getDefaultToolkit();
            Image i = new BufferedImage(24, 24, 2);
            Graphics2D g = (Graphics2D) i.getGraphics();
            g.drawImage(image, 0, 0, (ImageObserver) null);
            Cursor noCursor = t.createCustomCursor(i, new Point(x, y), "none");
            setCursor(noCursor);
            g.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void defaultCursor() {
        setCursor(new Cursor(0));
    }

    private void setup() {
        setTitle("taidz75 - Draw map");
        setResizable(false);
        setupKeyMouseAdapter();
        this.model = new MyTableModel((Object[][]) null, (Object[]) new String[]{"Layer", "Show"});
        this.tblListLayer.setModel(this.model);
        this.tblListLayer.getColumnModel().getColumn(1).setPreferredWidth(10);
        String[] layers = {"Bg 1", "Tile map", "Bg 2", "Waypoint", "Npc", "Mob", "Bg 3", "Bg 4", "Effect"};
        for (String l : layers) {
            this.model.addRow(new Object[]{l, new Boolean(true)});
        }
        this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);
    }

    private static class MyTableModel extends DefaultTableModel {

        public MyTableModel(Object[][] rowData, Object[] columnNames) {
            super(rowData, columnNames);
        }

        public Class getColumnClass(int col) {
            if (col == 0) {
                return String.class;
            }
            return Boolean.class;
        }

        public boolean isCellEditable(int row, int col) {
            if (col == 1) {
                return true;
            }
            return false;
        }
    }

    public void addBEff(int id) {
        this.subEffectMaps.clear();
        this.subEffectMaps.add(new SubEffectMap(id, this));
        if (this.effectList != null) {
            this.effectList.fillToTable();
        }
    }

    public void setEffectMapChoes(EffectMap eff) {
        this.effChose = null;
        this.bgChose = null;
        this.mobMapChose = null;
        this.npcMapChose = null;
        this.wpChose = null;
        this.effChose = eff;
        this.indexLayer = 8;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
        this.camera.camX = -eff.getX() + this.camera.width / 2;
        this.camera.camY = -eff.getY() + this.camera.height / 2;
    }

    public void setMobChose(MobMap mob) {
        this.effChose = null;
        this.bgChose = null;
        this.mobMapChose = null;
        this.npcMapChose = null;
        this.wpChose = null;
        this.mobMapChose = mob;
        this.indexLayer = 5;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
        this.camera.camX = -mob.getX() + this.camera.width / 2;
        this.camera.camY = -mob.getY() + this.camera.height / 2;
    }

    public void setBGItemMapChoose(BgItemMap bg, int indexLayer) {
        this.effChose = null;
        this.bgChose = null;
        this.mobMapChose = null;
        this.npcMapChose = null;
        this.wpChose = null;
        this.bgChose = bg;
        this.indexLayer = indexLayer;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
        this.camera.camX = -bg.getX() + this.camera.width / 2;
        this.camera.camY = -bg.getY() + this.camera.height / 2;
    }

    public void setNpcMapChose(NpcMap npc) {
        this.effChose = null;
        this.bgChose = null;
        this.mobMapChose = null;
        this.npcMapChose = null;
        this.wpChose = null;
        this.npcMapChose = npc;
        this.indexLayer = 4;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
        this.camera.camX = -npc.getX() + this.camera.width / 2;
        this.camera.camY = -npc.getY() + this.camera.height / 2;
    }

    public void setWaypointChose(Waypoint wp) {
        this.effChose = null;
        this.bgChose = null;
        this.mobMapChose = null;
        this.npcMapChose = null;
        this.wpChose = null;
        this.wpChose = wp;
        this.indexLayer = 3;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
        this.camera.camX = -wp.getX() + this.camera.width / 2;
        this.camera.camY = -wp.getY() + this.camera.height / 2;
    }

    public void setNpcTemplateChoose(NpcTemplate npc) {
        this.npcChoose = npc;
        this.indexLayer = 4;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
    }

    public void setMobtemplateChoose(MobTemplate mob) {
        this.mobChose = mob;
        this.indexLayer = 5;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
    }

    public void setEffectTemplateChose(EffectTemplate eff) {
        this.effTempChose = eff;
        this.indexLayer = 8;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
    }

    public void setBGItemTemplateChoose(BgItemTemplate bgItemChose, int layer) {
        switch (layer) {
            case 1:
                this.bgItemChose1 = bgItemChose;
                this.indexLayer = 0;
                break;
            case 2:
                this.bgItemChose2 = bgItemChose;
                this.indexLayer = 2;
                break;
            case 3:
                this.bgItemChose3 = bgItemChose;
                this.indexLayer = 6;
                break;
            case 4:
                this.bgItemChose4 = bgItemChose;
                this.indexLayer = 7;
                break;
        }
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
    }

    public void setTileChose(BufferedImage[][] tileChose, int[][] indexTileChose) {
        this.tilesChose = tileChose;
        this.indexTilesChose = indexTileChose;
        this.indexLayer = 1;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
    }

    public void setTileSet(BufferedImage[] tileSet, TilesetType tileSetType) {
        this.tileSet = tileSet;
        this.tileSetType = tileSetType;
        try {
            Layer layer = this.layers.get(1);
            if (layer != null) {
                ((TileMapLayer) layer).setTileSet(tileSet);
                ((TileMapLayer) layer).setTileSetType(tileSetType);
                ((TileMapLayer) layer).clearMap();
            }
        } catch (Exception exception) {
        }
    }

    private void createNew(int w, int h) {
        this.indexLayer = 1;
        this.tblListLayer.setRowSelectionInterval(this.indexLayer, this.indexLayer);
        this.camera.camX = 15;
        this.camera.camY = 17;
        this.mapWidth = w * 24;
        this.mapHeight = h * 24;
        this.layerPaintScreen = new BufferedImage(w * 24, h * 24, 2);
        this.mapName = null;
        this.mapId = -1;
        this.layers.clear();
        this.layers.add(new BgItemLayer(this, this.bgItemL1, w, h));
        this.layers.add(new TileMapLayer(this, w, h));
        this.layers.add(new BgItemLayer(this, this.bgItemL2, w, h));
        this.layers.add(new WaypointMapLayer(this, this.waypoints, w, h));
        this.layers.add(new NpcMapLayer(this, this.npcs, w, h));
        this.layers.add(new MobMapLayer(this, this.mobs, w, h));
        this.layers.add(new BgItemLayer(this, this.bgItemL3, w, h));
        this.layers.add(new BgItemLayer(this, this.bgItemL4, w, h));
        this.layers.add(new EffectMapLayer(this, this.effectMaps, this.subEffectMaps, w, h));
    }

    public static class Camera {

        public int camX;

        public int camY;

        public int width;

        public int height;
    }

    private void draw() {
        if (this.layerPaintScreen == null) {
            this.layerPaintScreen = new BufferedImage(1, 1, 2);
            return;
        }
        if (this.tamDem == null) {
            this.tamDem = new BufferedImage(this.panelScreen.getWidth(), this.panelScreen.getHeight(), 2);
            this.camera.width = this.panelScreen.getWidth();
            this.camera.height = this.panelScreen.getHeight();
        }
        long st = System.currentTimeMillis();
        try {
            for (Layer layer : this.layers) {
                if (layer != null) {
                    layer.draw();
                }
            }
        } catch (Exception exception) {
        }
        drawItemChose();
        drawToLayerScreen();
        drawToTamDem();
        drawToScreen();
    }

    private void drawToLayerScreen() {
        try {
            int x = -this.camera.camX;
            if (x < 0) {
                x = 0;
            }
            int y = -this.camera.camY;
            if (y < 0) {
                y = 0;
            }
            int w = this.camera.width;
            if (w + x > this.layerPaintScreen.getWidth()) {
                w = this.layerPaintScreen.getWidth() - x;
            }
            int h = this.camera.height;
            if (h + y > this.layerPaintScreen.getHeight()) {
                h = this.layerPaintScreen.getHeight() - y;
            }
            Graphics2D g = (Graphics2D) this.layerPaintScreen.getGraphics();
            g.setColor(Color.gray);
            g.fillRect(x, y, w, h);
            g.drawImage(((Layer) this.layers.get(8)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(8)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(0)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(8)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(1)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            if (this.isMapDouble) {
                g.drawImage(((Layer) this.layers.get(0)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            }
            g.drawImage(((Layer) this.layers.get(2)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(8)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(3)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(4)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(5)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(6)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(7)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.drawImage(((Layer) this.layers.get(8)).getBufferedImage().getSubimage(x, y, w, h), x, y, (ImageObserver) null);
            g.setColor(Color.white);
            g.drawRect(0, 0, this.layerPaintScreen.getWidth() - 1, this.layerPaintScreen.getHeight() - 1);
            g.dispose();
        } catch (Exception exception) {
        }
    }

    private void drawToTamDem() {
        Graphics2D gTamDem = (Graphics2D) this.tamDem.getGraphics();
        gTamDem.setColor(Color.gray);
        gTamDem.fillRect(0, 0, this.tamDem.getWidth(), this.tamDem.getHeight());
        gTamDem.drawImage(this.layerPaintScreen, this.camera.camX, this.camera.camY, (ImageObserver) null);
        if (this.holdR) {
            gTamDem.setStroke(new BasicStroke(1.0F));
            int x0 = this.anchorCopy[0];
            int y0 = this.anchorCopy[1];
            int x1 = this.mouseAxis[0];
            int y1 = this.mouseAxis[1];
            gTamDem.setColor(Color.GREEN);
            gTamDem.drawLine(x0, y0, x1, y1);
            gTamDem.drawString((int) Math.sqrt(Math.pow((x1 - x0), 2.0D) + Math.pow((y1 - y0), 2.0D)) + "", x0, y0);
        } else if (this.copyTilesChose) {
            gTamDem.setStroke(new BasicStroke(2.0F));
            gTamDem.setColor(Color.GREEN);
            int x = this.anchorCopy[0];
            int y = this.anchorCopy[1];
            int w = this.mouseAxis[0] - this.anchorCopy[0];
            int h = this.mouseAxis[1] - this.anchorCopy[1];
            if (w < 0) {
                x += w;
                w *= -1;
            }
            if (h < 0) {
                y += h;
                h *= -1;
            }
            gTamDem.drawRect(x, y, w, h);
        }
        if (Util.canDoLastTime(this.lastTimeCalFPS, this.timeCalFPS)) {
            try {
                this.fps = (int) (1000L / (System.currentTimeMillis() - this.st));
                this.fps = Util.range(this.fps - 3, this.fps + 3);
                if (this.fps < 0) {
                    this.fps = 0;
                } else if (this.fps > 752) {
                    this.fps = 752;
                }
            } catch (Exception exception) {
            }
            this.lastTimeCalFPS = System.currentTimeMillis();
        }
        gTamDem.setColor(Color.white);
        if (Util.canDoLastTime(this.lastTimetaidz, 10)) {
            this.lastTimetaidz = System.currentTimeMillis();
            if (this.ytaidz < 15 && this.xtaidz != 55) {
                this.ytaidz++;
            } else if (this.xtaidz > 55) {
                this.xtaidz--;
            } else {
                this.ytaidz--;
                if (this.ytaidz == -15) {
                    this.xtaidz = 1111;
                }
            }
        }
        gTamDem.drawString(this.fps + " FPS", 10, 15);
    }

    private void drawItemChose() {
        try {
            if (this.indexLayer < 0) {
                return;
            }
            Layer layer = this.layers.get(this.indexLayer);
            switch (this.indexLayer) {
                case 3:
                    ((WaypointMapLayer) layer).drawWpChose(this.mouseAxis[0] - this.camera.camX, this.mouseAxis[1] - this.camera.camY);
                    break;
                case 5:
                    ((MobMapLayer) layer).drawMobChoose(this.mobChose, this.mouseAxis[0] - this.camera.camX, this.mouseAxis[1] - this.camera.camY);
                    break;
                case 4:
                    ((NpcMapLayer) layer).drawNpcChoose(this.npcChoose, this.mouseAxis[0] - this.camera.camX, this.mouseAxis[1] - this.camera.camY);
                    break;
                case 0:
                    ((BgItemLayer) layer).drawBGChose(this.bgItemChose1, this.mouseAxis[0] - this.camera.camX, this.mouseAxis[1] - this.camera.camY);
                    break;
                case 2:
                    ((BgItemLayer) layer).drawBGChose(this.bgItemChose2, this.mouseAxis[0] - this.camera.camX, this.mouseAxis[1] - this.camera.camY);
                    break;
                case 6:
                    ((BgItemLayer) layer).drawBGChose(this.bgItemChose3, this.mouseAxis[0] - this.camera.camX, this.mouseAxis[1] - this.camera.camY);
                    break;
                case 7:
                    ((BgItemLayer) layer).drawBGChose(this.bgItemChose4, this.mouseAxis[0] - this.camera.camX, this.mouseAxis[1] - this.camera.camY);
                    break;
                case 8:
                    ((EffectMapLayer) layer).drawWpChose(this.effTempChose, this.mouseAxis[0] - this.camera.camX, this.mouseAxis[1] - this.camera.camY);
                    break;
                case 1:
                    ((TileMapLayer) layer).drawItemChose(this.tilesChose, this.mouseAxis[0] - this.camera.camX, this.mouseAxis[1] - this.camera.camY);
                    break;
            }
        } catch (Exception exception) {
        }
    }

    private void drawToScreen() {
        try {
            Graphics2D grphScreen = (Graphics2D) this.panelScreen.getGraphics();
            grphScreen.drawImage(this.tamDem, 0, 0, (ImageObserver) null);
            grphScreen.dispose();
        } catch (Exception exception) {
        }
    }

    public void moveMouse(Point p) {
        if (robot == null) {
            try {
                robot = new Robot();
            } catch (AWTException aWTException) {
            }
        }
        robot.mouseMove(p.x, p.y);
    }
}
