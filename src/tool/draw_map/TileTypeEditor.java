package tool.draw_map;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

public class TileTypeEditor extends JFrame {

    private BufferedImage tileset;
    private int tileSize = 24;
    private int cols, rows;
    private int[][] tileTypes;
    private JPanel panel;
    private JButton btnOk, btnReset;
    private boolean showGrid = true;

    // Panel chứa 4 hướng
    private JToggleButton btnLeft, btnUp, btnRight, btnDown;
    private JPanel pnlDirections;

    private int selectedX = -1, selectedY = -1; // ô đang chọn

    public interface OnConfirmListener {
        void onConfirm(int[][] tileTypes);
    }

    public TileTypeEditor(File pngFile, final OnConfirmListener listener) throws IOException {
        tileset = ImageIO.read(pngFile);
        cols = tileset.getWidth() / tileSize;
        rows = tileset.getHeight() / tileSize;
        tileTypes = new int[rows][cols];

        panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(tileset, 0, 0, null);

                // Vẽ lưới
                if (showGrid) {
                    g.setColor(Color.LIGHT_GRAY);
                    for (int x = 0; x <= tileset.getWidth(); x += tileSize)
                        g.drawLine(x, 0, x, tileset.getHeight());
                    for (int y = 0; y <= tileset.getHeight(); y += tileSize)
                        g.drawLine(0, y, tileset.getWidth(), y);
                }

                // Vẽ type
                for (int y = 0; y < rows; y++) {
                    for (int x = 0; x < cols; x++) {
                        int type = tileTypes[y][x];
                        int px = x * tileSize;
                        int py = y * tileSize;
                        g.setColor(Color.RED);
                        if ((type & 4) != 0) g.fillRect(px, py + 4, 4, tileSize - 8);       // trái
                        if ((type & 2) != 0) g.fillRect(px + 4, py, tileSize - 8, 4);       // trên
                        if ((type & 8) != 0) g.fillRect(px + tileSize - 4, py + 4, 4, tileSize - 8); // phải
                        if ((type & 8192) != 0) g.fillRect(px + 4, py + tileSize - 4, tileSize - 8, 4); // dưới
                    }
                }
                
                // Vẽ ô đang chọn
                if (selectedX >= 0 && selectedY >= 0) {
                    g.setColor(Color.RED);
                    g.drawRect(selectedX * tileSize, selectedY * tileSize, tileSize - 1, tileSize - 1);
                    g.drawRect(selectedX * tileSize + 1, selectedY * tileSize + 1, tileSize - 3, tileSize - 3); // tạo viền dày hơn
                }
            }
        };
        panel.setPreferredSize(new Dimension(tileset.getWidth(), tileset.getHeight()));

        // Khi click chọn ô
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / tileSize;
                int y = e.getY() / tileSize;
                if (x >= 0 && x < cols && y >= 0 && y < rows) {
                    selectedX = x;
                    selectedY = y;
                    int type = tileTypes[y][x];

                    // Cập nhật panel 4 hướng
                    btnLeft.setSelected((type & 4) != 0);                    
                    btnUp.setSelected((type & 2) != 0);
                    btnRight.setSelected((type & 8) != 0);
                    btnDown.setSelected((type & 8192) != 0                                                                                       );

                    panel.repaint();
                }
            }
        });

        // Panel 4 hướng
        pnlDirections = new JPanel(new FlowLayout());
        btnLeft = new JToggleButton("◀");
        btnUp = new JToggleButton("▲");
        btnRight = new JToggleButton("▶");
        btnDown = new JToggleButton("▼");
        btnReset = new JButton("Reset");

        pnlDirections.add(btnLeft);
        pnlDirections.add(btnUp);
        pnlDirections.add(btnRight);
        pnlDirections.add(btnDown);
        pnlDirections.add(btnReset);

        ActionListener updateTypeListener = (ActionEvent e) -> {
            if (selectedX >= 0 && selectedY >= 0) {
                int type1 = 0;
                if (btnLeft.isSelected()) {
                    type1 |= 4;
                }
                if (btnUp.isSelected()) {
                    type1 |= 2;
                }
                if (btnRight.isSelected()) {
                    type1 |= 8;
                }
                if (btnDown.isSelected()) {
                    type1 |= 8192;
                }
                tileTypes[selectedY][selectedX] = type1;
                panel.repaint();
                // ---- DEBUG ----
                System.out.println("🟢 Đang chọn tile: (" + selectedX + ", " + selectedY + ")");
                System.out.println("🔹 Type hiện tại: " + type1);

                // Nếu muốn hiển thị dạng mảng các bit (như [2,4,8])
                List<Integer> typeList = new ArrayList<>();
                int[] possibleBits = {2,4,8,8192};
                for (int bit : possibleBits) {
                    if ((type1 & bit) != 0) typeList.add(bit);
                }
                System.out.println("🔹 Type tách bit: " + typeList);
            }
        };

        btnLeft.addActionListener(updateTypeListener);
        btnUp.addActionListener(updateTypeListener);
        btnRight.addActionListener(updateTypeListener);
        btnDown.addActionListener(updateTypeListener);

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedX >= 0 && selectedY >= 0) {
                    tileTypes[selectedY][selectedX] = 0;
                    btnLeft.setSelected(false);
                    btnUp.setSelected(false);
                    btnRight.setSelected(false);
                    btnDown.setSelected(false);
                    panel.repaint();
                }
            }
        });

        JCheckBox chkShowGrid = new JCheckBox("Hiện lưới", true);
        chkShowGrid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGrid = chkShowGrid.isSelected();
                panel.repaint();
            }
        });

        btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listener.onConfirm(tileTypes);
                dispose();
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(chkShowGrid);
        bottomPanel.add(btnOk);

        setLayout(new BorderLayout());
        add(new JScrollPane(panel), BorderLayout.CENTER);
        add(pnlDirections, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        setTitle("Gán tile type");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
