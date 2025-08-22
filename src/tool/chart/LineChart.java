package tool.chart;

import tool.chart.blankchart.BlankPlotChart;
import tool.chart.blankchart.BlankPlotChatRender;
import tool.chart.blankchart.SeriesSize;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class LineChart extends JPanel {

    private final DecimalFormat df = new DecimalFormat("#,##0.##");
    private final List<ModelLegend> legends = new ArrayList<>();
    private final List<ModelChart> model = new ArrayList<>();
    private final Animator animator;
    private float animate;
    private String showLabel;
    private Point labelLocation = new Point();
    private final BlankPlotChart blankPlotChart;
    private final JPanel panelLegend;

    public LineChart() {
        blankPlotChart = new BlankPlotChart();
        panelLegend = new JPanel();
        setOpaque(false);

        TimingTargetAdapter timingTarget = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                repaint();
            }
        };
        animator = new Animator(800, timingTarget);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);

        blankPlotChart.getNiceScale().setMaxTicks(5);
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
            public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index) {}

            @Override
            public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index, List<Path2D.Double> gra) {
                double x = 0;
                int ss = 9;
                for (int i = 0; i < legends.size(); i++) {
                    double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight()) * animate;
                    if (index == 0) {
                        gra.get(i).moveTo(size.getX() + x + ss, size.getY() + size.getHeight() - seriesValues);
                    } else {
                        gra.get(i).lineTo(size.getX() + x + ss, size.getY() + size.getHeight() - seriesValues);
                    }
                    x += 18.0;
                }
                renderLabel(g2);
            }

            @Override
            public void renderGraphics(Graphics2D g2, List<Path2D.Double> gra) {
                g2.setStroke(new BasicStroke(3.0f));
                for (int i = 0; i < gra.size(); i++) {
                    ModelLegend legend = legends.get(i);
                    g2.setPaint(new GradientPaint(0, 0, legend.getColor(), getWidth(), 0, legend.getColorLight()));
                    g2.draw(gra.get(i));
                }
            }

            @Override
            public boolean mouseMoving(BlankPlotChart chart, MouseEvent evt, Graphics2D g2, SeriesSize size, int index) {
                double x = 0;
                int ss = 9;
                int sy = 6;
                for (int i = 0; i < legends.size(); i++) {
                    double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight()) * animate;
                    int[] px = {(int) (size.getX() + x), (int) (size.getX() + x + ss), (int) (size.getX() + x + 18),
                                (int) (size.getX() + x + 18), (int) (size.getX() + x + ss), (int) (size.getX() + x)};
                    int[] py = {(int) (size.getY() + size.getHeight() - seriesValues),
                                (int) (size.getY() + size.getHeight() - seriesValues - sy),
                                (int) (size.getY() + size.getHeight() - seriesValues),
                                (int) (size.getY() + size.getHeight()),
                                (int) (size.getY() + size.getHeight() + sy),
                                (int) (size.getY() + size.getHeight())};
                    if (new Polygon(px, py, px.length).contains(evt.getPoint())) {
                        showLabel = df.format(model.get(index).getValues()[i]);
                        labelLocation.setLocation((int) (size.getX() + x + ss), (int) (size.getY() + size.getHeight() - seriesValues - sy));
                        chart.repaint();
                        return true;
                    }
                    x += 18.0;
                }
                return false;
            }
        });

        initLayout();
    }

    private void renderLabel(Graphics2D g2) {
        if (showLabel != null) {
            Dimension s = getLabelWidth(showLabel, g2);
            int space = 3;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            g2.setColor(new Color(30, 30, 30));
            g2.fillRoundRect(labelLocation.x - s.width / 2 - space, labelLocation.y - s.height - space * 2,
                    s.width + space * 2, s.height + space * 2, 10, 10);
            g2.setColor(new Color(200, 200, 200));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g2.drawString(showLabel, labelLocation.x - s.width / 2, labelLocation.y - space * 2);
        }
    }

    public void addLegend(String name, Color color, Color color1) {
        ModelLegend data = new ModelLegend(name, color, color1);
        legends.add(data);
        panelLegend.add(new LegendItem(data));
        panelLegend.repaint();
        panelLegend.revalidate();
    }

    public void addData(ModelChart data) {
        model.add(data);
        blankPlotChart.setLabelCount(model.size());
        double max = data.getMaxValues();
        if (max > blankPlotChart.getMaxValues()) blankPlotChart.setMaxValues(max);
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
        if (!animator.isRunning()) animator.start();
    }

    private Dimension getLabelWidth(String text, Graphics2D g2) {
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(text, g2);
        return new Dimension((int) r2.getWidth(), (int) r2.getHeight());
    }

    private void initLayout() {
        panelLegend.setOpaque(false);
        panelLegend.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panelLegend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(blankPlotChart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(blankPlotChart, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(panelLegend, 28, 28, 28)
                                .addContainerGap())
        );
    }
}
