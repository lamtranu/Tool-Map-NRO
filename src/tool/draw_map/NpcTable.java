package tool.draw_map;

import tool.draw_map.load.NpcTemplate;
import tool.db.Manager;
import tool.utils.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hiển thị bảng danh sách NPC template
 */
public class NpcTable extends JFrame {

    private final DrawMapScr drawMapScr;
    private DefaultTableModel model;
    private JTable table;

    public NpcTable(DrawMapScr drawMapScr) {
        this.drawMapScr = drawMapScr;
        initUI();
        setup();
        setTitle("Twilight75 - Npc template");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /** Khởi tạo giao diện */
    private void initUI() {
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE));

        pack();
    }

    /** Gán model và cài đặt bảng */
    private void setup() {
        setResizable(false);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Avatar"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(model);
        table.setRowHeight(50);
        table.getColumnModel().getColumn(2).setCellRenderer(new ImageRender());

        // sự kiện chọn npc
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateSelection();
            }
        });
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                updateSelection();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                updateSelection();
            }
        });

        fillTable();
    }

    /** Đổ dữ liệu NPC vào bảng */
    private void fillTable() {
        model.setRowCount(0);
        for (NpcTemplate npc : Manager.gI().getNpcTemplates()) {
            model.addRow(new Object[]{npc.getId(), npc.getName(), npc.getAvatar()});
        }
    }

    /** Cập nhật NPC được chọn vào DrawMapScr */
    private void updateSelection() {
        int index = table.getSelectedRow();
        if (index != -1 && drawMapScr != null) {
            drawMapScr.setNpcTemplateChoose(Manager.gI().getNpcTemplates().get(index));
        }
    }

    /** Renderer cột Avatar */
    private static class ImageRender extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            try {
                int id = Integer.parseInt(value.toString());
                Image img = Util.getImageById(id, 2);
                if (img != null) {
                    img = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(img));
                }
            } catch (Exception ignored) {
            }
            label.setText("");
            return label;
        }
    }

    /** Main test */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NpcTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new NpcTable(null).setVisible(true));
    }
}
