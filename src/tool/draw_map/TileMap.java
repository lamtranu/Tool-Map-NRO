package tool.draw_map;

import tool.button.Button;
import tool.draw_map.load.TilesetType;
import tool.db.Manager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class TileMap extends JFrame {

    private boolean running;
    private Thread tDrawTile;
    private DrawMapScr drawMapScr;
    private TilesetType tilesetTypeData;
    private int tileId;
    private BufferedImage layerMask;
    private BufferedImage[] tile;
    private BufferedImage tileChose;
    private int indexTileChose;
    private Button btnChooseTile;
    private Button button2;
    private Button button3;
    private JLabel lblNameTile;
    private JPanel panelTileSet;

    public TileMap(DrawMapScr drawMapScr) {
        this.indexTileChose = -1;
        this.drawMapScr = drawMapScr;
        initComponents();
        setup();
        initThread();
        start();
        setDefaultCloseOperation(2);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                TileMap.this.running = false;
            }
        });
        pack();
        setAlwaysOnTop(true);
    }

    private void start() {
        this.running = true;
        this.tDrawTile.start();
    }

    private void initThread() {
        this.tDrawTile = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        while (true) {
                            draw();
                            Thread.sleep(10L);
                        }
                    } catch (Exception exception) {
                    }
                }
            }
        });
    }
    
    private void initComponents() {
        this.panelTileSet = new JPanel();
        this.lblNameTile = new JLabel();
        this.btnChooseTile = new Button();
        this.button2 = new Button();
        this.button3 = new Button();
        setDefaultCloseOperation(3);
        this.panelTileSet.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                TileMap.this.panelTileSetMouseClicked(evt);
            }
        });
        GroupLayout panelTileSetLayout = new GroupLayout(this.panelTileSet);
        this.panelTileSet.setLayout(panelTileSetLayout);
        panelTileSetLayout.setHorizontalGroup(panelTileSetLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
        panelTileSetLayout.setVerticalGroup(panelTileSetLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 240, 32767));
        this.lblNameTile.setFont(new Font("SansSerif", 1, 18));
        this.lblNameTile.setHorizontalAlignment(0);
        this.btnChooseTile.setBackground(new Color(204, 0, 204));
        this.btnChooseTile.setForeground(new Color(255, 255, 255));
        this.btnChooseTile.setText("Choose tile");
        this.btnChooseTile.setFont(new Font("SansSerif", 1, 14));
        this.btnChooseTile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TileMap.this.btnChooseTileActionPerformed(evt);
            }
        });
        this.button2.setBackground(new Color(255, 0, 153));
        this.button2.setForeground(new Color(255, 255, 255));
        this.button2.setText("<");
        this.button2.setFont(new Font("Tahoma", 1, 14));
        this.button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TileMap.this.button2ActionPerformed(evt);
            }
        });
        this.button3.setBackground(new Color(255, 0, 153));
        this.button3.setForeground(new Color(255, 255, 255));
        this.button3.setText(">");
        this.button3.setFont(new Font("Tahoma", 1, 14));
        this.button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TileMap.this.button3ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.lblNameTile, -1, 240, 32767).addComponent(this.panelTileSet, -1, -1, 32767).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent((Component) this.button2, -1, -1, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent((Component) this.btnChooseTile, -2, 109, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent((Component) this.button3, -1, -1, 32767))).addGap(0, 0, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.panelTileSet, -2, -1, -2).addGap(0, 0, 0).addComponent(this.lblNameTile, -2, 44, -2).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent((Component) this.btnChooseTile, -2, 52, -2).addComponent((Component) this.button2, -2, 52, -2).addComponent((Component) this.button3, -2, 52, -2)).addGap(0, 6, 32767)));
        layout.linkSize(1, new Component[]{(Component) this.btnChooseTile, (Component) this.button2, (Component) this.button3});
        pack();
    }

    private void btnChooseTileActionPerformed(ActionEvent evt) {
        openChoseTileSet();
    }

    private void openChoseTileSet() {
        (new Thread(() -> {
            JFileChooser fileChooser = new JFileChooser("data/tile");
            if (fileChooser.showOpenDialog(this) == 0) {
                File fileChose = fileChooser.getSelectedFile();
                readTileSet(fileChose);
            }
        })).start();
    }

//    public void readTileSet(File file) {
//        try {
//            this.tileId = Integer.parseInt(file.getName().replaceAll(".png", ""));
//            this.tilesetTypeData = Manager.gI().getTile_set_type().get(this.tileId - 1);
//            this.lblNameTile.setText(file.getName());
//            System.out.println("🔍 Load tileId = " + tileId + " from file: " + file.getName());
//
//            BufferedImage img = ImageIO.read(file);
//            int h = img.getHeight();
//            int nTile = h / 24;
//            this.tileChose = null;
//            this.tile = new BufferedImage[nTile];
//            for (int i = 0; i < nTile; i++) {
//                this.tile[i] = img.getSubimage(0, i * 24, 24, 24);
//                if (i == nTile - 1);
//            }
//            this.drawMapScr.setTileSet(this.tile, this.tilesetTypeData);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
    public void readTileSet(File file) {
    try {
        this.tileId = Integer.parseInt(file.getName().replaceAll(".png", ""));
        this.tilesetTypeData = Manager.gI().getTile_set_type().get(this.tileId - 1);
        this.lblNameTile.setText(file.getName());

        System.out.println("🔍 Đang load tileId = " + this.tileId + " từ file: " + file.getName());
        System.out.println("🧩 TilesetType ID = " + this.tilesetTypeData.id + ", Số tile có type = " + this.tilesetTypeData.tileType.size());

        BufferedImage img = ImageIO.read(file);
        int h = img.getHeight();
        int nTile = h / 24;

        this.tileChose = null;
        this.tile = new BufferedImage[nTile];

        for (int i = 0; i < nTile; i++) {
            this.tile[i] = img.getSubimage(0, i * 24, 24, 24);
            List<Integer> types = this.tilesetTypeData.tileType.get(i + 1);

            if (types != null) {
                System.out.println("🧱 Tile " + (i + 1) + " có type: " + types);
            } else {
                System.out.println("🧱 Tile " + (i + 1) + " KHÔNG có type.");
            }
        }

        this.drawMapScr.setTileSet(this.tile, this.tilesetTypeData);
    } catch (IOException | IndexOutOfBoundsException | NumberFormatException ex) {
        System.err.println("❌ Lỗi khi đọc tileSet từ file: " + file.getName());
        ex.printStackTrace();
    }
}


    private void panelTileSetMouseClicked(MouseEvent evt) {
        if (this.tile != null && this.tile.length != 0) {
            int x = evt.getX() / 24;
            int y = evt.getY() / 24;
            int chose = x + y * 10;
            if (chose < this.tile.length) {
                this.tileChose = this.tile[chose];
                this.indexTileChose = chose;
            } else {
                this.tileChose = null;
                this.indexTileChose = -1;
            }
            this.drawMapScr.setTileChose(new BufferedImage[][]{{this.tileChose},}, new int[][]{{this.indexTileChose}});
        }
    }

    private void button2ActionPerformed(ActionEvent evt) {
        int tileId = this.tileId - 1;
        File file = new File("data/tile/" + tileId);
        if (file.exists()) {
            readTileSet(file);
        }
    }

    private void button3ActionPerformed(ActionEvent evt) {
        int tileId = this.tileId + 1;
        File file = new File("data/tile/" + tileId);
        if (file.exists()) {
            readTileSet(file);
            
        }
    }

    public void show(Component parent) {
        setLocation(parent.getWidth() - getWidth() + parent.getX() - 5, parent.getHeight() - getHeight() + parent.getY() - 5);
        setVisible(true);
        if (this.tile == null) {
            File file = new File("data/tile/1");
            if (file.exists()) {
                readTileSet(file);
            }
        }
    }

    public BufferedImage[] getTile() {
        return this.tile;
    }

    private void setup() {
        setTitle("Tile map");
        setResizable(false);
        setLocationRelativeTo((Component) null);
        this.layerMask = new BufferedImage(this.panelTileSet.getWidth(), this.panelTileSet.getHeight(), 2);
    }

    private void draw() {
        if (this.tile == null) {
            return;
        }
        Graphics grph = this.layerMask.getGraphics();
        Graphics2D g = (Graphics2D) grph;
        clearImage(g);
        drawTileSet(g);
        drawTileChose(g);
        drawToScreen();
    }

    private void drawToScreen() {
        Graphics grph = this.panelTileSet.getGraphics();
        Graphics2D g = (Graphics2D) grph;
        g.drawImage(this.layerMask, 0, 0, (ImageObserver) null);
    }

    private void clearImage(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.layerMask.getWidth(), this.layerMask.getHeight());
    }

    private void drawTileSet(Graphics2D g) {
        int x = 0;
        int y = 0;
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
            if (x >= 240) {
                y += 24;
                x = 0;
            }
        }
        g.setColor(Color.WHITE);
        g.fillRect(216, 216, 24, 24);
        g.setColor(Color.BLACK);
        g.drawRect(216, 216, 24, 24);
        g.drawString("E", 228, 234);
    }

    private void drawTileChose(Graphics2D g) {
        g.setColor(Color.red);
        g.setStroke(new BasicStroke(2.0F));
        if (this.tileChose == null) {
            g.drawRect(216, 216, 24, 24);
            return;
        }
        int pX = 0;
        int pY = 0;
        for (int i = 0; i < this.tile.length; i++) {
            if (this.tileChose.equals(this.tile[i])) {
                g.drawRect(pX, pY, 24, 24);
                return;
            }
            pX += 24;
            if (pX >= 240) {
                pX = 0;
                pY += 24;
            }
        }
    }
}
