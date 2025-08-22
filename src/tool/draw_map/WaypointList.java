package tool.draw_map;

import tool.button.Button;
import tool.draw_map.template.Waypoint;
import tool.utils.NotifyUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import tool.db.DatabaseManagers;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class WaypointList extends JFrame {

    private DrawMapScr drawMapScr;
    private DefaultTableModel model1;
    private int index;
    private Button button1;
    private Button button2;
    private Button button3;
    private JCheckBox chkEnter;
    private JCheckBox chkOffline;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable tbl1;
    private JTextField txtGoX;
    private JTextField txtGoY;
    private JTextField txtH;
    private JTextField txtMapgo;
    private JTextField txtName;
    private JTextArea txtText;
    private JTextField txtW;
    private JTextField txtX;
    private JTextField txtY;

    public WaypointList(DrawMapScr drawMapScr) {
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
        this.txtName = new JTextField();
        this.txtMapgo = new JTextField();
        this.jLabel3 = new JLabel();
        this.txtGoX = new JTextField();
        this.jLabel4 = new JLabel();
        this.txtGoY = new JTextField();
        this.jLabel5 = new JLabel();
        this.chkEnter = new JCheckBox();
        this.chkOffline = new JCheckBox();
        this.txtX = new JTextField();
        this.jLabel2 = new JLabel();
        this.jLabel6 = new JLabel();
        this.txtY = new JTextField();
        this.txtW = new JTextField();
        this.jLabel7 = new JLabel();
        this.jLabel8 = new JLabel();
        this.txtH = new JTextField();
        this.button3 = new Button();
        setDefaultCloseOperation(3);
        this.tbl1.setModel(new DefaultTableModel(new Object[0][], (Object[]) new String[0]));
        this.tbl1.setSelectionMode(0);
        this.tbl1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                WaypointList.this.tbl1MouseClicked(evt);
            }
        });
        this.tbl1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                WaypointList.this.tbl1KeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                WaypointList.this.tbl1KeyReleased(evt);
            }
        });
        this.jScrollPane2.setViewportView(this.tbl1);
        this.button1.setBackground(new Color(255, 0, 0));
        this.button1.setForeground(new Color(255, 255, 255));
        this.button1.setText("Clear all");
        this.button1.setFont(new Font("SansSerif", 1, 14));
        this.button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WaypointList.this.button1ActionPerformed(evt);
            }
        });
        this.button2.setBackground(new Color(0, 204, 0));
        this.button2.setForeground(new Color(255, 255, 255));
        this.button2.setText("Save");
        this.button2.setFont(new Font("SansSerif", 1, 14));
        this.button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WaypointList.this.button2ActionPerformed(evt);
            }
        });
        this.txtText.setColumns(20);
        this.txtText.setFont(new Font("SansSerif", 1, 14));
        this.txtText.setLineWrap(true);
        this.txtText.setRows(5);
        this.txtText.setWrapStyleWord(true);
        this.txtText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                WaypointList.this.txtTextKeyReleased(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.txtText);
        this.jLabel1.setFont(new Font("SansSerif", 1, 12));
        this.jLabel1.setText("Name");
        this.txtName.setFont(new Font("SansSerif", 1, 12));
        this.txtName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                WaypointList.this.txtNameKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                WaypointList.this.txtNameKeyReleased(evt);
            }
        });
        this.txtMapgo.setFont(new Font("SansSerif", 1, 12));
        this.txtMapgo.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                WaypointList.this.txtMapgoKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                WaypointList.this.txtMapgoKeyReleased(evt);
            }
        });
        this.jLabel3.setFont(new Font("SansSerif", 1, 12));
        this.jLabel3.setText("Map go");
        this.txtGoX.setFont(new Font("SansSerif", 1, 12));
        this.txtGoX.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                WaypointList.this.txtGoXKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                WaypointList.this.txtGoXKeyReleased(evt);
            }
        });
        this.jLabel4.setFont(new Font("SansSerif", 1, 12));
        this.jLabel4.setText("Go x");
        this.txtGoY.setFont(new Font("SansSerif", 1, 12));
        this.txtGoY.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                WaypointList.this.txtGoYKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                WaypointList.this.txtGoYKeyReleased(evt);
            }
        });
        this.jLabel5.setFont(new Font("SansSerif", 1, 12));
        this.jLabel5.setText("Go y");
        this.chkEnter.setFont(new Font("SansSerif", 1, 12));
        this.chkEnter.setText("Is enter");
        this.chkEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WaypointList.this.chkEnterActionPerformed(evt);
            }
        });
        this.chkOffline.setFont(new Font("SansSerif", 1, 12));
        this.chkOffline.setText("Is offline");
        this.chkOffline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WaypointList.this.chkOfflineActionPerformed(evt);
            }
        });
        this.txtX.setFont(new Font("SansSerif", 1, 12));
        this.txtX.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                WaypointList.this.txtXKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                WaypointList.this.txtXKeyReleased(evt);
            }
        });
        this.jLabel2.setFont(new Font("SansSerif", 1, 12));
        this.jLabel2.setText("X");
        this.jLabel6.setFont(new Font("SansSerif", 1, 12));
        this.jLabel6.setText("Y");
        this.txtY.setFont(new Font("SansSerif", 1, 12));
        this.txtY.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                WaypointList.this.txtYKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                WaypointList.this.txtYKeyReleased(evt);
            }
        });
        this.txtW.setFont(new Font("SansSerif", 1, 12));
        this.txtW.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                WaypointList.this.txtWKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                WaypointList.this.txtWKeyReleased(evt);
            }
        });
        this.jLabel7.setFont(new Font("SansSerif", 1, 12));
        this.jLabel7.setText("Width");
        this.jLabel8.setFont(new Font("SansSerif", 1, 12));
        this.jLabel8.setText("Height");
        this.txtH.setFont(new Font("SansSerif", 1, 12));
        this.txtH.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                WaypointList.this.txtHKeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                WaypointList.this.txtHKeyReleased(evt);
            }
        });
        this.button3.setBackground(new Color(255, 0, 0));
        this.button3.setForeground(new Color(255, 255, 255));
        this.button3.setText("Remove");
        this.button3.setFont(new Font("SansSerif", 1, 14));
        this.button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WaypointList.this.button3ActionPerformed(evt);
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
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 61, 32767)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.chkOffline, -2, 304, -2)
                                                .addComponent(this.chkEnter, -2, 304, -2))
                                        .addContainerGap())
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(this.jLabel3, -1, -1, 32767)
                                                .addComponent(this.jLabel1, -1, 55, 32767)
                                                .addComponent(this.jLabel4, -1, -1, 32767)
                                                .addComponent(this.jLabel5, -1, -1, 32767)
                                                .addComponent(this.jLabel2, -1, 55, 32767)
                                                .addComponent(this.jLabel6, -1, 55, 32767))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent((Component) this.button1, GroupLayout.Alignment.TRAILING, -1, -1, 32767)
                                                .addComponent((Component) this.button2, GroupLayout.Alignment.TRAILING, -1, -1, 32767)
                                                .addComponent(this.txtGoX)
                                                .addComponent(this.txtGoY)
                                                .addComponent(this.txtName)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(this.txtY, GroupLayout.Alignment.LEADING, -1, 103, 32767)
                                                                .addComponent(this.txtX, GroupLayout.Alignment.LEADING))
                                                        .addGap(26, 26, 26)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(this.jLabel8, -2, 36, -2)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(this.txtH))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(this.jLabel7, -2, 36, -2)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(this.txtW))))
                                                .addComponent(this.txtMapgo)
                                                .addComponent((Component) this.button3, GroupLayout.Alignment.TRAILING, -1, -1, 32767)))))
                .addComponent(this.jScrollPane1));
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(this.txtName, -1, 42, 32767)
                                                .addComponent(this.jLabel1, -2, 42, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(this.txtX)
                                                        .addComponent(this.jLabel7, -2, 42, -2)
                                                        .addComponent(this.txtW, -2, 42, -2))
                                                .addComponent(this.jLabel2, -2, 42, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(this.txtY)
                                                        .addComponent(this.jLabel8, -2, 42, -2)
                                                        .addComponent(this.txtH, -2, 42, -2))
                                                .addComponent(this.jLabel6, -2, 42, -2))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(this.chkEnter)
                                        .addGap(4, 4, 4)
                                        .addComponent(this.chkOffline)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.jLabel3, -2, 42, -2)
                                                .addComponent(this.txtMapgo))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.jLabel4, -2, 42, -2)
                                                .addComponent(this.txtGoX))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.jLabel5, -2, 42, -2)
                                                .addComponent(this.txtGoY))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button2, -2, 36, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button3, -2, 36, -2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent((Component) this.button1, -2, 36, -2))
                                .addComponent(this.jScrollPane2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.jScrollPane1, -2, -1, -2)
                        .addContainerGap()));
        layout.linkSize(1, new Component[]{this.chkEnter, this.chkOffline});
        layout.linkSize(1, new Component[]{this.txtGoX, this.txtGoY, this.txtMapgo, this.txtName});
        layout.linkSize(1, new Component[]{this.txtH, this.txtX, this.txtY});
        pack();
    }

    private void tbl1MouseClicked(MouseEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            this.drawMapScr.setWaypointChose(this.drawMapScr.waypoints.get(this.index));
            this.txtName.setText(this.drawMapScr.wpChose.getName());
            this.chkEnter.setSelected(this.drawMapScr.wpChose.isEnter());
            this.chkOffline.setSelected(this.drawMapScr.wpChose.isOffline());
            this.txtMapgo.setText(this.drawMapScr.wpChose.getMapGo() + "");
            this.txtGoX.setText(this.drawMapScr.wpChose.getGoX() + "");
            this.txtGoY.setText(this.drawMapScr.wpChose.getGoY() + "");
            this.txtX.setText(this.drawMapScr.wpChose.getX() + "");
            this.txtY.setText(this.drawMapScr.wpChose.getY() + "");
            this.txtW.setText(this.drawMapScr.wpChose.getW() + "");
            this.txtH.setText(this.drawMapScr.wpChose.getH() + "");
        }
    }

    private void tbl1KeyPressed(KeyEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            this.drawMapScr.setWaypointChose(this.drawMapScr.waypoints.get(this.index));
            this.txtName.setText(this.drawMapScr.wpChose.getName());
            this.chkEnter.setSelected(this.drawMapScr.wpChose.isEnter());
            this.chkOffline.setSelected(this.drawMapScr.wpChose.isOffline());
            this.txtMapgo.setText(this.drawMapScr.wpChose.getMapGo() + "");
            this.txtGoX.setText(this.drawMapScr.wpChose.getGoX() + "");
            this.txtGoY.setText(this.drawMapScr.wpChose.getGoY() + "");
            this.txtX.setText(this.drawMapScr.wpChose.getX() + "");
            this.txtY.setText(this.drawMapScr.wpChose.getY() + "");
            this.txtW.setText(this.drawMapScr.wpChose.getW() + "");
            this.txtH.setText(this.drawMapScr.wpChose.getH() + "");
        }
    }

    private void tbl1KeyReleased(KeyEvent evt) {
        this.index = this.tbl1.getSelectedRow();
        if (this.index != -1) {
            this.drawMapScr.setWaypointChose(this.drawMapScr.waypoints.get(this.index));
            this.txtName.setText(this.drawMapScr.wpChose.getName());
            this.chkEnter.setSelected(this.drawMapScr.wpChose.isEnter());
            this.chkOffline.setSelected(this.drawMapScr.wpChose.isOffline());
            this.txtMapgo.setText(this.drawMapScr.wpChose.getMapGo() + "");
            this.txtGoX.setText(this.drawMapScr.wpChose.getGoX() + "");
            this.txtGoY.setText(this.drawMapScr.wpChose.getGoY() + "");
            this.txtX.setText(this.drawMapScr.wpChose.getX() + "");
            this.txtY.setText(this.drawMapScr.wpChose.getY() + "");
            this.txtW.setText(this.drawMapScr.wpChose.getW() + "");
            this.txtH.setText(this.drawMapScr.wpChose.getH() + "");
        }
    }

    private void button1ActionPerformed(ActionEvent evt) {
        this.drawMapScr.waypoints.clear();
        fillToTable();
    }

    private void button2ActionPerformed(ActionEvent evt) {
        try {
            System.out.println(this.txtText.getText());
            DatabaseManagers.executeUpdate("update map_template set waypoints = ? where id = ?", new Object[]{this.txtText.getText(), Integer.valueOf(this.drawMapScr.mapId)});
            NotifyUtil.showMessageDialog(this, "Lthc");
        } catch (Exception exception) {
        }
    }

    private void txtNameKeyPressed(KeyEvent evt) {
    }

    private void txtNameKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            try {
                this.drawMapScr.wpChose.setName(this.txtName.getText());
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtMapgoKeyPressed(KeyEvent evt) {
    }

    private void txtMapgoKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            try {
                this.drawMapScr.wpChose.setMapGo(Integer.parseInt(this.txtMapgo.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtGoXKeyPressed(KeyEvent evt) {
    }

    private void txtGoXKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            try {
                this.drawMapScr.wpChose.setGoX(Integer.parseInt(this.txtGoX.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtGoYKeyPressed(KeyEvent evt) {
    }

    private void txtGoYKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            try {
                this.drawMapScr.wpChose.setGoY(Integer.parseInt(this.txtGoY.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtTextKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == 10) {
            readTextWaypoint(this.txtText.getText());
        }
    }

    public void readTextWaypoint(String data) {
        try {
            this.model1.setRowCount(0);
            this.drawMapScr.waypoints.clear();
            JSONValue jv = new JSONValue();
            JSONArray dataArray = null;
            dataArray = (JSONArray) JSONValue.parse(data
                    .replaceAll("\\[\"\\[", "[[")
                    .replaceAll("\\]\"\\]", "]]")
                    .replaceAll("\",\"", ","));
            for (int j = 0; j < dataArray.size(); j++) {
                JSONArray dtwp = (JSONArray) JSONValue.parse(String.valueOf(dataArray.get(j)));
                String name = String.valueOf(dtwp.get(0));
                int x = Short.parseShort(String.valueOf(dtwp.get(1)));
                int y = Short.parseShort(String.valueOf(dtwp.get(2)));
                int x1 = Short.parseShort(String.valueOf(dtwp.get(3)));
                int y1 = Short.parseShort(String.valueOf(dtwp.get(4)));
                boolean enter = (Byte.parseByte(String.valueOf(dtwp.get(5))) == 1);
                boolean off = (Byte.parseByte(String.valueOf(dtwp.get(6))) == 1);
                int goMap = Short.parseShort(String.valueOf(dtwp.get(7)));
                int goX = Short.parseShort(String.valueOf(dtwp.get(8)));
                int goY = Short.parseShort(String.valueOf(dtwp.get(9)));
                this.drawMapScr.waypoints.add(new Waypoint(name, x, y, x1 - x, y1 - y, enter, off, goMap, goX, goY));
                dtwp.clear();
            }
            fillToTable();
        } catch (Exception exception) {
        }
    }

    private void chkEnterActionPerformed(ActionEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            this.drawMapScr.wpChose.setEnter(this.chkEnter.isSelected());
            fillToTable();
        }
    }

    private void chkOfflineActionPerformed(ActionEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            this.drawMapScr.wpChose.setOffline(this.chkOffline.isSelected());
            fillToTable();
        }
    }

    private void txtXKeyPressed(KeyEvent evt) {
    }

    private void txtXKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            try {
                this.drawMapScr.wpChose.setX(Integer.parseInt(this.txtX.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtYKeyPressed(KeyEvent evt) {
    }

    private void txtYKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            try {
                this.drawMapScr.wpChose.setY(Integer.parseInt(this.txtY.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtWKeyPressed(KeyEvent evt) {
    }

    private void txtWKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            try {
                this.drawMapScr.wpChose.setW(Integer.parseInt(this.txtW.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void txtHKeyPressed(KeyEvent evt) {
    }

    private void txtHKeyReleased(KeyEvent evt) {
        if (this.drawMapScr.wpChose != null) {
            try {
                this.drawMapScr.wpChose.setH(Integer.parseInt(this.txtH.getText()));
            } catch (Exception exception) {
            }
            fillToTable();
        }
    }

    private void button3ActionPerformed(ActionEvent evt) {
        this.drawMapScr.waypoints.remove(this.drawMapScr.wpChose);
        fillToTable();
    }

    public void fillToTable() {
        this.model1.setRowCount(0);
        JSONArray dataArray = new JSONArray();
        for (Waypoint wp : this.drawMapScr.waypoints) {
            JSONArray ja = new JSONArray();
            ja.add(wp.getName());
            ja.add(Integer.valueOf(wp.getX()));
            ja.add(Integer.valueOf(wp.getY()));
            ja.add(Integer.valueOf(wp.getW() + wp.getX()));
            ja.add(Integer.valueOf(wp.getH() + wp.getY()));
            ja.add(Integer.valueOf(wp.isEnter() ? 1 : 0));
            ja.add(Integer.valueOf(wp.isOffline() ? 1 : 0));
            ja.add(Integer.valueOf(wp.getMapGo()));
            ja.add(Integer.valueOf(wp.getGoX()));
            ja.add(Integer.valueOf(wp.getGoY()));
            dataArray.add(ja.toJSONString());
            this.model1.addRow(new Object[]{wp
                .getName(), Integer.valueOf(wp.getX()), Integer.valueOf(wp.getY()), Integer.valueOf(wp.getW()), Integer.valueOf(wp.getH()), Boolean.valueOf(wp.isEnter()), Boolean.valueOf(wp.isOffline()), Integer.valueOf(wp.getMapGo()), Integer.valueOf(wp.getGoX()), Integer.valueOf(wp.getGoY())});
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
        this.model1 = new DefaultTableModel((Object[]) new String[]{"Name", "X", "Y", "W", "H", "Enter", "Offline", "Map go", "GoX", "GoY"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.tbl1.setModel(this.model1);
        fillToTable();
    }
}
