package tool.draw_map;

import tool.draw_map.load.BgItemTemplate;
import tool.db.Manager;
import tool.utils.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BGItemTable extends JFrame {

    private DrawMapScr drawMapScr;

    private DefaultTableModel model1, model2, model3, model4;
    private List<BgItemTemplate> itemLayer1 = new ArrayList<>();
    private List<BgItemTemplate> itemLayer2 = new ArrayList<>();
    private List<BgItemTemplate> itemLayer3 = new ArrayList<>();
    private List<BgItemTemplate> itemLayer4 = new ArrayList<>();

    private JTable tbl1 = new JTable();
    private JTable tbl2 = new JTable();
    private JTable tbl3 = new JTable();
    private JTable tbl4 = new JTable();

    private JScrollPane scroll1 = new JScrollPane(tbl1);
    private JScrollPane scroll2 = new JScrollPane(tbl2);
    private JScrollPane scroll3 = new JScrollPane(tbl3);
    private JScrollPane scroll4 = new JScrollPane(tbl4);

    public BGItemTable(DrawMapScr drawMapScr) {
        this.drawMapScr = drawMapScr;
        setTitle("Twilight75 - Background Item Template");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
        setupTableModels();
        loadItems();
        fillToTables();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scroll1, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll2, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(scroll3, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll4, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
        );

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scroll1, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                    .addComponent(scroll2, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scroll3, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
                    .addComponent(scroll4, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
        ));

        addTableListeners(tbl1, itemLayer1, 1);
        addTableListeners(tbl2, itemLayer2, 2);
        addTableListeners(tbl3, itemLayer3, 3);
        addTableListeners(tbl4, itemLayer4, 4);
    }

    private void addTableListeners(JTable table, List<BgItemTemplate> layer, int layerId) {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectItem(table, layer, layerId);
            }
        });
        table.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) { selectItem(table, layer, layerId); }
            public void keyReleased(KeyEvent e) { selectItem(table, layer, layerId); }
        });
    }

    private void selectItem(JTable table, List<BgItemTemplate> layer, int layerId) {
        int index = table.getSelectedRow();
        if (index != -1) {
            drawMapScr.setBGItemTemplateChoose(layer.get(index), layerId);
        }
    }

    private void setupTableModels() {
        model1 = createTableModel(); tbl1.setModel(model1); tbl1.setRowHeight(50); tbl1.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
        model2 = createTableModel(); tbl2.setModel(model2); tbl2.setRowHeight(50); tbl2.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
        model3 = createTableModel(); tbl3.setModel(model3); tbl3.setRowHeight(50); tbl3.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
        model4 = createTableModel(); tbl4.setModel(model4); tbl4.setRowHeight(50); tbl4.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
    }

    private DefaultTableModel createTableModel() {
        return new DefaultTableModel(new String[]{"ID", "Image", "Image id"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
    }

    private void loadItems() {
    itemLayer1.clear();
    itemLayer2.clear();
    itemLayer3.clear();
    itemLayer4.clear();

    for (BgItemTemplate temp : Manager.gI().getBgItemTemplates()) {
        int layer = temp.getLayer();
        if (layer == 1) {
            itemLayer1.add(temp);
        } else if (layer == 2) {
            itemLayer2.add(temp);
        } else if (layer == 3) {
            itemLayer3.add(temp);
        } else if (layer == 4) {
            itemLayer4.add(temp);
        }
    }
}


    private void fillToTables() {
        fillTable(model1, itemLayer1);
        fillTable(model2, itemLayer2);
        fillTable(model3, itemLayer3);
        fillTable(model4, itemLayer4);
    }

    private void fillTable(DefaultTableModel model, List<BgItemTemplate> items) {
        model.setRowCount(0);
        for (BgItemTemplate temp : items) {
            model.addRow(new Object[]{temp.getId(), temp.getImageId(), temp.getImageId()});
        }
    }

    private class ImageRender extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            ImageIcon icon = null;
            try {
                int id = Integer.parseInt(value.toString());
                Image image = Util.getBgImageById(id, 1).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                icon = new ImageIcon(image);
            } catch (Exception ignored) {}
            return new JLabel(icon);
        }
    }
}

