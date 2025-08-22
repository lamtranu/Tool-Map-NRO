package tool.draw_map;

import tool.draw_map.load.MobTemplate;
import tool.db.Manager;
import tool.utils.Util;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MobTable
        extends JFrame {

    private DrawMapScr drawMapScr;
    private DefaultTableModel model1;
    private JScrollPane jScrollPane2;
    private JTable tbl1;

    public MobTable(DrawMapScr drawMapScr) {
        /*  34 */ this.drawMapScr = drawMapScr;
        /*  35 */ initComponents();
        /*  36 */ setup();
        /*  37 */ setDefaultCloseOperation(2);
        /*  38 */ setTitle("Twilight75 - Mob template");
    }

    private void initComponents() {
        /*  45 */ this.jScrollPane2 = new JScrollPane();
        /*  46 */ this.tbl1 = new JTable();
        /*  48 */ setDefaultCloseOperation(3);
        /*  50 */ this.tbl1.setModel(new DefaultTableModel(new Object[0][], (Object[]) new String[0]));
        /*  58 */ this.tbl1.setSelectionMode(0);
        /*  59 */ this.tbl1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                /*  61 */ MobTable.this.tbl1MouseClicked(evt);
            }
        });
        /*  64 */ this.tbl1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                /*  66 */ MobTable.this.tbl1KeyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                /*  69 */ MobTable.this.tbl1KeyReleased(evt);
            }
        });
        /*  72 */ this.jScrollPane2.setViewportView(this.tbl1);
        /*  74 */ GroupLayout layout = new GroupLayout(getContentPane());
        /*  75 */ getContentPane().setLayout(layout);
        /*  76 */ layout.setHorizontalGroup(layout
                /*  77 */.createParallelGroup(GroupLayout.Alignment.LEADING)
                /*  78 */.addComponent(this.jScrollPane2, -2, -1, -2));
        /*  80 */ layout.setVerticalGroup(layout
                /*  81 */.createParallelGroup(GroupLayout.Alignment.LEADING)
                /*  82 */.addComponent(this.jScrollPane2, -2, 274, -2));
        /*  85 */ pack();
    }

    private void tbl1MouseClicked(MouseEvent evt) {
        /*  89 */ int index = this.tbl1.getSelectedRow();
        /*  90 */ if (index != -1) {
            /*  91 */ this.drawMapScr.setMobtemplateChoose(Manager.gI().getMobTemplates().get(index));
        }
    }

    private void tbl1KeyPressed(KeyEvent evt) {
        /*  96 */ int index = this.tbl1.getSelectedRow();
        /*  97 */ if (index != -1) {
            /*  98 */ this.drawMapScr.setMobtemplateChoose(Manager.gI().getMobTemplates().get(index));
        }
    }

    private void tbl1KeyReleased(KeyEvent evt) {
        /* 103 */ int index = this.tbl1.getSelectedRow();
        /* 104 */ if (index != -1) {
            /* 105 */ this.drawMapScr.setMobtemplateChoose(Manager.gI().getMobTemplates().get(index));
        }
    }

    private void fillToTable() {
        /* 110 */ this.model1.setRowCount(0);
        /* 112 */ for (MobTemplate mob : Manager.gI().getMobTemplates()) {
            /* 114 */ this.model1.addRow(new Object[]{
                /* 115 */Integer.valueOf(mob.getId()), mob.getName(), Integer.valueOf(mob.getId())
            });
        }
    }

    private void setup() {
        /* 121 */ setResizable(false);
        /* 122 */ setLocationRelativeTo(null);
        /* 124 */ this.model1 = new DefaultTableModel((Object[]) new String[]{"ID", "Name", "Image"}, 0) {
            public boolean isCellEditable(int row, int column) {
                /* 127 */ return false;
            }
        };
        /* 130 */ this.tbl1.setModel(this.model1);
        /* 131 */ this.tbl1.setRowHeight(50);
        /* 132 */ this.tbl1.getColumnModel().getColumn(2).setCellRenderer(new ImageRender());
        /* 133 */ fillToTable();
    }

    public static void main(String[] args) {
        try {
            /* 143 */ for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                /* 144 */ if ("Nimbus".equals(info.getName())) {
                    /* 145 */ UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            /* 149 */        } catch (ClassNotFoundException ex) {
            /* 150 */ Logger.getLogger(MobTable.class.getName()).log(Level.SEVERE, (String) null, ex);
            /* 151 */        } catch (InstantiationException ex) {
            /* 152 */ Logger.getLogger(MobTable.class.getName()).log(Level.SEVERE, (String) null, ex);
            /* 153 */        } catch (IllegalAccessException ex) {
            /* 154 */ Logger.getLogger(MobTable.class.getName()).log(Level.SEVERE, (String) null, ex);
            /* 155 */        } catch (UnsupportedLookAndFeelException ex) {
            /* 156 */ Logger.getLogger(MobTable.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        /* 164 */ EventQueue.invokeLater(new Runnable() {
            public void run() {
                /* 166 */ (new MobTable(null)).setVisible(true);
            }
        });
    }

    private class ImageRender
            extends DefaultTableCellRenderer {

        private ImageRender() {
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            /* 180 */ ImageIcon icon = null;
            try {
                /* 182 */ String id = value.toString();
                /* 183 */ Image image = Util.getImageMobById(Integer.parseInt(id), 0);
                /* 184 */ image = image.getScaledInstance(40, 40, 4);
                /* 185 */ icon = new ImageIcon(image);
                /* 186 */            } catch (Exception exception) {
            }
            /* 188 */ return new JLabel(icon);
        }
    }
}
