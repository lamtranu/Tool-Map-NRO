package tool.chart;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class PolarAreaChart extends JComponent {

    private final List<ModelPolarAreaChart> list = new ArrayList<>();
    private double maxValues;
    private double totalValues;
    private final int PADDING_BOTTOM = 30;
    private float animate;
    private final Animator animator;
    private JPanel panelLegend;

    public PolarAreaChart() {
        initComponents();
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        animator = new Animator(800, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                repaint();
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        createChart(g);
    }

    private void createChart(Graphics g) {
        int width = getWidth();
        int height = getHeight() - PADDING_BOTTOM;
        int space = 5;
        int size = Math.min(width, height) - space;
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (!list.isEmpty()) {
            DecimalFormat df = new DecimalFormat("#,##0.##");
            double startAngle = 90.0;
            for (ModelPolarAreaChart data : list) {
                g2.setColor(data.getColor());
                double angle = valuesToAngle(data.getValues());
                double rs = valuesToRealSize(data.getValues(), size) * animate;
                Shape s = new Arc2D.Double(x + (size - rs) / 2.0, y + (size - rs) / 2.0, rs, rs, startAngle, angle, Arc2D.PIE);
                g2.fill(s);
                g2.setStroke(new BasicStroke(2f));
                g2.setColor(Color.BLACK);
                g2.draw(s);
                drawValues(g2, df.format(data.getValues()), startAngle + angle / 2.0, rs / 2.0);
                startAngle += angle;
            }
        } else {
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawOval(x, y, size, size);
        }
        g2.dispose();
    }

    private void drawValues(Graphics2D g2, String values, double angle, double radius) {
        int centerX = getWidth() / 2;
        int centerY = (getHeight() - PADDING_BOTTOM) / 2;
        Point p = getLocation(angle, radius);
        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(values, g2);
        g2.drawString(values, (int) (centerX + p.x - r.getWidth() / 2), (int) (centerY - p.y + fm.getAscent() / 2));
    }

    private Shape createShape(double start, double end, double values) {
        int width = getWidth();
        int height = getHeight() - PADDING_BOTTOM;
        double x = (width - values) / 2.0;
        double y = (height - values) / 2.0;
        return new Arc2D.Double(x, y, values, values, start, end, Arc2D.PIE);
    }

    private double valuesToRealSize(double values, int size) {
        return values * size / maxValues;
    }

    private double valuesToAngle(double values) {
        return values * 360 / totalValues;
    }

    private Point getLocation(double angle, double radius) {
        double x = Math.cos(Math.toRadians(angle)) * radius;
        double y = Math.sin(Math.toRadians(angle)) * radius;
        return new Point((int) x, (int) y);
    }

    private void calculateValues() {
        maxValues = 0;
        totalValues = 0;
        for (ModelPolarAreaChart data : list) {
            maxValues = Math.max(maxValues, data.getValues());
            totalValues += data.getValues();
        }
    }

    public void addItem(ModelPolarAreaChart data) {
        list.add(data);
        calculateValues();
        repaint();

        if (panelLegend != null) {
            PolarAreaLabel label = new PolarAreaLabel();
            label.setText(data.getName());
            label.setBackground(data.getColor());
            panelLegend.add(label);
            panelLegend.revalidate();
            panelLegend.repaint();
        }
    }

    public void start() {
        animate = 0;
        if (!animator.isRunning()) animator.start();
    }

    public void clear() {
        animate = 0;
        list.clear();
        if (panelLegend != null) {
            panelLegend.removeAll();
            panelLegend.revalidate();
            panelLegend.repaint();
        }
        repaint();
    }

    private void initComponents() {
        panelLegend = new JPanel();
        panelLegend.setOpaque(false);
        panelLegend.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelLegend, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()
                                .addContainerGap(250, Short.MAX_VALUE)
                                .addComponent(panelLegend, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
    }
}
