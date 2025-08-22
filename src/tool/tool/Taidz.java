package tool.tool;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import tool.db.DatabaseManagers;
import tool.db.Manager;
import tool.draw_map.DrawMapScr;

public class Taidz extends JFrame {
    public static Taidz I;

    public static Taidz gI() {
        if (I == null) I = new Taidz();
        return I;
    }

    private JDesktopPane desktop;

    public Taidz() {
        initComponents();

        // đóng DB khi đóng cửa sổ
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                DatabaseManagers.close();
            }
        });

        // mở DrawMapScr sau khi frame hiển thị
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                SwingUtilities.invokeLater(() -> openDrawMap());
                removeComponentListener(this); // chỉ mở 1 lần
            }

            @Override
            public void componentResized(ComponentEvent e) {
                resizeAllInternalFrames();
            }
        });
    }

    private void initComponents() {
        this.desktop = new JDesktopPane();
        this.desktop.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(this.desktop, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Draw Map Tool");
        setSize(1400,500);
        setLocationRelativeTo(null); // căn giữa
    }

    // mở DrawMapScr full desktop
    private void openDrawMap() {
        DrawMapScr drawMapScr = new DrawMapScr();
        drawMapScr.setClosable(true);
        drawMapScr.setResizable(true);
        drawMapScr.setMaximizable(true);

        drawMapScr.setSize(desktop.getSize());
        drawMapScr.setLocation(0, 0);
        drawMapScr.setVisible(true);

        desktop.add(drawMapScr);
        try {
            drawMapScr.setSelected(true);
        } catch (Exception ignored) {}
    }

    // resize tất cả JInternalFrame khi desktop thay đổi size
    private void resizeAllInternalFrames() {
        for (JInternalFrame frame : desktop.getAllFrames()) {
            frame.setSize(desktop.getSize());
            frame.setLocation(0, 0);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Manager.gI();
            Taidz map = new Taidz();
            map.setVisible(true);
        });
    }
}
