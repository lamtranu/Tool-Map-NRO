package tool.draw_map;

import tool.button.Button;
import tool.db.Manager;
import tool.draw_map.template.NpcMap;
import tool.utils.NotifyUtil;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import tool.db.DatabaseManagers;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class NpcList extends JFrame {

    private DrawMapScr drawMapScr;
    private DefaultTableModel model1;
    private int index;
    private Button button1;
    private Button button2;
    private Button button3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable tbl1;
    private JTextArea txtText;
    private JTextField txtX;
    private JTextField txtY;

    public NpcList(DrawMapScr drawMapScr) {
        this.drawMapScr = drawMapScr;
        initComponents();
        setup();
        setDefaultCloseOperation(2);
        setTitle("Twilight75 - List npc map");
        setAlwaysOnTop(true);
    }

    private void initComponents() {
        this.jScrollPane2 = new JScrollPane();
        this.tbl1 = new JTable();
        this.button1 = new Button();
        this.button2 = new Button();
        this.jScrollPane1 = new JScrollPane();
        this.txtText = new JTextArea();
        this.jLabel1 = new JLabel();
        this.txtX = new JTextField();
        this.txtY = new JTextField();
        this.jLabel2 = new JLabel();
        this.button3 = new Button();
        setDefaultCloseOperation(3);
        this.tbl1.setModel(new DefaultTableModel(new Object[0][], (Object[]) new String[0]));
        this.tbl1.setSelectionMode(0);
        this.tbl1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                NpcList.this.tbl1MouseClicked(evt);
            }
        });
        this.tbl1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                NpcList.this.tbl1KeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                NpcList.this.tbl1KeyReleased(evt);
            }
        });
        this.jScrollPane2.setViewportView(this.tbl1);
        this.button1.setBackground(new Color(255, 0, 0));
        this.button1.setForeground(new Color(255, 255, 255));
        this.button1.setText("Clear all");
        this.button1.setFont(new Font("SansSerif", 1, 14));
        this.button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NpcList.this.button1ActionPerformed(evt);
            }
        });
        this.button2.setBackground(new Color(0, 204, 0));
        this.button2.setForeground(new Color(255, 255, 255));
        this.button2.setText("Save");
        this.button2.setFont(new Font("SansSerif", 1, 14));
        this.button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NpcList.this.button2ActionPerformed(evt);
            }
        });
        this.txtText.setColumns(20);
        this.txtText.setFont(new Font("SansSerif", 1, 14));
        this.txtText.setLineWrap(true);
        this.txtText.setRows(5);
        this.txtText.setWrapStyleWord(true);
        this.txtText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                NpcList.this.txtTextKeyReleased(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.txtText);
        this.jLabel1.setFont(new Font("SansSerif", 1, 12));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("X");
        this.txtX.setFont(new Font("SansSerif", 1, 12));
        this.txtX.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                NpcList.this.txtXKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                NpcList.this.txtXKeyReleased(evt);
            }
        });
        this.txtY.setFont(new Font("SansSerif", 1, 12));
        this.txtY.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                NpcList.this.txtYKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                NpcList.this.txtYKeyReleased(evt);
            }
        });
        this.jLabel2.setFont(new Font("SansSerif", 1, 12));
        this.jLabel2.setHorizontalAlignment(0);
        this.jLabel2.setText("Y");
        this.button3.setBackground(new Color(255, 0, 0));
        this.button3.setForeground(new Color(255, 255, 255));
        this.button3.setText("Remove");
        this.button3.setFont(new Font("SansSerif", 1, 14));
        this.button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NpcList.this.button3ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(this.jScrollPane2, -2, 423, -2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent((Component) this.button1, -1, 201, 32767)
                                .addComponent((Component) this.button2, -1, 201, 32767)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel1, -2, 39, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(this.txtX))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel2, -2, 39, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(this.txtY))
                                .addComponent((Component) this.button3, -1, 201, 32767)))
                .addComponent(this.jScrollPane1));
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(this.jScrollPane2, -2, 274, -2)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel1, -1, -1, 32767)
                                                .addComponent(this.txtX, -1, 38, 32767))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.jLabel2, -1, -1, 32767)
                                                .addComponent(this.txtY, -1, 38, 32767))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
                                        .addComponent((Component) this.button2, -2, 36, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button3, -2, 36, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button1, -2, 36, -2)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.jScrollPane1, -2, -1, -2)));
        pack();
    }

    private void tbl1MouseClicked(MouseEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            this.drawMapScr.setNpcMapChose(this.drawMapScr.npcs.get(this.index));
            this.txtX.setText(((NpcMap) this.drawMapScr.npcs.get(this.index)).getX() + "");
            this.txtY.setText(((NpcMap) this.drawMapScr.npcs.get(this.index)).getY() + "");
        }
    }

    private void tbl1KeyPressed(KeyEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            this.drawMapScr.setNpcMapChose(this.drawMapScr.npcs.get(this.index));
            this.txtX.setText(((NpcMap) this.drawMapScr.npcs.get(this.index)).getX() + "");
            this.txtY.setText(((NpcMap) this.drawMapScr.npcs.get(this.index)).getY() + "");
        }
    }

    private void tbl1KeyReleased(KeyEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            this.drawMapScr.setNpcMapChose(this.drawMapScr.npcs.get(this.index));
            this.txtX.setText(((NpcMap) this.drawMapScr.npcs.get(this.index)).getX() + "");
            this.txtY.setText(((NpcMap) this.drawMapScr.npcs.get(this.index)).getY() + "");
        }
    }

    private void button1ActionPerformed(ActionEvent evt) {
        this.drawMapScr.npcs.clear();
        fillToTable();
    }

    private void button2ActionPerformed(ActionEvent evt) {
        try {
            System.out.println(this.txtText.getText());
            DatabaseManagers.executeUpdate("update map_template set npcs = ? where id = ?", new Object[]{this.txtText.getText(), Integer.valueOf(this.drawMapScr.mapId)});
            NotifyUtil.showMessageDialog(this, "Lthc");
        } catch (Exception exception) {
        }
    }

    public void readDataNpc(String data) {
        try {
            this.model1.setRowCount(0);
            this.drawMapScr.npcs.clear();
            JSONValue jv = new JSONValue();
            JSONArray dataArray = null;
            dataArray = (JSONArray) JSONValue.parse(data);
            for (int j = 0; j < dataArray.size(); j++) {
                JSONArray dtn = (JSONArray) JSONValue.parse(String.valueOf(dataArray.get(j)));
                int temp = Byte.parseByte(String.valueOf(dtn.get(0)));
                int x = Short.parseShort(String.valueOf(dtn.get(1)));
                int y = Short.parseShort(String.valueOf(dtn.get(2)));
                this.drawMapScr.npcs.add(new NpcMap(Manager.gI().getNpcTemplateById(temp), x, y));
                dtn.clear();
            }
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtTextKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == 10) {
            readDataNpc(this.txtText.getText());
        }
    }

    private void txtXKeyPressed(KeyEvent evt) {
        try {
            ((NpcMap) this.drawMapScr.npcs.get(this.index)).setX(Integer.parseInt(this.txtX.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtXKeyReleased(KeyEvent evt) {
        try {
            ((NpcMap) this.drawMapScr.npcs.get(this.index)).setX(Integer.parseInt(this.txtX.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtYKeyPressed(KeyEvent evt) {
        try {
            ((NpcMap) this.drawMapScr.npcs.get(this.index)).setY(Integer.parseInt(this.txtY.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void txtYKeyReleased(KeyEvent evt) {
        try {
            ((NpcMap) this.drawMapScr.npcs.get(this.index)).setY(Integer.parseInt(this.txtY.getText()));
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void button3ActionPerformed(ActionEvent evt) {
        this.drawMapScr.npcs.remove(this.drawMapScr.npcMapChose);
        fillToTable();
    }

    public void fillToTable() {
        this.model1.setRowCount(0);
        JSONArray dataArray = new JSONArray();
        for (NpcMap npc : this.drawMapScr.npcs) {
            JSONArray ja = new JSONArray();
            ja.add(Integer.valueOf(npc.getTemp().getId()));
            ja.add(Integer.valueOf(npc.getX()));
            ja.add(Integer.valueOf(npc.getY()));
            dataArray.add(ja.toJSONString());
            this.model1.addRow(new Object[]{Integer.valueOf(npc.getTemp().getId()), npc.getTemp().getName(), Integer.valueOf(npc.getTemp().getAvatar()), Integer.valueOf(npc.getX()), Integer.valueOf(npc.getY())});
        }
        this.txtText.setText(dataArray.toJSONString()
                .replaceAll("\\\\", "")
                .replaceAll("\\[\\\"\\[", "[[")
                .replaceAll("\\]\\\"\\,\\\"\\[", "],[")
                .replaceAll("\\]\\\"\\]", "]]"));
    }

    private void setup() {
        setResizable(false);
        setLocationRelativeTo(null);
        this.model1 = new DefaultTableModel((Object[]) new String[]{"ID", "Name", "Avatar", "x", "y"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.tbl1.setModel(this.model1);
        this.tbl1.setRowHeight(50);
        this.tbl1.getColumnModel().getColumn(2).setCellRenderer(new ImageRender());
        fillToTable();
    }

    private class ImageRender extends DefaultTableCellRenderer {

        private ImageRender() {
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            ImageIcon icon = null;
            try {
                String id = value.toString();
                Image image = Util.getImageById(Integer.parseInt(id), 2);
                image = image.getScaledInstance(40, 40, 4);
                icon = new ImageIcon(image);
            } catch (Exception exception) {
            }
            return new JLabel(icon);
        }
    }
}
