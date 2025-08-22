package tool.draw_map;
import tool.button.Button;
import tool.draw_map.template.BgItemMap;
import tool.utils.Util;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class BGItemList extends JFrame {
private DrawMapScr drawMapScr;
private DefaultTableModel model1;
private DefaultTableModel model2;
private DefaultTableModel model3;
private DefaultTableModel model4;
private List<BgItemMap> bgItemL1;
private List<BgItemMap> bgItemL2;
private List<BgItemMap> bgItemL3;
private List<BgItemMap> bgItemL4;
private Button button1;
private Button button2;
private JScrollPane jScrollPane1;
private JScrollPane jScrollPane2;
private JScrollPane jScrollPane5;
private JScrollPane jScrollPane6;
private JTable tbl1;
private JTable tbl2;
private JTable tbl3;
private JTable tbl4;
public BGItemList(DrawMapScr drawMapScr) {
this.bgItemL1 = new ArrayList<>();
this.bgItemL2 = new ArrayList<>();
this.bgItemL3 = new ArrayList<>();
this.bgItemL4 = new ArrayList<>();
this.drawMapScr = drawMapScr;
this.bgItemL1 = drawMapScr.bgItemL1;
this.bgItemL2 = drawMapScr.bgItemL2;
this.bgItemL3 = drawMapScr.bgItemL3;
this.bgItemL4 = drawMapScr.bgItemL4;
initComponents();
setup();
setDefaultCloseOperation(2);
setTitle("Twilight75 - List background item");
setAlwaysOnTop(true);
}
private void initComponents() {
this.jScrollPane1 = new JScrollPane();
this.tbl2 = new JTable();
this.jScrollPane2 = new JScrollPane();
this.tbl1 = new JTable();
this.jScrollPane5 = new JScrollPane();
this.tbl3 = new JTable();
this.jScrollPane6 = new JScrollPane();
this.tbl4 = new JTable();
this.button1 = new Button();
this.button2 = new Button();
setDefaultCloseOperation(3);
this.tbl2.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[0]));
this.tbl2.setSelectionMode(0);
this.tbl2.addMouseListener(new MouseAdapter() {
public void mouseClicked(MouseEvent evt) {
BGItemList.this.tbl2MouseClicked(evt);
}
});
this.tbl2.addKeyListener(new KeyAdapter() {
public void keyPressed(KeyEvent evt) {
BGItemList.this.tbl2KeyPressed(evt);
}
public void keyReleased(KeyEvent evt) {
BGItemList.this.tbl2KeyReleased(evt);
}
});
this.jScrollPane1.setViewportView(this.tbl2);
this.tbl1.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[0]));
this.tbl1.setSelectionMode(0);
this.tbl1.addMouseListener(new MouseAdapter() {
public void mouseClicked(MouseEvent evt) {
BGItemList.this.tbl1MouseClicked(evt);
}
});
this.tbl1.addKeyListener(new KeyAdapter() {
public void keyPressed(KeyEvent evt) {
BGItemList.this.tbl1KeyPressed(evt);
}
public void keyReleased(KeyEvent evt) {
BGItemList.this.tbl1KeyReleased(evt);
}
});
this.jScrollPane2.setViewportView(this.tbl1);
this.tbl3.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[0]));
this.tbl3.setSelectionMode(0);
this.tbl3.addMouseListener(new MouseAdapter() {
public void mouseClicked(MouseEvent evt) {
BGItemList.this.tbl3MouseClicked(evt);
}
});
this.tbl3.addKeyListener(new KeyAdapter() {
public void keyPressed(KeyEvent evt) {
BGItemList.this.tbl3KeyPressed(evt);
}
public void keyReleased(KeyEvent evt) {
BGItemList.this.tbl3KeyReleased(evt);
}
});
this.jScrollPane5.setViewportView(this.tbl3);
this.tbl4.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[0]));
this.tbl4.setSelectionMode(0);
this.tbl4.addMouseListener(new MouseAdapter() {
public void mouseClicked(MouseEvent evt) {
BGItemList.this.tbl4MouseClicked(evt);
}
});
this.tbl4.addKeyListener(new KeyAdapter() {
public void keyPressed(KeyEvent evt) {
BGItemList.this.tbl4KeyPressed(evt);
}
public void keyReleased(KeyEvent evt) {
BGItemList.this.tbl4KeyReleased(evt);
}
});
this.jScrollPane6.setViewportView(this.tbl4);
this.button1.setBackground(new Color(255, 0, 0));
this.button1.setForeground(new Color(255, 255, 255));
this.button1.setText("Remove");
this.button1.setFont(new Font("SansSerif", 1, 14));
this.button1.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent evt) {
BGItemList.this.button1ActionPerformed(evt);
}
});
this.button2.setBackground(new Color(255, 0, 0));
this.button2.setForeground(new Color(255, 255, 255));
this.button2.setText("Clear all");
this.button2.setFont(new Font("SansSerif", 1, 14));
this.button2.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent evt) {
BGItemList.this.button2ActionPerformed(evt);
}
});
GroupLayout layout = new GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jScrollPane2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, -1, -2).addGap(0, 0, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.jScrollPane5, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane6, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent((Component)this.button1, -2, 118, -2).addComponent((Component)this.button2, -2, 118, -2)).addContainerGap(-1, 32767)));
layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addComponent((Component)this.button2, -2, 34, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent((Component)this.button1, -2, 34, -2)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jScrollPane1, -1, 274, 32767).addComponent(this.jScrollPane2, -2, 0, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jScrollPane6, -1, 274, 32767).addComponent(this.jScrollPane5, -2, 0, 32767)))).addGap(0, 0, 32767)));
pack();
}
private void button1ActionPerformed(ActionEvent evt) {
if (this.drawMapScr.bgChose != null) {
this.bgItemL1.remove(this.drawMapScr.bgChose);
this.bgItemL2.remove(this.drawMapScr.bgChose);
this.bgItemL3.remove(this.drawMapScr.bgChose);
this.bgItemL4.remove(this.drawMapScr.bgChose);
}
fillToTable();
}
private void tbl1MouseClicked(MouseEvent evt) {
int index = this.tbl1.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL1.get(index), 0);
}
private void tbl1KeyPressed(KeyEvent evt) {
int index = this.tbl1.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL1.get(index), 0);
}
private void tbl1KeyReleased(KeyEvent evt) {
int index = this.tbl1.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL1.get(index), 0);
}
private void tbl2MouseClicked(MouseEvent evt) {
int index = this.tbl2.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL2.get(index), 2);
}
private void tbl2KeyPressed(KeyEvent evt) {
int index = this.tbl2.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL2.get(index), 2);
}
private void tbl2KeyReleased(KeyEvent evt) {
int index = this.tbl2.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL2.get(index), 2);
}
private void tbl3MouseClicked(MouseEvent evt) {
int index = this.tbl3.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL3.get(index), 6);
}
private void tbl3KeyPressed(KeyEvent evt) {
int index = this.tbl3.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL3.get(index), 6);
}
private void tbl3KeyReleased(KeyEvent evt) {
int index = this.tbl3.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL3.get(index), 6);
}
private void tbl4MouseClicked(MouseEvent evt) {
int index = this.tbl4.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL4.get(index), 7);
}
private void tbl4KeyPressed(KeyEvent evt) {
int index = this.tbl4.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL4.get(index), 7);
}
private void tbl4KeyReleased(KeyEvent evt) {
int index = this.tbl4.getSelectedRow();
if (index != -1)
this.drawMapScr.setBGItemMapChoose(this.bgItemL4.get(index), 7);
}
private void button2ActionPerformed(ActionEvent evt) {
this.bgItemL1.clear();
this.bgItemL2.clear();
this.bgItemL3.clear();
this.bgItemL4.clear();
}
public void fillToTable() {
this.model1.setRowCount(0);
this.model2.setRowCount(0);
this.model3.setRowCount(0);
this.model4.setRowCount(0);
for (BgItemMap temp : this.drawMapScr.bgItemL1) {
this.model1.addRow(new Object[] { Integer.valueOf(temp.getTemp().getId()), Integer.valueOf(temp.getTemp().getImageId()), Integer.valueOf(temp.getX()), Integer.valueOf(temp.getY()) });
}
for (BgItemMap temp : this.drawMapScr.bgItemL2) {
this.model2.addRow(new Object[] { Integer.valueOf(temp.getTemp().getId()), Integer.valueOf(temp.getTemp().getImageId()), Integer.valueOf(temp.getX()), Integer.valueOf(temp.getY()) });
}
for (BgItemMap temp : this.drawMapScr.bgItemL3) {
this.model3.addRow(new Object[] { Integer.valueOf(temp.getTemp().getId()), Integer.valueOf(temp.getTemp().getImageId()), Integer.valueOf(temp.getX()), Integer.valueOf(temp.getY()) });
}
for (BgItemMap temp : this.drawMapScr.bgItemL4) {
this.model4.addRow(new Object[] { Integer.valueOf(temp.getTemp().getId()), Integer.valueOf(temp.getTemp().getImageId()), Integer.valueOf(temp.getX()), Integer.valueOf(temp.getY()) });
}
}
private void setup() {
setResizable(false);
setLocationRelativeTo(null);
this.model1 = new DefaultTableModel((Object[])new String[] { "ID", "Image", "x", "y" }, 0) {
public boolean isCellEditable(int row, int column) {
return false;
}
};
this.tbl1.setModel(this.model1);
this.model2 = new DefaultTableModel((Object[])new String[] { "ID", "Image", "x", "y" }, 0) {
public boolean isCellEditable(int row, int column) {
return false;
}
};
this.tbl2.setModel(this.model2);
this.model3 = new DefaultTableModel((Object[])new String[] { "ID", "Image", "x", "y" }, 0) {
public boolean isCellEditable(int row, int column) {
return false;
}
};
this.tbl3.setModel(this.model3);
this.model4 = new DefaultTableModel((Object[])new String[] { "ID", "Image", "x", "y" }, 0) {
public boolean isCellEditable(int row, int column) {
return false;
}
};
this.tbl4.setModel(this.model4);
this.tbl1.setRowHeight(50);
this.tbl2.setRowHeight(50);
this.tbl3.setRowHeight(50);
this.tbl4.setRowHeight(50);
this.tbl1.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
this.tbl2.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
this.tbl3.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
this.tbl4.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
this.tbl1.setDragEnabled(true);
this.tbl1.setDropMode(DropMode.INSERT_ROWS);
this.tbl1.setTransferHandler(new TableRowTransferHandler(this.tbl1, this.bgItemL1));
this.tbl2.setDragEnabled(true);
this.tbl2.setDropMode(DropMode.INSERT_ROWS);
this.tbl2.setTransferHandler(new TableRowTransferHandler(this.tbl2, this.bgItemL2));
this.tbl3.setDragEnabled(true);
this.tbl3.setDropMode(DropMode.INSERT_ROWS);
this.tbl3.setTransferHandler(new TableRowTransferHandler(this.tbl3, this.bgItemL3));
this.tbl4.setDragEnabled(true);
this.tbl4.setDropMode(DropMode.INSERT_ROWS);
this.tbl4.setTransferHandler(new TableRowTransferHandler(this.tbl4, this.bgItemL4));
}
private class ImageRender extends DefaultTableCellRenderer {
private ImageRender() {}
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
ImageIcon icon = null;
try {
String id = value.toString();
Image image = Util.getBgImageById(Integer.parseInt(id), 1);
image = image.getScaledInstance(40, 40, 4);
icon = new ImageIcon(image);
} catch (Exception exception) {}
return new JLabel(icon);
}
}
public class TableRowTransferHandler extends TransferHandler {
// private final DataFlavor localObjectFlavor = (DataFlavor)new ActivationDataFlavor(Integer.class, "application/x-java-Integer;class=java.lang.Integer", "Integer Row Index");
private JTable table = null;
private List<BgItemMap> listBG;
public TableRowTransferHandler(JTable table, List<BgItemMap> list) {
this.table = table;
this.listBG = list;
}
protected Transferable createTransferable(JComponent c) {
assert c == this.table;
//return (Transferable)new DataHandler(new Integer(this.table.getSelectedRow()), this.localObjectFlavor.getMimeType());
return null;
}
public boolean canImport(TransferHandler.TransferSupport info) {
//boolean b = (info.getComponent() == this.table && info.isDrop() && info.isDataFlavorSupported(this.localObjectFlavor));
//      this.table.setCursor(b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
//      return b;
return false;
//boolean b = (info.getComponent() == this.table && info.isDrop() && info.isDataFlavorSupported(this.localObjectFlavor));
//      this.table.setCursor(b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
//      return b;
}
public int getSourceActions(JComponent c) {
return 3;
}
public boolean importData(TransferHandler.TransferSupport info) {
JTable target = (JTable)info.getComponent();
JTable.DropLocation dl = (JTable.DropLocation)info.getDropLocation();
int index = dl.getRow();
int max = this.table.getModel().getRowCount();
if (index < 0 || index > max)
index = max;
target.setCursor(Cursor.getPredefinedCursor(0));
try {
//Integer rowFrom = (Integer)info.getTransferable().getTransferData(this.localObjectFlavor);
//        if (rowFrom.intValue() != -1 && rowFrom.intValue() != index) {
//          move(rowFrom.intValue(), index);
//          BGItemList.this.fillToTable();
//          return true;
//        }
} catch (Exception e) {
e.printStackTrace();
}
return false;
}
protected void exportDone(JComponent c, Transferable t, int act) {
if (act == 2 || act == 0)
this.table.setCursor(Cursor.getPredefinedCursor(0));
}
private void move(int from, int to) {
BgItemMap bg = this.listBG.get(from);
this.listBG.add(to, bg);
if (from < to) {
this.listBG.remove(from);
} else {
this.listBG.remove(from + 1);
}
}
}
}
