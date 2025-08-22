package tool.radialmenu;
import java.awt.Color;
import javax.swing.Icon;
public class RadialItem
{
private Icon icon;
private Color color;
public Icon getIcon() {
/* 15 */     return this.icon;
}
public void setIcon(Icon icon) {
/* 19 */     this.icon = icon;
}
public Color getColor() {
/* 23 */     return this.color;
}
public void setColor(Color color) {
/* 27 */     this.color = color;
}
public RadialItem(Icon icon, Color color) {
/* 31 */     this.icon = icon;
/* 32 */     this.color = color;
}
public RadialItem() {}
}



