package tool.draw_map;

import tool.button.Button;
import tool.db.Manager;
import tool.draw_map.template.MobMap;
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

public class MobList extends JFrame {

    private DrawMapScr drawMapScr;
    private DefaultTableModel model1;
    private int index;
    private Button button1;
    private Button button2;
    private Button button3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable tbl1;
    private JTextField txtHp;
    private JTextField txtLevel;
    private JTextArea txtText;
    private JTextField txtX;
    private JTextField txtY;

    public MobList(DrawMapScr drawMapScr) {
        this.drawMapScr = drawMapScr;
        initComponents();
        setup();
        setDefaultCloseOperation(2);
        setTitle("Twilight75 - List mob map");
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
        this.jLabel2 = new JLabel();
        this.txtLevel = new JTextField();
        this.txtHp = new JTextField();
        this.jLabel3 = new JLabel();
        this.txtX = new JTextField();
        this.jLabel4 = new JLabel();
        this.txtY = new JTextField();
        this.button3 = new Button();
        setDefaultCloseOperation(3);
        this.tbl1.setModel(new DefaultTableModel(new Object[0][], (Object[]) new String[0]));
        this.tbl1.setSelectionMode(0);
        this.tbl1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                MobList.this.tbl1MouseClicked(evt);
            }
        });
        this.tbl1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                MobList.this.tbl1KeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                MobList.this.tbl1KeyReleased(evt);
            }
        });
        this.jScrollPane2.setViewportView(this.tbl1);
        this.button1.setBackground(new Color(255, 0, 0));
        this.button1.setForeground(new Color(255, 255, 255));
        this.button1.setText("Clear all");
        this.button1.setFont(new Font("SansSerif", 1, 14));
        this.button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MobList.this.button1ActionPerformed(evt);
            }
        });
        this.button2.setBackground(new Color(0, 204, 0));
        this.button2.setForeground(new Color(255, 255, 255));
        this.button2.setText("Save");
        this.button2.setFont(new Font("SansSerif", 1, 14));
        this.button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MobList.this.button2ActionPerformed(evt);
            }
        });
        this.txtText.setColumns(20);
        this.txtText.setFont(new Font("SansSerif", 1, 14));
        this.txtText.setLineWrap(true);
        this.txtText.setRows(5);
        this.txtText.setWrapStyleWord(true);
        this.txtText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                MobList.this.txtTextKeyReleased(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.txtText);
        this.jLabel1.setFont(new Font("SansSerif", 1, 12));
        this.jLabel1.setText("Level");
        this.jLabel2.setFont(new Font("SansSerif", 1, 12));
        this.jLabel2.setText("Hp");
        this.txtLevel.setFont(new Font("SansSerif", 1, 12));
        this.txtLevel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                MobList.this.txtLevelKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                MobList.this.txtLevelKeyReleased(evt);
            }
        });
        this.txtHp.setFont(new Font("SansSerif", 1, 12));
        this.txtHp.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                MobList.this.txtHpKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                MobList.this.txtHpKeyReleased(evt);
            }
        });
        this.jLabel3.setFont(new Font("SansSerif", 1, 12));
        this.jLabel3.setText("X");
        this.txtX.setFont(new Font("SansSerif", 1, 12));
        this.txtX.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                MobList.this.txtXKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                MobList.this.txtXKeyReleased(evt);
            }
        });
        this.jLabel4.setFont(new Font("SansSerif", 1, 12));
        this.jLabel4.setText("Y");
        this.txtY.setFont(new Font("SansSerif", 1, 12));
        this.txtY.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                MobList.this.txtYKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                MobList.this.txtYKeyReleased(evt);
            }
        });
        this.button3.setBackground(new Color(255, 0, 0));
        this.button3.setForeground(new Color(255, 255, 255));
        this.button3.setText("Remove");
        this.button3.setFont(new Font("SansSerif", 1, 14));
        this.button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MobList.this.button3ActionPerformed(evt);
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
                                .addComponent((Component) this.button1, -1, 185, 32767)
                                .addComponent((Component) this.button2, -1, 185, 32767)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(this.jLabel2, -1, -1, 32767)
                                                .addComponent(this.jLabel1, -1, 55, 32767)
                                                .addComponent(this.jLabel3, -1, -1, 32767)
                                                .addComponent(this.jLabel4, -1, -1, 32767))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.txtLevel)
                                                .addComponent(this.txtHp)
                                                .addComponent(this.txtX)
                                                .addComponent(this.txtY)))
                                .addComponent((Component) this.button3, -1, 185, 32767)))
                .addComponent(this.jScrollPane1));
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.txtLevel, -1, 42, 32767)
                                                .addComponent(this.jLabel1, -2, 42, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.jLabel2, -2, 42, -2)
                                                .addComponent(this.txtHp))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.jLabel3, -2, 42, -2)
                                                .addComponent(this.txtX))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.jLabel4, -2, 42, -2)
                                                .addComponent(this.txtY))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button2, -2, 36, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button3, -2, 36, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button1, -2, 36, -2))
                                .addComponent(this.jScrollPane2, -2, 0, 32767))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.jScrollPane1, -2, -1, -2)));
        layout.linkSize(1, new Component[]{this.txtHp, this.txtLevel});
        layout.linkSize(1, new Component[]{this.jLabel3, this.txtX, this.txtY});
        pack();
    }

    private void tbl1MouseClicked(MouseEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            this.drawMapScr.setMobChose(this.drawMapScr.mobs.get(this.index));
            this.txtLevel.setText(this.drawMapScr.mobMapChose.getLevel() + "");
            this.txtHp.setText(this.drawMapScr.mobMapChose.getHp() + "");
            this.txtX.setText(this.drawMapScr.mobMapChose.getX() + "");
            this.txtY.setText(this.drawMapScr.mobMapChose.getY() + "");
        }
    }

    private void tbl1KeyPressed(KeyEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            this.drawMapScr.setMobChose(this.drawMapScr.mobs.get(this.index));
            this.txtLevel.setText(this.drawMapScr.mobMapChose.getLevel() + "");
            this.txtHp.setText(this.drawMapScr.mobMapChose.getHp() + "");
            this.txtX.setText(this.drawMapScr.mobMapChose.getX() + "");
            this.txtY.setText(this.drawMapScr.mobMapChose.getY() + "");
        }
    }

    private void tbl1KeyReleased(KeyEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            this.drawMapScr.setMobChose(this.drawMapScr.mobs.get(this.index));
            this.txtLevel.setText(this.drawMapScr.mobMapChose.getLevel() + "");
            this.txtHp.setText(this.drawMapScr.mobMapChose.getHp() + "");
            this.txtX.setText(this.drawMapScr.mobMapChose.getX() + "");
            this.txtY.setText(this.drawMapScr.mobMapChose.getY() + "");
        }
    }

    private void button1ActionPerformed(ActionEvent evt) {
        this.drawMapScr.mobs.clear();
        fillToTable();
    }

    private void button2ActionPerformed(ActionEvent evt) {
        try {
            System.out.println(this.txtText.getText());
            DatabaseManagers.executeUpdate("update map_template set mobs = ? where id = ?", new Object[]{this.txtText.getText(), Integer.valueOf(this.drawMapScr.mapId)});
            NotifyUtil.showMessageDialog(this, "Lthc");
        } catch (Exception exception) {
        }
    }

    private void txtTextKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == 10) {
            readTextMob(this.txtText.getText());
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

    private void txtLevelKeyPressed(KeyEvent evt) {
        if (this.drawMapScr.mobMapChose != null) {
            try {
                this.drawMapScr.mobMapChose.setLevel(Integer.parseInt(this.txtLevel.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtLevelKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.mobMapChose != null) {
            try {
                this.drawMapScr.mobMapChose.setLevel(Integer.parseInt(this.txtLevel.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtHpKeyPressed(KeyEvent evt) {
        if (this.drawMapScr.mobMapChose != null) {
            try {
                this.drawMapScr.mobMapChose.setHp(Integer.parseInt(this.txtHp.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtHpKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.mobMapChose != null) {
            try {
                this.drawMapScr.mobMapChose.setHp(Integer.parseInt(this.txtHp.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtXKeyPressed(KeyEvent evt) {
    }

    private void txtXKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.mobMapChose != null) {
            try {
                this.drawMapScr.mobMapChose.setX(Integer.parseInt(this.txtX.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtYKeyPressed(KeyEvent evt) {
    }

    private void txtYKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.mobMapChose != null) {
            try {
                this.drawMapScr.mobMapChose.setY(Integer.parseInt(this.txtY.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void button3ActionPerformed(ActionEvent evt) {
        this.drawMapScr.mobs.remove(this.drawMapScr.mobMapChose);
        fillToTable();
    }

    public void fillToTable() {
        this.model1.setRowCount(0);
        JSONArray dataArray = new JSONArray();
        for (MobMap mob : this.drawMapScr.mobs) {
            JSONArray ja = new JSONArray();
            ja.add(Integer.valueOf(mob.getTemp().getId()));
            ja.add(Integer.valueOf(mob.getLevel()));
            ja.add(Integer.valueOf(mob.getHp()));
            ja.add(Integer.valueOf(mob.getX()));
            ja.add(Integer.valueOf(mob.getY()));
            dataArray.add(ja.toJSONString());
            this.model1.addRow(new Object[]{Integer.valueOf(mob.getTemp().getId()), mob.getTemp().getName(), Integer.valueOf(mob.getTemp().getId()), Integer.valueOf(mob.getX()), Integer.valueOf(mob.getY()), Integer.valueOf(mob.getLevel()), Integer.valueOf(mob.getHp())});
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
        this.model1 = new DefaultTableModel((Object[]) new String[]{"ID", "Name", "Image", "x", "y", "level", "hp"}, 0) {
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
                Image image = Util.getImageMobById(Integer.parseInt(id), 0);
                image = image.getScaledInstance(40, 40, 4);
                icon = new ImageIcon(image);
            } catch (Exception exception) {
            }
            return new JLabel(icon);
        }
    }
}
