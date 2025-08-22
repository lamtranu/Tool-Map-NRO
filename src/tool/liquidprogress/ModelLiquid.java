package tool.liquidprogress;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;
public class ModelLiquid
{
private Rectangle size;
public Rectangle getSize() {
/* 18 */     return this.size;
}
public void setSize(Rectangle size) {
/* 22 */     this.size = size;
}
public ModelLiquid(Rectangle size) {
/* 26 */     this.size = size;
}
public ModelLiquid() {}
public Shape createWaterStyle() {
/* 35 */     double width = this.size.getWidth();
/* 36 */     double heigh = this.size.getHeight();
/* 37 */     double space = width / 4.0D;
/* 38 */     double x = this.size.getX();
/* 39 */     double y = this.size.getY();
/* 40 */     GeneralPath gp1 = new GeneralPath(new CubicCurve2D.Double(x, y + heigh, x + space, y + heigh, x + space, y, x + width / 2.0D, y));
/* 41 */     gp1.lineTo(x + width / 2.0D, y + heigh);
/* 42 */     GeneralPath gp2 = new GeneralPath(new CubicCurve2D.Double(x + width / 2.0D, y, x + width - space, y, x + width - space, y + heigh, x + width, y + heigh));
/* 43 */     gp2.lineTo(x + width / 2.0D, y + heigh);
/* 44 */     Area area = new Area(gp1);
/* 45 */     area.add(new Area(gp2));
/* 46 */     return area;
}
}



