package tool.chart.blankchart;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.List;
public abstract class BlankPlotChatRender {
public abstract String getLabelText(int paramInt);
public abstract void renderSeries(BlankPlotChart paramBlankPlotChart, Graphics2D paramGraphics2D, SeriesSize paramSeriesSize, int paramInt);
public abstract void renderSeries(BlankPlotChart paramBlankPlotChart, Graphics2D paramGraphics2D, SeriesSize paramSeriesSize, int paramInt, List<Path2D.Double> paramList);
public abstract boolean mouseMoving(BlankPlotChart paramBlankPlotChart, MouseEvent paramMouseEvent, Graphics2D paramGraphics2D, SeriesSize paramSeriesSize, int paramInt);
public abstract void renderGraphics(Graphics2D paramGraphics2D, List<Path2D.Double> paramList);
public abstract int getMaxLegend();
}