package tool.draw_map;

import tool.button.Button;
import tool.db.Manager;
import tool.draw_map.template.EffectMap;
import tool.draw_map.template.MobMap;
import tool.draw_map.template.SubEffectMap;
import tool.utils.Util;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class EffectList extends JFrame {

    private DrawMapScr drawMapScr;
    private DefaultTableModel model1;
    private int index;
    private Button button1;
    private Button button2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JScrollPane jScrollPane2;
    private JTable tbl1;
    private JTextField txtFrom;
    private JTextField txtLayer;
    private JTextField txtLoop;
    private JTextField txtLoopCount;
    private JTextField txtTo;
    private JTextField txtType;
    private JTextField txtX;
    private JTextField txtY;

    public EffectList(DrawMapScr drawMapScr) {
        this.drawMapScr = drawMapScr;
        initComponents();
        setup();
        setDefaultCloseOperation(2);
        setTitle("Twilight75 - List effect map");
        setAlwaysOnTop(true);
    }

    private void initComponents() {
        this.jScrollPane2 = new JScrollPane();
        this.tbl1 = new JTable();
        this.button1 = new Button();
        this.button2 = new Button();
        this.jLabel1 = new JLabel();
        this.txtX = new JTextField();
        this.txtY = new JTextField();
        this.jLabel2 = new JLabel();
        this.txtLayer = new JTextField();
        this.jLabel3 = new JLabel();
        this.txtLoop = new JTextField();
        this.jLabel4 = new JLabel();
        this.txtLoopCount = new JTextField();
        this.jLabel5 = new JLabel();
        this.txtFrom = new JTextField();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.txtTo = new JTextField();
        this.jLabel8 = new JLabel();
        this.txtType = new JTextField();
        setDefaultCloseOperation(3);
        this.tbl1.setModel(new DefaultTableModel(new Object[0][], (Object[]) new String[0]));
        this.tbl1.setSelectionMode(0);
        this.tbl1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                EffectList.this.tbl1MouseClicked(evt);
            }
        });
        this.tbl1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                EffectList.this.tbl1KeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                EffectList.this.tbl1KeyReleased(evt);
            }
        });
        this.jScrollPane2.setViewportView(this.tbl1);
        this.button1.setBackground(new Color(255, 0, 0));
        this.button1.setForeground(new Color(255, 255, 255));
        this.button1.setText("Clear all");
        this.button1.setFont(new Font("SansSerif", 1, 14));
        this.button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EffectList.this.button1ActionPerformed(evt);
            }
        });
        this.button2.setBackground(new Color(255, 0, 0));
        this.button2.setForeground(new Color(255, 255, 255));
        this.button2.setText("Remove");
        this.button2.setFont(new Font("SansSerif", 1, 14));
        this.button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EffectList.this.button2ActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("SansSerif", 1, 12));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("x");
        this.txtX.setFont(new Font("SansSerif", 1, 12));
        this.txtX.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                EffectList.this.txtXKeyReleased(evt);
            }
        });
        this.txtY.setFont(new Font("SansSerif", 1, 12));
        this.txtY.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                EffectList.this.txtYKeyReleased(evt);
            }
        });
        this.jLabel2.setFont(new Font("SansSerif", 1, 12));
        this.jLabel2.setHorizontalAlignment(0);
        this.jLabel2.setText("y");
        this.txtLayer.setFont(new Font("SansSerif", 1, 12));
        this.txtLayer.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                EffectList.this.txtLayerKeyReleased(evt);
            }
        });
        this.jLabel3.setFont(new Font("SansSerif", 1, 12));
        this.jLabel3.setHorizontalAlignment(0);
        this.jLabel3.setText("layer");
        this.txtLoop.setFont(new Font("SansSerif", 1, 12));
        this.txtLoop.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                EffectList.this.txtLoopKeyReleased(evt);
            }
        });
        this.jLabel4.setFont(new Font("SansSerif", 1, 12));
        this.jLabel4.setHorizontalAlignment(0);
        this.jLabel4.setText("loop");
        this.txtLoopCount.setFont(new Font("SansSerif", 1, 12));
        this.txtLoopCount.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                EffectList.this.txtLoopCountKeyReleased(evt);
            }
        });
        this.jLabel5.setFont(new Font("SansSerif", 1, 12));
        this.jLabel5.setHorizontalAlignment(0);
        this.jLabel5.setText("count");
        this.txtFrom.setFont(new Font("SansSerif", 1, 12));
        this.txtFrom.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                EffectList.this.txtFromKeyReleased(evt);
            }
        });
        this.jLabel6.setFont(new Font("SansSerif", 1, 12));
        this.jLabel6.setHorizontalAlignment(0);
        this.jLabel6.setText("from");
        this.jLabel7.setFont(new Font("SansSerif", 1, 12));
        this.jLabel7.setHorizontalAlignment(0);
        this.jLabel7.setText("to");
        this.txtTo.setFont(new Font("SansSerif", 1, 12));
        this.txtTo.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                EffectList.this.txtToKeyReleased(evt);
            }
        });
        this.jLabel8.setFont(new Font("SansSerif", 1, 12));
        this.jLabel8.setHorizontalAlignment(0);
        this.jLabel8.setText("type");
        this.txtType.setFont(new Font("SansSerif", 1, 12));
        this.txtType.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                EffectList.this.txtTypeKeyReleased(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(this.jScrollPane2, -2, 508, -2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent((Component) this.button1, -1, 208, 32767)
                                .addComponent((Component) this.button2, -1, 208, 32767)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel1, -2, 57, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtX))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel2, -2, 57, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtY))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel3, -2, 57, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtLayer))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel4, -2, 57, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtLoop))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel5, -2, 57, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtLoopCount))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel6, -2, 57, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtFrom))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel7, -2, 57, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtTo))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel8, -2, 57, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtType)))));
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(this.jScrollPane2, GroupLayout.Alignment.LEADING, -2, 0, 32767)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel1, -1, -1, 32767)
                                                .addComponent(this.txtX, -1, 33, 32767))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel2, -2, 33, -2)
                                                .addComponent(this.txtY, -2, 33, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel3, -2, 33, -2)
                                                .addComponent(this.txtLayer, -2, 33, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel4, -2, 33, -2)
                                                .addComponent(this.txtLoop, -2, 33, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel5, -2, 33, -2)
                                                .addComponent(this.txtLoopCount, -2, 33, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel6, -2, 33, -2)
                                                .addComponent(this.txtFrom, -2, 33, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel7, -2, 33, -2)
                                                .addComponent(this.txtTo, -2, 33, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel8, -2, 33, -2)
                                                .addComponent(this.txtType, -2, 33, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button2, -2, 36, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button1, -2, 36, -2)))
                        .addGap(0, 0, 0)));
        pack();
    }

    private void tbl1MouseClicked(MouseEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            try {
                this.drawMapScr.setEffectMapChoes(this.drawMapScr.effectMaps.get(this.index));
                this.txtX.setText(this.drawMapScr.effChose.getX() + "");
                this.txtY.setText(this.drawMapScr.effChose.getY() + "");
                this.txtLayer.setText(this.drawMapScr.effChose.getLayer() + "");
                this.txtLoop.setText(this.drawMapScr.effChose.getLoop() + "");
                this.txtLoopCount.setText(this.drawMapScr.effChose.getLoopCount() + "");
                this.txtFrom.setText(this.drawMapScr.effChose.getIndexFrom() + "");
                this.txtTo.setText(this.drawMapScr.effChose.getIndexTo() + "");
                this.txtType.setText(this.drawMapScr.effChose.getType() + "");
            } catch (Exception e) {
                this.drawMapScr.setEffectMapChoes((EffectMap) this.drawMapScr.subEffectMaps.get(this.index - this.drawMapScr.effectMaps.size()));
                this.drawMapScr.setEffectMapChoes(this.drawMapScr.effectMaps.get(this.index));
                this.txtX.setText("");
                this.txtY.setText("");
                this.txtLayer.setText("");
                this.txtLoop.setText("");
                this.txtLoopCount.setText("");
                this.txtFrom.setText("");
                this.txtTo.setText("");
                this.txtType.setText("");
            }
        }
    }

    private void tbl1KeyPressed(KeyEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            try {
                this.drawMapScr.setEffectMapChoes(this.drawMapScr.effectMaps.get(this.index));
                this.txtX.setText(this.drawMapScr.effChose.getX() + "");
                this.txtY.setText(this.drawMapScr.effChose.getY() + "");
                this.txtLayer.setText(this.drawMapScr.effChose.getLayer() + "");
                this.txtLoop.setText(this.drawMapScr.effChose.getLoop() + "");
                this.txtLoopCount.setText(this.drawMapScr.effChose.getLoopCount() + "");
                this.txtFrom.setText(this.drawMapScr.effChose.getIndexFrom() + "");
                this.txtTo.setText(this.drawMapScr.effChose.getIndexTo() + "");
                this.txtType.setText(this.drawMapScr.effChose.getType() + "");
            } catch (Exception e) {
                this.drawMapScr.setEffectMapChoes((EffectMap) this.drawMapScr.subEffectMaps.get(this.index - this.drawMapScr.effectMaps.size()));
                this.drawMapScr.setEffectMapChoes(this.drawMapScr.effectMaps.get(this.index));
                this.txtX.setText("");
                this.txtY.setText("");
                this.txtLayer.setText("");
                this.txtLoop.setText("");
                this.txtLoopCount.setText("");
                this.txtFrom.setText("");
                this.txtTo.setText("");
                this.txtType.setText("");
            }
        }
    }

    private void tbl1KeyReleased(KeyEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            try {
                this.drawMapScr.setEffectMapChoes(this.drawMapScr.effectMaps.get(this.index));
                this.txtX.setText(this.drawMapScr.effChose.getX() + "");
                this.txtY.setText(this.drawMapScr.effChose.getY() + "");
                this.txtLayer.setText(this.drawMapScr.effChose.getLayer() + "");
                this.txtLoop.setText(this.drawMapScr.effChose.getLoop() + "");
                this.txtLoopCount.setText(this.drawMapScr.effChose.getLoopCount() + "");
                this.txtFrom.setText(this.drawMapScr.effChose.getIndexFrom() + "");
                this.txtTo.setText(this.drawMapScr.effChose.getIndexTo() + "");
                this.txtType.setText(this.drawMapScr.effChose.getType() + "");
            } catch (Exception e) {
                this.drawMapScr.setEffectMapChoes((EffectMap) this.drawMapScr.subEffectMaps.get(this.index - this.drawMapScr.effectMaps.size()));
                this.drawMapScr.setEffectMapChoes(this.drawMapScr.effectMaps.get(this.index));
                this.txtX.setText("");
                this.txtY.setText("");
                this.txtLayer.setText("");
                this.txtLoop.setText("");
                this.txtLoopCount.setText("");
                this.txtFrom.setText("");
                this.txtTo.setText("");
                this.txtType.setText("");
            }
        }
    }

    private void button1ActionPerformed(ActionEvent evt) {
        this.drawMapScr.effectMaps.clear();
        this.drawMapScr.subEffectMaps.clear();
        fillToTable();
    }

    private void button2ActionPerformed(ActionEvent evt) {
        this.drawMapScr.effectMaps.remove(this.drawMapScr.effChose);
        this.drawMapScr.subEffectMaps.remove(this.drawMapScr.effChose);
        fillToTable();
    }

    private void txtXKeyReleased(KeyEvent evt) {
        try {
            this.drawMapScr.effChose.setX(Integer.parseInt(this.txtX.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtYKeyReleased(KeyEvent evt) {
        try {
            this.drawMapScr.effChose.setY(Integer.parseInt(this.txtY.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtLayerKeyReleased(KeyEvent evt) {
        try {
            this.drawMapScr.effChose.setLayer(Integer.parseInt(this.txtLayer.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtLoopKeyReleased(KeyEvent evt) {
        try {
            this.drawMapScr.effChose.setLoop(Integer.parseInt(this.txtLoop.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtLoopCountKeyReleased(KeyEvent evt) {
        try {
            this.drawMapScr.effChose.setLoopCount(Integer.parseInt(this.txtLoopCount.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtFromKeyReleased(KeyEvent evt) {
        try {
            this.drawMapScr.effChose.setIndexFrom(Integer.parseInt(this.txtFrom.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtToKeyReleased(KeyEvent evt) {
        try {
            this.drawMapScr.effChose.setIndexTo(Integer.parseInt(this.txtTo.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtTypeKeyReleased(KeyEvent evt) {
        try {
            this.drawMapScr.effChose.setType(Integer.parseInt(this.txtType.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    public void readTextMob(String data) {
        try {
            this.model1.setRowCount(0);
            this.drawMapScr.mobs.clear();
            JSONValue jv = new JSONValue();
            JSONArray dataArray = null;
            dataArray = (JSONArray) JSONValue.parse(data);
            for (int j = 0; j < dataArray.size(); j++) {
                JSONArray dtm = (JSONArray) JSONValue.parse(String.valueOf(dataArray.get(j)));
                int temp = Byte.parseByte(String.valueOf(dtm.get(0)));
                int level = Byte.parseByte(String.valueOf(dtm.get(1)));
                int hp = Integer.parseInt(String.valueOf(dtm.get(2)));
                int x = Short.parseShort(String.valueOf(dtm.get(3)));
                int y = Short.parseShort(String.valueOf(dtm.get(4)));
                this.drawMapScr.mobs.add(new MobMap(Manager.gI().getMobTemplates().get(temp), x, y, level, hp));
                dtm.clear();
            }
            fillToTable();
        } catch (Exception exception) {
        }
    }

    public void fillToTable() {
        this.model1.setRowCount(0);
        try {
            for (EffectMap eff : this.drawMapScr.effectMaps) {
                this.model1.addRow(new Object[]{Integer.valueOf(eff.getTemp().getId()), Integer.valueOf(eff.getTemp().getId()), Integer.valueOf(eff.getX()), Integer.valueOf(eff.getY()), Integer.valueOf(eff.getLayer()),
                    Integer.valueOf(eff.getLoop()), Integer.valueOf(eff.getLoopCount()), Integer.valueOf(eff.getIndexFrom()), Integer.valueOf(eff.getIndexTo()), Integer.valueOf(eff.getType())});
            }
            for (SubEffectMap eff : this.drawMapScr.subEffectMaps) {
                this.model1.addRow(new Object[]{Integer.valueOf(eff.getTypeEff()), "beff-" + eff.getTypeEff()});
            }
        } catch (Exception exception) {
        }
    }

    private void setup() {
        setResizable(false);
        setLocationRelativeTo(null);
        this.model1 = new DefaultTableModel((Object[]) new String[]{"ID", "Image", "x", "y", "Layer", "Loop", "Loop count", "From", "To", "Type"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.tbl1.setModel(this.model1);
        this.tbl1.setRowHeight(50);
        this.tbl1.getColumnModel().getColumn(1).setCellRenderer(new ImageRender());
        fillToTable();
    }

    private class ImageRender extends DefaultTableCellRenderer {

        private ImageRender() {
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            ImageIcon icon = null;
            String vl = value.toString();
            try {
                Image image = null;
                if (vl.startsWith("beff-")) {
                    image = Util.getImageBEffectByType(Integer.parseInt(vl.replaceAll("beff-", "")));
                    image = image.getScaledInstance(40, 40, 4);
                } else {
                    image = Util.getImageEffectById(Integer.parseInt(vl));
                    image = image.getScaledInstance(40, 40, 4);
                }
                icon = new ImageIcon(image);
            } catch (Exception ex) {
                ex.printStackTrace();
                return new JLabel(vl);
            }
            return new JLabel(icon);
        }
    }
}
