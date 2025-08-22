package tool.chart;

import tool.chart.blankchart.BlankPlotChart;
import tool.chart.blankchart.BlankPlotChatRender;
import tool.chart.blankchart.SeriesSize;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Chart extends JPanel {

    private static final int SERIES_SIZE = 18;
    private static final int SERIES_SPACE = 10;
    private static final int BAR_DEPTH = 6;
    private static final int BAR_WIDTH = 18;

    private final DecimalFormat df = new DecimalFormat("#,##0.##");
    private final List<ModelLegend> legends = new ArrayList<>();
    private final List<ModelChart> model = new ArrayList<>();
    private final Animator animator;
    private float animate = 0f;
    private String showLabel;
    private Point labelLocation = new Point();
    private BlankPlotChart blankPlotChart;
    private JPanel panelLegend;

    public Chart() {
        initComponents();
        setOpaque(false);

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

        blankPlotChart.setBlankPlotChatRender(new BlankPlotChatRender() {
            @Override
            public int getMaxLegend() {
                return legends.size();
            }

            @Override
            public String getLabelText(int index) {
                return model.get(index).getLabel();
            }

            @Override
            public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index) {
                double totalWidth = SERIES_SIZE * legends.size() + SERIES_SPACE * (legends.size() - 1);
                double x = (size.getWidth() - totalWidth) / 2.0;

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

                for (int i = 0; i < legends.size(); i++) {
                    ModelLegend legend = legends.get(i);
                    double value = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight()) * animate;

                    int s = 9;
                    int sy = BAR_DEPTH;

                    // 3D bar polygons
                    int[] px = { (int)(size.getX() + x), (int)(size.getX() + x + s), (int)(size.getX() + x + s), (int)(size.getX() + x) };
                    int[] py = { (int)(size.getY() + size.getHeight() - value),
                                 (int)(size.getY() + size.getHeight() - value + sy),
                                 (int)(size.getY() + size.getHeight() + sy),
                                 (int)(size.getY() + size.getHeight()) };

                    GradientPaint gp = new GradientPaint((int)(size.getX() + x - s), 0, legend.getColorLight(),
                                                         (int)(size.getX() + x + s), 0, legend.getColor());
                    g2.setPaint(gp);
                    g2.fillPolygon(px, py, px.length);

                    int[] px1 = { (int)(size.getX() + x + s), (int)(size.getX() + x + BAR_WIDTH),
                                  (int)(size.getX() + x + BAR_WIDTH), (int)(size.getX() + x + s) };
                    int[] py1 = { (int)(size.getY() + size.getHeight() - value + sy),
                                  (int)(size.getY() + size.getHeight() - value),
                                  (int)(size.getY() + size.getHeight()),
                                  (int)(size.getY() + size.getHeight() + sy) };
                    g2.setColor(legend.getColorLight());
                    g2.fillPolygon(px1, py1, px1.length);

                    int[] px2 = { (int)(size.getX() + x), (int)(size.getX() + x + s),
                                  (int)(size.getX() + x + BAR_WIDTH), (int)(size.getX() + x + s) };
                    int[] py2 = { (int)(size.getY() + size.getHeight() - value),
                                  (int)(size.getY() + size.getHeight() - value - sy),
                                  (int)(size.getY() + size.getHeight() - value),
                                  (int)(size.getY() + size.getHeight() - value + sy) };
                    g2.fillPolygon(px2, py2, px2.length);

                    x += SERIES_SIZE + SERIES_SPACE;
                }

                // Tooltip label
                if (showLabel != null) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
                    Dimension sizeLabel = getLabelWidth(showLabel, g2);
                    int space = 3, spaceTop = 5;
                    g2.setColor(new Color(30,30,30));
                    g2.fillRoundRect(labelLocation.x - sizeLabel.width/2 - space,
                                     labelLocation.y - sizeLabel.height - space*2 - spaceTop,
                                     sizeLabel.width + space*2,
                                     sizeLabel.height + space*2,
                                     10,10);
                    g2.setColor(Color.WHITE);
                    g2.setComposite(AlphaComposite.SrcOver);
                    g2.drawString(showLabel, labelLocation.x - sizeLabel.width/2,
                                  labelLocation.y - spaceTop - space*2);
                }

                g2.setComposite(AlphaComposite.SrcOver);
            }

            @Override
            public boolean mouseMoving(BlankPlotChart chart, MouseEvent evt, Graphics2D g2, SeriesSize size, int index) {
                double totalWidth = SERIES_SIZE * legends.size() + SERIES_SPACE * (legends.size() - 1);
                double x = (size.getWidth() - totalWidth) / 2.0;

                for (int i = 0; i < legends.size(); i++) {
                    double value = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight()) * animate;
                    int s = 9;
                    int sy = BAR_DEPTH;
                    int[] px = { (int)(size.getX() + x), (int)(size.getX() + x + s), (int)(size.getX() + x + BAR_WIDTH),
                                 (int)(size.getX() + x + BAR_WIDTH), (int)(size.getX() + x + s), (int)(size.getX() + x) };
                    int[] py = { (int)(size.getY() + size.getHeight() - value), (int)(size.getY() + size.getHeight() - value - sy),
                                 (int)(size.getY() + size.getHeight() - value), (int)(size.getY() + size.getHeight()),
                                 (int)(size.getY() + size.getHeight() + sy), (int)(size.getY() + size.getHeight()) };

                    Polygon poly = new Polygon(px, py, px.length);
                    if (poly.contains(evt.getPoint())) {
                        showLabel = df.format(model.get(index).getValues()[i]);
                        labelLocation.setLocation((int)(size.getX() + x + s),
                                                  (int)(size.getY() + size.getHeight() - value - sy));
                        chart.repaint();
                        return true;
                    }
                    x += SERIES_SIZE + SERIES_SPACE;
                }
                return false;
            }

            @Override
            public void renderSeries(BlankPlotChart paramBlankPlotChart, Graphics2D paramGraphics2D, SeriesSize paramSeriesSize, int paramInt, List<Path2D.Double> paramList) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void renderGraphics(Graphics2D paramGraphics2D, List<Path2D.Double> paramList) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });

        blankPlotChart.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                for (int i = 0; i < model.size(); i++) {
                    blankPlotChart.getBlankPlotChatRender().mouseMoving(blankPlotChart, e, null, null, i);
                }
            }
        });
    }

    private void initComponents() {
        blankPlotChart = new BlankPlotChart();
        panelLegend = new JPanel();
        panelLegend.setOpaque(false);
        panelLegend.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panelLegend, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                                        .addComponent(blankPlotChart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(blankPlotChart, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(panelLegend, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }

    public void addLegend(String name, Color color, Color colorLight) {
        ModelLegend data = new ModelLegend(name, color, colorLight);
        legends.add(data);
        panelLegend.add(new LegendItem(data));
        panelLegend.repaint();
        panelLegend.revalidate();
    }

    public void addData(ModelChart data) {
        model.add(data);
        blankPlotChart.setLabelCount(model.size());
        double max = data.getMaxValues();
        if (max > blankPlotChart.getMaxValues()) {
            blankPlotChart.setMaxValues(max);
        }
    }

    public void clear() {
        animate = 0f;
        showLabel = null;
        blankPlotChart.setLabelCount(0);
        model.clear();
        repaint();
    }

    public void start() {
        showLabel = null;
        if (!animator.isRunning()) {
            animator.start();
        }
    }

    private Dimension getLabelWidth(String text, Graphics2D g2) {
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(text, g2);
        return new Dimension((int) r2.getWidth(), (int) r2.getHeight());
    }
}
